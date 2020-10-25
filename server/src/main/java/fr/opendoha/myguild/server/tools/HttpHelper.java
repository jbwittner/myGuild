package fr.opendoha.myguild.server.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of IHttpHelper
 */
@Component
public class HttpHelper implements IHttpHelper {

    protected final Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    protected RestTemplate restTemplate;

    @Value("${application.blizzard.wow.delay-ms}")
    protected final long DELAY_CALL_MS_BLIZZARD = 11;

    /**
     * Constructor
     */
    public HttpHelper(){
        this.restTemplate = new RestTemplate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getUrl(final String uri) throws MalformedURLException {
        return new URL(uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getForObject(final String url, final Class<T> responseType, final Object... uriVariables) {

        try {
            Thread.sleep(DELAY_CALL_MS_BLIZZARD);
        } catch (InterruptedException e) {
            this.logger.error("Error during the sleep");
        }

        final String urlDecoded = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);

        this.logger.info("Call urlDecoded : " + urlDecoded);

        final T getObject = this.restTemplate.getForObject(urlDecoded, responseType, uriVariables);

        return getObject;

    }

}