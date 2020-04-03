import java.util.*;

public class userInterface 
{	
    static Warehouse warehouse = new Warehouse();
    private enum UserType
    {
        NULL, CLIENT, MANAGER, SALESPERSON;
    }

    private static UserType userType = UserType.NULL;
    private static String username;
    private static String clientID;

    private static void processShipment(){
		Scanner scanner = new Scanner(System.in);
		String pID;
		String input;
		int amt;
		Boolean done = false;
		
		while(!done){
			System.out.print("Enter the ID of the next product on the delivery: ");
			pID = scanner.nextLine();
			System.out.print("Enter the amount delivered: ");
			input = scanner.nextLine();
			amt = Integer.parseInt(input);
		
			if(warehouse.searchProduct(pID) == null){
				System.out.print("No such product.");
			} else {
				warehouse.processDelivery(pID, amt);
			}
			System.out.print("More products on delivery? (y/n) ");
			input = scanner.nextLine();
			input = input.toUpperCase();
			if (input.equals("N")){
				done = true;
				break;
			}
		}	
	}
	
	private static void addClient() {
        String cName;
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the name of the client?");
        System.out.print("-->");
        input = scanner.nextLine();
        cName = input;

        warehouse.addClient(cName,"0");
        System.out.println("Client " + cName + " has been added.");
    }
	
	private static void actAsClient() {
		boolean back = false;
		
		while (!back)
                    {
                        userType = UserType.CLIENT;

                        System.out.print("Username: ");
                        user = scanner.next();

                        System.out.print("Password: ");
                        pass = scanner.next();

                        if (loginVerify(userType, user, pass))
                        {
                            while (!back)
                            {
                                clientHelp();

                                System.out.print("-->");
                                input = scanner.next();
                                System.out.println("");

                                back = clientSwitch(input);
                            }
                        }
                    }
	}
	
	
	private static void placeOrder()
    {
        try
        {
            warehouse.placeOrder(clientID);
        }   catch (NullPointerException npe)
        {
            System.out.println("Could not place order.");
        }
    }

    // Has user input ID of the user's cart they want to view
    private static void trackClientCart()
    {
        try
        {
            warehouse.trackClientCart(clientID);
        } catch (NullPointerException npe)
        {
            System.out.println("Could not get client's shopping cart.");
        }
    }

