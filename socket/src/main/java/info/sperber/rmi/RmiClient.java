package info.sperber.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Dodo on 09.03.2017.
 */
public class RmiClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        ChatServerInterface csi = (ChatServerInterface)registry.lookup("ChatServer");
        csi.addMessage("Hola!");
    }
}
