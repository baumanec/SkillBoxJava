
public class Loader
{
    public static void main(String[] args)
    {
        Cat kesha = new Cat();
        System.out.println("Kesha weigh: " + kesha.getWeight());

        kesha.feed(116.0);
        System.out.println("Kesha weigh: " + kesha.getWeight());

        System.out.println();

        Cat ryzhik = new Cat();
        System.out.println("Ryzhik weigh: " + ryzhik.getWeight());

        ryzhik.feed(72.0);
        System.out.println("Ryzhik weigh: " + ryzhik.getWeight());

        System.out.println();

        Cat kesya = new Cat();
        System.out.println("Kesya weigh: " + kesya.getWeight());

        while (kesya.getStatus() != "Dead")
        {
            kesya.meow();
            System.out.println("Kesya weigh: " + kesya.getWeight());
            System.out.println(kesya.getStatus());
        }

        System.out.println();

        Cat marti = new Cat();
        System.out.println("Marti weigh: " + marti.getWeight());

        while (marti.getStatus() != "Exploded")
        {
            marti.feed(1.0);
            System.out.println("Marti weigh: " + marti.getWeight());
            System.out.println(marti.getStatus());
        }

        System.out.println();

        Cat maksimilian = new Cat();
        System.out.println("Maksimilian weigh: " + maksimilian.getWeight());

        Cat business = new Cat();
        System.out.println("Business weigh: " + business.getWeight());

    }
}