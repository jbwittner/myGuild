package fr.opendoha.myguild.server.it.data.blizzard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedValuesData;

/**
 * Class to test toString method of LocalizedValuesData
 */
public class ToStringLocalizedValuesDataTest extends AbstractMotherIntegrationTest {

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
        final LocalizedValuesData localizedValuesData = new LocalizedValuesData();
        localizedValuesData.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
        localizedValuesData.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

        final String toStringExpectex = "LocalizedValuesData [en_US=" + localizedValuesData.getEn_US() + ", fr_FR=" + localizedValuesData.getFr_FR() + "]";

        Assertions.assertEquals(toStringExpectex, localizedValuesData.toString());
    }
}