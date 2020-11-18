package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of guild data
 */
@Data
public class GuildData {

    @JsonProperty("roster")
    private HrefData rosterHrefData;

    @JsonProperty("achievements")
    private HrefData achievementsHrefData;

    private String name;

    private Integer id;

    @JsonProperty("member_count")
    private Integer memberCount;

    @JsonProperty("achievement_points")
    private Integer achievementPoints;

    @JsonProperty("realm")
    private RealmData realmData;

    @JsonProperty("faction")
    private FactionData factionData;

    @JsonProperty("created_timestamp")
    private Long createdTimestamp;

    @JsonProperty("crest")
    private CrestData crestData;

}