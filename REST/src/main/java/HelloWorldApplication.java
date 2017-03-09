package main.java;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Created by Dodo on 09.03.2017.
 */
public class HelloWorldApplication extends Application {
    @Override
    public Restlet getInboundRoot() {
        Router router = new Router(getContext());

        router.attach("/hello/{id}", HelloWorldResource.class);

        return router;
    }
}
