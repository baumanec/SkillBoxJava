import java.time.LocalDate;

public class DepositAccount extends BankAccount {

  LocalDate lastIncome = LocalDate.now();

  public void take(double amountToTake) {
    LocalDate lastIncome = LocalDate.now();
    this.lastIncome = lastIncome;
    if (!(amountToTake <= 0) && (amountToTake < account)) {
      if (LocalDate.now().isAfter(lastIncome.minusMonths(1))) {
        this.account -= amountToTake;
        this.isDone = true;
      }
    } else {
      System.out.println("Введите сумму");
    }
  }

  public void put(double amountToPut) {
    if (!(amountToPut <= 0)) {
      this.lastIncome = LocalDate.now();
      account += amountToPut;
      this.isDone = true;
    } else {
      System.out.println("Введите сумму");
    }
  }
}
