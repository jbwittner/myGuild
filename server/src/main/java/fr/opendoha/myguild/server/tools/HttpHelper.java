package fr.opendoha.myguild.server.tools;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of IHttpHelper
 */
@Component
public class HttpHelper implements IHttpHelper {

    protected Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    protected RestTemplate restTemplate = new RestTemplate();

    @Value("${application.blizzard.wow.delay-ms}")
    protected long DELAY_CALL_MS_BLIZZARD = 11;

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getUrl(final String uri) throws MalformedURLException{
        return new URL(uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getForObject(final String url, final Class<T> responseType, final Object... uriVariables) {

        this.logger.info("Call : " + url);

        try{
            Thread.sleep(DELAY_CALL_MS_BLIZZARD);
        }catch(InterruptedException e){
            this.logger.error("Error during the sleep");
        }
        
        final T getObject = this.restTemplate.getForObject(url, responseType, uriVariables);

        return getObject;

    }

}