package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;

import javax.persistence.*;

/**
 * Model for the specialization role
 */
@Entity
@Table(name = "SPECIALIZATION_ROLES")
@Data
public class SpecializationRole {

    @Id
    @Column(name = "ID", nullable = false, updatable = false, insertable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Integer id;

    @Column(name = "IS_UPDATED", nullable = false)
    protected Boolean isUpdated = true;

    @Column(name = "TYPE",  nullable = false, unique = true)
    private String type;

    @Embedded
    private LocalizedModel localizedModel;

    /**
     * Method to set value of localizedModel with localizedStringData
     */
    public void buildLocalizedModel(final LocalizedStringData localizedStringData){
        this.localizedModel = new LocalizedModel();
        this.localizedModel.updateLocalizedValue(localizedStringData);
    }

    /**
     * Method to set directly the value of isUpdated
     */
    public void setIsUpdatedTrue(){
        this.isUpdated = true;
    }

    /**
     * Method to set directly the value of isUpdated
     */
    public void setIsUpdatedFalse(){
        this.isUpdated = false;
    }

}
