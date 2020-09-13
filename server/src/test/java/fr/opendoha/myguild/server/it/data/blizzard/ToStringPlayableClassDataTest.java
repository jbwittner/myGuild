package fr.opendoha.myguild.server.it.data.blizzard;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedValuesData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationData;
import fr.opendoha.myguild.server.data.blizzardgamedata.SpecializationRoleData;

/**
 * Class to test toString method of PlayableClassData
 */
public class ToStringPlayableClassDataTest extends AbstractMotherIntegrationTest {

    private static Integer LENGTH_VALUE = 20;

    static final Integer PLAYABLE_SPECIALIZATION = 3;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {}

    /**
     * Test of toString
     */
    @Test
    public void testToStringOk(){

        PlayableSpecializationData[] playableSpecializations = new PlayableSpecializationData[PLAYABLE_SPECIALIZATION];

        for(int i = 0; i < PLAYABLE_SPECIALIZATION; i++){

            final LocalizedValuesData localizedValuesSpecializationData = new LocalizedValuesData();
            localizedValuesSpecializationData.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
            localizedValuesSpecializationData.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

            final LocalizedValuesData localizedValuesRoleData = new LocalizedValuesData();
            localizedValuesRoleData.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
            localizedValuesRoleData.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

            final SpecializationRoleData specializationRoleData = new SpecializationRoleData();
            specializationRoleData.setName(localizedValuesRoleData);
            specializationRoleData.setType(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

            final PlayableSpecializationData playableSpecializationData = new PlayableSpecializationData();
            playableSpecializationData.setId(this.factory.getRandomInteger());
            playableSpecializationData.setName(localizedValuesSpecializationData);
            playableSpecializationData.setRole(specializationRoleData);

            playableSpecializations[i] = playableSpecializationData;

        }

        final LocalizedValuesData localizedValuesClassData = new LocalizedValuesData();
        localizedValuesClassData.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
        localizedValuesClassData.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

        final PlayableClassData playableClassData = new PlayableClassData();
        playableClassData.setId(this.factory.getRandomInteger());
        playableClassData.setName(localizedValuesClassData);
        playableClassData.setSpecializations(playableSpecializations);

        final String toStringExpectex = "PlayableClassData [id=" + playableClassData.getId() + ", name=" + playableClassData.getName() + ", specializations=" + Arrays.toString(playableClassData.getSpecializations()) + "]";

        Assertions.assertEquals(toStringExpectex, playableClassData.toString());
    }

}