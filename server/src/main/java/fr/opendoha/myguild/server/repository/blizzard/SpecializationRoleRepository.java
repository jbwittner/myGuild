package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.repository.AbstractRepository;

import java.util.Optional;

/**
 * Repository for SpecializationRole
 */
public interface SpecializationRoleRepository extends AbstractRepository<SpecializationRole, Integer> {

    /**
     * Method to find a specialization role by type
     */
    Optional<SpecializationRole> findByType(String type);
}