import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetadataExample {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/employee";
    static final String USER = "root";
    static final String PASS = "Mysql";

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                DatabaseMetaData metaData = conn.getMetaData();


                
                System.out.println("Database name: " + metaData.getDatabaseProductName());
                System.out.println("Database version: " + metaData.getDatabaseProductVersion());
                System.out.println("Driver name: " + metaData.getDriverName());
                System.out.println("Driver version: " + metaData.getDriverVersion());
                System.out.println("Database URL: " + metaData.getURL());
                System.out.println("JDBC major version: " + metaData.getJDBCMajorVersion());
                System.out.println("JDBC minor version: " + metaData.getJDBCMinorVersion());
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
            } 
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}