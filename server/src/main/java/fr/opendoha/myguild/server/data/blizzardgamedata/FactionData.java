package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of faction
 */
@Data
public class FactionData {

    private String type;

    @JsonProperty("name")
    private LocalizedStringData localizedStringData;
}
