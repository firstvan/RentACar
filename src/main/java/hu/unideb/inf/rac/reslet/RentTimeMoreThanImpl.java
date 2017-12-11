package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class RentTimeMoreThanImpl extends ServerResource implements RentTimeMoreThan{
    @Override
    public Representation rentTimeMoreThan() {
        StringRepresentation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            HashMap<String, Object> params = new HashMap<>();

            String query = "let $familyCarId := (for $car in doc('rentacardb')/company/cars/car where $car/category='familycar' return $car/@id) where (xs:date($rent/to) - xs:date($rent/from))<xs:dayTimeDuration(\"P100D\") and $rent/car/@idrefs = $familyCarId return $rent";

            String result = db.queryWithParam(query, params);
            rep = new StringRepresentation(result);
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
