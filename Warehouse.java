import java.util.*;
import java.io.*;

public class Warehouse
{
    SupplierList supplierList = new SupplierList();
    ClientList clientList = new ClientList();
    ProductList productList = new ProductList();

    private String getToken(String prompt) 
    {
		do {
		  try {
			System.out.println(prompt);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = reader.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
			if (tokenizer.hasMoreTokens()) {
				return tokenizer.nextToken();
			}
		  } 
		  catch (IOException ioe) {
			System.exit(0);
		  }
		} while (true);
    }

    // Returns true if yes, false if no
    public boolean yesOrNo(String prompt) 
    {
            String more = getToken(prompt + " (Y|y)[es] or anything else for no");
            if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
                return false;
            }
                return true;
    }

    // Prints out supplier information
    public void trackSupplierInformation()
    {
        System.out.println("List of suppliers:");

        Iterator<Supplier> suppliers = supplierList.getSuppliers();
        while (suppliers.hasNext())
        {
            System.out.println(suppliers.next());
        }
    }

    // Adds a supplier into SupplierList
    public void addSupplier(String sID, String sName)
    {
        Supplier sX = new Supplier(sID, sName);
        supplierList.insertSupplier(sX);
    }
	
    // Loads supplier list from SupplierListData file
    private SupplierList loadSupplierList()
    {
        try {
            FileInputStream file = new FileInputStream("SupplierListData");
            ObjectInputStream input = new ObjectInputStream(file);
            SupplierList supplierList = (SupplierList) input.readObject(); 
            input.close();
            System.out.println("Supplier data successfully loaded.");

            return supplierList;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }

    public void loadSupplierListToUI()
    {
        supplierList = loadSupplierList();
    }

    public void insertSupplier(String supplierID, String supplierName)
    {
        try
        {
            Supplier supplier = new Supplier(supplierID, supplierName);
            supplierList.insertSupplier(supplier);
        } catch (NullPointerException npe) {
            System.out.println(npe);
            System.exit(0);
        }
    }

    public void editSupplier(String oldSupplierID, String supplierID, String supplierName)
    {
        Supplier supplier = supplierList.getSupplier(oldSupplierID);
        supplier.setSupplierID(supplierID);
        supplier.setSupplierName(supplierName);
    }

    // Saves the SupplierList object to SupplierListData file
    public void saveSupplierList()
    {
        try {
            FileOutputStream file = new FileOutputStream("SupplierListData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(supplierList);
            output.close();
            System.out.println("Supplier data successfully saved.");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

	// Add a product to productList
	public void addProduct(String pID, String pName)
	{
		Product prod = new Product(pID, pName);
		productList.insertProduct(prod);
	}
	
	// Prints out product information
    public void trackProductInformation()
    {
        System.out.println("List of products:");

        Iterator<Product> products = productList.getProducts();
        while (products.hasNext())
        {
            System.out.println(products.next());
        }
    }
	
	 // Loads product list from ProductListData file
    public ProductList loadProductList()
    {
        try {
            FileInputStream file = new FileInputStream("ProductListData");
            ObjectInputStream input = new ObjectInputStream(file);
            ProductList productList = (ProductList) input.readObject(); 
            input.close();
            System.out.println("Product data successfully loaded.");

            return productList;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
	
    public void loadProductListToUI()
    {
        productList = loadProductList();
    }

    // Inserts a product into the ProductList
    public void insertProduct(String productID, String productName)
    {
        try
        {
            Product product = new Product(productID, productName);
            productList.insertProduct(product);
        } catch (NullPointerException npe) {
            System.out.println(npe);
            System.exit(0);
        }
    }

    public void editProduct(String oldProductID, String productID, String productName)
    {
        Product product = productList.getProduct(oldProductID);
        product.setProductID(productID);
        product.setProductName(productName);
    }
	
	// Saves the ProductList object to ProductListData file
    public void saveProductList()
    {
        try {
            FileOutputStream file = new FileOutputStream("ProductListData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(productList);
            output.close();
            System.out.println("Product data successfully saved.");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
	
	// Adds a client into ClientList
    public void addClient(String cID, String cName)
    {
        Client cl = new Client(cID, cName);
        clientList.insertClient(cl);
    }
	
	// Loads client list from ClientListData file
    private ClientList loadClientList()
    {
        try {
            FileInputStream file = new FileInputStream("ClientListData");
            ObjectInputStream input = new ObjectInputStream(file);
            ClientList clientList = (ClientList) input.readObject(); 
            input.close();
            System.out.println("Client data successfully loaded.");

            return clientList;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }

    public void loadClientListToUI()
    {
        clientList = loadClientList();
    }

    
    public void insertClient(String clientID, String clientName)
    {
        Client client = new Client(clientID, clientName);
        clientList.insertClient(client);
    }

    public void editClient(String oldClientID, String clientID, String clientName)
    {
        Client client = clientList.getClient(oldClientID);
        client.setClientID(clientID);
        client.setClientName(clientName);
    }
	
	// Saves the ClientList object to ClientListData file
    public void saveClientList()
    {
        try {
            FileOutputStream file = new FileOutputStream("ClientListData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(clientList);
            output.close();
            System.out.println("Client data successfully saved.");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
	
	// Prints out client information
    public void trackClientInformation()
    {
        System.out.println("List of clients:");

        Iterator<Client> clients = clientList.getClients();
        while (clients.hasNext())
        {
            System.out.println(clients.next());
        }
    }
}