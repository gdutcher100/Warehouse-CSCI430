import java.util.*;
import java.io.*;

public class Client implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final String CLIENT_STRING = "C";
    private String clientID;
    private String clientName;
	private String balance;
    private ShoppingCart shoppingCart = new ShoppingCart();
    private ShoppingCart order = new ShoppingCart();
    private List<String> invoice = new LinkedList<String>();
	private List<Waitlist> waitlistedProducts = new LinkedList<Waitlist>();

    private static Object deepCopy(Object object) 
    {
        try {
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
          outputStrm.writeObject(object);
          ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
          ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
          return objInputStream.readObject();
        }
        catch (Exception e) {
          e.printStackTrace();
          return null;
        }
    }

    public Client(String clientName, String balance)
    {
        clientID = CLIENT_STRING + (IDServer.instance()).getID();
        this.clientName = clientName;
		this.balance = balance;
    }

    public void setClientID(String clientID)
    {
        this.clientID = clientID;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }
	
	public void addToBalance(String charge)
    {
        balance = charge;
    }

    public String getID()
    {
        return clientID;
    }

    public String getName()
    {
        return clientName;
    }
	
	public String getClientBalance()
    {
        return balance;
    }

    public String toString()
    {
        return "ID: " + clientID + " Name: " + clientName + " Balance: " + balance;
    }
	
	public boolean removeWaitlist(String productID)
	{
		Iterator<Waitlist> itr = waitlistedProducts.iterator();
		String wlID;
		while (itr.hasNext()) {
			Waitlist curWaitlist = itr.next();
			wlID = curWaitlist.getProduct().getID();
			if (wlID == productID) {
				itr.remove();
				return true;
			}
		}
		return false;
	}

    public void addToCart(Product product, int quantity)
    {
        shoppingCart.insertProduct(product, quantity);
    }

    public Iterator<Product> getCartContents()
    {
        return shoppingCart.getCartContents();
    }

    public Iterator<Product> getOrderContents()
    {
        return order.getCartContents();
    }

    public ShoppingCart getShoppingCart()
    {
        return shoppingCart;
    }

    public ShoppingCart getOrders()
    {
        return order;
    }

    public void placeOrder()
    {
        order = (ShoppingCart) deepCopy(shoppingCart);
        order.placeOrder();
        shoppingCart.clearCart();
    }

    public void acceptOrder()
    {
        order.acceptOrder();
    }
	
	//add to waitlist
	public boolean addToWaitlist(Waitlist wlo)
	{
		return waitlistedProducts.add(wlo);
	}
	
	//get waitlist
	public Iterator<Waitlist> getWaitlistedProducts()
	{
		return waitlistedProducts.iterator();
	}
	
	//search on waitlist
	public Waitlist searchWaitListOnProduct(Product p)
	{
		Iterator<Waitlist> iterator = waitlistedProducts.iterator();
		Waitlist w;
		while (iterator.hasNext())
		{
			w = iterator.next();
			if (w.getProduct() == p)
			{
				return w;
			}
		}
		return null;
	}

    public float getTotalCost()
    {
        float totalCost = 0f;

        for (int i = 0; i < order.getCartList().size(); i++)
        {
            totalCost += order.getBuyPrice(i) * order.getQuantity(i);
        }

        return totalCost;
    }

    public void generateInvoice()
    {
        String invoiceString = "";

        for (int i = 0; i < order.getCartList().size(); i++)
        {
            invoiceString += 
            (
                "Product ID: "        + order.getProductID(i) +
                " | Product Name: "     + order.getProductName(i) +
                " | Product Price: "    + order.getBuyPrice(i) + 
                " | Product Quantity: " + order.getQuantity(i) + "\n"
            );
        }

        invoiceString += "-------------------------------\nTotal Cost: $" + getTotalCost() + "\n";

        invoice.add(invoiceString);
    }

    public List<String> getInvoiceList()
    {
        return invoice;
    }

    public void editShoppingCart(int quantity)
    {
       // shoppingCart
    }
	
	public String updateBalance()
	{
		this.balance = balance;
		
		for (int i = 0; i < order.getCartList().size(); i++)
        {
            balance += getTotalCost();
        }
		return balance;
	}
	
	public Boolean makePayment(String payment)
	{
		//this.balance = balance;
		//float bal = getTotalCost();
		float bal = Float.parseFloat(balance);
		float pay = Float.parseFloat(payment);
		if(pay <= bal)
		{
			bal -= pay;
			balance = String.valueOf(bal);
			return true;
		}
		
		else
			return false;
	}
}