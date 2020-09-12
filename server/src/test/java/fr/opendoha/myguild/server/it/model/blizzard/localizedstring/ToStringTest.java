package fr.opendoha.myguild.server.it.model.blizzard.localizedstring;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.LocalizedString;

/**
 * Class to test toString method of LocalizedString
 */
public class ToStringTest extends AbstractMotherIntegrationTest {
    
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
        final LocalizedString localizedString = this.factory.createLocalizedString();

        final String toStringExpectex = "LocalizedString [en_US=" + localizedString.getEn_US() + ", fr_FR=" + localizedString.getFr_FR() + "]";

        Assertions.assertEquals(toStringExpectex, localizedString.toString());
    }
    
}