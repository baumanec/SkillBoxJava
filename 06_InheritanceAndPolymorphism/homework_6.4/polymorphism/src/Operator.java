public class Operator extends Company implements Employee{
  @Override
  public double getMonthSalary() {
    return FIX_SALARY;
  }

  @Override
  public int compareTo(Employee employee) {
    return Double.compare(employee.getMonthSalary(), getMonthSalary());
  }
}
