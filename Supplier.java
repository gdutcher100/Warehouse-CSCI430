import java.util.*;
import java.io.*;

public class Supplier implements Serializable
{
    private String supplierID;
    private String supplierName;
    private List sellsProduct = new LinkedList();

    public Supplier(String supplierID, String supplierName)
    {
        this.supplierID = supplierID;
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