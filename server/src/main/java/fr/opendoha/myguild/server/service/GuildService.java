package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.dto.CharacterGuildSummaryDTO;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the guild service
 */
public interface GuildService {

    /**
     * Method used to fetch all data of the principal guild
     */
    void fetchPrincipalGuild() throws IOException;

    /**
     * Method used to fetch all members of the guild
     */
    void fetchGuildMembers() throws IOException;

    /**
     * Method used to get all members of the guild
     */
    List<CharacterGuildSummaryDTO> getGuildMembers();

}
