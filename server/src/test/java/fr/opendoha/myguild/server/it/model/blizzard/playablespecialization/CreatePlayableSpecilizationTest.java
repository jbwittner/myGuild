package fr.opendoha.myguild.server.it.model.blizzard.playablespecialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;

/**
 * Class to test the creation of playable specialization
 */
public class CreatePlayableSpecilizationTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected PlayableSpecializationRepository playableSpecializationRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected SpecializationRoleRepository specializationRoleRepository;

    protected PlayableSpecialization playableSpecializationOne;
    protected PlayableSpecialization playableSpecializationTwo;


    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        this.playableSpecializationOne = this.factory.createPlayableSpecializationWithSpecializationRole();
        this.playableClassRepository.saveAndFlush(this.playableSpecializationOne.getPlayableClass());
        this.specializationRoleRepository.saveAndFlush(this.playableSpecializationOne.getSpecializationRole());


        this.playableSpecializationTwo = this.factory.createPlayableSpecializationWithSpecializationRole();
        this.playableClassRepository.saveAndFlush(this.playableSpecializationTwo.getPlayableClass());
        this.specializationRoleRepository.saveAndFlush(this.playableSpecializationTwo.getSpecializationRole());
    }

    /**
     * Test to check the good creation of playable specialization
     */
    @Test
    public void createPlayableClassTestOk(){

        this.playableSpecializationRepository.saveAndFlush(this.playableSpecializationOne);

        final PlayableSpecialization playableSpecializationSaved = playableSpecializationRepository.findById(this.playableSpecializationOne.getId()).get();

        Assertions.assertEquals(this.playableSpecializationOne, playableSpecializationSaved);
    }

    /**
     * Test to check the good creation of playable specialization with a null playable class
     */
    @Test
    public void createPlayableClassNullPlayableClassTestOk(){

        this.playableSpecializationOne.setPlayableClass(null);

        this.playableSpecializationRepository.saveAndFlush(this.playableSpecializationOne);

        final PlayableSpecialization playableSpecializationSaved = playableSpecializationRepository.findById(this.playableSpecializationOne.getId()).get();

        Assertions.assertEquals(this.playableSpecializationOne, playableSpecializationSaved);
    }

    /**
     * Test to check the good creation of playable specialization with a duplicate playable class
     */
    @Test
    public void createPlayableClassDuplicatePlayableClassTestOk(){

        this.playableSpecializationOne.setPlayableClass(this.playableSpecializationTwo.getPlayableClass());

        this.playableSpecializationRepository.saveAndFlush(this.playableSpecializationOne);

        final PlayableSpecialization playableSpecializationSaved = playableSpecializationRepository.findById(this.playableSpecializationOne.getId()).get();

        Assertions.assertEquals(this.playableSpecializationOne, playableSpecializationSaved);
    }

    /**
     * Test to check the good creation of playable specialization with a null names
     */
    @Test
    public void createPlayableClassNullNamesNOk(){

        this.playableSpecializationOne.setNames(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            this.playableSpecializationRepository.saveAndFlush(this.playableSpecializationOne);
        });

    }

    /**
     * Test to check the good creation of playable specialization with a duplicate names
     */
    @Test
    public void createPlayableClassDuplicateNamesTestOk(){

        this.playableSpecializationOne.setNames(this.playableSpecializationTwo.getNames());

        this.playableSpecializationRepository.saveAndFlush(this.playableSpecializationOne);

        final PlayableSpecialization playableSpecializationSaved = playableSpecializationRepository.findById(this.playableSpecializationOne.getId()).get();

        Assertions.assertEquals(this.playableSpecializationOne, playableSpecializationSaved);

    }


    /**
     * Test to check the good creation of playable specialization with a null specialization role
     */
    @Test
    public void createPlayableClassNullSpecializationRoleTestOk(){

        this.playableSpecializationOne.setSpecializationRole(null);

        this.playableSpecializationRepository.saveAndFlush(this.playableSpecializationOne);

        final PlayableSpecialization playableSpecializationSaved = playableSpecializationRepository.findById(this.playableSpecializationOne.getId()).get();

        Assertions.assertEquals(this.playableSpecializationOne, playableSpecializationSaved);

    }

    /**
     * Test to check the good creation of playable specialization with a duplicate specialization role
     */
    @Test
    public void createPlayableClassDuplicateSpecializationRoleOk(){

        this.playableSpecializationOne.setSpecializationRole(this.playableSpecializationTwo.getSpecializationRole());

        this.playableSpecializationRepository.saveAndFlush(this.playableSpecializationOne);

        final PlayableSpecialization playableSpecializationSaved = playableSpecializationRepository.findById(this.playableSpecializationOne.getId()).get();

        Assertions.assertEquals(this.playableSpecializationOne, playableSpecializationSaved);
    }
    
}