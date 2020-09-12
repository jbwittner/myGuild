package fr.opendoha.myguild.server.it.model.blizzard.localizedstring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.dto.blizzard.LocalizedValuesDTO;
import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.LocalizedString;
import fr.opendoha.myguild.server.tools.TestObjectFactory;

/**
 * Test class for builder from DTO
 */
public class BuilderFromDTOTest extends AbstractMotherIntegrationTest {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {}

    /**
     * Test to set values with the DTO
     */
    @Test
    public void builderDTOTestOk(){

    final LocalizedString localizedString = new LocalizedString();

    final LocalizedValuesDTO localizedValuesDTO = new LocalizedValuesDTO();

    final String En_US = this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_LOCALIZED_STRING);
    final String Fr_FR = this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_LOCALIZED_STRING);


    localizedValuesDTO.setEn_US(En_US);
    localizedValuesDTO.setFr_FR(Fr_FR);

    localizedString.builderFromDTO(localizedValuesDTO);

    Assertions.assertEquals(localizedString.getEn_US(), localizedValuesDTO.getEn_US());
    Assertions.assertEquals(localizedString.getFr_FR(), localizedValuesDTO.getFr_FR());

    }
}