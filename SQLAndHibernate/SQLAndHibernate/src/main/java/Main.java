import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=Europe/Moscow";
        String user = "root";
        String pass = "i7mf6ux3";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            statement.execute("SELECT * "
                                    + "FROM purchaselist pl \n"
                                    + "WHERE YEAR(pl.subscription_date ) = 2018;");
            ResultSet resultSet = statement.executeQuery("SELECT pl.course_name, "
                                                                + "COUNT(*) / (max(month(pl.subscription_date)) - min(month(pl.subscription_date)) + 1) average\n"
                                                                + " FROM PurchaseList pl \n"
                                                                + " GROUP BY pl.course_name;");

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String averageBuyInMonth = resultSet.getString("average");
                System.out.println(courseName + " - " + averageBuyInMonth);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
