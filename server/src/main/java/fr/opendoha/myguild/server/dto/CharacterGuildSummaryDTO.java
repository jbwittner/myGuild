package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Character;
import lombok.Data;

/**
 * DTO for character summary of guild member
 */
@Data
public class CharacterGuildSummaryDTO {

    private Integer id;
    private String name;
    private Integer level;
    private String guildName;
    private RealmDTO realmDTO;
    private Integer indexPlayableClass;
    private Integer indexPlayableRace;
    private Integer indexFaction;

    /**
     * DTO Builder
     */
    public void build(final Character character){
        this.id = character.getId();
        this.name = character.getName();
        this.level = character.getLevel();

        if(character.getGuild() != null){
            this.guildName = character.getGuild().getName();    
        }
        
        this.realmDTO = new RealmDTO();
        this.realmDTO.build(character.getRealm());

        this.indexPlayableClass = character.getPlayableClass().getId();
        this.indexPlayableRace = character.getPlayableRace().getId();

        this.indexFaction = character.getFaction().getId();

    }
    
}
