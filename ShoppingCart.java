import java.io.Serializable;
import java.util.*;

public class ShoppingCart implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Product> inCart = new LinkedList<Product>();

    // NULL - The customer has not ordered the contents of the shopping cart
    // IN_PROGRESS - The customer's order is ready to be accepted or denied
    // ACCEPTED - The customer's order has been accepted and now ready to be processed
    // PROCESSED - The customer's order has been proccessed
    public enum Status
    {
        NULL, IN_PROGRESS, ACCEPTED, PROCESSED;
    }

    Status status;

    public ShoppingCart()
    {
        this.status = Status.NULL;
    }

    public boolean insertProduct(Product product, int quantity)
    {
        product.setQuantity(quantity);
        inCart.add(product);
        return true;
    }

    public int getSize()
    {
        return inCart.size();
    }

    public String getProductID(int i)
    {
        return inCart.get(i).getID();
    }

    public String getProductName(int i)
    {
        return inCart.get(i).getName();
    }

    public float getBuyPrice(int i)
    {
        return inCart.get(i).getBuyPrice();
    }

    public int getCurrentStock(int i)
    {
        return inCart.get(i).getCurrentStock();
    }

    public int getQuantity(int i)
    {
        return inCart.get(i).getQuantity();
    }

    public boolean verifyProductExists(String productID)
    {
        for (int i = 0; i < inCart.size(); i++)
        {
            if (productID.equals(inCart.get(i).getID()))
            {
                return true;
            }
        }

        return false;
    }

    public void setQuantity(String productID, int quantity)
    {
        for (int i = 0; i < inCart.size(); i++)
        {
            if (productID.equals(inCart.get(i).getID()))
            {
                inCart.get(i).setQuantity(quantity);
            }
        }
    }

    public void clearCart()
    {
        inCart.clear();
    }

    public Iterator<Product> getCartContents()
    {
        return inCart.iterator();
    }

    public List<Product> getCartList()
    {
        return inCart;
    }

    public String toString()
    {
        return inCart.toString();
    }

    public String getStatus()
    {
        return status.toString();
    }

    public void setStatus(Status s)
    {
        this.status = s;
    }

    public void placeOrder()
    {
        if (status.toString() == "NULL")
            this.status = Status.IN_PROGRESS;
    }

    
    public void acceptOrder()
    {
        if (status.toString() == "IN_PROGRESS")
            this.status = Status.ACCEPTED;
    }

    public void removeFromShoppingCart(String productID)
    {
        for (int i = 0; i < inCart.size(); i++)
        {
            if (productID.equals(inCart.get(i).getID()))
            {
                inCart.remove(i);
            }
        }
    }
}