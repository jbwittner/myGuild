package fr.opendoha.myguild.server.tools.oauth2.models;

import lombok.Data;

/**
 * POJO for reading in the oAuth2 access token response from https://_region_.battle.net/oauth/token .
 */
@Data
public class TokenResponse {

    /**
     * The access token used on future requests to the API.
     */
    private String access_token;

    /**
     * Type of the token
     */
    private String token_type;

    /**
     * Seconds from when received that the token will expire.
     */
    private Long expires_in;
}
