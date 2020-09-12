package fr.opendoha.myguild.server.it.dto.blizzard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.dto.blizzard.LocalizedValuesDTO;
import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;

/**
 * Class to test toString method of LocalizedValuesDTO
 */
public class ToStringLocalizedValuesDTOTest extends AbstractMotherIntegrationTest {

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
        final LocalizedValuesDTO localizedValuesDTO = new LocalizedValuesDTO();
        localizedValuesDTO.setEn_US(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));
        localizedValuesDTO.setFr_FR(this.factory.getUniqueRandomAsciiString(LENGTH_VALUE));

        final String toStringExpectex = "LocalizedValuesDTO [en_US=" + localizedValuesDTO.getEn_US() + ", fr_FR=" + localizedValuesDTO.getFr_FR() + "]";

        Assertions.assertEquals(toStringExpectex, localizedValuesDTO.toString());
    }
}