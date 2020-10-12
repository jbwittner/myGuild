package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.repository.AbstractRepository;

import java.util.Optional;

/**
 * Repository for Faction
 */
public interface FactionRepository extends AbstractRepository<Faction, Integer> {

    Optional<Faction> findByType(String type);

}
