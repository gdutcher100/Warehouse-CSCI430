import java.util.*;

public class userInterface 
{	
    static Warehouse warehouse = new Warehouse();

    private static void editSupplier()
    {
        String oldID;
        String newID;
        String newName;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input old ID of the supplier you want to edit: ");
        System.out.print("-->");
        oldID = scanner.nextLine();

        System.out.println("Input new ID for supplier: ");
        System.out.print("-->");
        newID = scanner.nextLine();

        System.out.println("Input new name for supplier: ");
        System.out.print("-->");
        newName = scanner.nextLine();

        warehouse.editSupplier(oldID, newID, newName);
    }

    private static void editProduct()
    {
        String oldID;
        String newID;
        String newName;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input old ID of the product you want to edit: ");
        System.out.print("-->");
        oldID = scanner.nextLine();

        System.out.println("Input new ID for product: ");
        System.out.print("-->");
        newID = scanner.nextLine();

        System.out.println("Input new name for product: ");
        System.out.print("-->");
        newName = scanner.nextLine();

        warehouse.editProduct(oldID, newID, newName);
    }

    private static void editClient()
    {
        String oldID;
        String newID;
        String newName;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input old ID of the client you want to edit: ");
        System.out.print("-->");
        oldID = scanner.nextLine();

        System.out.println("Input new ID for client: ");
        System.out.print("-->");
        newID = scanner.nextLine();

        System.out.println("Input new name for client: ");
        System.out.print("-->");
        newName = scanner.nextLine();

        warehouse.editClient(oldID, newID, newName);
    }

    // Adds a supplier to the supplierList
    private static void addSupplier()
    {
        String sName;
        String sID;
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the ID of the supplier?");
        System.out.print("-->");
        input = scanner.next();
        sID = input;

        System.out.println("What is the name of the supplier?");
        System.out.print("-->");
        input = scanner.next();
        sName = input;

        warehouse.addSupplier(sID, sName);
        System.out.println("Supplier " + sID + " has been added.");
    }

    // Add a product to productList
	private static void addProduct()
	{
		String pName;
		String pID;
		String input;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What is the ID of the product?");
        System.out.print("-->");
        input = scanner.nextLine();
        pID = input;
		
		System.out.println("What is the name of the product?");
        System.out.print("-->");
        input = scanner.nextLine();
        pName = input;

        warehouse.addProduct(pID, pName);
        System.out.println("Product " + pID + " has been added.");
    }
    
    // Adds a client into ClientList
    private static void addClient() {
        String cName;
        String cID;
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the ID of the client?");
        System.out.print("-->");
        input = scanner.next();
        cID = input;

        System.out.println("What is the name of the client?");
        System.out.print("-->");
        input = scanner.next();
        cName = input;

        warehouse.addClient(cID, cName);
        System.out.println("Client " + cID + " has been added.");
    }

    private static void loadLists() {
        if (warehouse.yesOrNo("Would you like to load the supplier list? (Y or N)")) {
            warehouse.loadSupplierListToUI();
        } else {
            warehouse.insertSupplier("01", "Supplier1");
            warehouse.insertSupplier("02", "Supplier2");
        }

        if (warehouse.yesOrNo("Would you like to load the product list? (Y or N)")) {
            warehouse.loadProductListToUI();
        } else {
            warehouse.insertProduct("01", "Product1");
            warehouse.insertProduct("02", "Product2");
        }

        if (warehouse.yesOrNo("Would you like to load the client list? (Y or N)")) {
            warehouse.loadClientListToUI();
        } else {
            warehouse.insertClient("01", "Client1");
            warehouse.insertClient("02", "Client2");
        }
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
            System.out.println("8. Save Product List");
            System.out.println("9. Track Client Information");
            System.out.println("10. Edit Client Information");
            System.out.println("11. Add Client");
            System.out.println("12. Save Client List");
            System.out.println("13. Quit");

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
                    warehouse.saveProductList();
                    break;
                case "9":
                    warehouse.trackClientInformation();
                    break;
                case "10":
                    editClient();
                    break;
                case "11":
                    addClient();
				break;
			case "12":
				warehouse.saveClientList();
				break;
            case "13":
                quit = true;
                break;
            }
        }
        scanner.close();
    }
}