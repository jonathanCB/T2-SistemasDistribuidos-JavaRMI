import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WarehouseInterface extends Remote {

    public String separateOrder(int orderId) throws RemoteException;
    
    public String generateInvoice(int orderId) throws RemoteException;

    public String sendOrder(int orderId) throws RemoteException;

    public String saleControl(int port) throws RemoteException;
}