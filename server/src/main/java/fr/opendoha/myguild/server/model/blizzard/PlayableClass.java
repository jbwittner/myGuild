package fr.opendoha.myguild.server.model.blizzard;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model for the playable class
 */
@Entity
@Table(name = "PLAYABLE_CLASS")
public class PlayableClass extends AbstractBlizzardModel{

    @OneToMany(mappedBy = "playableClass", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<PlayableSpecialization> playableSpecializations;

    public List<PlayableSpecialization> getPlayableSpecializations() {
        return playableSpecializations;
    }

    public void setPlayableSpecializations(final List<PlayableSpecialization> playableSpecializations) {
        this.playableSpecializations = playableSpecializations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj){
        final PlayableClass playableClass = (PlayableClass) obj;
        boolean status = false;
        
        if(obj != null){

            final boolean isIdEquals = this.id.equals(playableClass.getId());
            final boolean isNamesEquals = this.names.equals(playableClass.getNames());

            boolean isPlayableSpecializationsEquals;
            
            if(this.playableSpecializations == null){
                isPlayableSpecializationsEquals = playableClass.getPlayableSpecializations() == null ? true : false;
            } else {
                isPlayableSpecializationsEquals = this.playableSpecializations.equals(playableClass.getPlayableSpecializations());
            }
            
            final boolean isUpdatedEquals = this.isUdpated.equals(playableClass.getIsUdpated());

            if(isIdEquals && isNamesEquals && isPlayableSpecializationsEquals && isUpdatedEquals){
                status = true;
            }
        }

        return status;
    }

    @Override
    public String toString() {
        return "PlayableClass [id=" + this.id + ", playableSpecializations=" + playableSpecializations + ", names=" + this.names + "]";
    }


}