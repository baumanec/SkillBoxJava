import com.skillbox.airport.Airport;

public class Main
{
    public static void main(String[] args)
    {
        double size;
        Airport airport = Airport.getInstance();
        size = airport.getAllAircrafts().size();
        System.out.println(size);
    }
}
