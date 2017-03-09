package info.sperber.rmi;

import info.sperber.App;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dodo on 09.03.2017.
 */
public class ChatServer implements ChatServerInterface {
    @Override
    public void addMessage(String message) throws RemoteException {
        App.forwardMessageToClients(message);
    }

    @Override
    public List<String> pollMessages(int fromPosition) throws RemoteException {
        return new ArrayList<>();
    }
}
