import java.util.*;
import java.io.*;

public class Tester 
{
    public static String getToken(String prompt) 
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

    private static boolean yesOrNo(String prompt) 
    {
            String more = getToken(prompt + " (Y|y)[es] or anything else for no");
            if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
                return false;
            }
                return true;
    }

    // Prints out supplier information
    private static void trackSupplierInformation(SupplierList supplierList)
    {
        System.out.println("List of suppliers:");

        Iterator suppliers = supplierList.getSuppliers();
        while (suppliers.hasNext())
        {
            System.out.println(suppliers.next());
        }
    }

    // Adds a supplier into SupplierList
    private static void addSupplier(SupplierList supplierList)
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

        Supplier sX = new Supplier(sID, sName);
        supplierList.insertSupplier(sX);
        System.out.println("Supplier " + sX.getID() + " has been added.");
    }

    // Loads supplier list from SupplierCatData file
    private static SupplierList loadSupplierList()
    {
        try {
            FileInputStream file = new FileInputStream("SupplierCatData");
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

    // Saves the SupplierList object to SupplierCatData file
    private static void saveSupplierList(SupplierList supplierList)
    {
        try {
            FileOutputStream file = new FileOutputStream("SupplierCatData");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(supplierList);
            output.close();
            System.out.println("Supplier data successfully saved.");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main (String[] s) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        String input;
        SupplierList supplierList;

        if (yesOrNo("Would you like to load the supplier list? (Y or N)"))
        {
            supplierList = loadSupplierList();
        } else 
        {
            supplierList = SupplierList.instance();
            Supplier s1 = new Supplier("01", "Supplier1");
            Supplier s2 = new Supplier("02", "Supplier2");
            supplierList.insertSupplier(s1);
            supplierList.insertSupplier(s2);
        }

        while (!quit)
        {
            System.out.println("\nInput the number of the option you want to choose:");
            System.out.println("1. Track Supplier Information");
            System.out.println("2. Edit Supplier Information");
            System.out.println("3. Add Supplier");
            System.out.println("4. Save Supplier List");
            System.out.println("5. Quit");

            System.out.print("-->");
            input = scanner.next();
            System.out.println("");

            switch(input)
            {
            case "1": 
                trackSupplierInformation(supplierList);
                break;
            case "2":
                System.out.println("Input the ID of the supplier you want to edit:");
                System.out.print("-->");
                input = scanner.next();
                supplierList.editSupplier(input);
                break;
            case "3":
                addSupplier(supplierList);
                break;
            case "4":
                saveSupplierList(supplierList);
                break;
            case "5":
                quit = true;
                break;
            }
        }
        scanner.close();
    }
}