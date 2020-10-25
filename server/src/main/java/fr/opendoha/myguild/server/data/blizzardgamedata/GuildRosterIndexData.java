package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representation of guild roster index
 */
@Data
public class GuildRosterIndexData {

    @JsonProperty("members")
    private List<GuildMemberIndexData> guildMemberIndexDataList;

}
