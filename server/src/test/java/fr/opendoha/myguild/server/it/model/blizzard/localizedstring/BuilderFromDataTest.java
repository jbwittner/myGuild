package fr.opendoha.myguild.server.it.model.blizzard.localizedstring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.LocalizedString;
import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedValuesData;
import fr.opendoha.myguild.server.tools.TestObjectFactory;

/**
 * Test class for builder from Data
 */
public class BuilderFromDataTest extends AbstractMotherIntegrationTest {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {}

    /**
     * Test to set values with the Data
     */
    @Test
    public void builderDataTestOk(){

    final LocalizedString localizedString = new LocalizedString();

    final LocalizedValuesData localizedValuesData = new LocalizedValuesData();

    final String En_US = this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_LOCALIZED_STRING);
    final String Fr_FR = this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_LOCALIZED_STRING);


    localizedValuesData.setEn_US(En_US);
    localizedValuesData.setFr_FR(Fr_FR);

    localizedString.builderFromData(localizedValuesData);

    Assertions.assertEquals(localizedString.getEn_US(), localizedValuesData.getEn_US());
    Assertions.assertEquals(localizedString.getFr_FR(), localizedValuesData.getFr_FR());

    }
}