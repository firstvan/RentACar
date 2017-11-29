package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

public class SalesResourceImpl extends ServerResource implements SalesResource {

    @Override
    public Representation getSalesBySite() {
        Representation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            String id = (String) getRequest().getAttributes().get("siteId");
            String query = "xquery element results { for $sp in doc('rentacardb')/company/sites/site[@id=" + id + "]/employee/salesperson/person/@idrefs " +
                           "return doc('rentacardb')/company/persons/person[@id=$sp] }";
            String result = db.query(query);
            rep = new StringRepresentation(result);
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
