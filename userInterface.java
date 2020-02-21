import java.util.*;

public class userInterface 
{	
    static Warehouse warehouse = new Warehouse();

    private static void editSupplier()
    {
        String ID;
        String newName;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input ID of the supplier you want to edit: ");
        System.out.print("-->");
        ID = scanner.nextLine();

        System.out.println("Input new name for supplier: ");
        System.out.print("-->");
        newName = scanner.nextLine();

        warehouse.editSupplier(ID, newName);
    }

    private static void editProduct()
    {
        String ID;
        String newName;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input ID of the product you want to edit: ");
        System.out.print("-->");
        ID = scanner.nextLine();

        System.out.println("Input new name for product: ");
        System.out.print("-->");
        newName = scanner.nextLine();

        warehouse.editProduct(ID, newName);
    }

    private static void editClient()
    {
        String ID;
        String newName;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input old ID of the client you want to edit: ");
        System.out.print("-->");
        ID = scanner.nextLine();

        System.out.println("Input new name for client: ");
        System.out.print("-->");
        newName = scanner.nextLine();

        warehouse.editClient(ID, newName);
    }

    // Adds a supplier to the supplierList
    private static void addSupplier()
    {
        String sName;
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the name of the supplier?");
        System.out.print("-->");
        input = scanner.nextLine();
        sName = input;

        warehouse.addSupplier(sName);
        System.out.println("Supplier " + sName + " has been added.");
    }

    // Add a product to productList
	private static void addProduct()
	{
		String pName;
        float bPrice;
        int currentStock;
		String input;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What is the name of the product?");
        System.out.print("-->");
        input = scanner.nextLine();
        pName = input;

        System.out.println("What is the buy price of the product?");
        System.out.print("-->");
        input = scanner.nextLine();
        bPrice = Float.parseFloat(input);

        System.out.println("What is the quantity of the product?");
        System.out.print("-->");
        input = scanner.nextLine();
        currentStock = Integer.parseInt(input);

        warehouse.addProduct(pName, bPrice, currentStock);
        System.out.println("Product " + pName + " has been added.");
    }
	// Adds a supplier to the SoldBy list of a product
    private static void addProductSeller() {
		
	}
	// Displays SoldBy information for a specific seller
	private static void trackProductSellers() {
		
	}
	
    // Adds a client into ClientList
    private static void addClient() {
        String cName;
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the name of the client?");
        System.out.print("-->");
        input = scanner.nextLine();
        cName = input;

        warehouse.addClient(cName);
        System.out.println("Client " + cName + " has been added.");
    }

    // Has user input ID of the user's cart they want to view
    private static void trackClientCart()
    {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Input ID of the client's cart you want to view: ");
        System.out.print("-->");
        input = scanner.nextLine();

        warehouse.trackClientCart(input);
    }

    // Adds a product to the specified client's cart
    private static void addProductToClientCart()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;
        String productID;
        int quantity;

        System.out.println("Input ID of the client's cart you want to add a product to: ");
        System.out.print("-->");
        clientID = scanner.nextLine();

        System.out.println("Input ID of the product you want to the cart: ");
        System.out.print("-->");
        productID = scanner.nextLine();

        System.out.println("Input quantity of the product: ");
        System.out.print("-->");
        quantity = Integer.parseInt(scanner.nextLine());

