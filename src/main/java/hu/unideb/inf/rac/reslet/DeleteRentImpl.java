package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class DeleteRentImpl extends ServerResource implements DeleteRent {
    @Override
    public Representation deleteRent() {
        Representation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            HashMap<String, Object> params = new HashMap<>();

            String query = "let $deleteRent := function() {" +
                    "  for $person in doc('rentacardb')/company/persons/person" +
                    "  where $person/name eq \"Suzi Murford\"" +
                    "  return for $site in doc('rentacardb')/company/sites/site" +
                    "  where $site/address/city eq \"Budapest\"" +
                    "  return for $rent in $site/rents/rent" +
                    "  where $rent/person/@idrefs = $person/@id and $rent/from=\"2016-12-16\" and $rent/to=\"2017-10-12\"" +
                    "  return $rent" +
                    "}" +
                    "return delete node $deleteRent()";
            db.queryWithParam(query, params);

            String getQuery = "doc('rentacardb')/company/sites/site[1]/rents/rent/parent::node()";

            rep = new StringRepresentation(db.queryWithParam(getQuery, params));
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
