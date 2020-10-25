package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of guild member index
 */
@Data
public class GuildMemberIndexData {

    @JsonProperty("character")
    GuildMemberData guildMemberData;

    Integer rank;
}
