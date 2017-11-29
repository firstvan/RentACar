package hu.unideb.inf.rac.reslet;

import hu.unideb.inf.rac.controller.FileLoader;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

public class IndexResourceImpl extends ServerResource implements IndexResource {
    @Override
    public Representation getIndex() {
        StringRepresentation stringRepresentation = null;
        try {
            String index = FileLoader.getFile("index.html");
            stringRepresentation = new StringRepresentation(index);
            stringRepresentation.setMediaType(MediaType.TEXT_HTML);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringRepresentation;
    }
}
