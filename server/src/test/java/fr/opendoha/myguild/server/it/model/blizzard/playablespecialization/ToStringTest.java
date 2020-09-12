package fr.opendoha.myguild.server.it.model.blizzard.playablespecialization;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;

/**
 * Class to test toString method of PlayableSpecialization
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
        final PlayableSpecialization playableSpecialization = this.factory.createPlayableSpecializationWithSpecializationRole();

        String toStringExpectex = "PlayableSpecialization [id=" + playableSpecialization.getId() + ", specializationRole=" + playableSpecialization.getSpecializationRole();
        toStringExpectex = toStringExpectex + ", names=" + playableSpecialization.getNames().toString() +"]";

        Assertions.assertEquals(toStringExpectex, playableSpecialization.toString());
    }
    
}