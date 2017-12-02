package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class InsertRentImpl extends ServerResource implements InsertRent {
    @Override
    public Representation insertRent() {
        Representation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            HashMap<String, Object> params = new HashMap<>();

            String query = "let $getSiteByName := function($nev) {" +
                    "   for $site in doc('rentacardb')/company/sites/site" +
                    "   where $site/address/city = $nev" +
                    "   return $site" +
                    "}" +
                    "let $getNextRentId := function() {" +
                    "  max(for $id in doc('rentacardb')/company/sites/site/rents/rent/id/text()" +
                    "  return xs:integer($id)) + 1" +
                    "}" +
                    "let $getPersonId := function($licNum) {" +
                    "  doc('rentacardb')/company/persons/person[driverLicense eq $licNum]/@id" +
                    "}" +
                    "let $getCar := function($licPlateNum) {" +
                    "  doc('rentacardb')/company/cars/car[licensePlateNumber eq $licPlateNum]/@id" +
                    "}" +
                    "return insert node element rent {" +
                    "  element id { $getNextRentId() }," +
                    "  element person { attribute idrefs {$getPersonId(\"MJ027225\")} }," +
                    "  element from { \"2017-11-10\" }," +
                    "  element to { \"2017-12-02\" }," +
                    "  element distanceUnit { \"Metric\" }," +
                    "  element car { attribute idrefs { $getCar(\"XJN-961\") } }" +
                    "} into $getSiteByName('Budapest')/rents";
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
