import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements ClientInterface {

    public Client() throws RemoteException {
    }

    public static void main(String[] args) throws InterruptedException {

        /*
            Para que não precisemos passar as portas na hora da execução da classe 
            Client, vou deixar as portas já carregas através das variáveis abaixo:
        */ 
        String catalogPort = "20036";
        String billingPort = "30036";
        String warehousePort = "40036";
        
        if (args.length != 3) {
            System.out.println("Usage: java Client <server ip> <client ip> <sales number>");
            System.exit(1);
        }

        /*
            Aqui criamos uma classe chamada 'Connections', ela será responsável por
            criar as conexões com nossos 3 servidores: Catalog, Billing e Warehouse.
            O maior objetivo da criação dessa classe é deixar nossa classe 'Client'
            mais enxuta, com menos código e mais limpa visualmente.
        */
        Connections catalog = new Connections(args[1], catalogPort, args[0], args[2]);
        catalog.connectCatalog();     
        
        Connections billing = new Connections(args[1], billingPort, args[0], args[2]);
        billing.connectBilling();   
        
        Connections warehouse = new Connections(args[1], warehousePort, args[0], args[2]);
        warehouse.connectWarehouse();  
    }

    @Override
    public String sale(String sale) throws RemoteException {
        System.out.println(sale);
        return sale;
    }
}
