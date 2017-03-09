package info.sperber.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * Created by Dodo on 09.03.2017.
 */
public class ChatRestApp extends Application {
    @Override
    public Restlet getInboundRoot() {
            Router router = new Router(getContext());

            router.attach("/message", MessageRessource.class);

            return router;
    }
}
