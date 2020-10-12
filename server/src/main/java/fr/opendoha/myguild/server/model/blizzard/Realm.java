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
@Table(name = "realms")
public class Realm {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Integer id;

    @Column(name = "is_updated", nullable = false)
    protected Boolean isUpdated = true;

    @UpdateTimestamp
    protected LocalDateTime updateTime;

    @Column(name = "slug",  nullable = false, unique = true)
    private String slug;

    @Embedded
    private LocalizedModel localizedModel;

    public void setIsUpdatedTrue(){
        this.isUpdated = true;
    }

    public void updateLocalizedValue(LocalizedStringData localizedStringData){
        localizedModel = new LocalizedModel();
        localizedModel.setEnUS(localizedStringData.getEnUS());
        localizedModel.setEsES(localizedStringData.getEsES());
        localizedModel.setFrFR(localizedStringData.getFrFR());
        localizedModel.setItIT(localizedStringData.getItIT());
    }

}