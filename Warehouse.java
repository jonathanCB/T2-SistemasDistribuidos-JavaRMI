import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Warehouse extends UnicastRemoteObject implements WarehouseInterface{

    public Warehouse() throws RemoteException {
    }

    @Override
    public String separateOrder(int orderId) throws RemoteException {
        return ("WAREHOUSE - Order ID = '" + orderId + "' separate for shipping.");
    }

    @Override
    public String generateInvoice(int orderId) throws RemoteException {
        return ("WAREHOUSE - Invoice for order ID = '" + orderId + "' generated.");
    }

    @Override
    public String sendOrder(int orderId) throws RemoteException {
        return ("WAREHOUSE - Order ID = '" + orderId + "' dispatched.");
    }
    
}
