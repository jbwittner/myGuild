package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Model for the playable class
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PLAYABLE_CLASSES")
@Data
public class PlayableClass extends AbstractBlizzardModel{

    @Embedded
    private LocalizedModel localizedModel;

    @Column(name = "MEDIA_URL")
    private String mediaURL;

    @OneToMany(mappedBy = "playableClass")
    private List<PlayableSpecialization> playableSpecializationList;

    /**
     * Method to set value of localizedModel with localizedStringData
     */
    public void buildLocalizedModel(final LocalizedStringData localizedStringData){
        this.localizedModel = new LocalizedModel();
        this.localizedModel.updateLocalizedValue(localizedStringData);
    }

    @Override
    public String toString() {
        return "PlayableClass{" +
                "localizedModel=" + localizedModel +
                ", id=" + id +
                '}';
    }
}
