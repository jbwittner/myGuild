package fr.jbwittner.myguild.server.it.model.useraccount;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import fr.jbwittner.myguild.server.it.AbstractMotherTest;
import fr.jbwittner.myguild.server.model.Roles;
import fr.jbwittner.myguild.server.model.UserAccount;
import fr.jbwittner.myguild.server.repository.UserAccountRepository;

/**
 * Test class for creation of Account type
 */
public class CreationUserAccountTest extends AbstractMotherTest {

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
    public void createOk(){
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
    public void createDuplicateNickNameNOk(){
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
    public void createDuplicateEmailNOk(){
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
     * used password
     */
    @Test
    public void createDuplicatePasswordOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount duplicateUserAccount = this.factory.createUserAccount();
        duplicateUserAccount.setPassword(userAccount.getPassword());

        userAccountRepository.saveAndFlush(duplicateUserAccount);
    }

    /**
     * Test to check if no exception is thrown when you try to create a user account with an already
     * used enabled
     */
    @Test
    public void createDuplicateEnableOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount duplicateUserAccount = this.factory.createUserAccount();
        duplicateUserAccount.setEnabled(userAccount.isEnabled());

        userAccountRepository.saveAndFlush(duplicateUserAccount);
    }

    /**
     * Test to check if an exception is thrown when we try to create a user account
     * with a previously used roles entity
     */
    @Test
    public void createDuplicateRolesNOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount duplicateUserAccount = this.factory.createUserAccount();
        duplicateUserAccount.setRoles(userAccount.getRoles());

        Assertions.assertThrows(JpaSystemException.class, () -> {
            userAccountRepository.saveAndFlush(duplicateUserAccount);
        });
    }

    /**
     * Test to check if an exception is thrown when we try to create a user account
     * with a previously same used roles
     */
    @Test
    public void createDuplicateRolesOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccount duplicateUserAccount = this.factory.createUserAccount();

        final List<Roles> roles = new ArrayList<>();
        roles.add(Roles.ROLES_USER);

        duplicateUserAccount.setRoles(roles);

        userAccountRepository.saveAndFlush(duplicateUserAccount);
    }

}
