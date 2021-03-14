public class Operator implements Employee {

  private Company company;

  public Operator(Company company) {
    this.company = company;
  }

  public Company getCompany() {
    return company;
  }

  @Override
  public double getMonthSalary() {
    return FIX_SALARY;
  }

  @Override
  public int compareTo(Employee employee) {
    return Double.compare(employee.getMonthSalary(), getMonthSalary());
  }
}
