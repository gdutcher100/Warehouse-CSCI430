import java.util.*;
import java.io.*;

public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String productID;
	private String productName;
	private static final String PRODUCT_STRING = "P";
	private int quantity;
	private float buyPrice;
	private int currentStock;
	private List<SoldBy> sellingSuppliers = new LinkedList<SoldBy>();
	private List<Waitlist> waitlistedClients = new LinkedList<Waitlist>();

	
	public Product(String name, float buyPrice, int currentStock)
	{
		productID = PRODUCT_STRING + (IDServer.instance()).getID();
		this.productName = name;
		this.buyPrice = buyPrice;
		this.currentStock = currentStock;
		this.quantity = 0;
	}
	
	public void setProductName(String newName)
	{
		this.productName = newName;
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
	
	public boolean addToSellingSuppliers(SoldBy s) {
		return sellingSuppliers.add(s);
	}
	
	public Iterator<SoldBy> getSellers() {
		return sellingSuppliers.iterator();
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
		return ("ID: " + productID + " Name: " + productName + " Buy Price: " + 
		buyPrice + " Current Stock: " + currentStock);
	}
}