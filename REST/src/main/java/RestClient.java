package main.java;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/**
 * Created by Dodo on 09.03.2017.
 */
public class RestClient {

    public static void main(String[] args) {
        World world = new World("Der Client ist toll");

        Representation representation = new JacksonRepresentation<World>(world);
        new ClientResource("http://127.0.0.1:8085/rest/hello/10").put(representation);
    }
}
