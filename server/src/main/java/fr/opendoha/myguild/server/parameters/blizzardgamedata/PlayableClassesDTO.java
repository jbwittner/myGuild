package fr.opendoha.myguild.server.parameters.blizzardgamedata;

import java.util.Arrays;

/**
 * DTO to get the list of playable classes
 */
public class PlayableClassesDTO {

    private IndexDTO[] classes;

    public IndexDTO[] getClasses() {
        return classes.clone();
    }

    public void setClasses(final IndexDTO[] classes) {
        this.classes = classes.clone();
    }

    @Override
    public String toString() {
        return "PlayableClassesDTO [classes=" + Arrays.toString(classes) + "]";
    }
    
}