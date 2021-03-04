public class IndividualBusinessman extends Client {

  void put(double amountToPut) {
    if (!(amountToPut <= 0)) {
      if (amountToPut < 1000) {
        account += 0.99 * amountToPut;
        isDone = true;
      }
      if (amountToPut >= 1000) {
        account += 0.995 * amountToPut;
        isDone = true;
      }
    } else {
      System.out.println("Введите сумму");
    }
  }

  void getInformation() {
    System.out.println(
        "Снятие происходит без комиссий. При пополнении суммой меньше, чем 1000, комиссия составляет 1%. При пополнении суммой от 1000, комиссия составляет 0.5%"
            + System.lineSeparator() + "Ваш баланс: " + getAmount());
  }

}
