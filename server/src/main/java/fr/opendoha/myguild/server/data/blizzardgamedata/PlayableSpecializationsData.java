package fr.opendoha.myguild.server.data.blizzardgamedata;

import java.util.Arrays;

/**
 * Data to get the list of playable specializations
 */
public class PlayableSpecializationsData {

    private IndexData[] character_specializations;

    public IndexData[] getCharacter_specializations() {
        return character_specializations.clone();
    }

    public void setCharacter_specializations(final IndexData[] character_specializations) {
        this.character_specializations = character_specializations.clone();
    }

    @Override
    public String toString() {
        return "PlayableSpecializationsData [character_specializations=" + Arrays.toString(character_specializations) + "]";
    }

    
}