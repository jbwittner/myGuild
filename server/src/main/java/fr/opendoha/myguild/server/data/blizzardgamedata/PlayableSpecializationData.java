package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of playable specialization
 */
@Data
public class PlayableSpecializationData {

    private Integer id;

    @JsonProperty("name")
    private LocalizedStringData localizedStringData;

    @JsonProperty("media")
    private MediaData mediaData;

    @JsonProperty("role")
    private RoleData roleData;

}
