package info.sperber.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Dodo on 09.03.2017.
 */
public interface ChatServerInterface extends Remote {
    public void addMessage(String message) throws RemoteException;

    public List<String> pollMessages(int fromPosition) throws RemoteException;
}
