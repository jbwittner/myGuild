package fr.opendoha.myguild.server.controller;

import fr.opendoha.myguild.server.dto.GuildDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.exception.AreNotGuildMasterException;
import fr.opendoha.myguild.server.exception.GuildDoesNotUseApplication;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.exception.UnexpectedException;
import fr.opendoha.myguild.server.parameters.AddingGuildParameter;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.service.GuildService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * Method used to fetch all data of the principal guild
     */
    @GetMapping("/getGuildFromAccount")
    public List<GuildSummaryDTO> getGuildFromAccount(final OAuth2AuthenticationToken authentication)
            throws IOException {
        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);
        return this.guildService.getGuildFromAccount(blizzardAccountParameter);
    }

    /**
     * Method used to fetch all data of the principal guild
     */
    @PostMapping("/addingGuild")
    public void addingGuild(final OAuth2AuthenticationToken authentication,
            final @RequestBody AddingGuildParameter addingGuildParameter)
            throws IOException, GuildNotExistedException, AreNotGuildMasterException, UnexpectedException {
        final BlizzardAccountParameter blizzardAccountParameter = this.getBlizzardAccountParameter(authentication);
        addingGuildParameter.setBlizzardId(blizzardAccountParameter.getBlizzardId());
        addingGuildParameter.setToken(blizzardAccountParameter.getToken());
        this.guildService.addingGuild(addingGuildParameter);
    }

    /**
     * Method used to get data from the guild using the app
     */
    @GetMapping("/getGuild/{guildId}")
    public GuildDTO getGuild(final @PathVariable Integer guildId)
            throws GuildNotExistedException, GuildDoesNotUseApplication, IOException {
        return this.guildService.getGuild(guildId);
    }

}
