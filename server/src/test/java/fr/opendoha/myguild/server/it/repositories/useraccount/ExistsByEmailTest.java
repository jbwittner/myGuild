package fr.opendoha.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class to test the method existsByEmail
 */
public class ExistsByEmailTest extends MotherUserAccountRepositoryTest {


    /**
     * Test to check that the method existsByEmail return true if the email exist
     */
    @Test
    public void existsByEmailFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByEmail(randomUserAccount.getEmail());

        Assertions.assertTrue(isExist);
    }

    /**
     * Test to check that the method existsByEmail return false if the email do not exist
     */
    @Test
    public void existsByEmailNotFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByEmail(this.factory.getUniqueRandomEmail());

        Assertions.assertFalse(isExist);
    }

    /**
     * Test to check that the method existsByEmail return false if the input is null
     */
    @Test
    public void existsByEmailNullTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByEmail(null);

        Assertions.assertFalse(isExist);
    }

}