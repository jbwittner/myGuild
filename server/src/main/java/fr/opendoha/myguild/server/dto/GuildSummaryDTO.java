package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Guild;
import lombok.Data;

@Data
public class GuildSummaryDTO {

    private Integer id;
    private String name;
    private Boolean useApplication;
    private Boolean isGuildMaster;
    private Integer indexFaction;
    private RealmDTO realmDTO;

    /**
     * DTO Builder
     */
    public void build(final Guild guild){
        this.id = guild.getId();

        this.name = guild.getName();
        this.useApplication = guild.getUseApplication();

        this.indexFaction = guild.getFaction().getId();

        this.realmDTO = new RealmDTO();
        this.realmDTO.build(guild.getRealm());
    }
    
}
