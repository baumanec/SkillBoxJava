public class TopManager extends Company implements Employee{

  @Override
  public double getMonthSalary() {
    if (getIncome() > 10000000) {
      return 2.5 * FIX_SALARY;
    } else {
      return FIX_SALARY;
    }
  }

  @Override
  public int compareTo(Employee employee) {
    return Double.compare(employee.getMonthSalary(), getMonthSalary());
  }
}
