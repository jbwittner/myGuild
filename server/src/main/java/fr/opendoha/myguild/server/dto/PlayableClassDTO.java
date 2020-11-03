package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import lombok.Data;

@Data
public class PlayableClassDTO {

    private Integer index;
    private String mediaURL;
    private LocalizedStringDTO localizedStringDTO;

    /**
     * DTO Builder
     */
    public void build(final PlayableClass playableClass) {
        this.index = playableClass.getId();
        this.mediaURL = playableClass.getMediaURL();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(playableClass.getLocalizedModel());

    }
    
}
