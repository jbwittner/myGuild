package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import lombok.Data;

/**
 * DTO for playable specialization
 */
@Data
public class PlayableSpecializationDTO {
    
    private Integer index;
    private String mediaURL;
    private String specializationRoleType;
    private LocalizedStringDTO localizedStringDTO;

    /**
     * DTO Builder
     */
    public void build(final PlayableSpecialization playableSpecialization) {
        this.index = playableSpecialization.getId();
        this.mediaURL = playableSpecialization.getUrlMedia();

        this.specializationRoleType = playableSpecialization.getSpecializationRole().getType();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(playableSpecialization.getLocalizedModel());

    }
}
