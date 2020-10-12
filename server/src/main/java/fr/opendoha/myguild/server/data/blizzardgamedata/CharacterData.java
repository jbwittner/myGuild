package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharacterData {

    private Integer id;

    private String name;

    @JsonProperty("faction")
    private FactionData factionData;

    @JsonProperty("realm")
    private RealmData realmData;

    @JsonProperty("guild")
    private GuildIndexData guildIndexData;

    @JsonProperty("last_login_timestamp")
    private Long lastLoginTimestamp;

    @JsonProperty("average_item_level")
    private Integer averageItemLevel;

    @JsonProperty("equipped_item_level")
    private Integer equippedItemLevel;

    private Integer level;

}
