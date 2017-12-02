package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.dao.FirstBaseXDB;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import java.util.HashMap;

public class CarResourceImpl extends ServerResource implements CarResource {

    @Override
    public Representation retive() {
        StringRepresentation rep = null;
        try {
            String id = (String) getRequest().getAttributes().get("id");
            FirstBaseXDB database = FirstBaseXDB.getInstance();
            HashMap<String, Object> params = new HashMap<>();
            params.put("id", id);

            String query = "declare variable $id as xs:string external; " +
                    "doc('rentacardb')/company/cars/car[@id=$id]";

            String result = database.queryWithParam(query, params);
            rep = new StringRepresentation(result);
            rep.setMediaType(MediaType.APPLICATION_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }


}
