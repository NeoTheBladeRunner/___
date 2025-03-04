
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComplexInter extends Remote{
    int add1(int a1, int b1) throws RemoteException;
    int add2(int a2,int b2) throws RemoteException;
}