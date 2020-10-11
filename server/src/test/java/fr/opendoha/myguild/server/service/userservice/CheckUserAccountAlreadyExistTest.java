package fr.opendoha.myguild.server.service.userservice;

import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.service.UserService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.testhelper.TestObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test of checkUserAccountAlreadyExist
 */
public class CheckUserAccountAlreadyExistTest extends AbstractMotherIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    protected void initDataBeforeEach() {}

    /**
     * Test with a existed account
     */
    @Test
    public void testWithExistedAccount(){

        final UserAccount userAccount = new UserAccount();

        final String battleTag = this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_BATTLETAG);
        final String nickName = this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_NICKNAME);
        final Integer blizzardId = this.factory.getRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID);

        userAccount.setNickName(nickName);
        userAccount.setBlizzardId(blizzardId);
        userAccount.setBattleTag(battleTag);
        userAccount.setEmail(this.factory.getUniqueRandomEmail());

        this.userAccountRepository.saveAndFlush(userAccount);

        Assertions.assertTrue(this.userService.checkUserAccountAlreadyExist(blizzardId));

    }

    /**
     * Test without a existed account
     */
    @Test
    public void testWithNotExistedAccount(){

        final Integer blizzardId = this.factory.getRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID);

        Assertions.assertFalse(this.userService.checkUserAccountAlreadyExist(blizzardId));

    }
}
