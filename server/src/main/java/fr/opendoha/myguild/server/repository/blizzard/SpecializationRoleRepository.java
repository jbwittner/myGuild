package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.repository.AbstractRepository;

/**
 * Repository for SpecializationRole
 */
public interface SpecializationRoleRepository extends AbstractRepository<SpecializationRole, Integer>{

    /**
     * Method to find by type
     * @param type type to find
     * @return Specialization role with the type
     */
    SpecializationRole findByType(final String type);

    /**
     * Methode to check if the type exist
     * @param type type to check
     * @return true if the type exist, false otherwise
     */
    boolean existsByType(final String type);

    /**
     * Method to get the specialization role with the bigger id
     * @return specialization role with the biggest id
     */
    SpecializationRole findTopByOrderByIdDesc();
    
}