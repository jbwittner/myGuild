package fr.opendoha.myguild.server.it.dto.blizzard;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.dto.blizzard.IndexDTO;
import fr.opendoha.myguild.server.dto.blizzard.PlayableSpecializationsDTO;
import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;

/**
 * Class to test toString method of PlayableSpecializationsDTO
 */
public class ToStringPlayableSpecializationsDTOTest extends AbstractMotherIntegrationTest {

    private static Integer LENGTH_NAME = 20;
    private static Integer NUMBER_MIN_INDEX = 1;
    private static Integer NUMBER_MAX_INDEX = 5;

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
        final PlayableSpecializationsDTO playableSpecializationsDTO = new PlayableSpecializationsDTO();

        final Integer numberOfIndex = this.factory.getRandomInteger(NUMBER_MIN_INDEX, NUMBER_MAX_INDEX);

        IndexDTO[] IndexDTOs = new IndexDTO[numberOfIndex];

        for(int i = 0 ; i < numberOfIndex ; i++){
            final IndexDTO indexDTO = new IndexDTO();
            indexDTO.setId(this.factory.getRandomInteger());
            indexDTO.setName(this.factory.getRandomAlphanumericString(LENGTH_NAME));

            IndexDTOs[i] = indexDTO;
        }

        playableSpecializationsDTO.setCharacter_specializations(IndexDTOs);

        final String toStringExpectex = "PlayableSpecializationsDTO [character_specializations=" + Arrays.toString(playableSpecializationsDTO.getCharacter_specializations()) + "]";

        Assertions.assertEquals(toStringExpectex, playableSpecializationsDTO.toString());
    }

}