import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    private static final String MAP_FILE = "map.json";
    private static final String DATA_FILE = "D:/Downloads/data";
    private static final String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) throws IOException {

        ArrayList<File> fileList = new ArrayList<>();
        findingFiles(DATA_FILE, fileList);
        for (File file : fileList) {
            System.out.println(file.getName() + " - " + file.getAbsolutePath());
        }

        Map<String, Line> lineList = new HashMap<>();
        Document html = Jsoup.connect("https://skillbox-java.github.io").maxBodySize(0).get();
        Elements lines = html.select("span.js-metro-line");

        lines.forEach(element -> {
            Line line = new Line(element.attr("data-line"), element.text());
            lineList.put(line.getNumber(), line);
        });

        Elements stationsByLine = html.select("div.js-metro-stations");

        stationsByLine.forEach(station -> {
            if (lineList.containsKey(station.attr("data-line"))) {
                Line line = lineList.get(station.attr("data-line"));
                Elements stations = station.select("p.single-station");
                stations.forEach(st -> {
                    Station station2 = new Station(((st.select(".name")).text()), line);
                    line.addStation(station2);
                    if (st.toString().contains("t-icon-metroln")) {
                        station2.setHasConnection(true);
                    }
                });
            }
        });

        JSONObject stationsData = new JSONObject();
        parseStationsData(stationsData, lineList);

        JSONObject metroData = new JSONObject();
        createMap(metroData, stationsData);

        for (File jsonFile : getSetOfJsonDatesFiles(fileList)) {
            for (String key : lineList.keySet()) {
                parseDatesFromJson(createJsonData(jsonFile), lineList.get(key).getStations());
            }
        }

        for (File jsonFile : getSetOfJsonDepthsFiles(fileList)) {
            for (String key : lineList.keySet()) {
                parseDepthsFromJson(createJsonData(jsonFile), lineList.get(key).getStations());
            }
        }

        for (String key : lineList.keySet()) {
            parseDepthsFromCsv(getSetOfCsvDepthsFiles(fileList), lineList.get(key).getStations());
        }

        for (String key : lineList.keySet()) {
            parseDatesFromCsv(getSetOfCsvDatesFiles(fileList), lineList.get(key).getStations());
        }

        JSONArray listOfProperties = new JSONArray();
        for (String key : lineList.keySet()) {
            parseListOfProperties(listOfProperties, lineList.get(key).getStations());
        }

        JSONObject stationsProperties = new JSONObject();
        createStationsWithProperties(stationsProperties, listOfProperties);

        try {
            stationsOnLineCount();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void parseStationsData(JSONObject stationsData, Map<String, Line> lineList) {
        for (String key : lineList.keySet()) {
            JSONArray listOfStations = new JSONArray();
            lineList.get(key).getStations().forEach(St -> {
                if (!St.getName().equals("")) {
                    listOfStations.add("\n\t" + St.getName());
                }
            });
            stationsData.put(System.lineSeparator() + lineList.get(key).getName(), listOfStations);
        }
    }


    public static void parseListOfProperties(JSONArray listOfProperties, List<Station> stationList) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        stationList.forEach(station -> {
            JSONObject properties = new JSONObject();
            properties.put("name", station.getName());
            properties.put("line", station.getLine().getName());
            properties.put("hasConnection", station.isHasConnection());
            if (station.getDepth() != null) {
                properties.put("depth", station.getDepth());
            }
            if (station.getDate() != null) {
                properties.put("date", formatter.format(station.getDate()));
            }
            listOfProperties.add(properties);
        });
    }

    public static void createMap(JSONObject metroData, JSONObject stationsData) {
        metroData.put("stations", stationsData);

        try (FileWriter file = new FileWriter("map.json")) {
            file.write(metroData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createStationsWithProperties(JSONObject stationsData, JSONArray listOfProperties) {
        stationsData.put("stations", listOfProperties);

        try (FileWriter file = new FileWriter("stations.json")) {
            file.write(stationsData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findingFiles(String folderPath, List<File> fileList) {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] directoryFiles = folder.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        findingFiles(file.getPath(), fileList);
                    } else {
                        if ((file.getName().toLowerCase().endsWith(".json")) || (file.getName().toLowerCase().endsWith(".csv"))) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }

    private static JSONArray createJsonData(File jsonFile) {
        StringBuilder builder = new StringBuilder();
        JSONArray jsonData = new JSONArray();
        try {
            JSONParser parser = new JSONParser();
            List<String> lines = Files.readAllLines(Paths.get(jsonFile.getAbsolutePath()));
            lines.forEach(builder::append);
            jsonData = (JSONArray) parser.parse(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonData;
    }

    private static void stationsOnLineCount() throws Exception {

        FileReader reader = new FileReader(MAP_FILE);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONObject linesInMap = (JSONObject) jsonObject.get("stations");
        for (Object key : linesInMap.keySet()) {
            JSONArray stationsOnLine = (JSONArray) linesInMap.get(key);
            System.out.println("Количество станций на: " + key.toString() + " равно " + stationsOnLine.size());
        }
        reader.close();
    }

    private static void parseDatesFromJson(JSONArray jsonData, List<Station> stationList) {
        String stationName;
        Date stationDate;
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            for (Object jsonStation : jsonData) {
                JSONObject stationObject = (JSONObject) jsonStation;

                stationName = (String) stationObject.get("name");
                stationDate = formatter.parse(stationObject.get("date").toString());

                for (Station station : stationList) {
                    if (station.getName().equals(stationName)) {
                        station.setDate(stationDate);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseDepthsFromJson(JSONArray jsonData, List<Station> stationList) {
        Pattern pattern = Pattern.compile("[^[0-9],−]");
        String stationName = "";
        double stationDepth = 0.0;
        try {
            for (Object jsonStation : jsonData) {
                JSONObject stationObject = (JSONObject) jsonStation;
                for (Object key : stationObject.keySet()) {
                    if (key.toString().endsWith("name")) {
                        stationName = (String) stationObject.get(key);
                    }
                    try {
                        if (key.toString().startsWith("depth")) {
                            String valueOfKey = String.valueOf(stationObject.get(key).toString());
                            Matcher matcher = pattern.matcher(valueOfKey);
                            if (matcher.find()) {
                                valueOfKey = matcher.replaceAll("");
                            }
                            if (valueOfKey.contains(",")) {
                                valueOfKey = valueOfKey.replace(",", ".");
                            }
                            if (valueOfKey.contains("−")) {
                                valueOfKey = valueOfKey.replace("−", "");
                                stationDepth = -1 * (Double.parseDouble(valueOfKey));
                                continue;
                            }
                            if (valueOfKey.isEmpty()) {
                                continue;
                            }
                            stationDepth = Double.parseDouble(valueOfKey);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    for (Station station : stationList) {
                        if (station.getName().equals(stationName)) {
                            station.setDepth(stationDepth);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseDepthsFromCsv(Set<File> csvDepthsFiles, List<Station> stationList) {
        String stationName;
        double stationDepth;
        Pattern pattern = Pattern.compile("[^[0-9],−]");
        try {
            for (File csvFile : csvDepthsFiles) {
                List<String> lines = Files.readAllLines(Paths.get(csvFile.getAbsolutePath()));
                for (int i = 1; i < lines.size(); i++) {
                    String[] fragments = lines.get(i).split(",", 2);
                    if (fragments.length != 2) {
                        System.out.println("Wrong line" + lines.get(i));
                        continue;
                    }
                    Matcher matcher = pattern.matcher(fragments[1]);
                    if (matcher.find()) {
                        fragments[1] = matcher.replaceAll("");
                    }
                    if (fragments[1].contains(",")) {
                        fragments[1] = fragments[1].replace(",", ".");
                    }
                    if (fragments[1].isEmpty()) {
                        continue;
                    }
                    if (fragments[1].contains("−")) {
                        fragments[1] = fragments[1].replace("−", "");
                        stationDepth = -1 * (Double.parseDouble(fragments[1]));
                    } else {
                        stationDepth = Double.parseDouble(fragments[1]);
                    }
                    stationName = fragments[0];
                    for (Station station : stationList) {
                        if (station.getName().equals(stationName)) {
                            station.setDepth(stationDepth);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseDatesFromCsv(Set<File> csvDatesFiles, List<Station> stationList) {
        String stationName;
        Date stationDate;
        try {
            for (File csvFile : csvDatesFiles) {
                List<String> lines = Files.readAllLines(Paths.get(csvFile.getAbsolutePath()));
                for (int i = 1; i < lines.size(); i++) {
                    String[] fragments = lines.get(i).split(",");
                    if (fragments.length != 2) {
                        System.out.println("Wrong line" + lines.get(i));
                        continue;
                    }
                    stationName = fragments[0];
                    stationDate = new SimpleDateFormat(dateFormat).parse(fragments[1]);
                    for (Station station : stationList) {
                        if (station.getName().equals(stationName)) {
                            station.setDate(stationDate);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Set<File> getSetOfJsonDatesFiles(ArrayList<File> fileList) {
        Set<File> listOfJsonDatesFiles = new TreeSet<>();
        try {
            for (File file : fileList) {
                if ((file.getName().endsWith(".json")) && (file.getName().startsWith("dates"))) {
                    listOfJsonDatesFiles.add(file);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfJsonDatesFiles;
    }

    private static Set<File> getSetOfJsonDepthsFiles(ArrayList<File> fileList) {
        Set<File> listOfJsonDepthsFiles = new TreeSet<>();
        try {
            for (File file : fileList) {
                if ((file.getName().endsWith(".json")) && (file.getName().startsWith("depths"))) {
                    listOfJsonDepthsFiles.add(file);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfJsonDepthsFiles;
    }

    private static Set<File> getSetOfCsvDepthsFiles(ArrayList<File> fileList) {
        Set<File> listOfCsvDepthsFiles = new TreeSet<>();
        try {
            for (File file : fileList) {
                if ((file.getName().endsWith(".csv")) && (file.getName().startsWith("depths"))) {
                    listOfCsvDepthsFiles.add(file);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfCsvDepthsFiles;
    }

    private static Set<File> getSetOfCsvDatesFiles(ArrayList<File> fileList) {
        Set<File> listOfCsvDatesFiles = new TreeSet<>();
        try {
            for (File file : fileList) {
                if ((file.getName().endsWith(".csv")) && (file.getName().startsWith("dates"))) {
                    listOfCsvDatesFiles.add(file);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfCsvDatesFiles;
    }

}