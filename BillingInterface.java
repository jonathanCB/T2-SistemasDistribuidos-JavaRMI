import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BillingInterface extends Remote {

    public String addUser(String user) throws RemoteException;

    public String createOrder() throws RemoteException;
        
    public String addPaymentForm(String paymentForm) throws RemoteException;

    public double calcOrder() throws RemoteException;

    public String saleControl(int port) throws RemoteException;
}