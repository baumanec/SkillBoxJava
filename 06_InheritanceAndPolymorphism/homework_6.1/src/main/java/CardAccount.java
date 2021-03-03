public class CardAccount extends BankAccount {

  void take(double amountToTake) {
    if (!(amountToTake <= 0) && (amountToTake < account)) {
      this.account -= amountToTake + 0.01 * amountToTake;
      this.isDone = true;
    } else {
      System.out.println("Введите сумму");
    }
  }
}
