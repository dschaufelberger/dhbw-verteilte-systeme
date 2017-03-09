package info.sperber;

import info.sperber.entities.Product;
import info.sperber.rest.ChatRestApp;
import info.sperber.rest.Message;
import info.sperber.rmi.RmiServer;
import org.restlet.Component;
import org.restlet.data.Protocol;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static ArrayList<ChatClient> clients = new ArrayList<>();
    public static Storage storage;

    public static void main( String[] args ) throws Exception {
        new RmiServer().start();

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8085);
        component.getDefaultHost().attach("/rest", new ChatRestApp());
        component.start();


        ServerSocket server = new ServerSocket(4322);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("emf");
        storage = new Storage(factory.createEntityManager());

        while (true) {
            Socket client = null;

            try {
                client = server.accept();
                handleConnection(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void UpdateStorage(String name, int value) throws SQLException {
        Product product = new Product(name, value);
        if (storage.contains(product)) {
            storage.insert(product);
        } else {
            storage.update(product);
        }
    }

    public static void forwardMessageToClients(Message message) {
        for (ChatClient client: clients) {
            client.send(message.sender, message.content);
        }
    }

    private static void handleConnection(Socket client) throws IOException {
        ChatClient chatClient = new ChatClient(client);
        clients.add(chatClient);
        new Thread(chatClient).start();
    }

    public static void forwardMessageToClients(String message) {
        forwardMessageToClients(new Message("*anonymous*", message));
    }
}

