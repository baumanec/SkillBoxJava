import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
        sortBySalaryAndAlphabet(staff);
        for (Employee employee : staff){
            System.out.println(employee);
        }
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort((o1, o2) -> {
            int salarySorting = o1.getSalary().compareTo(o2.getSalary());
            if (salarySorting != 0) {
                return salarySorting;
            }
            return o1.getName().compareTo(o2.getName());
        });
    }
}