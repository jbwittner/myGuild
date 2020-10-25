package fr.opendoha.myguild.server.model.blizzard;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
    @Column(name = "ID", nullable = false, updatable = false, insertable = false, unique = true)
    protected Integer id;

    @Column(name = "IS_UPDATED", nullable = false)
    protected Boolean isUpdated = true;


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