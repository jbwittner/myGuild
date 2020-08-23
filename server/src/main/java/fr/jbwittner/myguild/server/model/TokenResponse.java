package fr.jbwittner.myguild.server.model;

/**
 * POJO for reading in the oAuth2 access token response from https://_region_.battle.net/oauth/token .
 */
public class TokenResponse {
    /**
     * The access token used on future requests to the API.
     */
    private String access_token;
    private String token_type;
    /**
     * Seconds from when received that the token will expire.
     */
    private Long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
}
