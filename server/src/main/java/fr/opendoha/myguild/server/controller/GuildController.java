package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.exception.AreNotGuildMasterException;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.exception.UnexpectedException;
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

    /**
     * Constructor
     */
    @Autowired
    public GuildController(final GuildService guildService,
            final OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        super(oAuth2AuthorizedClientService);
        this.guildService = guildService;
    }


    /**
     * Method used to initialize the guild
     */
    @GetMapping("/initialize")
    public void initialize()
            throws IOException, GuildNotExistedException, AreNotGuildMasterException, UnexpectedException {
        this.guildService.initialize();
    }

    /**
     * Method used to get data from the guild using the app
     */
    @GetMapping("/getGuildInformations")
    public GuildSummaryDTO getGuildInformations() throws IOException {
        return this.guildService.getGuildInformations();
    }

    /**
     * Method used to get the roster of the guild using the app
     */
    @GetMapping("/getGuildRoster")
    public List<CharacterSummaryDTO> getGuildRoster() throws IOException {
        return this.guildService.getGuildRoster();
    }

    /**
     * Method used to update data from the guild using the app
     */
    @GetMapping("/updateGuild")
    public GuildDTO updateGuild() throws IOException, UnexpectedException {
        return this.guildService.updateGuild();
    }

}
