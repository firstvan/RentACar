package hu.unideb.inf.rac;

import hu.unideb.inf.rac.model.Car;
import org.restlet.resource.Get;

public interface CarResource {
    @Get
    Car retive();

}
