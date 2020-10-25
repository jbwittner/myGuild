package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of playable class index
 */
@Data
public class PlayableClassesIndexData {

    @JsonProperty("classes")
    private List<IndexData> indexDataList;

}
