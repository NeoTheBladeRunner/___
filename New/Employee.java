import java.io.*;
import java.sql.*;

public class Employee{
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
                    System.out.println("2. Update Employee");
                    System.out.println("3. Delete Employee");
                    System.out.println("4. Search Employee");
                    System.out.println("5. Display All Employee`s");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");
                   ch=Integer.parseInt(br.readLine());
                    switch(ch){
                        case 1:
                        insertEmployee(conn,br);
                        break;
                    case 2:
                        updateEmployee(conn,br);
                        break;
                    case 3:
                        deleteEmployee(conn,br);
                        break;
                   
                    case 4:
                        displayAllEmployees(conn);
                        break;
                    case 5:
                    break;
                    default:
                    System.out.println("Invalid choice");
                    }
                    }while(ch!=5);
            }

            }catch(ClassNotFoundException e){
                System.out.println("Driver not found");
            }
            catch(SQLException | IOException e){
                System.out.println("Connection failed: " + e.getMessage());
            }
                          
        
            }


            private static void insertEmployee(Connection conn,BufferedReader br) throws IOException,SQLException {
                System.out.print("Enter Employee ID: ");
                int eno=Integer.parseInt(br.readLine());

                System.out.print("Enter Employee Name: ");
                String ename=br.readLine();
                System.out.print("Enter Employee Age: ");
                int age=Integer.parseInt(br.readLine());
                System.out.print("Enter Employee Salary: ");
                int salary=Integer.parseInt(br.readLine());

                String sql="INSERT INTO EMPLOYEE VALUES(eno,ename,salary) VALUES (?,?,?)";
                try(PreparedStatement pstmt=conn.prepareStatement(sql)){
                    pstmt.setInt(1,eno);
                    pstmt.setString(2,ename);
                    pstmt.setInt(3,salary);
                    int rows=pstmt.executeUpdate();
                    if (rows>0){
                        System.out.println("Employee inserted Successfully Completed!");
                    }
                   
                   
                }
            }

            private static void updateEmployee(Connection conn,BufferedReader br) throws IOException,SQLException{
                System.out.print("Enter Employee ID: ");
                int eno=Integer.parseInt(br.readLine());
                System.out.print("Enter New Employee Name: ");
                String ename=br.readLine();
                System.out.print("Enter New Employee Salary: ");
                int salary=Integer.parseInt(br.readLine());

                String sql="UPDATE EMPLOYEE SET ename=?.salary=? WHERE eno=?";
                try(PreparedStatement pstmt=conn.prepareStatement(sql)){
                    conn.setAutoCommit(false);
                    pstmt.setString(1,ename);
                    pstmt.setInt(2,salary);
                    pstmt.setInt(3,eno);
                    int rows=pstmt.executeUpdate();
                    if (rows>0){
                        conn.commit();
                        System.out.println("Employee updated Successfully");
                    }
                        else{
                            System.out.println("Employee not found.");
                            conn.rollback();
                        }

                }finally{
                    conn.setAutoCommit(true);

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

        private static void displayAllEmployees(Connection conn) throws SQLException{
            String sql="SELECT * FROM EMPLOYEE";
            try(Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql)){

           
            System.out.println("Employee ID\tEmployee Name\tEmployee Salary");
            while(rs.next()){
               System.out.println(rs.getInt("eno")+"\t" +rs.getString("ename")+"\t " +rs.getDouble("salary"));
            }
        }
        }
    



               


}
