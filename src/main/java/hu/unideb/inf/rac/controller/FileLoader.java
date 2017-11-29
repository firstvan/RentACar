package hu.unideb.inf.rac.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader {

    public static String getFile(String name) throws IOException, URISyntaxException {
        URL fileUrl = FileLoader.class.getClassLoader().getResource("hu/unideb/inf/rac/" + name);
        byte[] encoded = Files.readAllBytes(Paths.get(fileUrl.toURI()));
        return new String(encoded, Charset.forName("UTF-8"));
    }
}
