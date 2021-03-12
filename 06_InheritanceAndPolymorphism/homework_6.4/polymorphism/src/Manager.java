public class Manager extends Company implements Employee{

  @Override
  public double getMonthSalary() {
    return FIX_SALARY + (double) ((int)(0.5 * (Math.random() * 25000 + 115001))/10);
  }

  @Override
  public int compareTo(Employee employee) {
    return Double.compare(employee.getMonthSalary(), getMonthSalary());
  }
}
