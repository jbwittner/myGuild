package fr.jbwittner.myguild.server.repository;

import fr.jbwittner.myguild.server.model.UserAccount;

/**
 * Repository for UserAccount
 */
public interface UserAccountRepository extends AbstractRepository<UserAccount, Long> {

    /**
     * Method to find UserAccount by email
     * @param email email to find
     * @return UserAccount which the email match
     */
    UserAccount findByEmail(String email);

    /**
     * Method to find UserAccount by email
     * @param nickName nick name to find
     * @return UserAccount which the nick name match
     */
    UserAccount findByNickName(String nickName);

}
