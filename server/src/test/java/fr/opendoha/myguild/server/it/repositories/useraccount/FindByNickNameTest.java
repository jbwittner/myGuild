package fr.opendoha.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.tools.TestObjectFactory;


/**
 * Class to check the findByType method of AccountTypeRepository
 */
public class FindByNickNameTest extends MotherUserAccountRepositoryTest {

    /**
     * Test to check the findBy method when the UserAccount is in the database
     */
    @Test
    public void findByNickNameTestOk(){
        UserAccount userAccountSaved;

        for(final UserAccount userAccount: this.userAccountList){
            userAccountSaved = this.userAccountRepository.findByNickName(userAccount.getNickName());
            Assertions.assertEquals(userAccount, userAccountSaved);
        }
    }

    /**
     * Test to check the findBy method when the UserAccount the input is null
     */
    @Test
    public void findByNickNameNullTestNOk(){
        final UserAccount UserAccount = this.userAccountRepository.findByNickName(null);

        Assertions.assertNull(UserAccount);
    }

    /**
     * Test to check the findBy method when the UserAccount is not in the database
     */
    @Test
    public void findByNickNameNotExistTestOk(){
        final String randomString = this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME);

        final UserAccount UserAccount = this.userAccountRepository.findByNickName(randomString);

        Assertions.assertNull(UserAccount);
    }

}