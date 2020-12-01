package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.data.blizzardgamedata.GameDataMediaData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildData;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import lombok.Data;

@Data
public class GuildSummaryDTO {

    private Integer id;
    private String name;
    private Boolean useApplication;
    private Boolean isGuildMaster;
    private Boolean isFavorite;
    private Integer indexFaction;
    private RealmDTO realmDTO;
    private Integer achievementPoints;
    private Long createdTimestamp;
    private Integer memberCount;

    /**
     * DTO Builder
     */
    public void build(final Guild guild, final GuildData guildData){
        this.id = guild.getId();

        this.name = guild.getName();
        this.useApplication = guild.getUseApplication();

        this.indexFaction = guild.getFaction().getId();

        this.realmDTO = new RealmDTO();
        this.realmDTO.build(guild.getRealm());

        this.achievementPoints = guildData.getAchievementPoints();
        this.createdTimestamp = guildData.getCreatedTimestamp();
        this.memberCount = guildData.getMemberCount();
    }
    
}
