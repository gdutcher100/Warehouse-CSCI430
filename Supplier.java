import java.util.*;
import java.io.*;

public class Supplier implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final String SUPPLIER_STRING = "S";
    private String supplierID;
    private String supplierName;
    private List<Product> sellsProduct = new LinkedList<Product>();

    public Supplier(String supplierName)
    {
        supplierID = SUPPLIER_STRING + (IDServer.instance()).getID();
        this.supplierName = supplierName;
    }

    public void setSupplierID(String supplierID)
    {
        this.supplierID = supplierID;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getID()
    {
        return supplierID;
    }

    public String getName()
    {
        return supplierName;
    }

    public String toString()
    {
        return "ID: " + supplierID + " Name: " + supplierName;
    }
}