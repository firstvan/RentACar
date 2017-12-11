package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class UpdateDriverLicenseImpl extends ServerResource implements UpdateDriverLicense {
    @Override
    public Representation updateDriverLicense() {
        StringRepresentation rep = null;
        try {
            FirstBaseXDB database = FirstBaseXDB.getInstance();
            HashMap<String, Object> params = new HashMap<>();

            String query = "declare variable $cityName as xs:string:='Kuala Lumpur'; element results { let $person := doc('rentacardb')/company/persons/person let $old := $person/address[city=$cityName]/../driverLicense  let $new := concat('KL',substring($old, 3)) return replace value of node $old with $new }";

            String result = database.queryWithParam(query, params);
            rep = new StringRepresentation(result);
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
