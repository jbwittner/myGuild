package fr.opendoha.myguild.server.repository.blizzard;


import java.util.Optional;

import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.repository.AbstractRepository;

/**
 * Repository for Guild
 */
public interface GuildRepository extends AbstractRepository<Guild, Integer> {

    Optional<Guild> findByName(String name);

}
