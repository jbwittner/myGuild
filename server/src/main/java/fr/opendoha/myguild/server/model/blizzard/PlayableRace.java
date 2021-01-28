package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Model for the playable race
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PLAYABLE_RACES")
@Data
public class PlayableRace extends AbstractBlizzardModel{

    @Embedded
    private LocalizedModel localizedModel;

    @ManyToOne
    @JoinColumn(name = "FACTION_ID")
    private Faction faction;

    /**
     * Method to set value of localizedModel with localizedStringData
     */
    public void buildLocalizedModel(final LocalizedStringData localizedStringData){
        this.localizedModel = new LocalizedModel();
        this.localizedModel.updateLocalizedValue(localizedStringData);
    }

    @Override
    public String toString() {
        return "PlayableRace{" +
                "localizedModel=" + localizedModel +
                ", id=" + id +
                '}';
    }
}
