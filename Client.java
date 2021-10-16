import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements ClientInterface {

    public Client() throws RemoteException {
    }

    public static void main(String[] args) throws InterruptedException {

        Connections connectCatalog = new Connections(args[1], args[2], args[0], args[3]);
        connectCatalog.connectCatalog();
        // /*
        //  * Variável que está recebendo como parâmetro o número de 'vendas' que o usuário
        //  * quer fazer:
        //  */
        // int salesNumber = Integer.parseInt(args[3]);

        // if (args.length != 4) {
        //     System.out.println("Usage: java Client <server ip> <client ip> <client port> <sales number>");
        //     System.exit(1);
        // }

        // //-------------------- CONEXÃO COM O CATALOG -----------------------
        // try {
        //     System.setProperty("java.rmi.server.hostname", args[1]);
        //     LocateRegistry.createRegistry(Integer.parseInt(args[2]));
        //     System.out.println("java RMI registry created.");
        // } catch (RemoteException e) {
        //     System.out.println("java RMI registry already exists.");
        // }

        // try {
        //     String client = "rmi://" + args[1] + ":" + args[2] + "/Catalog2";
        //     Naming.rebind(client, new Client());
        //     System.out.println("Catalog Server is ready.");
        // } catch (Exception e) {
        //     System.out.println("Catalog Server failed: " + e);
        // }

        // String remoteHostName = args[0];
        // String connectLocation = "rmi://" + remoteHostName + ":20036/Catalog";

        // CatalogInterface catalog = null;
        // try {
        //     System.out.println("Connecting to server at : " + connectLocation);
        //     catalog = (CatalogInterface) Naming.lookup(connectLocation);
        // } catch (Exception e) {
        //     System.out.println("Client failed: ");
        //     e.printStackTrace();
        // }
        // //----------------------------------------------------------------

        // /*
        //  * Aqui estou usando a mesma variável que está guardando o número de vendas que
        //  * o usuário quer fazer (salesNumber), para definir a quantidade de vezes que os
        //  * métodos aleatórios serão chamados.
        //  */
        // int aux = 1;
        // /*
        //  * "Repita o laço enquanto 'aux=1' for menor ou igual ao número de vendas
        //  * (salesNumber), que o usuário quer fazer."
        //  * Esse laço servirá para repetirmos o mesmo número de vendas que o usuário setou.
        //  */
        // while (aux <= salesNumber) {
        //     System.out.println("\n------- Sale number [" + aux + "] -------");
        //     int aux2 = 1;
        //     int qtdMethods = salesNumber;
        //     /*
        //      * "Repita o laço enquanto 'aux2=1' for menor ou igual a variável
        //      * (qtdMethods), que o usuário quer fazer."
        //      * Esse laço servirá para repetirmos os métodos n vezes em cada venda.
        //      */
        //     while (aux2 <= qtdMethods) {
        //         try {
        //             catalog.saleControl(Integer.parseInt(args[2]));
        //             System.out.println("\nCall to server...");
        //             System.out.println("\n- Method [" + aux2 + "] -");
        //             Thread.sleep(1000);
        //         } catch (RemoteException e) {
        //             e.printStackTrace();
        //         }
        //         try {
        //             Thread.sleep(1000);
        //         } catch (InterruptedException ex) {
        //         }
        //         aux2++;
        //     }
        //     /*
        //      * Sair do programa quando o número de vendas setado pelo usuário for
        //      * contemplado.
        //      */
        //     if (aux == salesNumber) {
        //         System.exit(0);
        //     }
        //     aux++;
        // }
    }

    @Override
    public String sale(String sale) throws RemoteException {
        System.out.println(sale);
        return sale;
    }
}
