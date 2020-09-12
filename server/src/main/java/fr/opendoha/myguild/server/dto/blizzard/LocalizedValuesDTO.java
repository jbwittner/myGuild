package fr.opendoha.myguild.server.dto.blizzard;

/**
 * DTO used for the internationalized values
 */
public class LocalizedValuesDTO {

    private String en_US;
    private String fr_FR;

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

    @Override
    public String toString() {
        return "LocalizedValuesDTO [en_US=" + en_US + ", fr_FR=" + fr_FR + "]";
    }

}