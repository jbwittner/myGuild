package fr.opendoha.myguild.server.tools;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Helper for http request
 */
public interface IHttpHelper {

    /**
     * Methode to get URL
     * @param uri of the url
     * @return URL
     */
    URL getUrl(final String uri) throws MalformedURLException;

    /**
     * Methode to encapsulate the getForObject from RestTemplate
     * @return object from the get
     */
    <T> T getForObject(final String url, final Class<T> responseType, final Object... uriVariables);

}
