package fr.jbwittner.myguild.server.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Class of the User account
 */
@Entity
@Table(name = "USER_ACCOUNT" , uniqueConstraints = {
                @UniqueConstraint(columnNames = "EMAIL", name = "UK_EMAIL"),
                @UniqueConstraint(columnNames = "NICK_NAME", name = "UK_NICK_NAME")
            })
public class UserAccount extends AbstractModel {

    @Column(name = "EMAIL", nullable = false, length = 60)
    private String email;

    @Column(name = "NICK_NAME", nullable = false, length = 60)
    private String nickName;

    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @ElementCollection
    @CollectionTable(name="USER_ROLES",
                        joinColumns=@JoinColumn(name = "USER_ACCOUNT_ID"),
                        foreignKey = @ForeignKey(name = "FK_ACCOUNT_ID"))
    @Column(name = "ROLES")
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;

    /**
     * Default constructor
     */
    public UserAccount() {
        super();
        this.enabled = false;
    }

    /**
     * Constructor of User account
     * @param email email of the account
     * @param nickName nickName of the account
     * @param password password of the account
     * @param enabled enable the account
     * @param roles roles of the account
     */
    public UserAccount(final String email, final String nickName, final String password,
                        final boolean enabled, final List<Roles> roles) {
        super();
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
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
     * Getter of password
     * @return password of the account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of password
     * @param password password of the account
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Getter of Roles
     * @return List of Roles of the account
     */
    public List<Roles> getRoles() {
        return roles;
    }

    /**
     * Setter of Roles
     * @param Roles List of Roles of the account
     */
    public void setRoles(final List<Roles> roles) {
        this.roles = roles;
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
        return "UserAccount [email=" + email + ", enabled=" + enabled + ", nickName=" + nickName + ", password="
                + password + ", roles=" + roles + "]";
    }

}