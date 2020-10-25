package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of character media
 */
@Data
public class CharacterMediaData {

    @JsonProperty("avatar_url")
    String avatarUrl;

    @JsonProperty("bust_url")
    String bustUrl;

    @JsonProperty("assets")
    List<AssetMediaData> assetMediaDataList;

}
