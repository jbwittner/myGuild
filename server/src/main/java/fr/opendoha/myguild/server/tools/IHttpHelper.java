package fr.opendoha.myguild.server.tools;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Helper for http request
 */
public interface IHttpHelper {

    /**
     * Generate url with a uri
     * @param uri 
     * @return URL
     * @throws IOException
     */
    URL getUrl(final String uri) throws MalformedURLException;

    /**
     * Method to make a http get
     * @param url Url who want to call
     * @param responseType Type of DTO
     * @param uriVariables Url variables
     * @return Object with the response of the call
     */
    <T> T getForObject(final String url, final Class<T> responseType, final Object... uriVariables);
    
}
