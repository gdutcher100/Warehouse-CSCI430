import java.util.*;
import java.io.*;

public class SupplierList implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Supplier> suppliers = new LinkedList();
    private static SupplierList supplierList;

    private SupplierList()
    {

    }

    public static SupplierList instance()
    {
        if (supplierList == null)
        {
            return (supplierList = new SupplierList());
        } else {
            return supplierList;
        }
    }

    // Edits the supplier information
    public void editSupplier(String id)
    {
        boolean found = false;

        for (int i = 0; i < suppliers.size(); i++)
        {
            if (suppliers.get(i).getID().equals(id))
            {
                String input;
                Scanner scanner = new Scanner(System.in);

                suppliers.get(i).toString();

                System.out.println("Input new ID for supplier: ");
                System.out.print("-->");
                input = scanner.nextLine();
                suppliers.get(i).setSupplierID(input);

                System.out.println("Input new name for supplier: ");
                System.out.print("-->");
                input = scanner.nextLine();
                suppliers.get(i).setSupplierName(input);
                found = true;
            }
        }

        if (found == false)
        {
            System.out.println("Supplier not found.");
        }
    }

    // Inserts supplier in list
    public boolean insertSupplier(Supplier supplier) 
    {
        suppliers.add(supplier);
        return true;
    }

    // Iterates through supplier list
    public Iterator<Supplier> getSuppliers()
    {
        return suppliers.iterator();
    }

    public String toString() 
    {
    return suppliers.toString();
    }
}