package fr.opendoha.myguild.server.dto.blizzard;

import java.util.Arrays;

/**
 * DTO for the playable class
 */
public class PlayableClassDTO {

    private Integer id;

    private LocalizedValuesDTO name;

    private PlayableSpecializationDTO[] specializations;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public LocalizedValuesDTO getName() {
        return name;
    }

    public void setName(final LocalizedValuesDTO name) {
        this.name = name;
    }

    public PlayableSpecializationDTO[] getSpecializations() {
        return specializations.clone();
    }

    public void setSpecializations(final PlayableSpecializationDTO[] specializations) {
        this.specializations = specializations.clone();
    }

    @Override
    public String toString() {
        return "PlayableClassDTO [id=" + id + ", name=" + name + ", specializations=" + Arrays.toString(specializations) + "]";
    }

}