package fr.jbwittner.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.jbwittner.myguild.server.model.UserAccount;


/**
 * Class to check the findByType method of AccountTypeRepository
 */
public class FindByEmailTest extends MotherUserAccountRepositoryTest {

    /**
     * Test to check the findBy method when the UserAccount are in the database
     */
    @Test
    public void findByEmailOk(){
        UserAccount userAccountSaved;

        for(final UserAccount userAccount: this.userAccountList){
            userAccountSaved = this.userAccountRepository.findByEmail(userAccount.getEmail());
            Assertions.assertEquals(userAccount, userAccountSaved);
        }
    }

    /**
     * Test to check the findBy method when the UserAccount the input are null
     */
    @Test
    public void findByEmailNullOk(){
        final UserAccount UserAccount = this.userAccountRepository.findByEmail(null);

        Assertions.assertNull(UserAccount);
    }

    /**
     * Test to check the findBy method when the UserAccount are not in the database
     */
    @Test
    public void findByEmailNotExistOk(){
        final String randomString = this.factory.getUniqueRandomAsciiString(10);

        final UserAccount UserAccount = this.userAccountRepository.findByEmail(randomString);

        Assertions.assertNull(UserAccount);
    }

}