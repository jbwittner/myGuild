package fr.opendoha.myguild.server.it.data.blizzard;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationsData;

/**
 * Class to test toString method of PlayableSpecializationsData
 */
public class ToStringPlayableSpecializationsDataTest extends AbstractMotherIntegrationTest {

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
        final PlayableSpecializationsData playableSpecializationsData = new PlayableSpecializationsData();

        final Integer numberOfIndex = this.factory.getRandomInteger(NUMBER_MIN_INDEX, NUMBER_MAX_INDEX);

        IndexData[] IndexDatas = new IndexData[numberOfIndex];

        for(int i = 0 ; i < numberOfIndex ; i++){
            final IndexData indexData = new IndexData();
            indexData.setId(this.factory.getRandomInteger());
            indexData.setName(this.factory.getRandomAlphanumericString(LENGTH_NAME));

            IndexDatas[i] = indexData;
        }

        playableSpecializationsData.setCharacter_specializations(IndexDatas);

        final String toStringExpectex = "PlayableSpecializationsData [character_specializations=" + Arrays.toString(playableSpecializationsData.getCharacter_specializations()) + "]";

        Assertions.assertEquals(toStringExpectex, playableSpecializationsData.toString());
    }

}