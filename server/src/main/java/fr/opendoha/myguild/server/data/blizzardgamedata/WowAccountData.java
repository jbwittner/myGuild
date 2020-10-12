package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WowAccountData {

    private Integer id;

    @JsonProperty("characters")
    private CharacterSummaryData[] characterSummaryData;

}
