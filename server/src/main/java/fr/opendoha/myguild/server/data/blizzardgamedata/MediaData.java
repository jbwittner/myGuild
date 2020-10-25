package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of media data
 */
@Data
public class MediaData {

    private Integer id;

    @JsonProperty("key")
    private HrefData hrefData;
}
