package fr.opendoha.myguild.server.it.model.blizzard.playablespecialization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;

/**
 * Class to test equals method of PlayableSpecialization
 */
public class EqualsTest extends AbstractMotherIntegrationTest {

    private PlayableSpecialization playableSpecializationOne;
    private PlayableSpecialization playableSpecializationTwo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        this.playableSpecializationOne = this.factory.createPlayableSpecializationWithSpecializationRole();
        this.playableSpecializationTwo = this.factory.createPlayableSpecializationWithSpecializationRole();
    }

    /**
     * Test with two same playable specialization
     */
    @Test
    public void isEqualsTestOk(){
        Assertions.assertEquals(this.playableSpecializationOne, this.playableSpecializationOne);
    }
    
    /**
     * Test with two different playable specialization
     */
    @Test
    public void isNotEqualsTestOk(){
        Assertions.assertNotEquals(this.playableSpecializationOne, this.playableSpecializationTwo);
    }

    /**
     * Test with a null
     */
    @Test
    public void isNotEqualsNullTestOk(){
        Assertions.assertNotEquals(this.playableSpecializationOne, null);
    }

    /**
     * Test with a null specialization class for the second playable specialization
     */
    @Test
    public void isNotEqualNullPlayableClassTwoTestOk(){
        this.playableSpecializationTwo.setPlayableClass(null);
        Assertions.assertNotEquals(this.playableSpecializationOne, this.playableSpecializationTwo);
    }

    /**
     * Test with a null specialization class for the first playable specialization
     */
    @Test
    public void isNotEqualNullPlayableClassOneTestOk(){
        this.playableSpecializationOne.setPlayableClass(null);
        Assertions.assertNotEquals(this.playableSpecializationOne, this.playableSpecializationTwo);
    }

    /**
     * Test with a null specialization class for the two playable specialization
     */
    @Test
    public void isNotEqualNullPlayableClassOneAndTwoTestOk(){
        this.playableSpecializationOne.setPlayableClass(null);
        this.playableSpecializationTwo.setPlayableClass(null);
        Assertions.assertNotEquals(this.playableSpecializationOne, this.playableSpecializationTwo);
    }

    /**
     * Test with a null specialization role for the second playable specialization
     */
    @Test
    public void isNotEqualNullSpecializationTwoTestOk(){
        this.playableSpecializationTwo.setSpecializationRole(null);
        Assertions.assertNotEquals(this.playableSpecializationOne, this.playableSpecializationTwo);
    }

    /**
     * Test with a null specialization role for the first playable specialization
     */
    @Test
    public void isNotEqualNullSpecializationOneTestOk(){
        this.playableSpecializationOne.setSpecializationRole(null);
        Assertions.assertNotEquals(this.playableSpecializationOne, this.playableSpecializationTwo);
    }

    /**
     * Test with a null specialization role for the two playable specialization
     */
    @Test
    public void isNotEqualNullSpecializationOneAndTwoTestOk(){
        this.playableSpecializationOne.setSpecializationRole(null);
        this.playableSpecializationTwo.setSpecializationRole(null);
        Assertions.assertNotEquals(this.playableSpecializationOne, this.playableSpecializationTwo);
    }

    /**
     * Test with the same names
     */
    @Test
    public void isNotEqualSameNamesTestOk(){
        playableSpecializationOne.setNames(playableSpecializationTwo.getNames());
        Assertions.assertNotEquals(playableSpecializationOne, playableSpecializationTwo);
    }

    /**
     * Test with the same id
     */
    @Test
    public void isNotEqualsSameIdsOk(){
        playableSpecializationOne.setId(playableSpecializationTwo.getId());
        Assertions.assertNotEquals(playableSpecializationOne, playableSpecializationTwo);
    }    
}