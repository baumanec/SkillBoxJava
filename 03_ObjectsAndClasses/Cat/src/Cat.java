
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;
    private double amount;
    private double totalAmount;
    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        totalAmount = 0;
    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        weight = weight + amount;
        this.amount = amount;
    }

    public void drink(Double amount)
    {
        weight = weight + amount;
    }

    public void pee()
    {
        weight = weight - 0.01 * getWeight();
        System.out.println("I pee ... Clean the tray \uD83D\uDE43");
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
            return "Dead";
        }
        else if(weight > maxWeight) {
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