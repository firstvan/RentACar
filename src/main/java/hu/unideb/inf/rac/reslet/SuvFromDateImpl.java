package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class SuvFromDateImpl extends ServerResource implements SuvFromDate {
    @Override
    public Representation getSuvFromDate() {
        Representation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            String mfd = (String) getRequest().getAttributes().get("mfd");
            String from = (String) getRequest().getAttributes().get("from");
            from = from == null ? "1970-01-01" : from;
            HashMap<String, Object> params = new HashMap<>();
            params.put("eMfd", mfd);
            params.put("eDate", from);

            String query = "declare variable $eDate as xs:date external;" +
                    "declare variable $eMfd as xs:integer external;" +
                    "let $getCarsCount := function($date, $mfd) {" +
                    "  element { \"result\" } {count(let $basePath := doc('rentacardb')/company" +
                    "      for $suv in $basePath/sites/site/rentableCars/suvs/*/vehicles/car," +
                    "          $rented in $basePath/sites/site/rents/rent," +
                    "          $car in $basePath/cars/car" +
                    "      where $suv/@idrefs = $rented/car/@idrefs and $rented/from > $date and " +
                    "            $suv/@idrefs = $car/@id and $car/mfd > $mfd" +
                    "      return $suv)}" +
                    "}" +
                    "return element { \"results\" } {$getCarsCount($eDate, $eMfd)}";

            rep = new StringRepresentation(db.queryWithParam(query, params));
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
