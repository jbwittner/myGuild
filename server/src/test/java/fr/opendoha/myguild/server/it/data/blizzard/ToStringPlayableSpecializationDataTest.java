package fr.opendoha.myguild.server.it.data.blizzard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedValuesData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationData;
import fr.opendoha.myguild.server.data.blizzardgamedata.SpecializationRoleData;

/**
 * Class to test toString method of PlayableSpecializationData
 */
public class ToStringPlayableSpecializationDataTest extends AbstractMotherIntegrationTest {

    private static Integer LENGTH_VALUE = 20;

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

        final String toStringExpectex = "PlayableSpecializationData [id=" + playableSpecializationData.getId() + ", name=" + playableSpecializationData.getName() + ", role=" + playableSpecializationData.getRole() + "]";

        Assertions.assertEquals(toStringExpectex, playableSpecializationData.toString());
    }

}