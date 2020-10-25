package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of playable class
 */
@Data
public class PlayableClassData {

    private Integer id;

    @JsonProperty("name")
    private LocalizedStringData localizedStringData;

    @JsonProperty("specializations")
    private List<IndexData> specializationIndexDataList;

    @JsonProperty("media")
    private MediaHrefData mediaHrefData;

}
