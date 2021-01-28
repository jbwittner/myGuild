package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.GuildDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.exception.AreNotGuildMasterException;
import fr.opendoha.myguild.server.exception.GuildDoesNotUseApplication;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.exception.UnexpectedException;
import fr.opendoha.myguild.server.parameters.AddingGuildParameter;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the guild service
 */
public interface GuildService {

    /**
     * Method used to get all guild where are the user
     */
    List<GuildSummaryDTO> getGuildFromAccount(final BlizzardAccountParameter blizzardAccountParameter) throws IOException;

    /**
     * Method used to add a guild to the application
     */
    void addingGuild(final AddingGuildParameter addingGuildParameter) throws IOException, GuildNotExistedException, AreNotGuildMasterException, UnexpectedException;

    /**
     * Method used to get data from the guild using the app
     */
    GuildDTO getGuild(final Integer guildId) throws GuildNotExistedException, GuildDoesNotUseApplication, IOException;

}
