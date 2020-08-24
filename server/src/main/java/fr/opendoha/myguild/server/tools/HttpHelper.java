package fr.opendoha.myguild.server.tools;

import java.io.IOException;
import java.net.URL;

/**
 * Helper for http request
 */
public class HttpHelper {

    /**
     * Generate url with a uri
     * @param uri 
     * @return URL
     * @throws IOException
     */
    public URL getUrl(final String uri) throws IOException{
        return new URL(uri);
    }

}