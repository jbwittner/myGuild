package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of guild index
 */
@Data
public class GuildIndexData {

    @JsonProperty("key")
    private HrefData keyHrefData;

    private String name;

    private Integer id;

    @JsonProperty("realm")
    private RealmData realmData;

    @JsonProperty("faction")
    private FactionData factionData;

}
