package fr.opendoha.myguild.server.dto;

import java.util.ArrayList;
import java.util.List;

import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import lombok.Data;

/**
 * DTO for playable class
 */
@Data
public class PlayableClassDTO {

    private Integer index;
    private String mediaURL;
    private LocalizedStringDTO localizedStringDTO;
    private final List<Integer> playableSpecializationIndex = new ArrayList<>();

    /**
     * DTO Builder
     */
    public void build(final PlayableClass playableClass) {
        this.index = playableClass.getId();
        this.mediaURL = playableClass.getMediaURL();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(playableClass.getLocalizedModel());

        final List<PlayableSpecialization> playableSpecializations = playableClass.getPlayableSpecializationList();

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            this.playableSpecializationIndex.add(playableSpecialization.getId());
        }

    }
    
}
