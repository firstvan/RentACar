package hu.unideb.inf.rac.dao;

import org.basex.api.client.ClientQuery;
import org.basex.api.client.ClientSession;
import org.basex.core.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class FirstBaseXDB {

    private static FirstBaseXDB instance;

    private ClientSession session;

    private FirstBaseXDB() throws Exception {
        session = new ClientSession("localhost", 1984, "admin", "admin");
    }

    public static FirstBaseXDB getInstance() throws Exception {
        if (instance == null) {
            instance = new FirstBaseXDB();
        }

        return instance;
    }

    public void createDb() throws Exception {
        // define input stream
        ClassLoader classLoader = getClass().getClassLoader();
        final InputStream bais = classLoader.getResourceAsStream("hu/unideb/inf/rac/rent_a_car.xml");

        // create new database
        session.create("rentacardb", bais);
        System.out.println(session.info());

        // run query on database
        System.out.println(session.execute("xquery doc('rentacardb')"));
    }

    public void deleteDB() throws Exception {
        // drop database
        session.execute("drop db rentacardb");
    }

    public String query(final String query) throws IOException {
        return session.execute(query);
    }

    public String queryWithParam(final String query, final Map<String, Object> params) throws Exception {
        try (ClientQuery clientQuery = session.query(query)) {

            for(Map.Entry<String, Object> entry : params.entrySet()) {
                clientQuery.bind(entry.getKey(), entry.getValue());
            }

            return clientQuery.execute();
        }
    }

    public void addElement(InputStream node) throws IOException {
        session.execute("open rentacardb");
        session.add("company/cars ", node);
    }
}
