import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BillingInterface extends Remote {

    public String addUser(String user) throws RemoteException;

    public String createOrder(String product) throws RemoteException;
        
    public String addPayment(String paymentForm) throws RemoteException;

    public double calcOrder(int orderId) throws RemoteException;
}