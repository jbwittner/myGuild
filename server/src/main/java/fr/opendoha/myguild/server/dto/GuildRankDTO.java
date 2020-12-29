package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.GuildRank;
import lombok.Data;

/**
 * DTO for guild rank
 */
@Data
public class GuildRankDTO {
    
    private Integer rank;
    private String name;

    /**
     * DTO Builder
     */
    public void build(final GuildRank guildRank){
        this.name = guildRank.getName();
        this.rank = guildRank.getRank();
    }

}
