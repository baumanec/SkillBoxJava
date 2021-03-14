import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

  static int income;
  ArrayList<Employee> employers = new ArrayList<>();

  List<Employee> getTopSalaryStaff(int count) {
    List<Employee> topSalaryStaff = new ArrayList<>(employers);
    Collections.sort(topSalaryStaff);
    if (count <= 0 | count > topSalaryStaff.size()) {
      return Collections.emptyList();
    }
    return topSalaryStaff.subList(0, count);
  }

  List<Employee> getLowestSalaryStaff(int count) {
    List<Employee> lowestSalaryStaff = new ArrayList<>(employers);
    lowestSalaryStaff.sort(Collections.reverseOrder());
    if (count <= 0 | count > lowestSalaryStaff.size()) {
      return Collections.emptyList();
    }
    return lowestSalaryStaff.subList(0, count);
  }

  void hire(Employee employee) {
    employers.add(employee);
    employers.trimToSize();
  }

  void hireAll(List<Employee> listForHire) {
    employers.addAll(listForHire);
    employers.trimToSize();
  }

  void fire(int count) {
    if (count >= getSize()) {
      System.out.println("Нельзя уволить такое количество сотрудников");
    } else {
      for (int i = 0; i < count; i++) {
        employers.remove(i);
      }
      employers.trimToSize();
    }
  }

  int getSize() {
    return employers.size();
  }

  void setIncome(int income) {
    Company.income = income;
  }

  int getIncome() {
    return income;
  }


}
