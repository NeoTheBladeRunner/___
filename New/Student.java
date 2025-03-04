import java.io.*;
import java.sql.*;

public class Student{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    static final String USER = "root";
    static final String PASSWORD = "Mysql";

    public static void main(String[] args) throws Exception {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                 Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet rs = stmt.executeQuery("SELECT * FROM Student")) {

                if (!rs.isBeforeFirst()) {
                    System.out.println("No records in Student table");
                    return;
                }

                while (true) {
                    System.out.println("\n Menu \n 1.First \n 2.Last \n 3.Next \n 4.Previous \n 5.After Last \n 6. Absolute \n 7. Relative \n 8.Exit \n Enter your choice:");
                    int ch = Integer.parseInt(br.readLine());
                    switch (ch) {
                        case 1:
                            if (rs.first()) {
                                displayRecords(rs);
                            } 
                            break;
                        case 2:
                            if (rs.last()) {
                                displayRecords(rs);
                            } 
                            break;
                        case 3:
                            if (rs.next()) {
                                displayRecords(rs);
                            } 
                            break;
                        case 4:
                            if (rs.previous()) {
                                displayRecords(rs);
                            } 
                            break;
                        case 5:
                            rs.afterLast();
                            System.out.println("Moved After Last record ");
                            break;
                        case 6:
                            System.out.println("Enter your number to move");
                            int num = Integer.parseInt(br.readLine());
                            if (rs.absolute(num)) {
                                displayRecords(rs);
                            } 
                            break;
                        case 7:
                            System.out.println("Enter your relative position to move");
                            int rel = Integer.parseInt(br.readLine());
                            if (rs.relative(rel)) {
                                displayRecords(rs);
                            } 
                            break;
                        case 8:
                            System.out.println("Exiting...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
            } 
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }

    private static void displayRecords(ResultSet rs) throws SQLException {
        System.out.println("Name: " + rs.getString("name") + "\n Student ID: " + rs.getInt("id") + "\n Age: " + rs.getInt("age"));
    }
}