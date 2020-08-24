package fr.jbwittner.myguild.server.tools;

import java.io.IOException;
import java.net.URL;

public class HttpHelper {

    public HttpHelper() {
    }

    public URL getUrl(String uri) throws IOException{
        return new URL(uri);
    }

}