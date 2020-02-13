import java.util.*;
import java.io.*;

public class ClientList implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Client> clients = new LinkedList();
    private static ClientList clientList;

    private ClientList()
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

    // Edits the client information
    public void editClient(String id)
    {
        boolean found = false;

        for (int i = 0; i < clients.size(); i++)
        {
            if (clients.get(i).getID().equals(id))
            {
                String input;
                Scanner scanner = new Scanner(System.in);

                clients.get(i).toString();

                System.out.println("Input new ID for client: ");
                System.out.print("-->");
                input = scanner.nextLine();
                clients.get(i).setClientID(input);

                System.out.println("Input new name for client: ");
                System.out.print("-->");
                input = scanner.nextLine();
                clients.get(i).setClientName(input);
                found = true;
            }
        }

        if (found == false)
        {
            System.out.println("Client not found.");
        }
    }

    // Inserts client in list
    public boolean insertClient(Client client) 
    {
        clients.add(client);
        return true;
    }

    // Iterates through client list
    public Iterator getClients()
    {
        return clients.iterator();
    }

    public String toString() 
    {
    return clients.toString();
    }
}