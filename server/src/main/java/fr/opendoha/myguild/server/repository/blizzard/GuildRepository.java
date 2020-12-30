package fr.opendoha.myguild.server.repository.blizzard;


import java.util.Optional;

import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.repository.AbstractRepository;

/**
 * Repository for Guild
 */
public interface GuildRepository extends AbstractRepository<Guild, Integer> {

    /**
     * Find guild by name
     */
    Optional<Guild> findByName(String name);

    /**
     * Find guild by name and realm
     */
    Optional<Guild> findByNameAndRealm(String name, Realm realm);

}
