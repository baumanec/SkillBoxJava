import java.sql.SQLOutput;

public class Loader {
    public static void main(String[] args)
    {
        Cat kesha = new Cat(4000.0, "Кеша");
        kesha.setColor(Color.TURTLE_COLOR);
        System.out.println(kesha.getWeight() + "     " + kesha.getName() + "     " + kesha.getColor());
        System.out.println("Alive: " + Cat.count + "             " + "Dead: " + Cat.dead);

        kesha.feed(100.0);
        System.out.println(kesha.getStatus());
        System.out.println("Alive: " + Cat.count + "             " + "Dead: " + Cat.dead);

        kesha.feed(6000.0);
        System.out.println(kesha.getStatus());
        System.out.println("Alive: " + Cat.count + "             " + "Dead: " + Cat.dead);

        kesha.feed(100.0);
        System.out.println(kesha.getStatus());
        System.out.println("Alive: " + Cat.count + "             " + "Dead: " + Cat.dead);
    }
}