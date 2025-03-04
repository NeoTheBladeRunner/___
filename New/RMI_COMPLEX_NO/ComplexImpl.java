import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComplexImpl extends UnicastRemoteObject implements ComplexInter {
    public final long serialVersionUID = 1L;

    public ComplexImpl() throws RemoteException{
        // super();
    }
    public int add1(int var1,int var2) throws RemoteException{
        return var1+var2;
    }
    public int add2(int var1,int var2) throws RemoteException{
        return var1+var2;

    }
} 