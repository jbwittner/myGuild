package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.exception.UnexpectedException;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the guild service
 */
public interface GuildService {

    /**
     * Method used to initialize the application
     */
    void initialize() throws IOException, GuildNotExistedException, UnexpectedException;

    /**
     * Method used to get data from the guild using the app
     */
    GuildSummaryDTO getGuildInformations() throws IOException;

    /**
     * Method used to get data from the guild using the app
     * @throws IOException
     */
    List<CharacterSummaryDTO> getGuildRoster() throws IOException;

    /**
     * Method used to update guild informations
     */
    GuildDTO updateGuild() throws IOException, UnexpectedException;

}
