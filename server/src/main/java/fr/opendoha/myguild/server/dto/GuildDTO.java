package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Guild;
import lombok.Data;

/**
 * DTO for guild informations
 */
@Data
public class GuildDTO {

    private String name;
    private Integer memberCount;
    private Integer achievementPoints;
    private Boolean useApplication;
    private FactionDTO factionDTO;
    private RealmDTO realmDTO;

    /**
     * DTO Builder
     */
    public void build(final Guild guild){
        this.name = guild.getName();
        this.memberCount = guild.getMemberCount();
        this.achievementPoints = guild.getAchievementPoints();
        this.useApplication = guild.getUseApplication();

        this.factionDTO = new FactionDTO();
        this.factionDTO.build(guild.getFaction());

        this.realmDTO = new RealmDTO();
        this.realmDTO.build(guild.getRealm());
    }
    
}
