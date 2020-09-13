package fr.opendoha.myguild.server.it.data.blizzard;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassesData;

/**
 * Class to test toString method of PlayableClassesData
 */
public class ToStringPlayableClassesDataTest extends AbstractMotherIntegrationTest {

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
        final PlayableClassesData playableClassesData = new PlayableClassesData();

        final Integer numberOfIndex = this.factory.getRandomInteger();

        IndexData[] IndexDatas = new IndexData[numberOfIndex];

        for(int i = 0 ; i < numberOfIndex ; i++){
            final IndexData indexData = new IndexData();
            indexData.setId(this.factory.getRandomInteger());
            indexData.setName(this.factory.getRandomAlphanumericString(LENGTH_NAME));

            IndexDatas[i] = indexData;
        }

        playableClassesData.setClasses(IndexDatas);

        final String toStringExpectex = "PlayableClassesData [classes=" + Arrays.toString(playableClassesData.getClasses()) + "]";

        Assertions.assertEquals(toStringExpectex, playableClassesData.toString());
    }
    
}