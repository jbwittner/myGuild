package fr.opendoha.myguild.server.it.service.user;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.opendoha.myguild.server.exception.UserBattleTagAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserBlizzardIdAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.opendoha.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.opendoha.myguild.server.exception.ValidationDataException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;
import fr.opendoha.myguild.server.service.UserService;
import fr.opendoha.myguild.server.tools.TestObjectFactory;

/**
 * Class to check the registerNewUserAccount method
 */
public class RegisterNewUserAccountTest extends MotherUserServiceTest {

    @Autowired
    private UserService userService;

    /**
     * Test with all good parameters
     */
    @Test
    public void registerNewUserAccountOKTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        final UserAccount userAccount = this.userService.registerNewUserAccount(userRegistrationParameter);

        Assertions.assertEquals(userRegistrationParameter.getEmail(), userAccount.getEmail());
        Assertions.assertEquals(userRegistrationParameter.getNickName(), userAccount.getNickName());
        Assertions.assertEquals(userRegistrationParameter.getBlizzardId(), userAccount.getBlizzardId());
        Assertions.assertEquals(userRegistrationParameter.getBattletag(), userAccount.getBattleTag());
    }

    /**
     * Test with null email
     */
    @Test
    public void registerNewUserAccountEmailNullNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(null);
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
          
    }

    /**
     * Test with null nick name
     */
    @Test
    public void registerNewUserAccountNickNameNullNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(null);
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
          
    }

    /**
     * Test with null Blizzard Id
     */
    @Test
    public void registerNewUserAccountBlizzardIdNullNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(null);
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
    }

    /**
     * Test with null BattleTag
     */
    @Test
    public void registerNewUserAccountBattletagNullNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(null);

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
    }


    /**
     * Test with empty email
     */
    @Test
    public void registerNewUserAccountEmailEmptyNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail("");
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
          
    }

    /**
     * Test with empty nick name
     */
    @Test
    public void registerNewUserAccountNickNameEmptyNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName("");
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
          
    }

    /**
     * Test with empty BattleTag
     */
    @Test
    public void registerNewUserAccountBattleTagEmptyNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag("");

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
          
    }

    /**
     * Test with an email already used
     */
    @Test
    public void registerNewUserAccountEmailAlreadyUsedNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.randomUserAccount.getEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(UserEmailAlreadyUsedException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
        
    }

   /**
     * Test with an Nick Name already used
     */
    @Test
    public void registerNewUserAccountNickNameAlreadyUsedNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.randomUserAccount.getNickName());
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(UserNickNameAlreadyUsedException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });

    }

    /**
     * Test with an Blizzard Id already used
     */
    @Test
    public void registerNewUserAccountBlizzardIdAlreadyUsedNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.randomUserAccount.getBlizzardId());
        userRegistrationParameter.setBattleTag(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_BATTLETAG));

        Assertions.assertThrows(UserBlizzardIdAlreadyUsedException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });

    }

    /**
     * Test with an BattleTag already used
     */
    @Test
    public void registerNewUserAccountBattleTagAlreadyUsedNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setBlizzardId(this.factory.getUniqueRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        userRegistrationParameter.setBattleTag(this.randomUserAccount.getBattleTag());

        Assertions.assertThrows(UserBattleTagAlreadyUsedException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });


    }

}