package fr.jbwittner.myguild.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class configuration used to configure spring security
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Method used to set the password encoder of the application
     * @return PasswordEncoder used to encrypt passwords
     */
    @Bean
    public PasswordEncoder encoder() {
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    
}