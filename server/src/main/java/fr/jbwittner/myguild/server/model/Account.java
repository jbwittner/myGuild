package fr.jbwittner.myguild.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class of the Account
 */
@Entity
@Table(name = "ACCOUNT")
public class Account extends AbstractModel{

    @Column(name = "NICK_NAME", nullable = false, length = 20)
    private String nickName;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;

    /**
     * Default constructor
     */
    public Account() {
        super();
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Constructor of Account type
     * @param type String of type
     */
    public Account(final String nickName, String email, String password) {
        super();
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    /**
     * Getter of nickName
     * @return nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter of nickName
     * @param id nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Getter of email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of email
     * @param id email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of password
     * @param id password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
     @Override
    public String toString() {
        return "Account [id=" + this.id + "email=" + email + ", nickName=" + nickName + ", password=" + password + "]";
    }

}