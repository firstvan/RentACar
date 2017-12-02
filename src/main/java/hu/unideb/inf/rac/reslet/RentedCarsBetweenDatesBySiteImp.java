package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RentedCarsBetweenDatesBySiteImp extends ServerResource implements RentedCarsBetweenDatesBySite {
    @Override
    public Representation getCars() {
        Representation rep = null;
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            String id = (String) getRequest().getAttributes().get("siteId");
            id = id.equals("0") ? "" : id;
            String from = (String) getRequest().getAttributes().get("from");
            from = from == null? "1970-01-01" : from;
            String to = (String) getRequest().getAttributes().get("to");
            to = to == null ? formatter.format(now) : to;
            HashMap<String, Object> params = new HashMap<>();
            params.put("eSiteId", id);
            params.put("eFrom", from);
            params.put("eTo", to);

            String query = "declare variable $eFrom as xs:date external;" +
                    "declare variable $eTo as xs:date external;" +
                    "declare variable $eSiteId as xs:string external;" +
                    "let $getSites := function($site) {" +
                    "   for $a in (1)" +
                    "    return if ($site eq \"\") then" +
                    "              doc('rentacardb')/company/sites/site/rents/rent" +
                    "           else" +
                    "              doc('rentacardb')/company/sites/site[@id eq $site]/rents/rent" +
                    "}" +
                    "let $getCars := function($from, $to, $siteId) {" +
                    "      for $rent in $getSites($siteId)" +
                    "      where ($rent/from > $from and $rent/to < $to)" +
                    "      order by $rent/from" +
                    "      return for $car in doc('rentacardb')/company/cars/car" +
                    "             where $car/@id = $rent/car/@idrefs" +
                    "             return element { \"result\"} {($rent/from, $rent/to, $car) }" +
                    "}"+
                    "return element {\"results\"} {$getCars($eFrom, $eTo, $eSiteId)}";

            rep = new StringRepresentation(db.queryWithParam(query, params));
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
