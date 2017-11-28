package hu.unideb.inf.rac;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import hu.unideb.inf.rac.reslet.CarResourceImpl;
import hu.unideb.inf.rac.reslet.CarsReourceImpl;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

import org.restlet.routing.Router;

public class RentACarMain extends Application {

    public static void main(String[] args) throws Exception {
        new Server(Protocol.HTTP, 8888, new RentACarMain()).start();
        //reinitDB();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.setDefaultMatchingQuery(true);
        router.attach("http://localhost:8888/cars", CarsReourceImpl.class);
        router.attach("http://localhost:8888/car", CarResourceImpl.class);
        router.attach("http://localhost:8888/add", StringResourceImpl.class);
        return router;
    }

    private static void reinitDB() throws Exception {
        FirstBaseXDB firstBaseXDB = FirstBaseXDB.getInstance();
        firstBaseXDB.deleteDB();
        firstBaseXDB.createDb();
    }
}
