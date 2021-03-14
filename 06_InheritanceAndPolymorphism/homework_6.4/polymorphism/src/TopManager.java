public class TopManager implements Employee {

  private Company company;

  public TopManager(Company company) {
    this.company = company;
  }

  public Company getCompany() {
    return company;
  }

  @Override
  public double getMonthSalary() {
    if (getCompany().getIncome() > 10000000) {
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
