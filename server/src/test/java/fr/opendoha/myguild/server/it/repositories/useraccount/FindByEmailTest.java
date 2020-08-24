package fr.opendoha.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.model.UserAccount;


/**
 * Class to check the findByType method of AccountTypeRepository
 */
public class FindByEmailTest extends MotherUserAccountRepositoryTest {

    /**
     * Test to check the findBy method when the UserAccount is in the database
     */
    @Test
    public void findByEmailTestOk(){
        UserAccount userAccountSaved;

        for(final UserAccount userAccount: this.userAccountList){
            userAccountSaved = this.userAccountRepository.findByEmail(userAccount.getEmail());
            Assertions.assertEquals(userAccount, userAccountSaved);
        }
    }

    /**
     * Test to check the findBy method when the UserAccount the input is null
     */
    @Test
    public void findByEmailNullTestOk(){
        final UserAccount UserAccount = this.userAccountRepository.findByEmail(null);

        Assertions.assertNull(UserAccount);
    }

    /**
     * Test to check the findBy method when the UserAccount is not in the database
     */
    @Test
    public void findByEmailNotExistTestOk(){
        final String randomString = this.factory.getUniqueRandomEmail();

        final UserAccount UserAccount = this.userAccountRepository.findByEmail(randomString);

        Assertions.assertNull(UserAccount);
    }

}