package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @UpdateTimestamp
    protected LocalDateTime updateTime;

    @Column(name = "type",  nullable = false, unique = true)
    private String type;

    @Embedded
    private LocalizedModel localizedModel;

    public void updateLocalizedValue(LocalizedStringData localizedStringData){
        localizedModel = new LocalizedModel();
        localizedModel.setEnUS(localizedStringData.getEnUS());
        localizedModel.setEsES(localizedStringData.getEsES());
        localizedModel.setFrFR(localizedStringData.getFrFR());
        localizedModel.setItIT(localizedStringData.getItIT());
    }

    public void setIsUpdatedTrue(){
        this.isUpdated = true;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", isUpdated=" + isUpdated +
                ", updateTime=" + updateTime +
                ", type='" + type + '\'' +
                ", localizedModel=" + localizedModel +
                '}';
    }
}