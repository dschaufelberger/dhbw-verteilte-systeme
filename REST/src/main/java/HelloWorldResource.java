package main.java;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import java.io.IOException;

/**
 * Created by Dodo on 09.03.2017.
 */
public class HelloWorldResource extends ServerResource {

    @Get
    public Representation helloWorld() {
        World world = new World("hallo JSON Welt");
        return new JacksonRepresentation<World>(world);
    }

    @Put
    public void update(Representation representation) throws IOException {
        if(getRequestAttributes().containsKey("id")) {
            System.out.println("id is " + getRequestAttributes().get("id"));
        }

        JacksonRepresentation<World> worldRepresentation = new JacksonRepresentation<World>(representation, World.class);
        World world = worldRepresentation.getObject();
        System.out.println("world has message " + world.message);
    }
}
