package fr.opendoha.myguild.server.it.model.blizzard.playableclass;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;

/**
 * Class to test equals method of PlayableClass
 */
public class EqualsTest extends AbstractMotherIntegrationTest {

    private PlayableClass playableClassOne;
    private PlayableClass playableClassTwo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        this.playableClassOne = this.factory.createPlayableClassWithSpecialization();
        this.playableClassTwo = this.factory.createPlayableClassWithSpecialization();
    }

    /**
     * Test with two different playable class
     */
    @Test
    public void isNotEqualsOk(){
        Assertions.assertNotEquals(this.playableClassOne, this.playableClassTwo);
    }

    /**
     * Test a null
     */
    @Test
    public void isNotEqualsNullOk(){
        Assertions.assertNotEquals(this.playableClassOne, null);
    }

    /**
     * Test a null
     */
    @Test
    public void isNotEqualsNullPlayableSpecializationsNOk(){
        this.playableClassOne.setPlayableSpecializations(null);
        Assertions.assertNotEquals(this.playableClassOne, this.playableClassTwo);
    }

    /**
     * Test a null
     */
    @Test
    public void isEqualsNullPlayableSpecializationsOk(){
        this.playableClassOne.setPlayableSpecializations(null);
        this.playableClassTwo.setPlayableSpecializations(null);
        Assertions.assertNotEquals(this.playableClassOne, this.playableClassTwo);
    }

    /**
     * Test with the same battle names
     */
    @Test
    public void isNotEqualsBattleTagEqualsOk(){
        playableClassOne.setNames(playableClassTwo.getNames());
        Assertions.assertNotEquals(playableClassOne, playableClassTwo);
    }

    /**
     * Test with the same id
     */
    @Test
    public void isNotEqualsBlizzardIdEqualsOk(){
        playableClassOne.setId(playableClassTwo.getId());
        Assertions.assertNotEquals(playableClassOne, playableClassTwo);
    }    
}