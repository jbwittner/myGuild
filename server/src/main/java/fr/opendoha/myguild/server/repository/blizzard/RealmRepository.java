package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.repository.AbstractRepository;

import java.util.Optional;


/**
 * Repository for Realm
 */
public interface RealmRepository extends AbstractRepository<Realm, Integer> {

    Optional<Realm> findBySlug(String slug);

}
