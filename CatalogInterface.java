import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CatalogInterface extends Remote {

    public String listItems() throws RemoteException;

    public String addItemToCatalog(String item) throws RemoteException;

    public String removeItemFromCatalog(String item) throws RemoteException;

    public String saleControl(int port) throws RemoteException;
}