    private static void editClientCart()
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        try
        {
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

                    System.out.println(clientID);
                    System.out.println(quantity);

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

    // Adds a product to the specified client's cart
    private static void addProductToClientCart()
    {
        Scanner scanner = new Scanner(System.in);
        String productID;
        int quantity;

        try
        {
            System.out.println("Input ID of the product you want to add to the cart: ");
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

    private static void trackClientOrders()
    {
        try
        {
            warehouse.trackClientOrder(clientID);
        } catch (NullPointerException npe)
        {
            System.out.println("Could not find client.");
        }
    }

    //waitlist of products for a client
	private static void getWaitlistForClients()
	{
        try
        {
            Client c = warehouse.searchClient(clientID);
            int i = 1;
            if (c == null)
            {
            System.out.println("Client doesn't exist");
            return;
            }
				Iterator<Waitlist> waitlist = warehouse.getWaitlistedProducts(clientID);
				System.out.println("---------------------------------");
				while (waitlist.hasNext())
            {
				Waitlist curList = waitlist.next();
            System.out.println(i + " " + curList);
            i++;
            }
            System.out.println("---------------------------------\n");
        } catch (NullPointerException npe)
        {
            System.out.println("Could not get waitlist.");
        }
    }

    //waitlist of clients for a product
	private static void getWaitlistForProducts()
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
            Iterator<Waitlist> waitlist = warehouse.getWaitlistedClients(pID);
            System.out.println("---------------------------------");
            while (waitlist.hasNext())
            {
				Waitlist curList = waitlist.next();
				System.out.println(i + " " + curList);
				i++;
            }
            System.out.println("---------------------------------\n");
        } catch (NullPointerException npe)
        {
            System.out.println("Could not get waitlist.");
        }
	}

    private static void viewClientInvoice()
    {
        try
        {
            warehouse.viewClientInvoice(clientID);
        } catch (NullPointerException npe)
        {
            System.out.println("Could not view client's invoice.");
        }
    }

    private static boolean loginVerify(UserType ut, String user, String pass)
    {
        try
        {
            if (ut.toString() == "CLIENT")
            {
                clientID = warehouse.clientList.getClientID(user).getID();
                username = user;
                return true;
            }
            else if (ut.toString() == "MANAGER")
            {
                if (user.equalsIgnoreCase("manager"))
                {
                    return true;
                }
                else
                {
                    System.out.println("Invalid Username/Password");
                }
            }
            else if (ut.toString() == "SALESPERSON")
            {
                if (user.equalsIgnoreCase("salesclerk"))
                {
                    return true;
                }
                else
                {
                    System.out.println("Invalid Username/Password");
                }
            }
        }
        catch (NullPointerException npe)
        {
            System.out.println("Invalid Username/Password");
        }

        return false;
    }

    private static void clientHelp()
    {
        System.out.println("");
        System.out.println("1. View Acount");
        System.out.println("2. Place Order");
        System.out.println("3. View Cart");
        System.out.println("4. Edit Cart");
        System.out.println("5. Add Product To Cart");
        System.out.println("6. View Products");
        System.out.println("7. View Waitlist");
        System.out.println("8. Logout");
    }

    private static boolean clientSwitch(String input)
    {
        Scanner scanner = new Scanner(System.in);
        boolean back = false;
        boolean back2 = false;

        switch (input)
        {
            case "1":
                while (!back2)
                {
                    System.out.println("");
                    System.out.println("1. Show Orders");
                    System.out.println("2. Show Invoice");
                    System.out.println("3. Show Balance");
                    System.out.println("4. Back");

                    System.out.print("-->");
                    input = scanner.next();
                    System.out.println("");

                    switch (input)
                    {
                        case "1":
                            trackClientOrders();
                            break;
                        case "2":
                            viewClientInvoice();
                            break;
                        case "3":
                            warehouse.getOutstandingBalance(clientID);
                            break;
                        case "4":
                            back2 = true;
                            break;
                    }
                }

                break;
            case "2":
                placeOrder();
                break;
            case "3":
                trackClientCart();
                break;
            case "4":
                editClientCart();
                break;
            case "5":
                addProductToClientCart();
                break;
            case "6":
                warehouse.trackProductInformation();
                break;
            case "7":
                getWaitlistForClients();
                break;
            case "8":
                back = true;
                break;
        }

        if (back2 || !back)
        {
            return false;
        } 
        else
        {
            return true;
        }
    }

	private static void clerkHelp()
	{
		System.out.println("");
        System.out.println("1. Add New Client");
        System.out.println("2. Show Product List");
        System.out.println("3. Show Client List");
        System.out.println("4. Show Clients With Outstanding Balance");
        System.out.println("5. Access Client Account");
        System.out.println("6. Display Product Waitlist");
        System.out.println("7. Receive Shipment");
        System.out.println("8. Logout");
	}

	private static boolean clerkSwitch(String input)
	{
		Scanner scanner = new Scanner(System.in);
        boolean back = false;
		
		switch (input)
		{
			case "1":
				addClient();
				break;
			case "2":
				warehouse.trackProductInformation();
				break;
			case "3":
				warehouse.trackClientInformation();
				break;
			case "4":
				warehouse.trackClientsWithDues();
				break;
			case "5":
				actAsClient();
				break;
			case "6":
				getWaitlistForProducts();
				break;
			case "7":
				processShipment();
				break;
			case "8":
				back = true;
				break;
		}
		if (!back)
        {
            return false;
        } 
        else
        {
            return true;
        }
	}

    private static void loadLists() {
        try
        {
            warehouse.loadSupplierListToUI();
            warehouse.loadProductListToUI();
            warehouse.loadClientListToUI();
        } catch (NullPointerException npe)
        {
            System.out.println("Could not load list");
        }
    }

    public static void main(String[] s) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean back = false;
        String input;

        String user;
        String pass;

        loadLists();

        /*warehouse.addProduct("Computer", 700, 20);
        warehouse.saveProductList();*/

        while (!quit) 
        {
            back = false;

            System.out.println("What kind of user is accessing the system?");
            System.out.println("1. Client");
            System.out.println("2. Manager");
            System.out.println("3. Salesperson");
            System.out.println("4. Quit");

            System.out.print("-->");
            input = scanner.next();
            System.out.println("");

            input = input.toUpperCase();

            switch (input)
            {
                case "1": case "CLIENT":
                    while (!back)
                    {
                        userType = UserType.CLIENT;

                        System.out.print("Username: ");
                        user = scanner.next();

                        System.out.print("Password: ");
                        pass = scanner.next();

                        if (loginVerify(userType, user, pass))
                        {
                            while (!back)
                            {
                                clientHelp();

                                System.out.print("-->");
                                input = scanner.next();
                                System.out.println("");

                                back = clientSwitch(input);
                            }
                        }
                    }
                    break;
                case "2": case "MANAGER":
                    while (!back)
                    {
                        userType = UserType.MANAGER;

                        System.out.print("Username: ");
                        user = scanner.next();

                        System.out.print("Password: ");
                        pass = scanner.next();

                        if (loginVerify(userType, user, pass))
                        {
                            while (!back)
                            {
                                // managerHelp() goes here

                                System.out.print("-->");
                                input = scanner.next();
                                System.out.println("");

                                // back = managerSwitch(input); goes here
                            }
                        }
                    }
                    break;
                case "3": case "SALESPERSON":
                    while (!back)
                    {
                        userType = UserType.SALESPERSON;

                        System.out.print("Username: ");
                        user = scanner.next();

                        System.out.print("Password: ");
                        pass = scanner.next();

                        while (!back)
                        {
                            clerkHelp();
							
                            System.out.print("-->");
                            input = scanner.next();
                            System.out.println("");

                            back = clerkSwitch();
                        }
                    }
                    break;
                case "4": case "QUIT":
                    quit = true;
                    break;
            }
        }
    }
}