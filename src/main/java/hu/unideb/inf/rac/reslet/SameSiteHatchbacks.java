package hu.unideb.inf.rac.reslet;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface SameSiteHatchbacks {

    @Get
    Representation sameSiteHatchbacks();
}
