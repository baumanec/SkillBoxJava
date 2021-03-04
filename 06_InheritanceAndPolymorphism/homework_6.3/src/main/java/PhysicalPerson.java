public class PhysicalPerson extends Client {

  void getInformation() {
    System.out.println(
        "Пополнение и снятие происходят без комиссий" + System.lineSeparator() + "Ваш баланс: "
            + getAmount());
  }
}
