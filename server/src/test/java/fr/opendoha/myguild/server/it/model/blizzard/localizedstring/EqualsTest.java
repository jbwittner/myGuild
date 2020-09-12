package fr.opendoha.myguild.server.it.model.blizzard.localizedstring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.LocalizedString;

/**
 * Class to test equals method of LocalizedString
 */
public class EqualsTest extends AbstractMotherIntegrationTest {

    private LocalizedString localizedStringOne;
    private LocalizedString localizedStringTwo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        this.localizedStringOne = this.factory.createLocalizedString();
        this.localizedStringTwo = this.factory.createLocalizedString();
    }

    /**
     * Test with two different localized string
     */
    @Test
    public void isNotEqualsOk(){
        Assertions.assertNotEquals(this.localizedStringOne, this.localizedStringTwo);
    }

    /**
     * Test with null
     */
    @Test
    public void isNotEqualsNullOk(){
        Assertions.assertNotEquals(this.localizedStringOne, null);
    }

    /**
     * Test with same En_US
     */
    @Test
    public void isNotEqualSameEnUsOk(){
        this.localizedStringOne.setEn_US(this.localizedStringTwo.getEn_US());
        Assertions.assertNotEquals(this.localizedStringOne, this.localizedStringTwo);
    }

    /**
     * Test with same Fr_FR
     */
    @Test
    public void isNotEqualSameFrFROk(){
        this.localizedStringOne.setFr_FR(this.localizedStringTwo.getFr_FR());
        Assertions.assertNotEquals(this.localizedStringOne, this.localizedStringTwo);
    }

    /**
     * Test with same Fr_FR and same En_US
     */
    @Test
    public void isEqualSameFrFRSameEnUsOk(){
        this.localizedStringOne.setFr_FR(this.localizedStringTwo.getFr_FR());
        this.localizedStringOne.setEn_US(this.localizedStringTwo.getEn_US());
        Assertions.assertEquals(this.localizedStringOne, this.localizedStringTwo);
    }    
}