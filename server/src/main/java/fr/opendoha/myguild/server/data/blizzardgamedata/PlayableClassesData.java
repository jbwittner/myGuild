package fr.opendoha.myguild.server.data.blizzardgamedata;

import java.util.Arrays;

/**
 * Data to get the list of playable classes
 */
public class PlayableClassesData {

    private IndexData[] classes;

    public IndexData[] getClasses() {
        return classes.clone();
    }

    public void setClasses(final IndexData[] classes) {
        this.classes = classes.clone();
    }

    @Override
    public String toString() {
        return "PlayableClassesData [classes=" + Arrays.toString(classes) + "]";
    }
    
}