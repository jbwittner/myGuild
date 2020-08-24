package fr.opendoha.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.tools.TestObjectFactory;

/**
 * Class to test the method existsByNickName
 */
public class ExistsByNickNameTest extends MotherUserAccountRepositoryTest {


    /**
     * Test to check that the method existsByNickName return true if the nick name exist
     */
    @Test
    public void existsByNickNameFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByNickName(randomUserAccount.getNickName());

        Assertions.assertTrue(isExist);
    }

    /**
     * Test to check that the method existsByNickName return false if the nick name do not exist
     */
    @Test
    public void existsByNickNameNotFundTestOk(){
        final Boolean isExist = this.userAccountRepository.existsByNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));

        Assertions.assertFalse(isExist);
    }

    /**
     * Test to check that the method existsByNickName return false if the input is null
     */
    @Test
    public void existsByNickNameNullTestOK(){
        final Boolean isExist = this.userAccountRepository.existsByNickName(null);

        Assertions.assertFalse(isExist);
    }

}