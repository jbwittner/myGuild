package fr.opendoha.myguild.server.it.model.useraccount;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.UserAccount;

/**
 * Class to test toString method of UserAccount
 */
public class ToStringTest extends AbstractMotherIntegrationTest {
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {}

    /**
     * Test of toString
     */
    @Test
    public void createUserAccountTestOk(){
        final UserAccount userAccount = this.factory.createUserAccount();

        String toStringExpectex = "UserAccount [blizzardId=" + userAccount.getBlizzardId() + ", battleTag=" + userAccount.getBattleTag();
        toStringExpectex = toStringExpectex + ", email=" + userAccount.getEmail() + ", enabled=" + userAccount.isEnabled();
        toStringExpectex = toStringExpectex + ", nickName=" + userAccount.getNickName() + "]";

        Assertions.assertEquals(toStringExpectex, userAccount.toString());
    }
    
}