import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Billing extends UnicastRemoteObject implements BillingInterface{

    private static volatile String sale;
    private static volatile boolean changed;
	private static volatile String remoteHostName, remoteHostPort;
    
    public Billing() throws RemoteException {		
	}

    public static void main(String[] args) throws RemoteException{
        if (args.length != 1) {
			System.out.println("Usage: java Billing <server ip>");
			System.exit(1);
		}

        try {
			System.setProperty("java.rmi.server.hostname", args[0]);
			LocateRegistry.createRegistry(30036);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

        try {
			String server = "rmi://" + args[0] + ":30036/Billing";
			Naming.rebind(server, new Billing());
			System.out.println("Billing Server is ready.");
		} catch (Exception e) {
			System.out.println("Billing Server failed: " + e);
		}

        while (true) {
			if (changed == true) {
				changed = false;

				String connectLocation = "rmi://" + remoteHostName + ":" + remoteHostPort +"/Billing2";

				ClientInterface billing2 = null;
				try {
					System.out.println("Calling client back at : " + connectLocation);
					billing2 = (ClientInterface) Naming.lookup(connectLocation);
				} catch (Exception e) {
					System.out.println ("Callback failed: ");
					e.printStackTrace();
				}

				try {
					billing2.sale(sale);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

            try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {}
        }
    }

    //------------------- MÉTODOS DO SERVIÇO 'BILLING' -----------------------

    /*
        Aqui simulamos a criação de usuário, onde recebemos um nome de 
        usuário e retornamos uma mensagem de confirmação:
    */
    public String addUser(String user) throws RemoteException {
        return ("BILLING - ADD USER\n User '" + user + "' has been added.");
    }

    /*
        O método 'createOrder' simula a criação de um pedido. Recebemos 
        um 'product' como argumento e retornamos o "id" do pedido criado:
    */ 
    public String createOrder() throws RemoteException {
        //Gerando um número aleatório de 0 a 100 para simular o id de uma venda.
        Random random = new Random();
        int orderId = random.nextInt(1000);
        return ("BILLING - CREATE ORDER\n Order: [ID=" + orderId + "] successfully created.");
    }

    /*
        Método para receber a forma de pagamento desejada. Retorna uma 
        mensagem de confirmação:
    */
    public String addPaymentForm(String paymentForm) throws RemoteException {        
        return ("BILLING - ADD PAYMENT FORM\n Payment form added: " + paymentForm);
    }

    /*
        Método que recebe o id do pedido e retorna o preço total desse
        pedido:
    */
    public double calcOrder() throws RemoteException {
        Random random = new Random();
        double totalPrice = random.nextDouble() * 10000;
        return totalPrice;
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
        int max = 4;
        int randomMethod = (int)Math.floor(Math.random()*(max-min+1)+min);

        changed = true;
		try {
			remoteHostName = getClientHost();
			remoteHostPort = Integer.toString(port);
		} catch (Exception e) {
			System.out.println ("Failed to get client IP");
			e.printStackTrace();
		}

        //Retorna o método addUser().
        if (randomMethod == 1) {
            Random randomUser = new Random();
            int user = randomUser.nextInt(1000);
            sale = addUser("[ID=" + user + "]");
            return sale;
        } 
        
        //Retorna o método createOrder().
        if (randomMethod == 2) {
            sale = createOrder();
            return sale;
        }

        //Retorna o método addPaymentForm().
        if (randomMethod == 3) {
            sale = addPaymentForm("Credit card");
            return sale;
        }

        //Retorna o método calcOrder().
        if (randomMethod == 4) {
            Random randomOrder = new Random();
            int orderId = randomOrder.nextInt(1000);
            String formattedPrice = String.format("%.2f", calcOrder());
            sale = "BILLING - ORDER CALCULATION\n Order: [ID=" + orderId + "]\n Total price: $" + formattedPrice;
            return sale;
        }

        return "Error 404";
    }
    
}
