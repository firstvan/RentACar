package hu.unideb.inf.rac;

import org.restlet.resource.ServerResource;

public class StringResourceImpl extends ServerResource implements StringResouce {
    @Override
    public String addCar() {
        return "Add";
    }
}
