import java.util.*;
import java.io.*;

public class SupplierList implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Supplier> suppliers = new LinkedList();
    private static SupplierList supplierList;

    public SupplierList()
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

    // Inserts supplier in list
    public boolean insertSupplier(Supplier supplier) 
    {
        suppliers.add(supplier);
        return true;
    }

    // Returns the specified supplier by ID
    public Supplier getSupplier(String supplierID)
    {
        for (int i = 0; i < suppliers.size(); i++)
        {
            if (suppliers.get(i).getID().equals(supplierID))
            {
                return suppliers.get(i);
            }
        }
        return null;
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