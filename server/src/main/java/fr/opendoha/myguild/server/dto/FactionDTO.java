package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Faction;
import lombok.Data;

/**
 * DTO for faction
 */
@Data
public class FactionDTO {
    
    private Integer index;
    private String type;
    private LocalizedStringDTO localizedStringDTO;

    /**
     * DTO Builder
     */
    public void build(final Faction faction){
        this.index = faction.getId();
        this.type = faction.getType();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(faction.getLocalizedModel());
    }

}
