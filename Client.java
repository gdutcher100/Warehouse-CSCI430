import java.util.*;
import java.io.*;

public class Client implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String clientID;
    private String clientName;
    private ShoppingCart shoppingCart = new ShoppingCart();
    private ShoppingCart order = new ShoppingCart();
    private List<String> invoice = new LinkedList<String>();

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

    public Client(String clientID, String clientName)
    {
        this.clientID = clientID;
        this.clientName = clientName;
    }

    public void setClientID(String clientID)
    {
        this.clientID = clientID;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getID()
    {
        return clientID;
    }

    public String getName()
    {
        return clientName;
    }

    public String toString()
    {
        return "ID: " + clientID + " Name: " + clientName;
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

    private float getTotalCost()
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
}