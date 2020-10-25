package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of role
 */
@Data
public class RoleData {

    private String type;

    @JsonProperty("name")
    private LocalizedStringData localizedStringData;

}
