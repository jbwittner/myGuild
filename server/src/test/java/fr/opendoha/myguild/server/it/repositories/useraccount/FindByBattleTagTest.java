package fr.opendoha.myguild.server.it.repositories.useraccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.tools.TestObjectFactory;


/**
 * Class to check the findByType method of AccountTypeRepository
 */
public class FindByBattleTagTest extends MotherUserAccountRepositoryTest {

    /**
     * Test to check the findBy method when the UserAccount is in the database
     */
    @Test
    public void findByBattleTagTestOk(){
        UserAccount userAccountSaved;

        for(final UserAccount userAccount: this.userAccountList){
            userAccountSaved = this.userAccountRepository.findByBattleTag(userAccount.getBattleTag());
            Assertions.assertEquals(userAccount, userAccountSaved);
        }
    }

    /**
     * Test to check the findBy method when the UserAccount the input is null
     */
    @Test
    public void findByBattleTagNullTestOk(){
        final UserAccount UserAccount = this.userAccountRepository.findByBattleTag(null);

        Assertions.assertNull(UserAccount);
    }

    /**
     * Test to check the findBy method when the UserAccount is not in the database
     */
    @Test
    public void findByBattleTagNotExistTestOk(){
        final String randomString = this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG);

        final UserAccount UserAccount = this.userAccountRepository.findByBattleTag(randomString);

        Assertions.assertNull(UserAccount);
    }

}