package hu.unideb.inf.rac;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

import org.restlet.routing.Router;

public class RentACarMain extends Application {

    public static void main(String[] args) throws Exception {
        new Server(Protocol.HTTP, 8888, new RentACarMain()).start();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.setDefaultMatchingQuery(true);
        router.attach("http://localhost:8888/myquery", FirstQuery.class);
        router.attach("http://localhost:8888/add", StringResourceImpl.class);
        return router;
    }

}
