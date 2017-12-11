package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class AverageDistanceImpl extends ServerResource implements AverageDistance {
    @Override
    public Representation getAverageDistance() {
        Representation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            HashMap<String, Object> params = new HashMap<>();

            String query = "element results { avg(for $r in doc('rentacardb')/company/sites/site/rents/rent for $c in doc('rentacardb')/company/cars/car where $c[category='familycar'] and $c[engine>1.6] and $r/car/@idrefs=$c/@id return $r/distance) }";

            String result = db.queryWithParam(query, params);
            rep = new StringRepresentation(result);
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
