
import java.sql.*;

public class ResultsetMetadata{
    static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/Employee";
    static final String USER="root";
    static final String PASSWORD="Mysql";

    public static void main(String args[]){
        try{
            Class.forName(JDBC_DRIVER);

            try(
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement stmt=conn.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT * FROM Employee"))
            {
            ResultSetMetaData rsMetaData = rs.getMetaData();

            int columnCount=rsMetaData.getColumnCount();
            System.out.println("Number of columns: "+columnCount);
            
            for(int i=1;i<=columnCount;i++){
                
                    System.out.println("Column " + i + ":");
                    System.out.println("    Name: " + rsMetaData.getColumnName(i));
                    System.out.println("    Type: " + rsMetaData.getColumnTypeName(i));
                    System.out.println("    Size: " + rsMetaData.getColumnDisplaySize(i));
                    System.out.println("    Nullable: " + rsMetaData.isNullable(i));
                    System.out.println("    Auto Increment: " + rsMetaData.isAutoIncrement(i));  
                

            }
        }catch(SQLException e) { 
            System.out.println("Error: " + e.getMessage());
            
    }
}
    catch(ClassNotFoundException e){
        System.out.println("Error: " + e.getMessage());
    }
}
}