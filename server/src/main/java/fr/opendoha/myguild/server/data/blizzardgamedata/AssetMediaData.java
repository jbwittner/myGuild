package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of asset media
 */
@Data
public class AssetMediaData {

    private String key;

    private String value;

    @JsonProperty("sile_data_id")
    private Integer fileDataId;

}
