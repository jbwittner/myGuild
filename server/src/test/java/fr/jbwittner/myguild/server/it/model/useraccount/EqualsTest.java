package fr.jbwittner.myguild.server.it.model.useraccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import fr.jbwittner.myguild.server.it.AbstractMotherIntegrationTest;
import fr.jbwittner.myguild.server.model.UserAccount;

/**
 * Class to test equals method of UserAccount
 */
public class EqualsTest extends AbstractMotherIntegrationTest {

    private UserAccount userAccountOne;
    private UserAccount userAccountTwo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        this.userAccountOne = this.factory.createUserAccount();
        this.userAccountTwo = this.factory.createUserAccount();
    }

    /**
     * Test with two different account
     */
    @Test
    public void isNotEqualsOk(){
        Assertions.assertNotEquals(this.userAccountOne, this.userAccountTwo);
    }

    /**
     * Test with the same battle tag
     */
    @Test
    public void isNotEqualsBattleTagEqualsOk(){
        userAccountOne.setBattleTag(userAccountTwo.getBattleTag());
        Assertions.assertNotEquals(userAccountOne, userAccountTwo);
    }

    /**
     * Test with the same blizzard id
     */
    @Test
    public void isNotEqualsBlizzardIdEqualsOk(){
        userAccountOne.setBlizzardId(userAccountTwo.getBlizzardId());
        Assertions.assertNotEquals(userAccountOne, userAccountTwo);
    }

    /**
     * Test with the same email
     */
    @Test
    public void isNotEqualsEmailEqualsOk(){
        userAccountOne.setEmail(userAccountTwo.getEmail());
        Assertions.assertNotEquals(userAccountOne, userAccountTwo);
    }

    /**
     * Test with the same nick name
     */
    @Test
    public void isNotEqualsNickNameEqualsOk(){
        userAccountOne.setNickName(userAccountTwo.getNickName());
        Assertions.assertNotEquals(userAccountOne, userAccountTwo);
    }

    /**
     * Test with the same account
     */
    @Test
    public void isEqualsOk(){
        userAccountOne.setBattleTag(userAccountTwo.getBattleTag());
        userAccountOne.setBlizzardId(userAccountTwo.getBlizzardId());
        userAccountOne.setEmail(userAccountTwo.getEmail());
        userAccountOne.setNickName(userAccountTwo.getNickName());
        Assertions.assertEquals(userAccountOne, userAccountTwo);
    }
    
}