package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of game data media
 */
@Data
public class GameDataMediaData {

    private Integer id;

    @JsonProperty("assets")
    private List<AssetMediaData> assetMediaDataList;

}
