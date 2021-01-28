package fr.opendoha.myguild.server.aop.serviceinterceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import fr.opendoha.myguild.server.aop.interceptor.ServiceInterceptor;
import fr.opendoha.myguild.server.exception.UserAccountNotRegistred;
import fr.opendoha.myguild.server.exception.UserNotIdentifiedException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;

/**
 * Test class to test checkIsRegistred method
 */
public class CheckIsRegistredTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Override
    protected void initDataBeforeEach() throws Exception {}

    /**
     * Test with no user
     */
    @Test
    public void UserNotIdentifiedExceptionFail() {
        final ServiceInterceptor serviceInterceptor = new ServiceInterceptor();

        final Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        final User user = new User("null", "null", authorities);
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Assertions.assertThrows(UserNotIdentifiedException.class,
            () -> serviceInterceptor.checkIsRegistred());

    }

    /**
     * Test with no user
     */
    @Test
    public void UserAccountNotRegistredFail() {
        final ServiceInterceptor serviceInterceptor = new ServiceInterceptor(userAccountRepository);

        final Collection<FakeGrantedAuthority> authorities = new ArrayList<>();
        final FakeGrantedAuthority fakeGrantedAuthority = new FakeGrantedAuthority();
        authorities.add(fakeGrantedAuthority);

        final Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", this.factory.getRandomInteger());

        final DefaultOAuth2User user = new DefaultOAuth2User(authorities, attributes, "id");
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Assertions.assertThrows(UserAccountNotRegistred.class,
            () -> serviceInterceptor.checkIsRegistred());

    }

    /**
     * Test with user
     */
    @Test
    public void CheckRegistrationOk() throws UserAccountNotRegistred, UserNotIdentifiedException {
        final UserAccount userAccount = this.factory.getUserAccount();
        this.userAccountRepository.save(userAccount);

        final ServiceInterceptor serviceInterceptor = new ServiceInterceptor(userAccountRepository);

        final Collection<FakeGrantedAuthority> authorities = new ArrayList<>();
        final FakeGrantedAuthority fakeGrantedAuthority = new FakeGrantedAuthority();
        authorities.add(fakeGrantedAuthority);

        final Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", userAccount.getBlizzardId());

        final DefaultOAuth2User user = new DefaultOAuth2User(authorities, attributes, "id");
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        serviceInterceptor.checkIsRegistred();

    }

    /**
     * Fake granted authority
     */
    private class FakeGrantedAuthority implements GrantedAuthority {

        private static final long serialVersionUID = 1L;
    
        @Override
        public String getAuthority() {
            return "Authority";
        }
    
    }
    
}