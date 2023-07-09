import java.sql.*;

public class DBConnection {

    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/learn";
    private static final String user = "root";
    private static final String password = "root";
    private static int recordCount = 0;

    private static final StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) "
                + "VALUES "
                + insertQuery
                + "ON DUPLICATE KEY UPDATE `count` = `count` + 1";
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) {
        birthDay = birthDay.replace('.', '-');

        insertQuery
                .append(insertQuery.length() == 0 ? "" : ",")
                .append("('")
                .append(name)
                .append("', '")
                .append(birthDay)
                .append("', 1)");
        recordCount++;
    }

    public static void insertQueryAndRecordCountClear() {
        insertQuery.setLength(0);
        recordCount = 0;
    }

    public static int getRecordCount() {
        return recordCount;
    }
//    public static void printVoterCounts() throws SQLException {
//        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
//        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
//        while (rs.next()) {
//            System.out.println("\t" + rs.getString("name") + " (" +
//                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
//        }
//    }
}