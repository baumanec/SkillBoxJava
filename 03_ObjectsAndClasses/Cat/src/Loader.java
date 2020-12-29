import java.sql.SQLOutput;

public class Loader
{
    public static void main(String[] args)
    {
        Cat kesha = new Cat(2100.0);
        System.out.println(kesha.getWeight());
        System.out.println(getKitten().getWeight());
        System.out.println(getKitten().getWeight());
        System.out.println(getKitten().getWeight());
    }

    private static Cat getKitten()
    {
        return new Cat(1100.0);
    }
}