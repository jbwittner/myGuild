package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.parameters.AddingGuildParameter;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.service.BlizzardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller used to manage blizzard data
 */
@RestController
@RequestMapping("blizzard")
public class BlizzardController extends MotherController {

    protected final BlizzardService blizzardService;
    protected final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    /**
     * Method used to get the blizzard account information
     */
    protected BlizzardAccountParameter getBlizzardAccountParameter(final OAuth2AuthenticationToken authentication){

        final BlizzardAccountParameter blizzardAccountParameter = new BlizzardAccountParameter();

        final OAuth2AuthorizedClient authorizedClient =
                this.oAuth2AuthorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());

        final String token = authorizedClient.getAccessToken().getTokenValue();

        blizzardAccountParameter.setToken(token);

        final Integer blizzardId = this.getBlizzardId(authentication);

        blizzardAccountParameter.setBlizzardId(blizzardId);

        return blizzardAccountParameter;
    }

    /**
     * Constructor
     */
    @Autowired
    public BlizzardController(final BlizzardService blizzardService,
                              final OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        super();
        this.blizzardService = blizzardService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    /**
     * Method
     */
    @GetMapping("/fetchAccountCharacter")
    public void fetchAccountCharacter(final OAuth2AuthenticationToken authentication){

        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);

        this.blizzardService.fetchAccountCharacter(blizzardAccountParameter);
    }

}
