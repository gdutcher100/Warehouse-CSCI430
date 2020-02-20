import java.util.*;
import java.io.*;

public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String productID;
	private String productName;
	private List<Supplier> suppliedBy = new LinkedList<Supplier>();
	private float buyPrice;
	private List<Float> salePrice;
	private int currentStock;
	private int quantity;
	private List<Waitlist> waitlistedClients = new LinkedList<Waitlist>();

	
	public Product(String id, String name, float buyPrice, int currentStock)
	{
		this.productID = id;
		this.productName = name;
		this.buyPrice = buyPrice;
		this.currentStock = currentStock;
		this.quantity = 0;
	}
	
	public void setProductName(String newName)
	{
		this.productName = newName;
	}
	
	public void setProductID(String newID)
	{
		this.productID = newID;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public String getID()
	{
		return productID;
	}
	
	public String getName()
	{
		return productName;
	}

	public int getQuantity()
	{
		return quantity;
	}
	
	//waitlist
	public Iterator<Waitlist> getWaitlistedClients()
    {
      return waitlistedClients.iterator();
    }
	
	public boolean addToWaitlist(Waitlist w){
      return waitlistedClients.add(w);
  	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public int getCurrentStock() {
		return currentStock;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public String toString()
	{
		return "ID: " + productID + " Name: " + productName + " Buy Price: " + 
		buyPrice + " Current Stock: " + currentStock + " Quantity: " + quantity;
	}
}