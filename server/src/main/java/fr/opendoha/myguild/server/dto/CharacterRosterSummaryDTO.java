package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Character;
import lombok.Data;

@Data
public class CharacterRosterSummaryDTO {

    private Integer id;
    private String name;
    private Integer level;
    private String guildName;
    private RealmDTO realmDTO;
    private Integer indexPlayableClass;
    private Integer indexPlayableRace;
    private Integer indexFaction;
    private Integer averageItemLevel;
    private Integer equippedItemLevel;
    private Long lastLoginTimestamp;

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
        this.averageItemLevel = character.getAverageItemLevel();
        this.equippedItemLevel = character.getEquippedItemLevel();
        this.lastLoginTimestamp = character.getLastLoginTimestamp();

    }
    
}
