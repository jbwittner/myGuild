package fr.opendoha.myguild.server.it.model.blizzard.specializationrole;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;

/**
 * Class to test the creation of specialization role
 */
public class CreateSpecializationRoleTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected SpecializationRoleRepository specializationRoleRepository;

    @Autowired
    protected PlayableSpecializationRepository playableSpecializationRepository;

    protected SpecializationRole specializationRoleOne;
    protected SpecializationRole specializationRoleTwo;


    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        List<PlayableSpecialization> playableSpecializations;

        this.specializationRoleOne = this.factory.createSpecilizationRoleWithPlayableSpecialization();

        playableSpecializations = specializationRoleOne.getPlayableSpecializations();

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            this.playableSpecializationRepository.saveAndFlush(playableSpecialization);
        }

        this.specializationRoleTwo = this.factory.createSpecilizationRoleWithPlayableSpecialization();

        playableSpecializations = specializationRoleTwo.getPlayableSpecializations();

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            this.playableSpecializationRepository.saveAndFlush(playableSpecialization);
        }
    }

    /**
     * Test to check the good creation of specialization role
     */
    @Test
    public void createSpecializationRoleTestOk(){

        this.specializationRoleRepository.saveAndFlush(this.specializationRoleOne);

        final SpecializationRole specializationRoleSaved = specializationRoleRepository.findById(this.specializationRoleOne.getId()).get();

        Assertions.assertEquals(this.specializationRoleOne, specializationRoleSaved);
    }

    /**
     * Test to check if we have a exception when we try to create a specialization role without names
     */
    @Test
    public void createSpecializationRoleNullNamesNOk(){

        this.specializationRoleOne.setNames(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            this.specializationRoleRepository.saveAndFlush(this.specializationRoleOne);
        });
    }

    /**
     * Test to check the good creation of specialization role with a duplicate name
     */
    @Test
    public void createSpecializationRoleDuplicateNamesTestOk(){

        this.specializationRoleOne.setNames(this.specializationRoleTwo.getNames());

        this.specializationRoleRepository.saveAndFlush(this.specializationRoleOne);

        final SpecializationRole specializationRoleSaved = specializationRoleRepository.findById(this.specializationRoleOne.getId()).get();

        Assertions.assertEquals(this.specializationRoleOne, specializationRoleSaved);
    }

    /**
     * Test to check the good creation of specialization role with a null type
     */
    @Test
    public void createSpecializationRoleNullTypeTestNOk(){

        this.specializationRoleOne.setType(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            this.specializationRoleRepository.saveAndFlush(this.specializationRoleOne);
        });
    }

    /**
     * Test to check the good creation of specialization role with a duplicate type
     */
    @Test
    public void createSpecializationRoleDuplicateTypeTestOk(){

        this.specializationRoleOne.setType(this.specializationRoleTwo.getType());

        this.specializationRoleRepository.saveAndFlush(this.specializationRoleOne);

        final SpecializationRole specializationRoleSaved = specializationRoleRepository.findById(this.specializationRoleOne.getId()).get();

        Assertions.assertEquals(this.specializationRoleOne, specializationRoleSaved);
    }
    
}