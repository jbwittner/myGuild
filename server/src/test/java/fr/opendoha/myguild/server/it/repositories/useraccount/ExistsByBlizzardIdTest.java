package fr.opendoha.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.tools.TestObjectFactory;

/**
 * Class to test the method existsByEmail
 */
public class ExistsByBlizzardIdTest extends MotherUserAccountRepositoryTest {


    /**
     * Test to check that the method existsByEmail return true if the email exist
     */
    @Test
    public void existsByBlizzardIdFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByBlizzardId(randomUserAccount.getBlizzardId());

        Assertions.assertTrue(isExist);
    }

    /**
     * Test to check that the method existsByEmail return false if the email do not exist
     */
    @Test
    public void existsByBlizzardIdNotFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));

        Assertions.assertFalse(isExist);
    }

    /**
     * Test to check that the method existsByEmail return false if the input is null
     */
    @Test
    public void existsByBlizzardIdNullTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByBlizzardId(null);

        Assertions.assertFalse(isExist);
    }

}