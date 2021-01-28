package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import lombok.Data;

/**
 * DTO for playable race
 */
@Data
public class PlayableRaceDTO {

    private Integer index;
    private LocalizedStringDTO localizedStringDTO;
    private Integer factionIndex;

    /**
     * DTO Builder
     */
    public void build(final PlayableRace playableRace) {
        this.index = playableRace.getId();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(playableRace.getLocalizedModel());

        this.factionIndex = playableRace.getFaction().getId();

    }
    
}
