package fr.opendoha.myguild.server.it.model.blizzard.specializationrole;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;

/**
 * Class to test toString method of SpecializationRole
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
        final SpecializationRole specializationRole = this.factory.createSpecilizationRoleWithPlayableSpecialization();

        final String toStringExpectex = "SpecializationRole [id=" + specializationRole.getId() + ", type=" + specializationRole.getType() + ", names=" + specializationRole.getNames().toString() + "]";;

        Assertions.assertEquals(toStringExpectex, specializationRole.toString());
    }
    
}