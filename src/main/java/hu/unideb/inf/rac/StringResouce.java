package hu.unideb.inf.rac;

import org.restlet.resource.Get;

public interface StringResouce {
    @Get
    String addCar();
}
