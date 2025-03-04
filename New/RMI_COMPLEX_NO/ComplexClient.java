import java.rmi.Naming;
import java.io.*;

public class ComplexClient {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the 1st Comple number:");
        int a1 = Integer.parseInt(br.readLine());
        int b1 = Integer.parseInt(br.readLine());
        System.out.println("Enter the  2n complex numbers");
        int a2 = Integer.parseInt(br.readLine());
        int b2 = Integer.parseInt(br.readLine());

        try {
            ComplexInter obj = (ComplexInter) Naming.lookup("rmi://localhost/com");
            int ans1 = obj.add1(a1, b1);
            int ans2 = obj.add2(a2, b2);
            System.out.println("Sum= " + ans1 + "+ i" + ans2);

        } catch (Exception e) {
            System.out.println("Error Occured: " + e);
        }
    }

}