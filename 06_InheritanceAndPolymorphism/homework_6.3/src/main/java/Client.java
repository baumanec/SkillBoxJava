public abstract class Client {

    double account = 0.0;
    boolean isDone;

    public double getAmount() {
        return this.account;
    }

    void put(double amountToPut) {
        if (!(amountToPut <= 0)) {
            this.account += amountToPut;
            this.isDone = true;
        } else {
            System.out.println("Введите сумму");
        }
    }

    void take(double amountToTake) {
        if (!(amountToTake <= 0) && (amountToTake < this.account)) {
            this.account -= amountToTake;
            this.isDone = true;
        } else {
            System.out.println("Введите сумму");
        }
    }

    abstract void getInformation();

}
