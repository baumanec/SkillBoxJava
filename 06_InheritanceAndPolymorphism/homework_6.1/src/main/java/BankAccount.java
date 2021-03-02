public class BankAccount {

  double account = 0.0;
  boolean isDone;

  public double getAmount() {
    return account;
  }

  public void put(double amountToPut) {
    if (!(amountToPut <= 0)) {
      account += amountToPut;
      isDone = true;
    } else {
      System.out.println("Введите сумму");
    }
  }

  public void take(double amountToTake) {
    if (!(amountToTake <= 0) && (amountToTake < account)) {
      account -= amountToTake;
      isDone = true;
    } else {
      System.out.println("Введите сумму");
    }
  }

  boolean send(BankAccount receiver, double amount) {
    if (getAmount() > amount) {
      take(amount);
      receiver.put(amount);
    }
    return this.isDone;
  }

}
