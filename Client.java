import java.util.*;
import java.io.*;

public class Client implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String clientID;
    private String clientName;
    private List<Product> buysProduct = new LinkedList();

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
}