package fr.opendoha.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.tools.TestObjectFactory;


/**
 * Class to check the findByType method of AccountTypeRepository
 */
public class FindByBlizzardIdTest extends MotherUserAccountRepositoryTest {

    /**
     * Test to check the findBy method when the UserAccount is in the database
     */
    @Test
    public void findByBlizzardIdTestOk(){
        UserAccount userAccountSaved;

        for(final UserAccount userAccount: this.userAccountList){
            userAccountSaved = this.userAccountRepository.findByBlizzardId(userAccount.getBlizzardId());
            Assertions.assertEquals(userAccount, userAccountSaved);
        }
    }

    /**
     * Test to check the findBy method when the UserAccount the input is null
     */
    @Test
    public void findByBlizzardIdNullTestOk(){
        final UserAccount UserAccount = this.userAccountRepository.findByBlizzardId(null);

        Assertions.assertNull(UserAccount);
    }

    /**
     * Test to check the findBy method when the UserAccount is not in the database
     */
    @Test
    public void findByBlizzardIdNotExistTestOk(){
        final Integer randomInteger = this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID);

        final UserAccount UserAccount = this.userAccountRepository.findByBlizzardId(randomInteger);

        Assertions.assertNull(UserAccount);
    }

}