package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Covenant;
import lombok.Data;

/**
 * DTO for playable race
 */
@Data
public class CovenantDTO {

    private Integer index;
    private LocalizedStringDTO localizedStringDTO;
    private Integer factionIndex;

    /**
     * DTO Builder
     */
    public void build(final Covenant covenant) {
        this.index = covenant.getId();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(covenant.getLocalizedModel());

    }
    
}
