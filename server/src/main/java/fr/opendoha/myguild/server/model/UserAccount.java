package fr.opendoha.myguild.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Class of the User account
 */
@Entity
@Table(name = "USER_ACCOUNT" , uniqueConstraints = {
                @UniqueConstraint(columnNames = "BLIZZARD_ID", name = "UK_BLIZZARD_ID"),
                @UniqueConstraint(columnNames = "BATTLETAG", name = "UK_BATTLETAG"),
                @UniqueConstraint(columnNames = "EMAIL", name = "UK_EMAIL"),
                @UniqueConstraint(columnNames = "NICK_NAME", name = "UK_NICK_NAME")
            })
public class UserAccount extends AbstractModel {

    @Column(name = "BLIZZARD_ID", nullable = false)
    private Integer blizzardId;

    @Column(name = "BATTLETAG", nullable = false, length = 60)
    private String battleTag;

    @Column(name = "NICK_NAME", nullable = false, length = 60)
    private String nickName;

    @Column(name = "EMAIL", nullable = false, length = 60)
    private String email;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    /**
     * Default constructor
     */
    public UserAccount() {
        super();
        this.enabled = true;
    }

    /**
     * Constructor of User account
     * @param blizzardId blizzard Id of the account
     * @param battleTag battleTag of the account
     * @param email email of the account
     * @param nickName nickName of the account
     */
    public UserAccount(final Integer blizzardId, final String battleTag, final String email, final String nickName) {
        super();
        this.email = email;
        this.nickName = nickName;
        this.battleTag = battleTag;
        this.enabled = true;
        this.blizzardId = blizzardId;
    }

	/**
     * Getter of email
     * @return email of the account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of username
     * @param username username of the account
     */
    public void setEmail(final String username) {
        this.email = username;
    }

    /**
     * Getter of nickName
     * @return nickName of the account
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter of nickName
     * @param nickName nickName of the account
     */
    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    /**
     * Getter of battleTag
     * @return battleTag of the account
     */
    public String getBattleTag() {
        return battleTag;
    }

    /**
     * Setter of battleTag
     * @param battleTag battleTag of the account
     */
    public void setBattleTag(final String battleTag) {
        this.battleTag = battleTag;
    }

    /**
     * Getter of blizzardId
     * @return blizzardId of the account
     */
    public Integer getBlizzardId() {
        return blizzardId;
    }

    /**
     * Setter of blizzardId
     * @param blizzardId blizzardId of the account
     */
    public void setBlizzardId(final Integer blizzardId) {
        this.blizzardId = blizzardId;
    }

    /**
     * Getter of enabled
     * @return boolean indicating whether the account is activated
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Setter of enabled
     * @param enabled boolean indicating whether the account is activated
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UserAccount [blizzardId=" + this.blizzardId + ", battleTag=" + battleTag + ", email=" + email + ", enabled="
                + enabled + ", nickName=" + nickName + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj){
        final UserAccount userAccount = (UserAccount) obj;
        boolean status = false;
        
        if(obj != null){

            final boolean isBlizzardIdEquals = this.blizzardId.equals(userAccount.getBlizzardId());
            final boolean isBattleTagEquals = this.battleTag.equals(userAccount.getBattleTag());
            final boolean isEmailEquals = this.email.equals(userAccount.getEmail());
            final boolean isNickNameEquals = this.nickName.equals(userAccount.getNickName());

            if(isBlizzardIdEquals && isBattleTagEquals && isEmailEquals && isNickNameEquals){
                status = true;
            }
        }

        return status;
    }
}