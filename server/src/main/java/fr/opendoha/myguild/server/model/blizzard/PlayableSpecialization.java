package fr.opendoha.myguild.server.model.blizzard;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model for the playable specialization
 */
@Entity
@Table(name = "PLAYABLE_SPECIALIZATION")
public class PlayableSpecialization extends AbstractBlizzardModel{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPACIALIZATION_ROLE")
    SpecializationRole specializationRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYABLE_CLASS")
    PlayableClass playableClass;

    public SpecializationRole getSpecializationRole() {
        return specializationRole;
    }

    public void setSpecializationRole(final SpecializationRole specializationRole) {
        this.specializationRole = specializationRole;
    }

    public PlayableClass getPlayableClass() {
        return playableClass;
    }

    public void setPlayableClass(final PlayableClass playableClass) {
        this.playableClass = playableClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj){
        final PlayableSpecialization playableSpecialization = (PlayableSpecialization) obj;
        boolean status = false;
        
        if(obj != null){

            final boolean isIdEquals = this.id.equals(playableSpecialization.getId());
            final boolean isNamesEquals = this.names.equals(playableSpecialization.getNames());

            boolean isSpecializationRoleEquals;
            
            if(this.specializationRole == null){
                isSpecializationRoleEquals = playableSpecialization.getSpecializationRole() == null ? true : false;
            } else {
                isSpecializationRoleEquals = this.specializationRole.equals(playableSpecialization.getSpecializationRole());
            }

            boolean isPlayableClassEquals;
            
            if(this.playableClass == null){
                isPlayableClassEquals = playableSpecialization.getPlayableClass() == null ? true : false;
            } else {
                isPlayableClassEquals = this.playableClass.equals(playableSpecialization.getPlayableClass());
            }

            final boolean isUpdatedEquals = this.isUdpated.equals(playableSpecialization.getIsUdpated());

            if(isIdEquals && isNamesEquals && isSpecializationRoleEquals && isPlayableClassEquals && isUpdatedEquals){
                status = true;
            }
        }

        return status;
    }

    @Override
    public String toString() {
        return "PlayableSpecialization [id=" + this.id + ", specializationRole=" + specializationRole + ", names=" + this.names + "]";
    }
    
}