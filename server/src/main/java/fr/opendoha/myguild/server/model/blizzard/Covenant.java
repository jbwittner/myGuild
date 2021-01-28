package fr.opendoha.myguild.server.model.blizzard;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Model for the covenants
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "COVENANT")
public class Covenant extends AbstractBlizzardModel{

    @Embedded
    private LocalizedModel localizedModel;

    /**
     * Method to set value of localizedModel with localizedStringData
     */
    public void buildLocalizedModel(final LocalizedStringData localizedStringData){
        this.localizedModel = new LocalizedModel();
        this.localizedModel.updateLocalizedValue(localizedStringData);
    }
    
}
