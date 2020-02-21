import java.util.*;
import java.io.*;

public class IDServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCounter;
	private static IDServer server;
	private IDServer() {
		idCounter = 1;
	}
	public static IDServer instance() {
		if (server == null) {
			return (server = new IDServer());
		} else {
			return server;
		}
	}
	public int getID() {
		return idCounter++;
	}
	public String toString() {
		return ("IdServer" + idCounter);
	}
	public static void retrieve(ObjectInputStream input) throws IOException {
		try {
			server = (IDServer) input.readObject();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		try {
			input.defaultReadObject();
			if (server == null) {
				server = (IDServer) input.readObject();
			} else {
				input.readObject();
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}