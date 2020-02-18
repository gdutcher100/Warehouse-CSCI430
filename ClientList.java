import java.util.*;
import java.io.*;

public class ClientList implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Client> clients = new LinkedList();
    private static ClientList clientList;

    public ClientList()
    {

    }

    public static ClientList instance()
    {
        if (clientList == null)
        {
            return (clientList = new ClientList());
        } 
		else {
            return clientList;
        }
    }

    public Client getClient(String clientID)
    {
        for (int i = 0; i < clients.size(); i++)
        {
            if (clients.get(i).getID().equals(clientID))
            {
                return clients.get(i);
            }
        }
        return null;
    }

    // Inserts client in list
    public boolean insertClient(Client client) 
    {
        clients.add(client);
        return true;
    }

    // Iterates through client list
    public Iterator<Client> getClients()
    {
        return clients.iterator();
    }

    public String toString() 
    {
    return clients.toString();
    }
}