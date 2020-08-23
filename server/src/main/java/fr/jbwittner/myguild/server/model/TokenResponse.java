package fr.jbwittner.myguild.server.model;

/**
 * POJO for reading in the oAuth2 access token response from https://_region_.battle.net/oauth/token .
 */
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

    /**
     * Getter of the access token
     * @return access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * Setter of the access token
     * @param access_token
     */
    public void setAccess_token(final String access_token) {
        this.access_token = access_token;
    }

    /**
     * Getter of the Token type
     * @return token_type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * Setter of token type
     * @param token_type
     */
    public void setToken_type(final String token_type) {
        this.token_type = token_type;
    }

    /**
     * Getter of expires in
     * @return expires_in
     */
    public Long getExpires_in() {
        return expires_in;
    }

    /**
     * Setter of expires in
     * @param expires_in
     */
    public void setExpires_in(final Long expires_in) {
        this.expires_in = expires_in;
    }
}
