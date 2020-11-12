package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Guild;
import lombok.Data;

@Data
public class GuildSummaryDTO {

    private String name;
    private Boolean useApplication;
    private Boolean isGuildMaster;
    private FactionDTO factionDTO;
    private RealmDTO realmDTO;

    /**
     * DTO Builder
     */
    public void build(final Guild guild){
        this.name = guild.getName();
        this.useApplication = guild.getUseApplication();

        this.factionDTO = new FactionDTO();
        this.factionDTO.build(guild.getFaction());

        this.realmDTO = new RealmDTO();
        this.realmDTO.build(guild.getRealm());
    }
    
}
