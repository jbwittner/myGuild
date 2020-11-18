package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildsAccountDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.service.BlizzardService;
import fr.opendoha.myguild.server.service.IBlizzardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Controller used to manage blizzard data
 */
@RestController
@RequestMapping("blizzard")
public class BlizzardController extends MotherController {

    protected final IBlizzardService blizzardService;
    protected final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

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
     * Method used to fetch account characters
     */
    @GetMapping("/fetchAccountCharacter")
    public List<CharacterSummaryDTO> fetchAccountCharacter(final OAuth2AuthenticationToken authentication){

        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);

        return this.blizzardService.fetchCharacterAccount(blizzardAccountParameter);
    }

    /**
     * Method used to fetch all static data (playable class, playable race, etc...)
     */
    @GetMapping("/fetchStaticData")
    public void fetchStaticData() throws IOException {
        this.blizzardService.fetchStaticData();
    }

    @GetMapping("/getStaticData")
    public StaticDataDTO getStaticData(){
        return this.blizzardService.getStaticData();
    }

    /**
     * Method to get all guilds who the account have a character 
     */
    @GetMapping("/getGuildsAccount")
    public List<GuildSummaryDTO> getGuildsAccount(final OAuth2AuthenticationToken authentication) throws IOException {

        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);

        return this.blizzardService.fetchGuildsAccount(blizzardAccountParameter);

    }

}
