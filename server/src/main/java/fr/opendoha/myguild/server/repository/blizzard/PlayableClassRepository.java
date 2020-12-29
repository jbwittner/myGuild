package fr.opendoha.myguild.server.repository.blizzard;

import java.util.List;

import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.repository.AbstractRepository;

/**
 * Repository for PlayableClass
 */
public interface PlayableClassRepository extends AbstractRepository<PlayableClass, Integer> {

    /**
     * Method to find a playable class by is updated
     */
    List<PlayableClass> findByIsUpdatedTrue();

}
