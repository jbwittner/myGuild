package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of guild member
 */
@Data
public class GuildMemberData {

    @JsonProperty("key")
    HrefData hrefData;

    String name;

    Integer level;

    Integer id;

    @JsonProperty("playable_class")
    IndexData playableClassIndexData;

    @JsonProperty("playable_race")
    IndexData playableRaceIndexData;


}
