package fr.opendoha.myguild.server.model.blizzard;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model for the specialization role
 */
@Entity
@Table (name = "SPECIALIZATION_ROLE")
public class SpecializationRole extends AbstractBlizzardModel{

    @Column(name = "TYPE", nullable = false)
    protected String type;

    @OneToMany(mappedBy="specializationRole")
    private List<PlayableSpecialization> playableSpecializations ;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

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
        final SpecializationRole specializationRole = (SpecializationRole) obj;
        boolean status = false;
        
        if(obj != null){

            final boolean isIdEquals = this.id.equals(specializationRole.getId());
            final boolean isNamesEquals = this.names.equals(specializationRole.getNames());
            final boolean isTypeEquals = this.type.equals(specializationRole.getType());

            boolean isPlayableSpecializationsEquals;

            if(this.playableSpecializations == null){
                isPlayableSpecializationsEquals = specializationRole.getPlayableSpecializations() == null ? true : false;
            } else {
                isPlayableSpecializationsEquals = this.playableSpecializations.equals(specializationRole.getPlayableSpecializations());
            }

            final boolean isUpdatedEquals = this.isUdpated.equals(specializationRole.getIsUdpated());

            if(isIdEquals && isNamesEquals && isTypeEquals && isPlayableSpecializationsEquals && isUpdatedEquals){
                status = true;
            }
        }

        return status;
    }

    @Override
    public String toString() {
        return "SpecializationRole [id=" + this.id + ", type=" + type + ", names=" + this.names + "]";
    }

}