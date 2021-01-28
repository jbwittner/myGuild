package fr.opendoha.myguild.server.data.blizzardgamedata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of covenant index
 */
@Data
public class CovenantIndexData {

    @JsonProperty("covenants")
    private List<IndexData> indexDataList;

}
