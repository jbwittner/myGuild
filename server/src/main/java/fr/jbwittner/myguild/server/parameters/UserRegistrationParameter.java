package fr.jbwittner.myguild.server.parameters;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import fr.jbwittner.myguild.server.validation.ValidEmail;

/**
 * Parameter used for the registration of new user account
 */
public class UserRegistrationParameter {
    
    @NotNull
    @NotEmpty
    private String nickName;
     
    @NotNull
    @NotEmpty
    private String password;
     
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    /**
     * Getter of the nick name
     * @return nick name of the account
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * Setter of the nick name
     * @param nickName nick name of the account
     */
    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    /**
     * Getter of the password
     * @return password of the account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of the password
     * @param password password of the account
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Getter of the email
     * @return email of the account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email
     * @param email email of the account
     */
    public void setEmail(final String email) {
        this.email = email;
    }
     
}