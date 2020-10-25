package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of playable race index
 */
@Data
public class PlayableRacesIndexData {

    @JsonProperty("races")
    private List<IndexData> indexDataList;
}
