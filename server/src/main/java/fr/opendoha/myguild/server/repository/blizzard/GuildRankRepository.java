package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.blizzard.GuildRank;
import fr.opendoha.myguild.server.repository.AbstractRepository;

import java.util.Optional;

/**
 * Repository for GuildRank
 */
public interface GuildRankRepository extends AbstractRepository<GuildRank, Integer> {

    /**
     * Find a guild rank model by rank
     */
    Optional<GuildRank> findByRank(Integer rank);

}
