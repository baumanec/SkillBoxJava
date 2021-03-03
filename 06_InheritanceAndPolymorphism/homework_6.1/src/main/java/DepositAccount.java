import java.time.LocalDate;

public class DepositAccount extends BankAccount {

  private LocalDate lastIncome = LocalDate.now();

  void take(double amountToTake) {
    lastIncome = LocalDate.now();
    if (!(amountToTake <= 0) && (amountToTake < account)) {
      if (LocalDate.now().isAfter(lastIncome.minusMonths(1))) {
        this.account -= amountToTake;
        this.isDone = true;
      }
    } else {
      System.out.println("Введите сумму");
    }
  }

  void put(double amountToPut) {
    if (!(amountToPut <= 0)) {
      lastIncome = LocalDate.now();
      account += amountToPut;
      this.isDone = true;
    } else {
      System.out.println("Введите сумму");
    }
  }
}
