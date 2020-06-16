package fr.jbwittner.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.jbwittner.myguild.server.model.UserAccount;


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
        final String randomString = this.factory.getUniqueRandomAsciiString(10);

        final UserAccount UserAccount = this.userAccountRepository.findByNickName(randomString);

        Assertions.assertNull(UserAccount);
    }

}