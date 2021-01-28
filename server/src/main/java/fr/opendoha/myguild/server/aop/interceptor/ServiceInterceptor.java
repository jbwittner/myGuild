package fr.opendoha.myguild.server.aop.interceptor;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import fr.opendoha.myguild.server.exception.UserAccountNotRegistred;
import fr.opendoha.myguild.server.exception.UserNotIdentifiedException;
import fr.opendoha.myguild.server.exception.ValidationDataException;
import fr.opendoha.myguild.server.repository.UserAccountRepository;

/**
 * Interceptor used for services
 */
@Aspect
@Component
@Profile("!test")
public class ServiceInterceptor extends MotherInterceptor {

    protected final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    protected UserAccountRepository userAccountRepository;

    /**
     * Constructor
     */
    public ServiceInterceptor(){
        super();
    }

    /**
     * Constructor
     */
    @Autowired
    public ServiceInterceptor(final UserAccountRepository userAccountRepository){
        super();
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Interceptor used to check the input data validity
     */
    @Before("execution(* fr.opendoha.myguild.server.service.*.*(..))")
    public void validationInterceptor(final JoinPoint joinPoint) throws ValidationDataException {
        this.validationInputData(joinPoint, this.logger);
    }

    /**
     * Interceptor used to check if the user is registered on the application
     */
    @Before("execution(* fr.opendoha.myguild.server.service.*.*(..)) && !@annotation(fr.opendoha.myguild.server.annotation.IgnoreCheckUser)")
    public void checkIsRegistred() throws UserAccountNotRegistred, UserNotIdentifiedException {

        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof DefaultOAuth2User){
            final DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) principal;
            final Integer id = defaultOAuth2User.getAttribute("id");
 
            final boolean accountExist = this.userAccountRepository.existsByBlizzardId(id);

            if(!accountExist){
                final String battletag = defaultOAuth2User.getAttribute("battletag");
                throw new UserAccountNotRegistred(battletag);
            }

        } else {
            throw new UserNotIdentifiedException();
        }
    }

}