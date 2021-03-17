import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Flight.Type;
import com.skillbox.airport.Terminal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

  }

  public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
    //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
    Date currentDay = new Date();
    List<Terminal> terminals = new ArrayList<>(airport.getTerminals());
    List<Flight> flights = new ArrayList<>();
    for (Terminal terminal : terminals) {
      flights.addAll(terminal.getFlights());
    }

    return flights.stream()
        .filter(flight -> flight.getDate().getTime() - currentDay.getTime() <= 7200000)
        .filter(flight -> flight.getType().equals(Type.DEPARTURE))
        .collect(Collectors.toList());
  }

}