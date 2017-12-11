package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class SameSiteHatchbacksImpl extends ServerResource implements SameSiteHatchbacks{
    @Override
    public Representation sameSiteHatchbacks() {
        StringRepresentation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            HashMap<String, Object> params = new HashMap<>();

            String query = "element results { let $madelenaId := (for $x in  doc('rentacardb')/company/persons/person where $x/name = 'Madelena Farrant' return $x/@id) let $carId := (for $site in  doc('rentacardb')/company/sites/site where $site/employee/salesperson/person/@idrefs=$madelenaId return $site/rentableCars/hatchbacks/petrol/vehicles/car/@idrefs) for $car in  doc('rentacardb')/company/cars/car where $car/@id=$carId return $car }";

            String result = db.queryWithParam(query, params);
            rep = new StringRepresentation(result);
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
