import java.io.*;
import java.sql.*;

public class Employee1{
    private static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DB_URL="jdbc:mysql://localhost/employee";
    private static final String USER="root";
    private static final String PASS="Mysql";


    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);

            try(Connection conn=DriverManager.getConnection(DB_URL, USER, PASS);
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in))){
                int ch;
                do{
                    System.out.println("MENU:/t");
                    System.out.println("1. Insert Employee");
                    System.out.println("2. Delete Employee");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                   ch=Integer.parseInt(br.readLine());
                    switch(ch){
                        case 1:
                        insertEmployee(conn,br);
                        break;
                    case 2:
                        deleteEmployee(conn,br);
                        break;
                    case 3:
                    break;
                    default:
                    System.out.println("Invalid choice");
                    }
                    }while(ch!=4);
            }

            }catch(ClassNotFoundException e){
                System.out.println("Driver not found");
            }
            catch(SQLException | IOException e){
                System.out.println("Connection failed: " + e.getMessage());
            }
                          
        
            }


            private static void insertEmployee(Connection conn, BufferedReader br) throws IOException, SQLException {
                System.out.print("Enter Employee ID: ");
                int eno = Integer.parseInt(br.readLine());
            
                System.out.print("Enter Employee Name: ");
                String ename = br.readLine();
            
                System.out.print("Enter Employee Age: ");
                int age = Integer.parseInt(br.readLine());
            
                System.out.print("Enter Employee Salary: ");
                int salary = Integer.parseInt(br.readLine());
            
                // Correct SQL query
                String sql = "INSERT INTO EMPLOYEE (eno, ename, age, salary) VALUES (?, ?, ?, ?)";
            
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, eno);
                    pstmt.setString(2, ename);
                    pstmt.setInt(3, age);  // Add age
                    pstmt.setInt(4, salary);
            
                    int rows = pstmt.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Employee inserted successfully!");
                    }
                }
            }
            

            

            private static void deleteEmployee(Connection conn,BufferedReader br) throws IOException, SQLException{
                System.out.println("Enter the Employee Number:");
                int eno= Integer.parseInt(br.readLine());
                String sql="DELETE FROM EMPLOYEE WHERE ENO=?";
                try(PreparedStatement pstmt=conn.prepareStatement(sql)){
                    conn.setAutoCommit(false);
                    pstmt.setInt(1,eno);
                    int rows=pstmt.executeUpdate();
                    if(rows>0){
                        conn.commit();
                        System.out.println("Employee deleted Successfully!");
    

                }else{
                    System.out.println("Employye not found!");
                    conn.rollback();
                    
                }
            }finally{
                conn.setAutoCommit(true);
            }
        }

        


               


}
