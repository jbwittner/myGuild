package fr.opendoha.myguild.server.data.blizzardgamedata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Class representation of character data
 */
@Data
public class CharacterMediaData {

    @JsonProperty("character")
    private CharacterSummaryData characterSummaryData;

    @JsonProperty("assets")
    private List<AssetsData> assetsDatas;

}
