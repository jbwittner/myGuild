package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Model for the guild realms
 */
@EqualsAndHashCode()
@Data
@Entity
@Table(name = "REALMS")
public class Realm{

    @Id
    @Column(name = "ID", nullable = false, updatable = false, insertable = false, unique = true)
    protected Integer id;

    @Column(name = "SLUG",  nullable = false, unique = true)
    private String slug;

    @Embedded
    private LocalizedModel localizedModel;

    /**
     * Method to set value of localizedModel with localizedStringData
     */
    public void buildLocalizedModel(final LocalizedStringData localizedStringData){
        this.localizedModel = new LocalizedModel();
        this.localizedModel.updateLocalizedValue(localizedStringData);
    }

    @Override
    public String toString() {
        return "Realm{" +
                "slug='" + slug + '\'' +
                ", localizedModel=" + localizedModel +
                ", id=" + id +
                '}';
    }
}