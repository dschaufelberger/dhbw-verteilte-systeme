package main.java;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class RestServer {
    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8085);
        component.getDefaultHost().attach("/rest", new HelloWorldApplication());
        component.start();
    }
}