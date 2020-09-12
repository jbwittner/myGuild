package fr.opendoha.myguild.server.it.model.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.repository.UserAccountRepository;

/**
 * Test class for creation of Account type
 */
public class CreationUserAccountTest extends AbstractMotherIntegrationTest {

    @Autowired
    UserAccountRepository userAccountRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {}

    /**
     * Test to check the good creation of user account
     */
    @Test
    public void createUserAccountTestOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount userAccountSaved = userAccountRepository.findByEmail(userAccount.getEmail());

        Assertions.assertEquals(userAccount, userAccountSaved);
    }

    /**
     * Test to check if an exception is thrown when we try to create a user account
     * with a previously used nickname
     */
    @Test
    public void createDuplicateNickNameTestNOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount duplicateUserAccount = this.factory.createUserAccount();
        duplicateUserAccount.setNickName(userAccount.getNickName());

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userAccountRepository.saveAndFlush(duplicateUserAccount);
        });
    }

    /**
     * Test to check if an exception is thrown when we try to create a user account
     * with a previously used email
     */
    @Test
    public void createDuplicateEmailTestNOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount duplicateUserAccount = this.factory.createUserAccount();
        duplicateUserAccount.setEmail(userAccount.getEmail());

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userAccountRepository.saveAndFlush(duplicateUserAccount);
        });
    }

    /**
     * Test to check if no exception is thrown when you try to create a user account with an already
     * used enabled
     */
    @Test
    public void createDuplicateEnableTestOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount duplicateUserAccount = this.factory.createUserAccount();
        duplicateUserAccount.setEnabled(userAccount.isEnabled());

        userAccountRepository.saveAndFlush(duplicateUserAccount);
    }

}
