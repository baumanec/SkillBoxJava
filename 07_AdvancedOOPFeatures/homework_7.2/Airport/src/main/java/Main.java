import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Flight.Type;
import com.skillbox.airport.Terminal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
  }

  public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
    List<Terminal> terminals = new ArrayList<>(airport.getTerminals());
    List<Flight> flights = new ArrayList<>();
    for (Terminal terminal : terminals) {
      flights.addAll(terminal.getFlights());
    }

    Calendar currentTime = new GregorianCalendar();
    currentTime.add(Calendar.HOUR_OF_DAY, 2);
    return flights.stream()
        .filter(flight -> flight.getDate().before(currentTime.getTime()))
        .filter(flight -> flight.getType().equals(Type.DEPARTURE))
        .collect(Collectors.toList());
  }

}