import java.io.IOError;
import java.io.IOException;
import java.util.*;

public class userInterface 
{	
    static Warehouse warehouse = new Warehouse();

    private static void editSupplier()
    {
        String ID;
        String newName;
        Scanner scanner = new Scanner(System.in);

        try
        {
            System.out.println("Input ID of the supplier you want to edit: ");
            System.out.print("-->");
            ID = scanner.nextLine();

            System.out.println("Input new name for supplier: ");
            System.out.print("-->");
            newName = scanner.nextLine();

            warehouse.editSupplier(ID, newName);
        } catch (NullPointerException npe)
        {
            System.out.println("Supplier not found");
        }
    }

    private static void editProduct()
    {
        String ID;
        String newName;
        Scanner scanner = new Scanner(System.in);

        try
        {
            System.out.println("Input ID of the product you want to edit: ");
            System.out.print("-->");
            ID = scanner.nextLine();

            System.out.println("Input new name for product: ");
            System.out.print("-->");
            newName = scanner.nextLine();

            warehouse.editProduct(ID, newName);
        } catch (NullPointerException npe)
        {
            System.out.println("Product not found.");
        }
    }

    private static void editClient()
    {
        String ID;
        String newName;
        Scanner scanner = new Scanner(System.in);

        try
        {
            System.out.println("Input ID of the client you want to edit: ");
            System.out.print("-->");
            ID = scanner.nextLine();

            System.out.println("Input new name for client: ");
            System.out.print("-->");
            newName = scanner.nextLine();

            warehouse.editClient(ID, newName);
        } catch (NullPointerException npe)
        {
            System.out.println("Client can't be found.");
        }
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
		String productId;
		String supplierId;
		float buyPrice;
		String input;
		Scanner scanner = new Scanner(System.in);
        
        try
        {
            System.out.println("Enter the ID of the product for which there is a new Supplier. ");
            System.out.print("-->");
            productId = scanner.nextLine();
            
            System.out.println("Enter the ID of the Supplier that now sells the product.");
            System.out.print("-->");
            supplierId = scanner.nextLine();
            
            System.out.println("Enter the purchase price of the product from this supplier.");
            System.out.print("-->");
            input = scanner.nextLine();
            buyPrice = Float.parseFloat(input);
            
            warehouse.addToSellingSuppliers(productId, supplierId, buyPrice);	
            System.out.println("Seller for product " + productId + " updated.");
        } catch (NullPointerException npe)
        {
            System.out.println("Could add product seller.");
        }
	}
	
	// Displays SoldBy information for a specific seller
	private static void trackProductSellers() {
		String productId;
		Scanner scanner = new Scanner(System.in);
        
        try
        {
            System.out.println("Enter the ID of the product whose sellers you wish to display.");
            System.out.print("-->");
            productId = scanner.nextLine();
            
            warehouse.trackProductSellers(productId);
        } catch (NullPointerException npe)
        {
            System.out.println("Product not found.");
        }
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

        try
        {
            System.out.println("Input ID of the client's cart you want to view: ");
            System.out.print("-->");
            input = scanner.nextLine();

            warehouse.trackClientCart(input);
        } catch (NullPointerException npe)
        {
            System.out.println("Could not get client's shopping cart.");
        }
    }

    // Adds a product to the specified client's cart
    private static void addProductToClientCart()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;
        String productID;
        int quantity;

