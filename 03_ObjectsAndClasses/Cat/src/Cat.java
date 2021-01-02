import java.util.Scanner;

public class Cat
{
    public static final int EYES_COUNT = 2;
    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;

    private double originWeight;
    private double weight;

    public double minWeight;
    public double maxWeight;
    private double amount;
    private double totalAmount;
    public static int count;
    public static int dead;
    private String color;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        totalAmount = 0;
        count = ++count;
        this.count = count;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    public Cat (double weight)
    {
        this();
        this.weight = weight;
    }

    public static int getCount()
    {
        System.out.println("Cats' quantity: " + count + "        " + "Lost cats: " + dead);
        return 0;
    }

    public void meow()
    {
        if((weight < minWeight) || (weight > maxWeight)) {
            System.out.println("The cat cannot meow because it is dead");
        }
        else {
            System.out.println("Meow");
        }
        weight = weight - 1;
    }

    public void feed(Double amount)
    {
        if((weight < minWeight) || (weight > maxWeight)) {
            System.out.println("The cat cannot eat because it is dead");
        }
        weight = weight + amount;
        this.amount = amount;
    }

    public void drink(Double amount)
    {
        if((weight < minWeight) || (weight > maxWeight)){
            System.out.println("The cat cannot drink because it is dead");
        }
        weight = weight + amount;
    }

    public void pee()
    {
        if((weight < minWeight) || (weight > maxWeight)){
            System.out.println("The cat cannot pee because it is dead");
        }
        else{
            System.out.println("I peed ... Clean the tray \uD83D\uDE38");
        }
        weight = weight - 0.01 * getWeight();
    }

    public double getTotalAmount()
    {
        totalAmount = totalAmount + amount;
        return totalAmount;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            count = count - 1;
            dead = ++dead;
            return "Dead";
        }
        else if(weight > maxWeight) {
            count = count - 1;
            dead = ++dead;
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}