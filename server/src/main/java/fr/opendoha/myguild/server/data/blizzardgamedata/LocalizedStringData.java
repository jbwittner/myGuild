package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of localized values
 */
@Data
public class LocalizedStringData {

    @JsonProperty("en_US")
    private String enUS;

    @JsonProperty("es_ES")
    private String esES;

    @JsonProperty("fr_FR")
    private String frFR;

    @JsonProperty("it_IT")
    private String itIT;
}
