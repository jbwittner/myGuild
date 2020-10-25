package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.repository.AbstractRepository;

/**
 * Repository for Guild
 */
public interface GuildRepository extends AbstractRepository<Guild, Integer> {

    /**
     * Get the main guild
     */
    Guild findByIsMainGuildTrue();

}
