package fr.opendoha.myguild.server.it.model.blizzard.specializationrole;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;

/**
 * Class to test equals method of SpecializationRole
 */
public class EqualsTest extends AbstractMotherIntegrationTest {

    private SpecializationRole specializationRoleOne;
    private SpecializationRole specializationRoleTwo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() {
        this.specializationRoleOne = this.factory.createSpecilizationRoleWithPlayableSpecialization();
        this.specializationRoleTwo = this.factory.createSpecilizationRoleWithPlayableSpecialization();
    }

    /**
     * Test with two different specialization role
     */
    @Test
    public void isNotEqualsTestOk(){
        Assertions.assertNotEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

    /**
     * Test with a null
     */
    @Test
    public void isNotEqualsNullTestOk(){
        Assertions.assertNotEquals(this.specializationRoleOne, null);
    }

    /**
     * Test with the same id
     */
    @Test
    public void isNotEqualsNullSameIdTestOk(){
        this.specializationRoleTwo.setId(this.specializationRoleOne.getId());
        Assertions.assertNotEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

    /**
     * Test with the same names
     */
    @Test
    public void isNotEqualsSameNamesTestOk(){
        this.specializationRoleTwo.setNames(this.specializationRoleOne.getNames());
        Assertions.assertNotEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

    /**
     * Test with the same playable specializations
     */
    @Test
    public void isNotEqualsSamePlayableSpecializationTestOk(){
        this.specializationRoleTwo.setPlayableSpecializations(this.specializationRoleOne.getPlayableSpecializations());
        Assertions.assertNotEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

    /**
     * Test with null playable specializations for the two specializations role
     */
    @Test
    public void isNotEqualsNullTwoSpecializationRoleTestOk(){
        this.specializationRoleOne.setPlayableSpecializations(null);
        this.specializationRoleTwo.setPlayableSpecializations(null);
        Assertions.assertNotEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

    /**
     * Test with null playable specializations
     */
    @Test
    public void isNotEqualsNullSpecializationRoleTestOk(){
        this.specializationRoleOne.setPlayableSpecializations(null);
        Assertions.assertNotEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

    /**
     * Test with the same type
     */
    @Test
    public void isNotEqualsSameTypeTestOk(){
        this.specializationRoleTwo.setType(this.specializationRoleOne.getType());
        Assertions.assertNotEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

    /**
     * Test is equals
     */
    @Test
    public void isEqualsTestOk(){
        this.specializationRoleTwo.setId(this.specializationRoleOne.getId());
        this.specializationRoleTwo.setNames(this.specializationRoleOne.getNames());
        this.specializationRoleTwo.setPlayableSpecializations(this.specializationRoleOne.getPlayableSpecializations());
        this.specializationRoleTwo.setType(this.specializationRoleOne.getType());
        Assertions.assertEquals(this.specializationRoleOne, this.specializationRoleTwo);
    }

}