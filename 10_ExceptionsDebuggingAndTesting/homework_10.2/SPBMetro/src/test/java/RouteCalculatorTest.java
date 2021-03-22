import core.Line;
import core.Station;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

public class RouteCalculatorTest extends TestCase {

  StationIndex stationIndex;
  RouteCalculator routeCalculator;

  @Override
  protected void setUp() throws Exception {
    Line line1;
    Line line2;
    Line line3;
    Line line4;

    Station s0;
    Station s1;
    Station s2;
    Station s3;
    Station s4;
    Station s5;
    Station s6;
    Station s7;
    Station s8;
    Station s9;
    Station s10;
    Station s11;
    Station s12;
    Station s13;
    Station s14;
    Station s15;

    stationIndex = new StationIndex();
    routeCalculator = new RouteCalculator(stationIndex);
    stationIndex.addLine(line1 = new Line(1, "Арбатско-Покровская"));
    stationIndex.addLine(line2 = new Line(2, "Кольцевая"));
    stationIndex.addLine(line3 = new Line(3, "Сокольническая"));
    stationIndex.addLine(line4 = new Line(4, "Люблинско-Дмитровская"));

    stationIndex.addStation(s1 = new Station("Семёновская", line1));
    stationIndex.addStation(s2 = new Station("Электрозаводская", line1));
    stationIndex.addStation(s3 = new Station("Бауманская", line1));
    stationIndex.addStation(s4 = new Station("Курская", line1));
    stationIndex.addStation(s0 = new Station("Площадь Революции", line1));
    stationIndex.addStation(s5 = new Station("Курская-Кольцевая", line2));
    stationIndex.addStation(s6 = new Station("Буферная-Кольцевая", line2));
    stationIndex.addStation(s7 = new Station("Комсомольская-Кольцевая", line2));
    stationIndex.addStation(s8 = new Station("Чистые Пруды", line3));
    stationIndex.addStation(s9 = new Station("Красные Ворота", line3));
    stationIndex.addStation(s10 = new Station("Комсомольская", line3));
    stationIndex.addStation(s11 = new Station("Красносельская", line3));
    stationIndex.addStation(s12 = new Station("Сокольники", line3));
    stationIndex.addStation(s13 = new Station("Преображенская площадь", line3));
    stationIndex.addStation(s14 = new Station("Чкаловская", line4));
    stationIndex.addStation(s15 = new Station("Сретенский бульвар", line4));

    line1.addStation(s1);
    line1.addStation(s2);
    line1.addStation(s3);
    line1.addStation(s4);
    line1.addStation(s0);
    line2.addStation(s5);
    line2.addStation(s6);
    line2.addStation(s7);
    line3.addStation(s8);
    line3.addStation(s9);
    line3.addStation(s10);
    line3.addStation(s11);
    line3.addStation(s12);
    line3.addStation(s13);
    line4.addStation(s14);
    line4.addStation(s15);

    List<Station> connection1To2 = new ArrayList<>() {{
      add(stationIndex.getStation("Курская"));
      add(stationIndex.getStation("Курская-Кольцевая"));
    }};
    stationIndex.addConnection(connection1To2);

    List<Station> connected2To3 = new ArrayList<>() {{
      add(stationIndex.getStation("Комсомольская-Кольцевая"));
      add(stationIndex.getStation("Комсомольская"));
    }};
    stationIndex.addConnection(connected2To3);

    List<Station> connection1To4 = new ArrayList<>() {{
      add(stationIndex.getStation("Курская"));
      add(stationIndex.getStation("Чкаловская"));
    }};
    stationIndex.addConnection(connection1To4);

    List<Station> connection4To3 = new ArrayList<>() {{
      add(stationIndex.getStation("Сретенский бульвар"));
      add(stationIndex.getStation("Чистые Пруды"));
    }};
    stationIndex.addConnection(connection4To3);
  }

  public void testGetShortestRoute() {
    List<Station> buffer = new ArrayList<>(
        routeCalculator.getShortestRoute(stationIndex.getStation("Бауманская"),
            stationIndex.getStation("Сретенский бульвар")));
    List<String> expected = new ArrayList<>() {{
      add("Бауманская");
      add("Курская");
      add("Чкаловская");
      add("Сретенский бульвар");
    }};
    List<String> actual = new ArrayList<>();
    for (Station stations : buffer) {
      actual.add(stations.toString());
    }
    assertEquals(expected, actual);
  }

  public void testCalculateDuration() {
    Double actual = RouteCalculator.calculateDuration(
        routeCalculator.getShortestRoute(stationIndex.getStation("Бауманская", 1),
            stationIndex.getStation("Комсомольская-Кольцевая", 2)));
    Double expected = 11.0;
    assertEquals(expected, actual);
  }

  public void testGetRouteOnTheLine() {
    List<Station> buffer = new ArrayList<>(
        routeCalculator.getShortestRoute(stationIndex.getStation("Семёновская"),
            stationIndex.getStation("Курская")));
    List<String> expected = new ArrayList<>() {{
      add("Семёновская");
      add("Электрозаводская");
      add("Бауманская");
      add("Курская");
    }};
    List<String> actual = new ArrayList<>();
    for (Station stations : buffer) {
      actual.add(stations.toString());
    }
    assertEquals(expected, actual);
  }

  public void testGetRouteWithOneConnection() {
    List<Station> buffer = new ArrayList<>(
        routeCalculator.getShortestRoute(stationIndex.getStation("Семёновская"),
            stationIndex.getStation("Буферная-Кольцевая")));
    List<String> expected = new ArrayList<>() {{
      add("Семёновская");
      add("Электрозаводская");
      add("Бауманская");
      add("Курская");
      add("Курская-Кольцевая");
      add("Буферная-Кольцевая");
    }};
    List<String> actual = new ArrayList<>();
    for (Station stations : buffer) {
      actual.add(stations.toString());
    }
    assertEquals(expected, actual);
  }

  public void testGetRouteViaConnectedLine() {
    List<Station> buffer = new ArrayList<>(
        routeCalculator.getShortestRoute(stationIndex.getStation("Семёновская"),
            stationIndex.getStation("Площадь Революции")));
    List<String> expected = new ArrayList<>() {{
      add("Семёновская");
      add("Электрозаводская");
      add("Бауманская");
      add("Курская");
      add("Площадь Революции");
    }};
    List<String> actual = new ArrayList<>();
    for (Station stations : buffer) {
      actual.add(stations.toString());
    }
    assertEquals(expected, actual);
  }

  public void testGetRouteWithTwoConnections() {
    List<Station> buffer = new ArrayList<>(
        routeCalculator.getShortestRoute(stationIndex.getStation("Бауманская"),
            stationIndex.getStation("Красносельская")));
    List<String> expected = new ArrayList<>() {{
      add("Бауманская");
      add("Курская");
      add("Курская-Кольцевая");
      add("Буферная-Кольцевая");
      add("Комсомольская-Кольцевая");
      add("Комсомольская");
      add("Красносельская");
    }};
    List<String> actual = new ArrayList<>();
    for (Station stations : buffer) {
      actual.add(stations.toString());
    }
    assertEquals(expected, actual);
  }
}
