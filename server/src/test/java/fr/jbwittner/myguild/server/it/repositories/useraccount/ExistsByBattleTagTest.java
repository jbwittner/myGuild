package fr.jbwittner.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.jbwittner.myguild.server.tools.TestObjectFactory;

/**
 * Class to test the method existsByEmail
 */
public class ExistsByBattleTagTest extends MotherUserAccountRepositoryTest {


    /**
     * Test to check that the method existsByEmail return true if the email exist
     */
    @Test
    public void existsByBattleTagFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByBattleTag(randomUserAccount.getBattleTag());

        Assertions.assertTrue(isExist);
    }

    /**
     * Test to check that the method existsByEmail return false if the email do not exist
     */
    @Test
    public void existsByBattleTagNotFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertFalse(isExist);
    }

    /**
     * Test to check that the method existsByEmail return false if the input is null
     */
    @Test
    public void existsByBattleTagNullTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByBattleTag(null);

        Assertions.assertFalse(isExist);
    }

}