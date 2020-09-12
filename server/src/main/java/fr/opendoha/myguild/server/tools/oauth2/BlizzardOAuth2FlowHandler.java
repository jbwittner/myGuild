package fr.opendoha.myguild.server.tools.oauth2;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.opendoha.myguild.server.tools.oauth2.models.TokenResponse;
import fr.opendoha.myguild.server.tools.HttpHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * {@inheritDoc}
 */
@Service
public class BlizzardOAuth2FlowHandler implements OAuth2FlowHandler {

    Logger logger = LoggerFactory.getLogger(BlizzardOAuth2FlowHandler.class);

    @Value("${spring.security.oauth2.client.registration.oauth-blizard.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.oauth-blizard.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.blizzard.token-uri}")
    private String tockenUrl;

    private static final String ENCODING = "UTF-8";

    private ObjectMapper objectMapper;

    private String token = null;
    private Instant tokenExpiry = null; // Instant when the token will expire

    private final Object tokenLock = new Object();

    private HttpHelper httpHelper;

    /**
     * Constructor of BlizzardOAuth2FlowHandler
     * @param objectMapper ObjectMapper provides functionality for reading and writing JSON
     */
    @Autowired
    public BlizzardOAuth2FlowHandler(
        final ObjectMapper objectMapper,
        final HttpHelper httpHelper) {
        this.objectMapper = objectMapper;
        this.httpHelper = httpHelper;
    }

    /**
     * @param httpHelper the httpHelper to set
     */
    public void setHttpHelper(final HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() throws IOException {
        if(isTokenInvalid()){
            logger.info("Fetching/Creating token.");

            final String encodedCredentials = Base64.getEncoder().encodeToString(String.format("%s:%s", this.clientId, this.clientSecret).getBytes(BlizzardOAuth2FlowHandler.ENCODING));

            // ------------------------------------------------- Allows testing/mocking of the URL connection object
            HttpURLConnection con = null;

            try{
            final URL url = httpHelper.getUrl(this.tockenUrl);

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", String.format("Basic %s", encodedCredentials));
            con.setDoOutput(true);
            con.getOutputStream().write("grant_type=client_credentials".getBytes(BlizzardOAuth2FlowHandler.ENCODING));

            final int responseCode = con.getResponseCode();
            logger.info(String.format("Sent 'POST' to %s requesting access token via client credentials grant type.", url));
            logger.info(String.format("Result code: %s", responseCode));

            final String response = IOUtils.toString(con.getInputStream(), BlizzardOAuth2FlowHandler.ENCODING);

            logger.debug(String.format("Response: %s", response));

            // Reads the JSON response and converts it to TokenResponse class or throws an exception
            final TokenResponse tokenResponse = objectMapper.readValue(response, TokenResponse.class);
            synchronized (tokenLock) {
                tokenExpiry = Instant.now().plusSeconds(tokenResponse.getExpires_in());
                token = tokenResponse.getAccess_token();
            }

            } finally {
                if(con != null){
                    con.disconnect();
                }
            }
        }
        synchronized (tokenLock){
            return token;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTokenInvalid(){
        Boolean value;

        synchronized (tokenLock) {
            if (token == null) {
                value = true;
            }else if (tokenExpiry == null) {
                value = true;
            }else{
                value = Instant.now().isAfter(tokenExpiry);
            }
            return value;
        }
    }

}
