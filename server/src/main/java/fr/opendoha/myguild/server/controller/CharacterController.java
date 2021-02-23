package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.exception.IsNotCharacterAccountException;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;
import fr.opendoha.myguild.server.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Constructor
     */
    @Autowired
    public CharacterController(final CharacterService characterService,
            final OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        super(oAuth2AuthorizedClientService);
        this.characterService = characterService;
    }

    /**
     * Method used to fetch account characters
     */
    @GetMapping("/fetchCharacterAccount")
    public List<CharacterSummaryDTO> fetchCharacterAccount(final OAuth2AuthenticationToken authentication)
            throws IOException {

        final BlizzardAccountParameter parameter = this.getBlizzardAccountParameter(authentication);

        return this.characterService.fetchCharacterAccount(parameter);
    }

    /**
     * Method used to get account characters
     */
    @GetMapping("/getCharacterAccount")
    public List<CharacterSummaryDTO> getCharacterAccount(final OAuth2AuthenticationToken authentication) {

        final BlizzardAccountParameter parameter = this.getBlizzardAccountParameter(authentication);

        return this.characterService.getCharacterAccount(parameter);
    }

    /**
     * Method used to set a favorite character
     */
    @PostMapping("/setFavoriteCharacter")
    public void setFavoriteCharacter(final OAuth2AuthenticationToken authentication,
            final @RequestBody FavoriteCharacterParameter parameter)
            throws CharacterNotExistedException, IsNotCharacterAccountException {

        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);
        parameter.setBlizzardId(blizzardAccountParameter.getBlizzardId());
        parameter.setToken(blizzardAccountParameter.getToken());

        this.characterService.setFavoriteCharacter(parameter);

    }

}
