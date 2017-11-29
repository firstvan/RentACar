package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.jaxb.JAXBUtil;
import hu.unideb.inf.rac.dao.FirstBaseXDB;
import hu.unideb.inf.rac.model.Car;
import org.restlet.resource.ServerResource;

public class CarResourceImpl extends ServerResource implements CarResource {

    @Override
    public Car retive() {
        Car car = null;
        try {
            String id = (String) getRequest().getAttributes().get("id");
            FirstBaseXDB database = FirstBaseXDB.getInstance();
            String query = "xquery doc('rentacardb')/company/cars/car[@id=" + id + "]";
            String result = database.query(query);
            car = JAXBUtil.objectFromString(Car.class, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }


}
