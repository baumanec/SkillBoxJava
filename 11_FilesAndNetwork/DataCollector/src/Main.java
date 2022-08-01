import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<Station> stationList = new ArrayList<>();
            Document html = Jsoup.connect("https://skillbox-java.github.io").maxBodySize(0).get();
            Elements lines = html.select("span.js-metro-line");
            List<Line> lineList = getLineList(lines);
            Elements elementsWithStations = html.select("div.js-metro-stations");
        elementsWithStations.forEach(element -> {
//                System.out.println(element);
                Elements stationsOnLine = element.select("p.single-station");
                stationsOnLine.forEach(stations -> {
                    Station station = new Station("", null);
                    for (int i = 0; i < lineList.size(); i++) {
                        if (lineList.get(i).getNumber() == elementsWithStations.attr("data-line")) {
                            station.setName(stationsOnLine.select(".name").text());
                            station.setLine(lineList.get(i));
                            lineList.get(i).addStation(station);
                        }
                    }
                });
            });

          for (int i = 0; i < lineList.size(); i++) {
              System.out.println(lineList.get(i).getNumber() + " - " + lineList.get(i).getName());
              for (int j = 0; j < lineList.get(i).getStations().size(); j++) {
                  System.out.println(lineList.get(i).getStations().get(j).getLine().getNumber() + " - " + lineList.get(i).getStations().get(j).getName());
              }
          }


    }
    public static List<Line> getLineList(Elements lines) {
        List<Line> lineList = new ArrayList<>();
        lines.forEach(element -> {
            Line line = new Line(element.attr("data-line"), element.text());
            lineList.add(line);
        });
        return lineList;
    }

}
