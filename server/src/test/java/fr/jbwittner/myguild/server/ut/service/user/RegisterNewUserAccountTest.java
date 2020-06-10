package fr.jbwittner.myguild.server.ut.service.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.jbwittner.myguild.server.exception.UserEmailAlreadyUsedException;
import fr.jbwittner.myguild.server.exception.UserNickNameAlreadyUsedException;
import fr.jbwittner.myguild.server.model.Roles;
import fr.jbwittner.myguild.server.model.UserAccount;
import fr.jbwittner.myguild.server.parameters.UserRegistrationParameter;
import fr.jbwittner.myguild.server.repository.UserAccountRepository;
import fr.jbwittner.myguild.server.service.UserService;
import fr.jbwittner.myguild.server.tools.TestObjectFactory;
import fr.jbwittner.myguild.server.ut.AbstractMotherUnitTest;

/**
 * Class to check the registerNewUserAccount method
 */
public class RegisterNewUserAccountTest extends AbstractMotherUnitTest {

    private UserAccountRepository userAccountRepository = Mockito.mock(UserAccountRepository.class);
    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        this.userService = new UserService(this.userAccountRepository, this.passwordEncoder);
    }

    /**
     * Test with all good parameters
     */
    @Test
    public void registerNewUserAccountOKTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

        when(userAccountRepository.save(any(UserAccount.class))).thenAnswer(i -> i.getArguments()[0]);
        when(passwordEncoder.encode(any(String.class))).thenAnswer(i -> i.getArguments()[0]);

        final UserAccount userAccount = this.userService.registerNewUserAccount(userRegistrationParameter);

        Assertions.assertEquals(userRegistrationParameter.getEmail(), userAccount.getEmail());
        Assertions.assertEquals(userRegistrationParameter.getNickName(), userAccount.getNickName());
        Assertions.assertEquals(userRegistrationParameter.getPassword(), userAccount.getPassword());
        Assertions.assertEquals(Arrays.asList(Roles.ROLES_USER), userAccount.getRoles());
    }

    /**
     * Test with an email already used
     */
    @Test
    public void registerNewUserAccountEmailAlreadyUsedNOkTest(){

        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        when(userAccountRepository.existsByEmail(any(String.class))).thenThrow(UserEmailAlreadyUsedException.class);

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
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

        when(userAccountRepository.existsByEmail(any(String.class))).thenThrow(UserNickNameAlreadyUsedException.class);

        userRegistrationParameter.setEmail(this.factory.getUniqueRandomEmail());
        userRegistrationParameter.setNickName(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_NICKNAME));
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
        userRegistrationParameter.setPassword(this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_PASSWORD));

        when(userAccountRepository.save(any(UserAccount.class))).thenAnswer(i -> i.getArguments()[0]);
        when(passwordEncoder.encode(any(String.class))).thenAnswer(i -> i.getArguments()[0]);

        final UserAccount userAccount = this.userService.registerNewUserAccount(userRegistrationParameter);

        Assertions.assertEquals(userRegistrationParameter.getEmail(), userAccount.getEmail());
        Assertions.assertEquals(userRegistrationParameter.getNickName(), userAccount.getNickName());
        Assertions.assertEquals(userRegistrationParameter.getPassword(), userAccount.getPassword());
        Assertions.assertEquals(Arrays.asList(Roles.ROLES_USER), userAccount.getRoles());

    }

}