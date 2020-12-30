package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;
import fr.opendoha.myguild.server.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Controller used to manage blizzard data
 */
@RestController
@RequestMapping("character")
public class CharacterController extends MotherController {

    protected final CharacterService characterService;
    protected final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    /**
     * Constructor
     */
    @Autowired
    public CharacterController(final CharacterService characterService,
            final OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        super();
        this.characterService = characterService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
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

    /**
     * Method used to fetch account characters
     */
    @GetMapping("/fetchAccountCharacter")
    public List<CharacterSummaryDTO> fetchAccountCharacter(final OAuth2AuthenticationToken authentication)
            throws IOException {

        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);

        return this.characterService.fetchCharacterAccount(blizzardAccountParameter);
    }

    /**
     * Method used to set a favorite character
     */
    @PostMapping("/setFavoriteCharacter")
    public void setFavoriteCharacter(final OAuth2AuthenticationToken authentication,
            final @RequestBody FavoriteCharacterParameter parameter) throws IOException, CharacterNotExistedException {

        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);

        this.characterService.setFavoriteCharacter(blizzardAccountParameter, parameter);

    }

}
