package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import lombok.Data;

@Data
public class SpecializationRoleDTO {

    private String type; 
    private LocalizedStringDTO localizedStringDTO;

    /**
     * DTO Builder
     */
    public void build(final SpecializationRole specializationRole) {
        this.type = specializationRole.getType();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(specializationRole.getLocalizedModel());

    }
    
}
