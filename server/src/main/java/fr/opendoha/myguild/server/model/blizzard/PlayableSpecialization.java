package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Model for the playable specialization
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PLAYABLE_SPECIALIZATIONS")
@Data
public class PlayableSpecialization extends AbstractBlizzardModel{

    @Embedded
    private LocalizedModel localizedModel;

    @Column(name = "MEDIA_URL")
    private String urlMedia;

    @ManyToOne
    @JoinColumn(name = "PLAYABLE_CLASS_ID")
    private PlayableClass playableClass;

    @ManyToOne
    @JoinColumn(name = "SPECIALIZATION_ROLE_ID")
    private SpecializationRole specializationRole;

    /**
     * Method to set value of localizedModel with localizedStringData
     */
    public void buildLocalizedModel(final LocalizedStringData localizedStringData){
        this.localizedModel = new LocalizedModel();
        this.localizedModel.updateLocalizedValue(localizedStringData);
    }
}
