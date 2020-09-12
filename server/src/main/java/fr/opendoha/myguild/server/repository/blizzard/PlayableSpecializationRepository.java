package fr.opendoha.myguild.server.repository.blizzard;

import java.util.List;

import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.repository.AbstractRepository;

/**
 * Repository for PlayableSpecialization
 */
public interface PlayableSpecializationRepository extends AbstractRepository<PlayableSpecialization, Integer>{

    /**
     * Method to find by specialization role
     * @param specializationRole specilization role to find
     * @return List of playable specialization
     */
    List<PlayableSpecialization> findBySpecializationRole(final SpecializationRole specializationRole);
    
}