public class LegalPerson extends Client {

  void take(double amountToTake) {
    if (!(amountToTake <= 0) && (amountToTake < account)) {
      account -= 1.01 * amountToTake;
      isDone = true;
    } else {
      System.out.println("Введите сумму");
    }
  }

  void getInformation() {
    System.out.println(
        "Пополнение происходит без комиссий. Снятие - с комиссией 1%" + System.lineSeparator()
            + "Ваш баланс: " + getAmount());
  }

}
