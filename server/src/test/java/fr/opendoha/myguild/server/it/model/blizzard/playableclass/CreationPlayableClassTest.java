package fr.opendoha.myguild.server.it.model.blizzard.playableclass;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;

/**
 * Class to test the creation of playable class
 */
public class CreationPlayableClassTest extends AbstractMotherIntegrationTest{

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected PlayableSpecializationRepository playableSpecializationRepository;

    @Autowired
    protected SpecializationRoleRepository specializationRoleRepository;

    protected PlayableClass playableClassOne;
    protected PlayableClass playableClassTwo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        List<PlayableSpecialization> playableSpecializations;

        this.playableClassOne = this.factory.createPlayableClassWithSpecialization();

        playableSpecializations = playableClassOne.getPlayableSpecializations();

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            this.specializationRoleRepository.saveAndFlush(playableSpecialization.getSpecializationRole());
            this.playableSpecializationRepository.saveAndFlush(playableSpecialization);
        }

        this.playableClassTwo = this.factory.createPlayableClassWithSpecialization();

        playableSpecializations = playableClassTwo.getPlayableSpecializations();

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            this.specializationRoleRepository.saveAndFlush(playableSpecialization.getSpecializationRole());
            this.playableSpecializationRepository.saveAndFlush(playableSpecialization);
        }

    }

    /**
     * Test to check the good creation of playable class
     */
    @Test
    public void createPlayableClassTestOk(){

        this.playableClassRepository.saveAndFlush(this.playableClassOne);

        final PlayableClass playableClassSaved = playableClassRepository.findById(this.playableClassOne.getId()).get();

        Assertions.assertEquals(this.playableClassOne, playableClassSaved);
    }

    /**
     * Test to check the good creation of playable class with a duplicate name
     */
    @Test
    public void createDuplicateNameTestOk(){

        this.playableClassTwo.setNames(playableClassOne.getNames());

        this.playableClassRepository.save(playableClassOne);
        this.playableClassRepository.save(playableClassTwo);

        this.playableClassRepository.flush();
    }

    /**
     * Test to check the good creation of playable class with a null name
     */
    @Test
    public void createNullNameTestNOk(){

        this.playableClassOne.setNames(null);

        this.playableClassRepository.save(playableClassOne);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            this.playableClassRepository.saveAndFlush(playableClassOne);
        });
    }

    /**
     * Test to check the good creation of playable class with a duplicate playable specialization
     */
    @Test
    public void createDuplicatePlayableSpecializationsTestOk(){

        this.playableClassTwo.setPlayableSpecializations(playableClassOne.getPlayableSpecializations());

        this.playableClassRepository.save(playableClassOne);
        this.playableClassRepository.save(playableClassTwo);

        this.playableClassRepository.flush();
    }

    /**
     * Test to check the good creation of playable class with a null playable specialization
     */
    @Test
    public void createPlayableSpecializationsNullPlayableSpecializationTestOk(){

        this.playableClassOne.setPlayableSpecializations(null);

        this.playableClassRepository.save(playableClassOne);

        this.playableClassRepository.saveAndFlush(playableClassOne);
    }
    
}