        try
        {
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
        } catch (NullPointerException npe)
        {
            System.out.println("Could not add product to client cart.");
        }
    }

    private static void placeOrder()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        try
        {
            System.out.println("Input ID of the client cart you want to place an order for: ");
            System.out.print("-->");
            clientID = scanner.nextLine();

            warehouse.placeOrder(clientID);
        }   catch (NullPointerException npe)
        {
            System.out.println("Could not place order.");
        }
    }

    private static void trackClientOrders()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        try
        {
            System.out.println("Input ID of the client's orders you want to view: ");
            System.out.print("-->");
            clientID = scanner.nextLine();

            warehouse.trackClientOrder(clientID);
        } catch (NullPointerException npe)
        {
            System.out.println("Could not find client.");
        }
    }

    private static void acceptClientOrders()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        try
        {
            System.out.println("Input ID of the client's orders you want to accept: ");
            System.out.print("-->");
            clientID = scanner.nextLine();

            warehouse.trackClientOrder(clientID);

            warehouse.acceptOrder(clientID);
            System.out.println(clientID + "\'s order has been accepted.");

            warehouse.processOrder(clientID);
        } catch (NullPointerException npe)
        {
            System.out.println("Could accept client's order.");
        }
    }

    private static void viewClientInvoice()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;

        try
        {
            System.out.println("Input ID of the client's invoice(s) you want to view: ");
            System.out.print("-->");
            clientID = scanner.nextLine();

            warehouse.viewClientInvoice(clientID);
        } catch (NullPointerException npe)
        {
            System.out.println("Could not view client's invoice.");
        }
    }

    private static void editClientCart()
    {
        Scanner scanner = new Scanner(System.in);
        String clientID;
        String input = "";

        try
        {
            System.out.println("Input ID of the client's cart you want to view: ");
            System.out.print("-->");
            clientID = scanner.nextLine();

            while (!input.equals("QUIT"))
            {
                System.out.println("");
                warehouse.trackClientCart(clientID);

                System.out.println("\nDo you want to edit quantity or remove product from cart? (Type 'edit', 'remove', or 'quit' to quit.)");
                System.out.print("-->");
                input = scanner.nextLine();
        
                input = input.toUpperCase();

                if (input.equals("EDIT"))
                {
                    String productID;
                    int quantity;

                    System.out.println("Input ID of the product's quantity you want to edit: ");
                    System.out.print("-->");
                    productID = scanner.nextLine();

                    System.out.println("Input the desired quantity: ");
                    System.out.print("-->");
                    quantity = Integer.parseInt(scanner.nextLine());

                    if (warehouse.editShoppingCart(clientID, productID, quantity))
                        System.out.println("\nClient's cart has been successfully edited.");
                    else
                        System.out.println("Product is not in cart");
                }

                else if (input.equals("REMOVE"))
                {
                    String productID;

                    System.out.println("Input ID of the product you want to remove: ");
                    System.out.print("-->");
                    productID = scanner.nextLine();

                    warehouse.removeFromShoppingCart(clientID, productID);
                } 

                else if (input.equals("QUIT"))
                {
                    break;
                } 
            
                else
                {
                    System.out.println("Try typing that again (or type 'quit' to exit).");
                }
            } 
        } catch (NullPointerException npe)
        {
            System.out.println("Invalid input");
        }
    }

    private static void loadLists() {
        try
        {
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
                warehouse.addProductToClientCart("C5", "P3", 4);
            }
        } catch (NullPointerException npe)
        {
            System.out.println("Could not load list");
        }
    }
	
	//waitlist of clients for a product
	public static void getWaitlistForProducts()
	{
        try
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
        } catch (NullPointerException npe)
        {
            System.out.println("Could not get waitlist.");
        }
	}
	
	//waitlist of products for a client
	public static void getWaitlistForClients()
	{
        try
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
        } catch (NullPointerException npe)
        {
            System.out.println("Could not get waitlist.");
        }
    }
    
    private static void generalHelp()
    {
        System.out.println("\nInput the number of the option you want to choose:");
        System.out.println("1. Supplier Options");
        System.out.println("2. Client Options");
        System.out.println("3. Product Option");
        System.out.println("4. Quit");
    }

    private static void supplierHelp()
    {
        System.out.println("\n1. Track Supplier Information");
        System.out.println("2. Edit Supplier Information");
        System.out.println("3. Add Supplier");
        System.out.println("4. Save Supplier List");
        System.out.println("5. Add Seller for a Product");
        System.out.println("6. View all Sellers for a Product");
        System.out.println("7. Back");
    }

    private static void clientHelp()
    {
        System.out.println("\n1. Track Client Information");
        System.out.println("2. Edit Client Information");
        System.out.println("3. Add Client");
        System.out.println("4. Save Client List");
        System.out.println("5. Track Client Cart");
        System.out.println("6. Track Client Orders");
        System.out.println("7. Add Product to Client Cart");
        System.out.println("8. Edit Client Cart");
        System.out.println("9. Place Order for Client");
        System.out.println("10. Accept Client Orders");
        System.out.println("11. View Client Invoice");
        System.out.println("12. List all clients with outstanding balance");
        System.out.println("13. View waitlist of products for a client");
        System.out.println("14. View waitlist of clients for a product");
        System.out.println("15. Back");
    }

    private static void productHelp()
    {
        System.out.println("\n1. Track Product Information");
        System.out.println("2. Edit Product Information");
        System.out.println("3. Add Product");
        System.out.println("4. Save Product List");
        System.out.println("5. Back");
    }

    private static boolean clientSwitch(String input)
    {
        switch (input)
        {
            case "1":
                warehouse.trackClientInformation();
                break;
            case "2":
                editClient();
                break;
            case "3":
                addClient();
                break;
            case "4":
                warehouse.saveClientList();
                break;
            case "5":
                trackClientCart();
                break;
            case "6":
                trackClientOrders();
                break;
            case "7":
                addProductToClientCart();
                break;
            case "8":
                editClientCart();
                break;
            case "9":
                placeOrder();
                break;
            case "10":
                acceptClientOrders();
                break;
            case "11":
                viewClientInvoice();
                break;
            case "12":
                warehouse.getOutstandingBalances();
                break;
            case "13":
                getWaitlistForProducts();
                break;
            case "14":
                getWaitlistForClients();
                break;
            case "15":
                return true;
        }

        return false;
    }

    private static boolean supplierSwitch(String input)
    {
        switch (input)
        {
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
                addProductSeller();
                break;
            case "6":
                trackProductSellers();
                break;
            case "7":
                return true;
        }

        return false;
    }

    private static boolean productSwitch(String input)
    {
        switch (input)
        {
            case "1":
                warehouse.trackProductInformation();
                break;
            case "2":
                editProduct();
                break;
            case "3":
                addProduct();
                break;
            case "4":
                warehouse.saveProductList();
                break;
            case "5":
                return true;
        }

        return false;
    }

    public static void main(String[] s) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean back = false;
        String input;

        loadLists();

        while (!quit) {
            generalHelp();

            System.out.print("-->");
            input = scanner.next();
            System.out.println("");

            switch (input)
            {
                case "1":
                    while (!back)
                    {
                        supplierHelp();

                        System.out.print("-->");
                        input = scanner.next();
                        System.out.println("");

                        back = supplierSwitch(input);
                    }
                    back = false;
                    break;
                case "2":
                    while (!back)
                    {
                        clientHelp();

                        System.out.print("-->");
                        input = scanner.next();
                        System.out.println("");

                        back = clientSwitch(input);
                    }
                    back = false;
                    break;
                case "3":
                    while (!back)
                    {
                        productHelp();

                        System.out.print("-->");
                        input = scanner.next();
                        System.out.println("");

                        back = productSwitch(input);
                    }
                    back = false;
                    break;
                case "4":
                    quit = true;
                    break;
            }
        }
        scanner.close();
    }
}