        warehouse.addProductToClientCart(clientID, productID, quantity);
    }

    private static void placeOrder()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        System.out.println("Input ID of the client cart you want to place an order for: ");
        System.out.print("-->");
        clientID = scanner.nextLine();

        warehouse.placeOrder(clientID);
    }

    private static void trackClientOrders()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        System.out.println("Input ID of the client's orders you want to view: ");
        System.out.print("-->");
        clientID = scanner.nextLine();

        warehouse.trackClientOrder(clientID);
    }

    private static void acceptClientOrders()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        System.out.println("Input ID of the client's orders you want to accept: ");
        System.out.print("-->");
        clientID = scanner.nextLine();

        warehouse.trackClientOrder(clientID);

        warehouse.acceptOrder(clientID);
        System.out.println(clientID + "\'s order has been accepted.");

        warehouse.processOrder(clientID);
    }

    private static void viewClientInvoice()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        System.out.println("Input ID of the client's invoice(s) you want to view: ");
        System.out.print("-->");
        clientID = scanner.nextLine();

        warehouse.viewClientInvoice(clientID);
    }

    private static void loadLists() {
        if (warehouse.yesOrNo("Would you like to load the supplier list? (Y or N)")) {
            warehouse.loadSupplierListToUI();
        } else {
            warehouse.insertSupplier("Supplier1");
            warehouse.insertSupplier("Supplier2");
        }

        if (warehouse.yesOrNo("Would you like to load the product list? (Y or N)")) {
            warehouse.loadProductListToUI();
        } else {
            warehouse.insertProduct("Product1", 5f, 7);
            warehouse.insertProduct("Product2", 18f, 3);
        }

        if (warehouse.yesOrNo("Would you like to load the client list? (Y or N)")) {
            warehouse.loadClientListToUI();
        } else {
            warehouse.insertClient("Client1");
            warehouse.insertClient("Client2");
            //warehouse.addProductToClientCart("01", "01", 34);
        }
    }
	
	//waitlist of clients for a product
	public static void getWaitlistForProducts()
	{
		String pID = warehouse.getToken("Enter product ID: ");
		Product p = warehouse.searchProduct(pID);
		int i = 1;
		if (p == null)
		{
		  System.out.println("Product doesn't exist");
		  return;
		}
		Iterator waitlist = warehouse.getWaitlistedClients(pID);
		Object w;
		System.out.println("---------------------------------");
		while (waitlist.hasNext())
		{
		  w = waitlist.next();
		  System.out.println(i + ".) Client: " + w);
		  i++;
		}
		System.out.println("---------------------------------\n");
	}
	
	//waitlist of products for a client
	public static void getWaitlistForClients()
	{
		String cID = warehouse.getToken("Enter client ID: ");
		Client c = warehouse.searchClient(cID);
		int i = 1;
		if (c == null)
		{
		  System.out.println("Client doesn't exist");
		  return;
		}
		Iterator waitlist = warehouse.getWaitlistedProducts(cID);
		Object w;
		System.out.println("---------------------------------");
		while (waitlist.hasNext())
		{
		  w = waitlist.next();
		  System.out.println(i + ".) Product: " + w);
		  i++;
		}
		System.out.println("---------------------------------\n");
	}

    public static void main(String[] s) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        String input;

        loadLists();

        while (!quit) {
            System.out.println("\nInput the number of the option you want to choose:");
            System.out.println("1. Track Supplier Information");
            System.out.println("2. Edit Supplier Information");
            System.out.println("3. Add Supplier");
            System.out.println("4. Save Supplier List");
            System.out.println("5. Track Product Information");
            System.out.println("6. Edit Product Information");
            System.out.println("7. Add Product");
			System.out.println("8. Add Seller for a Product");
			System.out.println("9. View all Sellers for a Product");
            System.out.println("10. Save Product List");
            System.out.println("11. Track Client Information");
            System.out.println("12. Edit Client Information");
            System.out.println("13. Add Client");
            System.out.println("14. Save Client List");
            System.out.println("15. Track Client Cart");
            System.out.println("16. Track Client Orders");
            System.out.println("17. Add Product to Client Cart");
            System.out.println("18. Place Order for Client");
            System.out.println("19. Accept Client Orders");
            System.out.println("20. View Client Invoice");
			System.out.println("21. List all clients with outstanding balance");
			System.out.println("22. View waitlist of products for a client");
			System.out.println("23. View waitlist of clients for a product");
            System.out.println("24. Quit");

            System.out.print("-->");
            input = scanner.next();
            System.out.println("");

            switch (input) {
                case "1":
                    warehouse.trackSupplierInformation();
                    break;
                case "2":
                    editSupplier();
                    break;
                case "3":
                    addSupplier();
                    break;
                case "4":
                    warehouse.saveSupplierList();
                    break;
                case "5":
                    warehouse.trackProductInformation();
                    break;
                case "6":
                    editProduct();
                    break;
                case "7":
                    addProduct();
                    break;
				case "8":
					addProductSeller();
					break;
				case "9":
					trackProductSellers();
					break;
                case "10":
                    warehouse.saveProductList();
                    break;
                case "11":
                    warehouse.trackClientInformation();
                    break;
                case "12":
                    editClient();
                    break;
                case "13":
                    addClient();
				    break;
                case "14":
                    warehouse.saveClientList();
                    break;
                case "15":
                    trackClientCart();
                    break;
                case "16":
                    trackClientOrders();
                    break;
                case "17":
                    addProductToClientCart();
                    break;
                case "18":
                    placeOrder();
                    break;
                case "19":
                    acceptClientOrders();
                    break;
                case "20":
                    viewClientInvoice();
                    break;
				case "21":
                    warehouse.getOutstandingBalances();
                    break;
				case "22":
					getWaitlistForClients();
					break;
				case "23":
					getWaitlistForProducts();
					break;
                case "24":
                    quit = true;
                    break;
            }
        }
        scanner.close();
    }
}