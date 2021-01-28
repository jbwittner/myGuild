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
@Table(name = "FACTIONS")
public class Faction{

    @Id
    @Column(name = "ID", nullable = false, updatable = false, insertable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Integer id;

    @Column(name = "TYPE",  nullable = false, unique = true)
    private String type;

    @Embedded
    private LocalizedModel localizedModel;

    /**
     * Method to update the values of localizedModel
     */
    public void updateLocalizedValue(final LocalizedStringData localizedStringData){
        this.localizedModel = new LocalizedModel();
        this.localizedModel.setItIT(localizedStringData.getItIT());
        this.localizedModel.setFrFR(localizedStringData.getFrFR());
        this.localizedModel.setEsES(localizedStringData.getEsES());
        this.localizedModel.setEnUS(localizedStringData.getEnUS());
        this.localizedModel.setDeDE(localizedStringData.getDeDE());
        this.localizedModel.setEnGB(localizedStringData.getEnGB());
        this.localizedModel.setEsMX(localizedStringData.getEsMX());
        this.localizedModel.setKoKR(localizedStringData.getKoKR());
        this.localizedModel.setPtBR(localizedStringData.getPtBR());
        this.localizedModel.setRuRU(localizedStringData.getRuRU());
        this.localizedModel.setZhCN(localizedStringData.getZhCN());
        this.localizedModel.setZhTW(localizedStringData.getZhTW());
    }

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", localizedModel=" + localizedModel +
                '}';
    }
}