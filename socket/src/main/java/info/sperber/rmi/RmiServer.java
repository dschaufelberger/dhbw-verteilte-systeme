package info.sperber.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Dodo on 09.03.2017.
 */
public class RmiServer {

    public void start() throws RemoteException {
        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        ChatServer chatServer = new ChatServer();

        ChatServerInterface stub = (ChatServerInterface) UnicastRemoteObject.exportObject(chatServer, 0);

        RemoteServer.setLog(System.out);

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("ChatServer", stub);

        System.out.println("Chatserver angemeldet");
    }
}
