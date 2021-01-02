import java.sql.SQLOutput;

public class Loader {
    public static void main(String[] args)
    {
        Color color = Color.BLACK_COLOR;
        Cat kesha = new Cat(2100.0, "Кеша", color.name());
        Cat martin = new Cat(kesha.getWeight(), kesha.getName(), kesha.getColor());
        System.out.println(kesha.getWeight() + "     " + kesha.getName() + "     " + kesha.getColor());
        System.out.println(martin.getWeight() + "     " + martin.getName() + "     " + martin.getColor());
    }
}