package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of wow account
 */
@Data
public class WowAccountData {

    private Integer id;

    @JsonProperty("characters")
    private List<CharacterSummaryData> characterSummaryData;

}
