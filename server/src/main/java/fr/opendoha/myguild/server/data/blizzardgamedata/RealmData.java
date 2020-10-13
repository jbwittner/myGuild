package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of realm
 */
@Data
public class RealmData {

    @JsonProperty("key")
    private HrefData keyHrefData;

    @JsonProperty("name")
    private LocalizedStringData localizedStringData;

    private Integer id;

    private String slug;

}
