package fr.opendoha.myguild.server.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;

import java.util.Objects;

/**
 * Mother controller
 */
public class MotherController {

    protected OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    /**
     * Constructor
     */
    public MotherController(final OAuth2AuthorizedClientService oAuth2AuthorizedClientService){

        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;

    }

    /**
     * Constructor
     */
    public MotherController(){
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Method to get the battle tag from the authentication
     */
    protected String getBattleTag(final OAuth2AuthenticationToken authentication){

        final String battleTag = Objects.requireNonNull(
                authentication.getPrincipal().getAttribute("battletag")
        ).toString();

        return battleTag;
    }

    /**
     * Method to get the blizzard id tag from the authentication
     */
    protected Integer getBlizzardId(final OAuth2AuthenticationToken authentication){
        
        final Integer id = Integer.parseInt(
                Objects.requireNonNull(
                        authentication.getPrincipal().getAttribute("id")
                ).toString()
        );

        return id;
    }

    /**
     * Method used to get the blizzard account information
     */
    protected BlizzardAccountParameter getBlizzardAccountParameter(final OAuth2AuthenticationToken authentication) {

        final BlizzardAccountParameter blizzardAccountParameter = new BlizzardAccountParameter();

        final OAuth2AuthorizedClient authorizedClient = this.oAuth2AuthorizedClientService
                .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        final String token = authorizedClient.getAccessToken().getTokenValue();

        blizzardAccountParameter.setToken(token);

        final Integer blizzardId = this.getBlizzardId(authentication);

        blizzardAccountParameter.setBlizzardId(blizzardId);

        return blizzardAccountParameter;
    }

}
