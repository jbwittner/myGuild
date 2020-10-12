package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of character summary
 */
@Data
public class CharacterSummaryData {

    @JsonProperty("character")
    private HrefData characterHrefData;

    private Integer id;

}
