package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of index data
 */
@Data
public class IndexData {

    private Integer id;

    @JsonProperty("key")
    private HrefData hrefData;

    @JsonProperty("name")
    private LocalizedStringData localizedStringData;

}
