import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Warehouse extends UnicastRemoteObject implements WarehouseInterface{

    private static volatile String sale;
    private static volatile boolean changed;
	private static volatile String remoteHostName, remoteHostPort;

    public Warehouse() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException{

        if (args.length != 1) {
			System.out.println("Usage: java Warehouse <server ip>");
			System.exit(1);
		}

        //------------------- SUBINDO O SERVIDOR -----------------------

        try {
			System.setProperty("java.rmi.server.hostname", args[0]);
			LocateRegistry.createRegistry(40036);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

        try {
			String server = "rmi://" + args[0] + ":40036/Warehouse";
			Naming.rebind(server, new Warehouse());
			System.out.println("Warehouse Server is ready.");
		} catch (Exception e) {
			System.out.println("Warehouse Server failed: " + e);
		}

        while (true) {
			if (changed == true) {
				changed = false;

				String connectLocation = "rmi://" + remoteHostName + ":" + remoteHostPort +"/Warehouse2";

				ClientInterface warehouse2 = null;
				try {
					System.out.println("Calling client back at : " + connectLocation);
					warehouse2 = (ClientInterface) Naming.lookup(connectLocation);
				} catch (Exception e) {
					System.out.println ("Callback failed: ");
					e.printStackTrace();
				}

				try {
					warehouse2.sale(sale);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

            try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {}
        }
    }


    //------------------- MÉTODOS DO SERVIÇO 'CATALOG' -----------------------
    
    /*
     * Método que "separa o pedido" do estoque:
     */
    public String separateOrder(int orderId) throws RemoteException {
        return ("WAREHOUSE - SEPARATE ORDER\n Order [ID=" + orderId + "] separate for shipping.");
    }

    /*
     * Método que "gera a nota fiscal":
     */
    public String generateInvoice(int orderId) throws RemoteException {
        return ("WAREHOUSE - GENERATE INVOICE\n Invoice for order [ID=" + orderId + "] generated.");
    }

    /*
     * Método que "envia o pedido":
     */
    public String sendOrder(int orderId) throws RemoteException {
        return ("WAREHOUSE - SEND ORDER\n Order [ID=" + orderId + "] dispatched.");
    }

    public String saleControl(int port) throws RemoteException {
        /*
            Variáveis que controlarão o valor mínimo e o valor máximo
            que nossa variável randomMethod irá variar. Com isso poderemos
            retornar métodos aleatórios:
        */
        int min = 1;
        int max = 3;
        int randomMethod = (int)Math.floor(Math.random()*(max-min+1)+min);

        changed = true;
		try {
			remoteHostName = getClientHost();
			remoteHostPort = Integer.toString(port);
		} catch (Exception e) {
			System.out.println ("Failed to get client IP");
			e.printStackTrace();
		}

        //Retorna o método separateOrder().
        if (randomMethod == 1) {
            Random randomItem = new Random();
            int orderId = randomItem.nextInt(1000);
            sale = separateOrder(orderId);
            return sale;
        } 
        
        //Retorna o método generateInvoice().
        if (randomMethod == 2) {
            Random randomItem = new Random();
            int orderId = randomItem.nextInt(1000);
            sale = generateInvoice(orderId);
            return sale;
        }

        //Retorna o método sendOrder().
        if (randomMethod == 3) {
            Random randomItem = new Random();
            int orderId = randomItem.nextInt(1000);
            sale = sendOrder(orderId);
            return sale;
        }

        return "Error 404";
    }
    
}
