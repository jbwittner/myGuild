package fr.opendoha.myguild.server.it.service.blizzardgamedata;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableClassDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;

/**
 * Class to check the updateData method of BlizzardGameData
 */
public class UpdateDataTest extends MotherBlizzardGameDataTest {

    /**
     * Method to check the data after the tests
     */
    private void checkDataAfter() {
        final long countPlayableClass = this.playableClassRepository.count();
        final long countPlayableRepository = this.playableSpecializationRepository.count();
        final long countSpecializationRole = this.specializationRoleRepository.count();

        Assertions.assertEquals(this.listPlayableClassDTO.size(), countPlayableClass);
        Assertions.assertEquals(this.listPlayableSpecializationDTO.size(), countPlayableRepository);
        Assertions.assertEquals(this.listSpecializationRoleDTO.size(), countSpecializationRole);

        for(final PlayableClassDTO playableClassDTO : this.listPlayableClassDTO){
            final PlayableClass playableClass = this.playableClassRepository.findById(playableClassDTO.getId()).get();
            Assertions.assertEquals(playableClassDTO.getId(), playableClass.getId());
            Assertions.assertEquals(playableClassDTO.getName().getEn_US(), playableClass.getNames().getEn_US());
            Assertions.assertEquals(playableClassDTO.getName().getFr_FR(), playableClass.getNames().getFr_FR());
        }

        for(final PlayableSpecializationDTO playableSpecializationDTO : this.listPlayableSpecializationDTO){
            final PlayableSpecialization playableSpecialization = this.playableSpecializationRepository.findById(playableSpecializationDTO.getId()).get();
            Assertions.assertEquals(playableSpecializationDTO.getId(), playableSpecialization.getId());
            Assertions.assertEquals(playableSpecializationDTO.getName().getEn_US(), playableSpecialization.getNames().getEn_US());
            Assertions.assertEquals(playableSpecializationDTO.getName().getFr_FR(), playableSpecialization.getNames().getFr_FR());
            Assertions.assertEquals(playableSpecializationDTO.getPlayable_class().getId(), playableSpecialization.getPlayableClass().getId());
            Assertions.assertEquals(playableSpecializationDTO.getRole().getType(), playableSpecialization.getSpecializationRole().getType());
            Assertions.assertEquals(playableSpecializationDTO.getRole().getName().getEn_US(), playableSpecialization.getSpecializationRole().getNames().getEn_US());
            Assertions.assertEquals(playableSpecializationDTO.getRole().getName().getFr_FR(), playableSpecialization.getSpecializationRole().getNames().getFr_FR());
        }

        final List<PlayableClass> listPlayableClass = this.playableClassRepository.findAll();

        for(final PlayableClass playableClass : listPlayableClass){
            Assertions.assertEquals(true, playableClass.getIsUdpated());
        }

        final List<PlayableSpecialization> listPlayableSpecialization = this.playableSpecializationRepository.findAll();

        for(final PlayableSpecialization playableSpecialization : listPlayableSpecialization){
            Assertions.assertEquals(true, playableSpecialization.getIsUdpated());
        }

        final List<SpecializationRole> listSpecializationRole = this.specializationRoleRepository.findAll();

        for(final SpecializationRole specializationRole : listSpecializationRole){
            Assertions.assertEquals(true, specializationRole.getIsUdpated());
        }
    }

    /**
     * Test to verify the first use of data update method
     */
    @Test
    public void firstUpdateTestOk() throws IOException {

        this.blizzardGameData.updateData();

        this.checkDataAfter();

    }

    /**
     * Test to verify multiple use of data update method with same data
     */
    @Test
    public void multipleUpdateSameDataOk() throws IOException {

        this.blizzardGameData.updateData();

        this.prepareDatas();

        this.prepareDTOMock();

        this.blizzardGameData.updateData();

        this.checkDataAfter();

    }

    /**
     * Test to verify multiple use of data update method with new data
     */
    @Test
    public void multipleUpdateNewDataOk() throws IOException {

        this.blizzardGameData.updateData();

        this.blizzardGameData.updateData();

        this.checkDataAfter();

    }
}
