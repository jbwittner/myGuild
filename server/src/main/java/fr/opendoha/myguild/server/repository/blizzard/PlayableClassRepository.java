package fr.opendoha.myguild.server.repository.blizzard;

import java.util.List;

import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.repository.AbstractRepository;

/**
 * Repository for PlayableClass
 */
public interface PlayableClassRepository extends AbstractRepository<PlayableClass, Integer> {

    List<PlayableClass> findByIsUpdatedTrue();

}
