package fr.opendoha.myguild.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Class configuration used to configure spring security
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Configuration of security
     */
    @Override
    public void configure(final HttpSecurity http) throws Exception {

        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/", "index.html", "/favicon.ico", "/*manifest.json", "workbox-*/*.js", "/*.js", "/*.png", "/static/**", "/*.svg", "/*.jpg").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/")
                .and().oauth2Login();
    }

}