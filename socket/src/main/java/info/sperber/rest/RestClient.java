package info.sperber.rest;

import info.sperber.rest.Message;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/**
 * Created by Dodo on 09.03.2017.
 */
public class RestClient {

    public static void main(String[] args) {
        Message Message = new Message("Kassandra", "hallo dominik");

        Representation representation = new JacksonRepresentation<Message>(Message);
        new ClientResource("http://127.0.0.1:8085/rest/message").post(representation);
    }
}
