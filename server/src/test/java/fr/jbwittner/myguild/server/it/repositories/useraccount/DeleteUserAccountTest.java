package fr.jbwittner.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.jbwittner.myguild.server.repository.UserAccountRepository;

/**
 * Class to check the delete method of UserAccountRepository
 */
public class DeleteUserAccountTest extends MotherUserAccountRepositoryTest {

    @Autowired
    UserAccountRepository userAccountRepository;

    /**
     * Test to verify if deleting an account is correct
     */
    @Test
    public void deleteOneAccountTypeTestOk(){

        final Long numberAccountTypeBefore = this.userAccountRepository.count();

        this.userAccountRepository.delete(this.randomUserAccount);

        this.userAccountRepository.flush();

        final Long numberAccountTypeAfter = this.userAccountRepository.count();

        Assertions.assertEquals(numberAccountTypeBefore, numberAccountTypeAfter + 1);

    }

    /**
     * Test to verify if the deletion of all accounts is correct
     */
    @Test
    public void deleteAllAccountTypeTestOk(){

        this.userAccountRepository.deleteAll();

        this.userAccountRepository.flush();

        final Long numberAccountTypeAfter = this.userAccountRepository.count();

        Assertions.assertEquals(0, numberAccountTypeAfter);

    }

}