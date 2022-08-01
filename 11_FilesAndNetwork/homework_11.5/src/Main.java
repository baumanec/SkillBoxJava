import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) throws IOException {

        String folderPath = "D:/Downloads/data";
        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();

        FindingFiles calculator = new FindingFiles(root);

        ForkJoinPool pool = new ForkJoinPool();
        List<File> findingFiles = new ArrayList<>(pool.invoke(calculator));
        for (File files : findingFiles) {
            System.out.println(files.getName());
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");

        System.exit(0);

        List<Line> lineList = new ArrayList<>();
        List<Station> stationList = new ArrayList<>();
        List<Connection> connectionList = new ArrayList<>();
        Document html = Jsoup.connect("https://skillbox-java.github.io").maxBodySize(0).get();
        Elements lines = html.select("span.js-metro-line");

        lines.forEach(element -> {
            Line line = new Line(element.attr("data-line"), element.text());
            lineList.add(line);
        });

        lineList.forEach(line -> System.out.println(line.getNumber() + " - " + line.getName()));

        Elements stationsByLine = html.select("div.js-metro-stations");
        lineList.forEach(line -> {
            stationsByLine.forEach(station -> {
                if (station.attr("data-line").equals(line.getNumber())) {
                    Elements stations = station.select("p.single-station");
                    stations.forEach(st -> {
                        Station station2 = new Station(((st.select(".name")).text()), line);
                        stationList.add(station2);
                        line.addStation(station2);
                        Station stationTo = new Station("", line);
                        Line lineTo = new Line("", "");
                        if (st.toString().contains("t-icon-metroln")) {
                            Elements connections = st.select(".t-icon-metroln");
                            connections.forEach(con -> {
                                stationTo.setName(getStationToName(con.attr("title")));
                                lineTo.setNumber(getLineToName(con.className()));
                            });
                            connectionList.add(createConnection(station2, stationTo, line, lineTo));
                        }
                    });
                };
            });
        });

        JSONObject stationsData = new JSONObject();
        parseStationsData(stationsData, lineList);

        JSONArray listOfConnections = new JSONArray();
        parseListOfConnections(listOfConnections, connectionList);

        JSONObject metroData = new JSONObject();
        createFile(metroData, stationsData, listOfConnections);

    }

    public static Connection createConnection (Station stationOn, Station stationTo, Line lineOn, Line lineTo) {
        Connection connection = new Connection(null, null, null, null);
        connection.setStationOn(stationOn);
        connection.setStationTo(stationTo);
        connection.setLineOn(lineOn);
        connection.setLineTo(lineTo);
        return connection;
    }

    public static JSONObject parseStationsData(JSONObject stationsData, List<Line> lineList) {
        lineList.forEach(line -> {
            JSONArray listOfStations = new JSONArray();
            line.getStations().forEach(St -> {
                if (!St.getName().equals("")) {
                    listOfStations.add(St.getName());
                }
            });
            stationsData.put(line.getNumber(), listOfStations);
        });
        return stationsData;
    }

    public static JSONArray parseListOfConnections (JSONArray listOfConnections, List<Connection> connectionList) {
        connectionList.forEach(connect -> {
            JSONArray connectionStations = new JSONArray();
            JSONObject connectionStationOn = new JSONObject();
            JSONObject connectionStationTo = new JSONObject();
            connectionStationOn.put("lineOn", connect.getLineOn().getNumber());
            connectionStationOn.put("stationOn", connect.getStationOn().getName());
            connectionStationTo.put("lineTo", connect.getLineTo().getNumber());
            connectionStationTo.put("stationTo", connect.getStationTo().getName());
            connectionStations.add(connectionStationOn);
            connectionStations.add(connectionStationTo);
            listOfConnections.add(connectionStations);
        });
        return listOfConnections;
    }

    public static void createFile (JSONObject metroData, JSONObject stationsData,JSONArray listOfConnections ) {
        metroData.put("stations", stationsData);
        metroData.put("connections", listOfConnections);

        try (FileWriter file = new FileWriter("test.json")) {
            file.write(metroData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLineToName(String attribute) {
        String ln = "ln-";
        int start = attribute.indexOf(ln) + ln.length();
        String lineToName = attribute.substring(start);
        return lineToName;
    }

    public static String getStationToName(String attribute) {
        String st = "«";
        int start = attribute.indexOf(st) + st.length();
        int end = attribute.indexOf(("»"), start);
        String stationToName = attribute.substring(start, end);
        return stationToName;
    }
}