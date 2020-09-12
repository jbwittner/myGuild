package fr.opendoha.myguild.server.it.dto.blizzard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.dto.blizzard.IndexDTO;
import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;

/**
 * Class to test toString method of IndexDTO
 */
public class ToStringIndexDTOTest extends AbstractMotherIntegrationTest {

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
        final IndexDTO indexDTO = new IndexDTO();
        indexDTO.setId(this.factory.getRandomInteger());
        indexDTO.setName(this.factory.getRandomAlphanumericString(LENGTH_NAME));

        final String toStringExpectex = "IndexDTO [id=" + indexDTO.getId() + ", name=" + indexDTO.getName() + "]";

        Assertions.assertEquals(toStringExpectex, indexDTO.toString());
    }
    
}