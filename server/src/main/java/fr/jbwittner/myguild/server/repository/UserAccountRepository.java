package fr.jbwittner.myguild.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jbwittner.myguild.server.model.UserAccount;

/**
 * Repository for UserAccount
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    /**
     * Method to find UserAccount by email
     * @param email email to find
     * @return UserAccount which the email match
     */
    UserAccount findByEmail(String email);

    /**
     * Method to delete UserAccount
     * @param userAccount UserAccount to delete
     */
    @Override
    void delete(UserAccount userAccount);

}
