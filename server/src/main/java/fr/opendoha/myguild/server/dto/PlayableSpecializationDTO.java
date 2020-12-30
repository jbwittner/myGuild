package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
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

        final SpecializationRole specializationRole = playableSpecialization.getSpecializationRole();

        this.specializationRoleType = specializationRole.getType();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(playableSpecialization.getLocalizedModel());

    }
}
