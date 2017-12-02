package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.basex.api.client.ClientQuery;
import org.basex.query.QueryProcessor;
import org.basex.core.Context;
import org.basex.query.value.Value;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class SalesResourceImpl extends ServerResource implements SalesResource {

    @Override
    public Representation getSalesBySite() {
        Representation rep = null;
        try {
            FirstBaseXDB db = FirstBaseXDB.getInstance();
            String id = (String) getRequest().getAttributes().get("siteId");
            HashMap<String, Object> params = new HashMap<>();
            params.put("id", id);

            String query = "declare variable $id as xs:string external; " +
                    "element results { for $sp in doc('rentacardb')/company/sites/site[@id=$id]/employee/salesperson/person/@idrefs " +
                    "return doc('rentacardb')/company/persons/person[@id=$sp] }";

            rep = new StringRepresentation(db.queryWithParam(query, params));
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
