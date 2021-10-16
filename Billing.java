import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Billing extends UnicastRemoteObject implements BillingInterface{

    public Billing() throws RemoteException {		
	}

    /*
        Aqui simulamos a criação de usuário, onde recebemos um nome de 
        usuário e retornamos uma mensagem de confirmação:
    */
	@Override
    public String addUser(String user) throws RemoteException {
        return ("BILLING - User '" + user + "' has been added.");
    }

    /*
        O método 'createOrder' simula a criação de um pedido. Recebemos 
        um 'product' como argumento e retornamos o "id" do pedido criado:
    */ 
    @Override
    public String createOrder(String product) throws RemoteException {
        //Gerando um número aleatório de 0 a 100 para simular o id de uma venda.
        Random random = new Random();
        int orderId = random.nextInt(100);
        return ("BILLING - Order ID = '" + orderId + "' successfully created.\n" 
            + "Product on order: " + product);
    }

    /*
        Método para receber a forma de pagamento desejada. Retorna uma 
        mensagem de confirmação:
    */
    @Override
    public String addPayment(String paymentForm) throws RemoteException {        
        return ("BILLING - Payment form added: " + paymentForm);
    }

    /*
        Método que recebe o id do pedido e retorna o preço total desse
        pedido:
    */
    @Override
    public double calcOrder(int orderId) throws RemoteException {
        Random random = new Random();
        double totalPrice = random.nextDouble() * 10000;
        return totalPrice;
    }
    
}
