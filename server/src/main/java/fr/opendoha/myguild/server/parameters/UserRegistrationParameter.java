package fr.opendoha.myguild.server.parameters;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import fr.opendoha.myguild.server.validation.ValidEmail;

/**
 * Parameter used for the registration of new user account
 */
public class UserRegistrationParameter {
    
    @NotNull
    @NotEmpty
    private String nickName;
     
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String battleTag;

    @NotNull
    private Integer blizzardId;

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

    /**
     * Getter of blizzardId
     * @return id used of the account by Blizzard
     */
    public Integer getBlizzardId() {
        return blizzardId;
    }

    /**
     * Setter of blizzardId
     * @param blizzardId id used of the account by Blizzard
     */
    public void setBlizzardId(final Integer blizzardId) {
        this.blizzardId = blizzardId;
    }

    /**
     * Getter of battletag
     * @return battletag of the account
     */
    public String getBattletag() {
        return this.battleTag;
    }

    /**
     * Setter of battletag
     * @param battletag battletag of the account
     */
    public void setBattleTag(final String battleTag) {
        this.battleTag = battleTag;
    }
     
}