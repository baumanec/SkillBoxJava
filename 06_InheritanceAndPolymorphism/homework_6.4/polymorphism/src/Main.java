import java.util.ArrayList;
import java.util.List;

public class Main {

  static Company company = new Company();
  static List<Double> salary = new ArrayList<>();
  static List<Employee> listForHire = new ArrayList<>();
  static List<Double> topSalary = new ArrayList<>();
  static List<Double> lowestSalary = new ArrayList<>();

  public static void main(String... args) {

    company.setIncome(10000001);
    System.out.println("Доход компании: " + company.getIncome());

    for (int i = 0; i < 180; i++) {
      listForHire.add(new Operator(company));
      if (i < 10) {
        company.hire(new TopManager(company));
      }
      if (i < 80) {
        company.hire(new Manager(company));
      }
    }

    company.hireAll(listForHire);

    for (Employee employee : company.employers) {
      salary.add(employee.getMonthSalary());
    }

    System.out.println("Количество сотрудников: " + company.getSize() + System.lineSeparator());
    System.out.println("Список сотрудников: " + company.employers + System.lineSeparator());
    System.out.println("Зарплаты сотрудников: " + salary + System.lineSeparator());

    System.out.println("Список высоких зарплат: " + listOfTopSalaries(15) + System.lineSeparator());
    System.out
        .println("Список низких зарплат: " + listOfLowestSalaries(30) + System.lineSeparator());

    company.fire(company.getSize() / 2);

    System.out.println("Количество сотрудников: " + company.getSize() + System.lineSeparator());
    System.out.println("Список сотрудников: " + company.employers + System.lineSeparator());
    System.out.println("Зарплаты сотрудников: " + salary + System.lineSeparator());

    topSalary.clear();
    lowestSalary.clear();

    System.out.println("Список высоких зарплат: " + listOfTopSalaries(15) + System.lineSeparator());
    System.out
        .println("Список низких зарплат: " + listOfLowestSalaries(30) + System.lineSeparator());
  }

  static List<Double> listOfTopSalaries(int count) {
    for (Employee staff : company.getTopSalaryStaff(count)) {
      topSalary.add(staff.getMonthSalary());
    }
    return topSalary;
  }

  static List<Double> listOfLowestSalaries(int count) {
    for (Employee staff : company.getLowestSalaryStaff(count)) {
      lowestSalary.add(staff.getMonthSalary());
    }
    return lowestSalary;
  }
}
