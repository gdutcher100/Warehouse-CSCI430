import java.util.*;
import java.io.*;

public class Product implements Serializable
{
	private String productID;
	private String productName;
	private List suppliedBy = new LinkedList();
	
	public Product(String id, String name)
	{
		this.productID = id;
		this.productName = name;
	}
	
	public void setProductName(String newName)
	{
		this.productName = newName;
	}
	
	public void setProductID(String newID)
	{
		this.productID = newID;
	}
	
	public String getID()
	{
		return productID;
	}
	
	public String getName()
	{
		return productName;
	}
	
	public String toString()
	{
		return "ID: " + productID + " Name: " + productName;
	}
}