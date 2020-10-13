package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.repository.AbstractRepository;

import java.util.Optional;

/**
 * Repository for Faction
 */
public interface FactionRepository extends AbstractRepository<Faction, Integer> {

    /**
     * Method to find a faction by type
     */
    Optional<Faction> findByType(String type);

}
