package fr.opendoha.myguild.server.it.service.blizzardgamedata;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationData;
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

        Assertions.assertEquals(this.listPlayableClassData.size(), countPlayableClass);
        Assertions.assertEquals(this.listPlayableSpecializationData.size(), countPlayableRepository);
        Assertions.assertEquals(this.listSpecializationRoleData.size(), countSpecializationRole);

        for(final PlayableClassData playableClassData : this.listPlayableClassData){
            final PlayableClass playableClass = this.playableClassRepository.findById(playableClassData.getId()).get();
            Assertions.assertEquals(playableClassData.getId(), playableClass.getId());
            Assertions.assertEquals(playableClassData.getName().getEn_US(), playableClass.getNames().getEn_US());
            Assertions.assertEquals(playableClassData.getName().getFr_FR(), playableClass.getNames().getFr_FR());
        }

        for(final PlayableSpecializationData playableSpecializationData : this.listPlayableSpecializationData){
            final PlayableSpecialization playableSpecialization = this.playableSpecializationRepository.findById(playableSpecializationData.getId()).get();
            Assertions.assertEquals(playableSpecializationData.getId(), playableSpecialization.getId());
            Assertions.assertEquals(playableSpecializationData.getName().getEn_US(), playableSpecialization.getNames().getEn_US());
            Assertions.assertEquals(playableSpecializationData.getName().getFr_FR(), playableSpecialization.getNames().getFr_FR());
            Assertions.assertEquals(playableSpecializationData.getPlayable_class().getId(), playableSpecialization.getPlayableClass().getId());
            Assertions.assertEquals(playableSpecializationData.getRole().getType(), playableSpecialization.getSpecializationRole().getType());
            Assertions.assertEquals(playableSpecializationData.getRole().getName().getEn_US(), playableSpecialization.getSpecializationRole().getNames().getEn_US());
            Assertions.assertEquals(playableSpecializationData.getRole().getName().getFr_FR(), playableSpecialization.getSpecializationRole().getNames().getFr_FR());
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

        this.prepareDataMock();

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
