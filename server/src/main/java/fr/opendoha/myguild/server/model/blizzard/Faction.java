package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Model for the factions
 */
@EqualsAndHashCode()
@Data
@Entity
@Table(name = "factions")
public class Faction{

    @Id
    @Column(name = "id", nullable = false, updatable = false, insertable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Integer id;

    @Column(name = "is_updated", nullable = false)
    protected Boolean isUpdated = true;

    @Column(name = "type",  nullable = false, unique = true)
    private String type;

    @Embedded
    private LocalizedModel localizedModel;

    /**
     * Method to update the values of localizedModel
     */
    public void updateLocalizedValue(final LocalizedStringData localizedStringData){
        localizedModel = new LocalizedModel();
        localizedModel.setEnUS(localizedStringData.getEnUS());
        localizedModel.setEsES(localizedStringData.getEsES());
        localizedModel.setFrFR(localizedStringData.getFrFR());
        localizedModel.setItIT(localizedStringData.getItIT());
    }

    /**
     * Method to set directly the value of isUpdated
     */
    public void setIsUpdatedTrue(){
        this.isUpdated = true;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", isUpdated=" + isUpdated +
                ", type='" + type + '\'' +
                ", localizedModel=" + localizedModel +
                '}';
    }
}