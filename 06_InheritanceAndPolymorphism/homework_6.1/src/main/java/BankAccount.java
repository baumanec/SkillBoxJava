public class BankAccount {

  protected double account = 0.0;
  protected boolean isDone;

  double getAmount() {
    return account;
  }

  void put(double amountToPut) {
    if (!(amountToPut <= 0)) {
      account += amountToPut;
      isDone = true;
    } else {
      System.out.println("Введите сумму");
    }
  }

  void take(double amountToTake) {
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
