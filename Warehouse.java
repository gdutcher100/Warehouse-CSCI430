import java.util.*;
import java.io.*;

public class Warehouse
{
    SupplierList supplierList = new SupplierList();
    ClientList clientList = new ClientList();
    ProductList productList = new ProductList();

    public String getToken(String prompt) 
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

    private static Object deepCopy(Object object) 
    {
        try {
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
          outputStrm.writeObject(object);
          ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
          ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
          return objInputStream.readObject();
        }
        catch (Exception e) {
          e.printStackTrace();
          return null;
        }
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
    public void addSupplier(String sName)
    {
        Supplier sX = new Supplier(sName);
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

    public void insertSupplier(String supplierName)
    {
        try
        {
            Supplier supplier = new Supplier(supplierName);
            supplierList.insertSupplier(supplier);
        } catch (NullPointerException npe) {
            System.out.println(npe);
            System.exit(0);
        }
    }

    public void editSupplier(String supplierID, String supplierName)
    {
        Supplier supplier = supplierList.getSupplier(supplierID);
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
	public void addProduct(String pName, float buyPrice, int currentStock)
	{
		Product prod = new Product(pName, buyPrice, currentStock);
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

    public void addToCart(String clientID, int quantity, Product product)
    {
        clientList.getClient(clientID).addToCart(product, quantity);;
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
    public void insertProduct(String productName, float buyPrice, int currentStock)
    {
        try
        {
            Product product = new Product(productName, buyPrice, currentStock);
            productList.insertProduct(product);
        } catch (NullPointerException npe) {
            System.out.println(npe);
            System.exit(0);
        }
    }

    public void editProduct(String ProductID, String productName)
    {
        Product product = productList.getProduct(ProductID);
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
    public void addClient(String cName)
    {
        Client cl = new Client(cName);
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

    
    public void insertClient(String clientName)
    {
        Client client = new Client(clientName);
        clientList.insertClient(client);
    }

    public void editClient(String clientID, String clientName)
    {
        Client client = clientList.getClient(clientID);
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

    // Prints contents of client's cart
    public void trackClientCart(String clientID)
    {
        if (clientList.getClient(clientID).getShoppingCart().getStatus() == "NULL")
        {
            System.out.println("List of products in cart:");

            Iterator<Product> products = clientList.getClient(clientID).getCartContents();
            while (products.hasNext())
            {
                System.out.println(products.next());
            }
        }
    }

    // Prints contents of client's order
    public void trackClientOrder(String clientID)
    {
        if (clientList.getClient(clientID).getOrders().getStatus() == "IN_PROGRESS")
        {
            System.out.println("List of products in order:");

            Iterator<Product> products = clientList.getClient(clientID).getOrderContents();
            while (products.hasNext())
            {
                System.out.println(products.next());
            }
        }
    }

    public void addProductToClientCart(String clientID, String productID, int quantity)
    {
        Product product = (Product) deepCopy(productList.getProduct(productID));
        clientList.getClient(clientID).addToCart(product, quantity);;
    }

    public void placeOrder(String clientID)
    {
        clientList.getClient(clientID).placeOrder();
    }

    public void acceptOrder(String clientID)
    {
        clientList.getClient(clientID).acceptOrder();
    }

    public void viewClientInvoice(String clientID)
    {
        for (int i = 0; i < clientList.getClient(clientID).getInvoiceList().size(); i++)
        {
            System.out.println(clientList.getClient(clientID).getInvoiceList().get(i));
        }
    }
	
	//search products given id
	public Product searchProduct(String pID)
	{
		return productList.getProduct(pID);
	}
	
	//search clients given id
	public Client searchClient(String cID)
	{
		return clientList.getClient(cID);
	}
	
	public void addToSellingSuppliers(String productID, String supplierID, float price) {
		Product product = productList.getProduct(productID);
		Supplier supplier = supplierList.getSupplier(supplierID);
		
		SoldBy seller = new SoldBy(product, supplier, price);
		product.addToSellingSuppliers(seller);
		System.out.println("Seller for " + product.getName() + " updated.");
	}
	
	//adds products and clients to waitlist
	public void addToWaitlist(String clientID, String productID, int quantity) 
	{
		Product product = productList.getProduct(productID);
		Client client = clientList.getClient(clientID);
		
		Waitlist waitlist = new Waitlist(client, product, quantity);
		product.addToWaitlist(waitlist);
		client.addToWaitlist(waitlist);
		System.out.println("Waitlist updated");
	}
	
	public Iterator getWaitlistedProducts(String clientID)
	{
		return clientList.getClient(clientID).getWaitlistedProducts();
	}
  
	public Iterator getWaitlistedClients(String productID)
	{
		return productList.getProduct(productID).getWaitlistedClients();
	}
  
	//get a list of all clients with outstanding balances
	public String getOutstandingBalances() 
	{
		Iterator clients = clientList.getClients();
		String clientBalance = "";
		while(clients.hasNext()) {
		  Client client = (Client)(clients.next());
		if(client.getTotalCost() > 0.0) {
			clientBalance += client.toString() + "\n";
		  }
		}
		return clientBalance;
	}

    public void processOrder(String clientID)
    {
        boolean successful = true;

        if (clientList.getClient(clientID).getOrders().status.toString() == "ACCEPTED")
        {
            for (int i = 0; i < clientList.getClient(clientID).getOrders().getSize(); i++)
            {
                if (clientList.getClient(clientID).getOrders().getCurrentStock(i) < clientList.getClient(clientID).getOrders().getQuantity(i))
                {
                    System.out.println("Cannot be processed because of insufficient stock, putting order on waitlist.");
                    
					// Put on waitlist
					int quantity = clientList.getClient(clientID).getOrders().getQuantity(i);
					String productID = clientList.getClient(clientID).getOrders().getProductID(i);
					addToWaitlist(clientID, productID, quantity);
					System.out.println("Waitlist updated");
					
                    successful = false;
                    break;
                }
            }

            if (successful == true)
            {
                for (int i = 0; i < clientList.getClient(clientID).getOrders().getSize(); i++)
                {
                    String productID = clientList.getClient(clientID).getOrders().getProductID(i);
                    int remainingQuantity = productList.getProduct(productID).getCurrentStock() - clientList.getClient(clientID).getOrders().getQuantity(i);

                    productList.getProduct(productID).setCurrentStock(remainingQuantity);

                    // Generate invoice
                    clientList.getClient(clientID).generateInvoice();
                }
            }
        }
    }
}