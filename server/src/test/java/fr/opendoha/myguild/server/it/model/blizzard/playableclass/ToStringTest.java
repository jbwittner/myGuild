package fr.opendoha.myguild.server.it.model.blizzard.playableclass;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;

/**
 * Class to test toString method of PlayableClass
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
        final PlayableClass playableClass = this.factory.createPlayableClassWithSpecialization();

        String toStringExpectex = "PlayableClass [id=" + playableClass.getId() + ", playableSpecializations=" + playableClass.getPlayableSpecializations();
        toStringExpectex = toStringExpectex + ", names=" + playableClass.getNames() + "]";

        Assertions.assertEquals(toStringExpectex, playableClass.toString());
    }
    
}