package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of playable race
 */
@Data
public class PlayableRaceData {

    private Integer id;

    @JsonProperty("name")
    private LocalizedStringData localizedStringData;

    @JsonProperty("faction")
    private FactionData factionData;

}
