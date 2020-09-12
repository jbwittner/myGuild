package fr.opendoha.myguild.server.model.blizzard;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import fr.opendoha.myguild.server.dto.blizzard.LocalizedValuesDTO;

/**
 * Embeddable model used for the internationalization
 */
@Embeddable
public class LocalizedString {

    @Column(name = "EN_US", nullable = false)
    protected String en_US;

    @Column(name = "FR_FR", nullable = false)
    protected String fr_FR;

    public String getEn_US() {
        return en_US;
    }

    public void setEn_US(final String en_US) {
        this.en_US = en_US;
    }

    public String getFr_FR() {
        return fr_FR;
    }

    public void setFr_FR(final String fr_FR) {
        this.fr_FR = fr_FR;
    }

    /**
     * Method used to get the value from the DRO
     */
    public void builderFromDTO(final LocalizedValuesDTO localizedValuesDTO) {
        this.en_US = localizedValuesDTO.getEn_US();
        this.fr_FR = localizedValuesDTO.getFr_FR();
    }

    @Override
    public String toString() {
        return "LocalizedString [en_US=" + en_US + ", fr_FR=" + fr_FR + "]";
    }

        /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj){
        final LocalizedString localizedString = (LocalizedString) obj;
        boolean status = false;
        
        if(obj != null){

            final boolean isEN_USEquals = this.en_US.equals(localizedString.getEn_US());
            final boolean isFR_FREquals = this.fr_FR.equals(localizedString.getFr_FR());

            if(isEN_USEquals && isFR_FREquals){
                status = true;
            }
        }

        return status;
    }

}