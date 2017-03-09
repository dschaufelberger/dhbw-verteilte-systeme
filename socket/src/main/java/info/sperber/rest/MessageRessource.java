package info.sperber.rest;


import info.sperber.App;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;

/**
 * Created by Dodo on 09.03.2017.
 */
public class MessageRessource extends ServerResource {
    
    
    
    @Post
    public void send(Representation representation) throws IOException {
        JacksonRepresentation<Message> messageRepresentation = new JacksonRepresentation<Message>(representation, Message.class);
        Message message = messageRepresentation.getObject();

        App.forwardMessageToClients(message);
    }

}
