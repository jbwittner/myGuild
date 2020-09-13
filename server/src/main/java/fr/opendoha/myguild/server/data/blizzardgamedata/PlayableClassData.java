package fr.opendoha.myguild.server.data.blizzardgamedata;

import java.util.Arrays;

/**
 * Data for the playable class
 */
public class PlayableClassData {

    private Integer id;

    private LocalizedValuesData name;

    private PlayableSpecializationData[] specializations;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public LocalizedValuesData getName() {
        return name;
    }

    public void setName(final LocalizedValuesData name) {
        this.name = name;
    }

    public PlayableSpecializationData[] getSpecializations() {
        return specializations.clone();
    }

    public void setSpecializations(final PlayableSpecializationData[] specializations) {
        this.specializations = specializations.clone();
    }

    @Override
    public String toString() {
        return "PlayableClassData [id=" + id + ", name=" + name + ", specializations=" + Arrays.toString(specializations) + "]";
    }

}