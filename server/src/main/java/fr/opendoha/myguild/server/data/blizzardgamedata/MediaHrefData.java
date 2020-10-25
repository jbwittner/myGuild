package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of media href
 */
@Data
public class MediaHrefData {

    private Integer id;

    @JsonProperty("key")
    private HrefData hrefData;
}
