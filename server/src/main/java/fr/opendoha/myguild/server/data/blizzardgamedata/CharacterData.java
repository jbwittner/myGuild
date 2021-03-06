package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of character
 */
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

    @JsonProperty("race")
    private IndexData raceIndexData;

    @JsonProperty("character_class")
    private IndexData classIndexData;

    @JsonProperty("media")
    private HrefData mediaHrefData;

    private Integer level;

    @JsonProperty("covenant_progress")
    private CovenantProgressData covenantProgressData;

}
