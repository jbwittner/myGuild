package fr.opendoha.myguild.server.service.userservice;

import fr.opendoha.myguild.server.exception.UserBattleTagAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserBlizzardIdAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.service.UserService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.testhelper.TestObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test of registerNewUserAccount
 */
public class RegisterNewUserAccountTest extends AbstractMotherIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountRepository userAccountRepository;

    UserAccount userAlreadyRegistered;

    @Override
    protected void initDataBeforeEach() {

        this.userAlreadyRegistered = new UserAccount();

        final UserRegistrationParameter userRegistrationParameter = this.getUserRegistrationParameter();

        userAlreadyRegistered.setBattleTag(userRegistrationParameter.getBattleTag());
        userAlreadyRegistered.setBlizzardId(userRegistrationParameter.getBlizzardId());
        userAlreadyRegistered.setEmail(userRegistrationParameter.getEmail());
        userAlreadyRegistered.setNickName(userRegistrationParameter.getNickName());

        this.userAccountRepository.saveAndFlush(userAlreadyRegistered);

    }

    private UserRegistrationParameter getUserRegistrationParameter(){
        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        final String battleTag = this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_BATTLETAG);
        final String nickName = this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_NICKNAME);
        final Integer blizzardId = this.factory.getRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID);

        userRegistrationParameter.setBattleTag(battleTag);
        userRegistrationParameter.setBlizzardId(blizzardId);
        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(nickName);

        return userRegistrationParameter;
    }

    /**
     * Test with a new account
     */
    @Test
    public void testWithNewAccountOk() throws UserEmailAlreadyUsedException, UserNickNameAlreadyUsedException,
            UserBattleTagAlreadyUsedException, UserBlizzardIdAlreadyUsedException {
        final UserRegistrationParameter userRegistrationParameter = this.getUserRegistrationParameter();

        this.userService.registerNewUserAccount(userRegistrationParameter);

        final UserAccount userAccount =
                this.userAccountRepository.findByBlizzardId(userRegistrationParameter.getBlizzardId());

        Assertions.assertEquals(userRegistrationParameter.getBattleTag(), userAccount.getBattleTag());
        Assertions.assertEquals(userRegistrationParameter.getBlizzardId(), userAccount.getBlizzardId());
        Assertions.assertEquals(userRegistrationParameter.getEmail(), userAccount.getEmail());
        Assertions.assertEquals(userRegistrationParameter.getNickName(), userAccount.getNickName());
    }

    /**
     * Test with blizzard id already used
     */
    @Test
    public void testWithBlizzardIdAlreadyUsedOk(){
        final UserRegistrationParameter userRegistrationParameter = this.getUserRegistrationParameter();

        userRegistrationParameter.setBlizzardId(this.userAlreadyRegistered.getBlizzardId());

        Assertions.assertThrows(UserBlizzardIdAlreadyUsedException.class,
                () -> this.userService.registerNewUserAccount(userRegistrationParameter));

    }

    /**
     * Test with nick name already used
     */
    @Test
    public void testWithNickNameAlreadyUsedOk(){
        final UserRegistrationParameter userRegistrationParameter = this.getUserRegistrationParameter();

        userRegistrationParameter.setNickName(this.userAlreadyRegistered.getNickName());

        Assertions.assertThrows(UserNickNameAlreadyUsedException.class,
                () -> this.userService.registerNewUserAccount(userRegistrationParameter));

    }

    /**
     * Test with email already used
     */
    @Test
    public void testWithEmailAlreadyUsedOk(){
        final UserRegistrationParameter userRegistrationParameter = this.getUserRegistrationParameter();

        userRegistrationParameter.setEmail(this.userAlreadyRegistered.getEmail());

        Assertions.assertThrows(UserEmailAlreadyUsedException.class,
                () -> this.userService.registerNewUserAccount(userRegistrationParameter));

    }

    /**
     * Test with battle tag already used
     */
    @Test
    public void testWithBattleTagAlreadyUsedOk(){
        final UserRegistrationParameter userRegistrationParameter = this.getUserRegistrationParameter();

        userRegistrationParameter.setBattleTag(this.userAlreadyRegistered.getBattleTag());

        Assertions.assertThrows(UserBattleTagAlreadyUsedException.class, () -> this.userService.registerNewUserAccount(userRegistrationParameter));

    }
}
