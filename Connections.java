import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class Connections {

    String property;
    String registry;
    String remoteHostName;
    int salesNumber;

    public Connections(String property, String registryCatalog, String remoteHostName, String salesNumber) {
        this.property = property;
        this.registry = registryCatalog;
        this.remoteHostName = remoteHostName;
        this.salesNumber = Integer.parseInt(salesNumber);
    }
    
    //---------- Método que conecta com o servidor 'CATALOG' ----------
    public void connectCatalog() throws InterruptedException {
        try {
            System.out.println("\n************************* CATALOG SERVER *************************");
            System.setProperty("java.rmi.server.hostname", this.property);
            LocateRegistry.createRegistry(Integer.parseInt(this.registry));
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        try {
            String client = "rmi://" + this.property + ":" + this.registry + "/Catalog2";
            Naming.rebind(client, new Client());
            System.out.println("Catalog Server is ready.");
        } catch (Exception e) {
            System.out.println("Catalog Server failed: " + e);
        }

        String remoteHostName = this.remoteHostName;
        String connectLocation = "rmi://" + remoteHostName + ":20036/Catalog";

        CatalogInterface catalog = null;
        try {
            System.out.println("Connecting to server at : " + connectLocation);
            catalog = (CatalogInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        int aux = 1;

        /*
         * Laço que repete as vendas até o número de vendas que o client passou por parâmetro.
         */
        while (aux <= salesNumber) {
            System.out.println(" \n*********** CATALOG ***********");
            System.out.println("------- Sale number [" + aux + "] -------");
            
            int aux2 = 1;
            /*
             * A variável "qtdMethods" servirá para controlar quantas vezes os métodos se repetirão 
             * em cada venda.
             */
            int qtdMethods = 3;
            /*
             * "Repita o laço enquanto 'aux2' for menor ou igual a variável
             * (qtdMethods)."
             * Esse laço servirá para repetirmos os métodos 3 vezes em cada venda.
             */
            while (aux2 <= qtdMethods) {
                try {
                    catalog.saleControl(Integer.parseInt(this.registry));
                    System.out.println("\nCall to server [CATALOG]...");
                    System.out.println("\n- Method [" + aux2 + "] -");
                    Thread.sleep(1000);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                aux2++;
            }
            /*
             * Sair do programa quando o número de vendas setado pelo usuário for
             * contemplado.
             */
            if (aux == salesNumber) {
                return;
            }
            aux++;
        }
    }

    //----------Método que conecta com o servidor 'BILLING' ----------
    public void connectBilling() throws InterruptedException {
        try {
            System.out.println("\n************************* BILLING SERVER *************************");
            System.setProperty("java.rmi.server.hostname", this.property);
            LocateRegistry.createRegistry(Integer.parseInt(this.registry));
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        try {
            String client = "rmi://" + this.property + ":" + this.registry + "/Billing2";
            Naming.rebind(client, new Client());
            System.out.println("Billing Server is ready.");
        } catch (Exception e) {
            System.out.println("Billing Server failed: " + e);
        }

        String remoteHostName = this.remoteHostName;
        String connectLocation = "rmi://" + remoteHostName + ":30036/Billing";

        BillingInterface billing = null;
        try {
            System.out.println("Connecting to server at : " + connectLocation);
            billing = (BillingInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        int aux = 1;

        while (aux <= salesNumber) {
            System.out.println(" \n*********** BILLING ***********");
            System.out.println("------- Sale number [" + aux + "] -------");
            int aux2 = 1;
            /*
             * A variável "qtdMethods" servirá para controlar quantas vezes os métodos se repetirão 
             * em cada venda.
             */
            int qtdMethods = 3;
            /*
             * "Repita o laço enquanto 'aux2 = 1' for menor ou igual a variável
             * (qtdMethods), que o usuário quer fazer."
             * Esse laço servirá para repetirmos os métodos n vezes em cada venda.
             */
            while (aux2 <= qtdMethods) {
                try {
                    billing.saleControl(Integer.parseInt(this.registry));
                    System.out.println("\nCall to server [BILLING]...");
                    System.out.println("\n- Method [" + aux2 + "] -");
                    Thread.sleep(1000);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                aux2++;
            }
            /*
             * Sair do programa quando o número de vendas setado pelo usuário for
             * contemplado.
             */
            if (aux == salesNumber) {
                return;
            }
            aux++;
        }
    }

    //---------- Método que conecta com o servidor 'WAREHOUSE' ----------
    public void connectWarehouse() throws InterruptedException {
        try {
            System.out.println("\n************************* WAREHOUSE SERVER *************************");
            System.setProperty("java.rmi.server.hostname", this.property);
            LocateRegistry.createRegistry(Integer.parseInt(this.registry));
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }

        try {
            String client = "rmi://" + this.property + ":" + this.registry + "/Warehouse2";
            Naming.rebind(client, new Client());
            System.out.println("Warehouse Server is ready.");
        } catch (Exception e) {
            System.out.println("Warehouse Server failed: " + e);
        }

        String remoteHostName = this.remoteHostName;
        String connectLocation = "rmi://" + remoteHostName + ":40036/Warehouse";

        WarehouseInterface warehouse = null;
        try {
            System.out.println("Connecting to server at : " + connectLocation);
            warehouse = (WarehouseInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Client failed: ");
            e.printStackTrace();
        }

        int aux = 1;

        while (aux <= salesNumber) {
            System.out.println(" \n*********** WAREHOUSE ***********");
            System.out.println("------- Sale number [" + aux + "] -------");
            int aux2 = 1;
            /*
             * A variável "qtdMethods" servirá para controlar quantas vezes os métodos se repetirão 
             * em cada venda.
             */
            int qtdMethods = 3;
            /*
             * "Repita o laço enquanto 'aux2 = 1' for menor ou igual a variável
             * (qtdMethods), que o usuário quer fazer."
             * Esse laço servirá para repetirmos os métodos n vezes em cada venda.
             */
            while (aux2 <= qtdMethods) {
                try {
                    warehouse.saleControl(Integer.parseInt(this.registry));
                    System.out.println("\nCall to server [WAREHOUSE]...");
                    System.out.println("\n- Method [" + aux2 + "] -");
                    Thread.sleep(1000);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                aux2++;
            }
            /*
             * Sair do programa quando o número de vendas setado pelo usuário for
             * contemplado.
             */
            if (aux == salesNumber) {
                System.exit(0);
            }
            aux++;
        }
    }
}
