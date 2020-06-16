package fr.jbwittner.myguild.server.it.service.user;


import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.jbwittner.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.jbwittner.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.jbwittner.myguild.server.exception.ValidationDataException;
import fr.jbwittner.myguild.server.model.Roles;
import fr.jbwittner.myguild.server.model.UserAccount;
import fr.jbwittner.myguild.server.parameters.UserRegistrationParameter;
import fr.jbwittner.myguild.server.service.UserService;
import fr.jbwittner.myguild.server.tools.TestObjectFactory;

/**
 * Class to check the registerNewUserAccount method
 */
public class RegisterNewUserAccountTest extends MotherUserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Test with all good parameters
     */
    @Test
    public void registerNewUserAccountOKTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

        final UserAccount userAccount = this.userService.registerNewUserAccount(userRegistrationParameter);

        Assertions.assertEquals(userRegistrationParameter.getEmail(), userAccount.getEmail());
        Assertions.assertEquals(userRegistrationParameter.getNickName(), userAccount.getNickName());
        Assertions.assertTrue(passwordEncoder.matches(userRegistrationParameter.getPassword(), userAccount.getPassword()));
        Assertions.assertEquals(Arrays.asList(Roles.ROLES_USER), userAccount.getRoles());
    }

    /**
     * Test with null email
     */
    @Test
    public void registerNewUserAccountEmailNullNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(null);
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));  
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

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
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
          
    }

    /**
     * Test with null password
     */
    @Test
    public void registerNewUserAccountPasswordNullNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setPassword(null);

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
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

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
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

        Assertions.assertThrows(ValidationDataException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
          
    }

    /**
     * Test with empty password
     */
    @Test
    public void registerNewUserAccountPasswordEmptyNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setPassword("");

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
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

        Assertions.assertThrows(UserEmailAlreadyUsedException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });
        
    }

    /**
     * Test with an nick name already used
     */
    @Test
    public void registerNewUserAccountNickNameAlreadyUsedNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.randomUserAccount.getNickName());
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

        Assertions.assertThrows(UserNickNameAlreadyUsedException.class, () -> {
            this.userService.registerNewUserAccount(userRegistrationParameter);
        });

    }

    /**
     * Test with an password already used
     */
    @Test
    public void registerNewUserAccountPasswordAlreadyUsedOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setPassword(this.randomUserAccount.getPassword());

        final UserAccount userAccount = this.userService.registerNewUserAccount(userRegistrationParameter);

        Assertions.assertEquals(userRegistrationParameter.getEmail(), userAccount.getEmail());
        Assertions.assertEquals(userRegistrationParameter.getNickName(), userAccount.getNickName());
        Assertions.assertTrue(passwordEncoder.matches(userRegistrationParameter.getPassword(), userAccount.getPassword()));
        Assertions.assertEquals(Arrays.asList(Roles.ROLES_USER), userAccount.getRoles());

    }

}