package fr.jbwittner.myguild.server.it.model.useraccount;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.jbwittner.myguild.server.it.AbstractMotherIntegrationTest;
import fr.jbwittner.myguild.server.model.UserAccount;

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

        String toStringExpectex = "UserAccount [email=" + userAccount.getEmail() + ", enabled=" + userAccount.isEnabled();
        toStringExpectex = toStringExpectex + ", nickName=" + userAccount.getNickName() + ", password=";
        toStringExpectex = toStringExpectex + userAccount.getPassword() + ", roles=" + userAccount.getRoles()+ "]";

        Assertions.assertEquals(toStringExpectex, userAccount.toString());
    }
    
}