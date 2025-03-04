import java.rmi.Naming;

public class ComplexReg {
    public static void main(String args[]){
     
    try{
        ComplexImpl comp=new ComplexImpl();
        Naming.rebind("com",comp);
        System.out.println("Object registered !");
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}