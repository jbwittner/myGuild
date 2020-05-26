package fr.jbwittner.myguild.server.repository;

import fr.jbwittner.myguild.server.model.Account;

/**
 * Repository for account
 */
public interface AccountTypeRepository extends AbstractRepository<Account, Long> {

    /**
     * Method to find account by nickname
     * @param nickName nickname to find
     * @return Account with matching  nickname
     */
    Account findByNickName(String nickName);

    /**
     * Method to find account by email
     * @param nickName email to find
     * @return Account with matching email
     */
    Account findByEmail(String email);
}