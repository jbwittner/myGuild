package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.CharacterGuildSummaryDTO;
import fr.opendoha.myguild.server.service.GuildService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Controller used to manage blizzard data
 */
@RestController
@RequestMapping("guild")
public class GuildController extends MotherController {

    protected final GuildService guildService;
    protected final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    /**
     * Constructor
     */
    @Autowired
    public GuildController(final GuildService guildService,
            final OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        super();
        this.guildService = guildService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    /**
     * Method used to fetch all data of the principal guild
     */
    @GetMapping("/fetchPrincipalGuild")
    public void fetchPrincipalGuild() throws IOException {
        this.guildService.fetchPrincipalGuild();
    }

    /**
     * Method used to fetch all members of the guild
     */
    @GetMapping("/fetchGuildMembers")
    public void fetchGuildMembers() throws IOException {
        this.guildService.fetchGuildMembers();
    }

    /**
     * Method used to fetch all members of the guild
     */
    @GetMapping("/getGuildMembers")
    public List<CharacterGuildSummaryDTO> getGuildMembers() {
        return this.guildService.getGuildMembers();
    }


}
