package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.model.blizzard.Realm;
import lombok.Data;

/**
 * DTO for realm
 */
@Data
public class RealmDTO {

    private String slug;
    private LocalizedStringDTO localizedStringDTO;

    /**
     * DTO Builder
     */
    public void build(final Realm realm){
        this.slug = realm.getSlug();

        this.localizedStringDTO = new LocalizedStringDTO();
        this.localizedStringDTO.build(realm.getLocalizedModel());
    }
    
}
