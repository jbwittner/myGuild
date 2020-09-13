package fr.opendoha.myguild.server.it.dto.blizzard;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.IndexDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableClassesDTO;

/**
 * Class to test toString method of PlayableClassesDTO
 */
public class ToStringPlayableClassesDTOTest extends AbstractMotherIntegrationTest {

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
        final PlayableClassesDTO playableClassesDTO = new PlayableClassesDTO();

        final Integer numberOfIndex = this.factory.getRandomInteger();

        IndexDTO[] IndexDTOs = new IndexDTO[numberOfIndex];

        for(int i = 0 ; i < numberOfIndex ; i++){
            final IndexDTO indexDTO = new IndexDTO();
            indexDTO.setId(this.factory.getRandomInteger());
            indexDTO.setName(this.factory.getRandomAlphanumericString(LENGTH_NAME));

            IndexDTOs[i] = indexDTO;
        }

        playableClassesDTO.setClasses(IndexDTOs);

        final String toStringExpectex = "PlayableClassesDTO [classes=" + Arrays.toString(playableClassesDTO.getClasses()) + "]";

        Assertions.assertEquals(toStringExpectex, playableClassesDTO.toString());
    }
    
}