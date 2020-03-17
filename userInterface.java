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

    private static void placeOrder()
    {
        Scanner scanner = new Scanner(System.in);

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
        Scanner scanner = new Scanner(System.in);

        try
        {
            warehouse.trackClientOrder(clientID);
        } catch (NullPointerException npe)
        {
            System.out.println("Could not find client.");
        }
    }

    private static void viewClientInvoice()
    {
        Scanner scanner = new Scanner(System.in);

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
        System.out.println("7. Logout");
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

    public static void main(String[] s) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean back = false;
        String input;

        String user;
        String pass;

        warehouse.addClient("C1", "100");

        warehouse.addProduct("P1", 30, 4);

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
                            // salespersonHelp() goes here

                            System.out.print("-->");
                            input = scanner.next();
                            System.out.println("");

                            // back = salespersonSwitch(input); goes here
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