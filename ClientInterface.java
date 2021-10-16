import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public String sale(String sale) throws RemoteException;
}
