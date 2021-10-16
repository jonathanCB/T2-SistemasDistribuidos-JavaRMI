import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Catalog extends UnicastRemoteObject implements CatalogInterface{
    
    private static volatile String sale;
    private static volatile boolean changed;
	private static volatile String remoteHostName, remoteHostPort;

    public Catalog() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException{
        if (args.length != 1) {
			System.out.println("Usage: java Catalog <server ip>");
			System.exit(1);
		}

        try {
			System.setProperty("java.rmi.server.hostname", args[0]);
			LocateRegistry.createRegistry(20036);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

        try {
			String server = "rmi://" + args[0] + ":20036/Catalog";
			Naming.rebind(server, new Catalog());
			System.out.println("Catalog Server is ready.");
		} catch (Exception e) {
			System.out.println("Catalog Server failed: " + e);
		}

        while (true) {
			if (changed == true) {
				changed = false;

				String connectLocation = "rmi://" + remoteHostName + ":" + remoteHostPort +"/Catalog2";

				ClientInterface catalog2 = null;
				try {
					System.out.println("Calling client back at : " + connectLocation);
					catalog2 = (ClientInterface) Naming.lookup(connectLocation);
				} catch (Exception e) {
					System.out.println ("Callback failed: ");
					e.printStackTrace();
				}

				try {
					catalog2.sale(sale);
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

    /* -------------------------------------------------------------------------
        Método que 'lista' todos os itens do catálogo:
    */
    public String listItems() throws RemoteException {
        sale = ("CATALOG - LIST OF ITEMS:"
                + "\n - Playstation 5"
                + "\n - Headset Redragon Zeus"
                + "\n - TV Sony 4k 55'"
                + "\n - Joystick Playstation 5"
                + "\n - Smartphone Samsung Galaxy S20\n");        
		
        return sale;
    }

    /* -------------------------------------------------------------------------
        Método que 'adiciona' um item ao catálogo:
    */
    public String addItemToCatalog(String item) throws RemoteException {
        return ("CATALOG - ADD ITEM:\n " + item + " added to catalog.\n");
    }

    /* -------------------------------------------------------------------------
        Método que 'remove' um item ao catálogo:
    */
    public String removeItemFromCatalog(String item) throws RemoteException {
        return ("CATALOG - REMOVE ITEM:\n " + item + " removed from catalog.\n");
    }
    
    /* -------------------------------------------------------------------------
        Método que controla a 'venda'. Ele será responsável por retornar métodos
        aleatórios do catálogo:
    */
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

        //Retorna o método listItems().
        if (randomMethod == 1) {
            return listItems();
        } 
        
        //Retorna o método addItemToCatalog().
        if (randomMethod == 2) {
            Random randomItem = new Random();
            int item = randomItem.nextInt(1000);
            sale = addItemToCatalog("Item [" + item + "]");
            return sale;
        }

        //Retorna o método removeItemFromCatalog().
        if (randomMethod == 3) {
            Random randomItem = new Random();
            int item = randomItem.nextInt(1000);
            sale = removeItemFromCatalog("Item [" + item + "]");
            return sale;
        }

        return "Error 404";
    }
}
