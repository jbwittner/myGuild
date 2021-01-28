package fr.opendoha.myguild.server.service.userservice;

import fr.opendoha.myguild.server.dto.UserAccountDTO;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.parameters.FetchingUserAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.service.UserService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.testhelper.TestObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test of getAccountInfo
 */
public class GetAccountInfoTest extends AbstractMotherIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    protected void initDataBeforeEach() {}

    /**
     * Test with a existed account
     * 
     * @throws UserAccountNotExistedException
     */
    @Test
    public void testWithExistedAccount() {
        final UserAccount userAccount = new UserAccount();

        final String battleTag = this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_BATTLETAG);
        final String nickName = this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_NICKNAME);
        final Integer blizzardId = this.factory.getRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID);
        final String email = this.factory.getUniqueRandomEmail();

        final FetchingUserAccountParameter fetchingUserAccountParameter = new FetchingUserAccountParameter();
        fetchingUserAccountParameter.setBattleTag(battleTag);
        fetchingUserAccountParameter.setBlizzardId(blizzardId);

        userAccount.setNickName(nickName);
        userAccount.setBlizzardId(blizzardId);
        userAccount.setBattleTag(battleTag);
        userAccount.setEmail(email);

        this.userAccountRepository.saveAndFlush(userAccount);

        final UserAccountDTO userAccountDTO = this.userService.fetchAccountInfo(fetchingUserAccountParameter);

        Assertions.assertEquals(userAccount.getBattleTag(), userAccountDTO.getBattleTag());
        Assertions.assertEquals(userAccount.getBlizzardId(), userAccountDTO.getBlizzardId());
        Assertions.assertEquals(userAccount.getEmail(), userAccountDTO.getEmail());
        Assertions.assertEquals(userAccount.getNickName(), userAccountDTO.getNickName());

    }

}
