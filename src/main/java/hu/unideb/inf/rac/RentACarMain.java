package hu.unideb.inf.rac;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import hu.unideb.inf.rac.reslet.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

import org.restlet.routing.Router;

public class RentACarMain extends Application {

    private static FirstBaseXDB firstBaseXDB;

    public static void main(String[] args) throws Exception {
        new Server(Protocol.HTTP, 8888, new RentACarMain()).start();
        firstBaseXDB = FirstBaseXDB.getInstance();
        //initDB();
        reinitDB();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.setDefaultMatchingQuery(true);
        router.attach("http://localhost:8888/", IndexResourceImpl.class);
        router.attach("http://localhost:8888/cars", CarResourceImpl.class);
        router.attach("http://localhost:8888/car/{id}", CarResourceImpl.class);
        router.attach("http://localhost:8888/salesBySite/{siteId}", SalesResourceImpl.class);
        router.attach("http://localhost:8888/carsbyrentdate?siteid={siteId}&from={from}&to={to}", RentedCarsBetweenDatesBySiteImp.class);
        router.attach("http://localhost:8888/suvFromDate?mfd={mfd}&from={from}", SuvFromDateImpl.class);
        router.attach("http://localhost:8888/deleteRent", DeleteRentImpl.class);
        router.attach("http://localhost:8888/addARent", InsertRentImpl.class);
        router.attach("http://localhost:8888/updateDriverLicense", UpdateDriverLicenseImpl.class);
        router.attach("http://localhost:8888/averageDistance", AverageDistanceImpl.class);
        router.attach("http://localhost:8888/sameSiteHatchbacks", SameSiteHatchbacksImpl.class);
        return router;
    }

    private static void initDB() throws Exception {
        firstBaseXDB.createDb();
    }

    private static void reinitDB() throws Exception {
        firstBaseXDB.deleteDB();
        firstBaseXDB.createDb();
    }
}
