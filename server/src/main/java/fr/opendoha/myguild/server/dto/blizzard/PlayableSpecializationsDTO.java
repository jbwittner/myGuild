package fr.opendoha.myguild.server.dto.blizzard;

import java.util.Arrays;

/**
 * DTO to get the list of playable specializations
 */
public class PlayableSpecializationsDTO {

    private IndexDTO[] character_specializations;

    public IndexDTO[] getCharacter_specializations() {
        return character_specializations.clone();
    }

    public void setCharacter_specializations(final IndexDTO[] character_specializations) {
        this.character_specializations = character_specializations.clone();
    }

    @Override
    public String toString() {
        return "PlayableSpecializationsDTO [character_specializations=" + Arrays.toString(character_specializations) + "]";
    }

    
}