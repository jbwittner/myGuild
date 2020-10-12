package fr.opendoha.myguild.server.model.blizzard;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Abstract class for Blizzard models
 */
@Data
@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
})
public class AbstractBlizzardModel {

    @Id
    @Column(name = "id", nullable = false, updatable = false, insertable = false, unique = true)
    protected Integer id;

    @Column(name = "is_updated", nullable = false)
    protected Boolean isUpdated = true;

    @UpdateTimestamp
    protected LocalDateTime updateTime;

    /**
     * Method to set directly the value of isUpdated
     */
    public void setIsUpdatedTrue(){
        this.isUpdated = true;
    }

}