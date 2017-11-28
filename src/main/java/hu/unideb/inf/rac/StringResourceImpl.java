package hu.unideb.inf.rac;

import hu.unideb.inf.jaxb.JAXBUtil;
import hu.unideb.inf.rac.dao.FirstBaseXDB;
import hu.unideb.inf.rac.model.Car;
import org.restlet.resource.ServerResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class StringResourceImpl extends ServerResource implements StringResouce {
    @Override
    public String addCar() {
        String result = null;
        Car car = new Car();
        car.setBrand("Opel");
        car.setCategory("SUV");
        car.setColor("Pink");
        car.setFuel("Diesel");
        car.setType("Mokka");
        car.setId(1010);
        car.setLicensePlateNumber("NNP-101");
        car.setVintage("2015");
        car.setEngine("1.8");
        try {
            FirstBaseXDB database = FirstBaseXDB.getInstance();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JAXBUtil.toXML(car, outputStream);
            database.addElement(new ByteArrayInputStream(outputStream.toByteArray()));
            //String query = "xquery doc('rentacardb')/company/cars/car";
            //result = database.query(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
