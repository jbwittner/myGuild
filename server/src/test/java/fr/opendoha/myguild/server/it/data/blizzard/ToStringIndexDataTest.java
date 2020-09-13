package fr.opendoha.myguild.server.it.data.blizzard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;

/**
 * Class to test toString method of IndexData
 */
public class ToStringIndexDataTest extends AbstractMotherIntegrationTest {

    private static Integer LENGTH_NAME = 20;

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
        final IndexData indexData = new IndexData();
        indexData.setId(this.factory.getRandomInteger());
        indexData.setName(this.factory.getRandomAlphanumericString(LENGTH_NAME));

        final String toStringExpectex = "IndexData [id=" + indexData.getId() + ", name=" + indexData.getName() + "]";

        Assertions.assertEquals(toStringExpectex, indexData.toString());
    }
    
}