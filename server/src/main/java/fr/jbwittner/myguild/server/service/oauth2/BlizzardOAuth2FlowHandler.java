package fr.jbwittner.myguild.server.service.oauth2;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.jbwittner.myguild.server.model.TokenResponse;

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

    @Autowired
    private ObjectMapper objectMapper;

    private String token = null;
    private Instant tokenExpiry = null; // Instant when the token will expire

    private final Object tokenLock = new Object();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() throws IOException {
        if(isTokenInvalid()){
            logger.info("Fetching/Creating token.");

            String encodedCredentials = Base64.getEncoder().encodeToString(String.format("%s:%s", this.clientId, this.clientSecret).getBytes(BlizzardOAuth2FlowHandler.ENCODING));

            // ------------------------------------------------- Allows testing/mocking of the URL connection object
            HttpURLConnection con = null;

            try{
            URL url = new URL(this.tockenUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", String.format("Basic %s", encodedCredentials));
            con.setDoOutput(true);
            con.getOutputStream().write("grant_type=client_credentials".getBytes(BlizzardOAuth2FlowHandler.ENCODING));

            int responseCode = con.getResponseCode();
            logger.info(String.format("Sent 'POST' to %s requesting access token via client credentials grant type.", url));
            logger.info(String.format("Result code: %s", responseCode));

            String response = IOUtils.toString(con.getInputStream(), BlizzardOAuth2FlowHandler.ENCODING);

            logger.debug(String.format("Response: %s", response));

            // Reads the JSON response and converts it to TokenResponse class or throws an exception
            TokenResponse tokenResponse = objectMapper.readValue(response, TokenResponse.class);
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
        synchronized (tokenLock) {
            if (token == null) {
                return true;
            }
            if (tokenExpiry == null) {
                return true;
            }
            return Instant.now().isAfter(tokenExpiry);
        }
    }
}
