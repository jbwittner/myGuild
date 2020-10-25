package fr.opendoha.myguild.server.model.blizzard;

import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embeddable model
 */
@Data
@Embeddable
public class LocalizedModel {

    @Column(name="EN_US")
    private String enUS;

    @Column(name="ES_MX")
    private String esMX;

    @Column(name="PT_BR")
    private String ptBR;

    @Column(name="DE_DE")
    private String deDE;

    @Column(name="EN_GB")
    private String enGB;

    @Column(name="ES_ES")
    private String esES;

    @Column(name="FR_FR")
    private String frFR;

    @Column(name="IT_IT")
    private String itIT;

    @Column(name="RU_RU")
    private String ruRU;

    @Column(name="KO_KR")
    private String koKR;

    @Column(name="ZH_TW")
    private String zhTW;

    @Column(name="ZH_CN")
    private String zhCN;

    /**
     * Method to update the values of localizedModel
     */
    public void updateLocalizedValue(final LocalizedStringData localizedStringData){
        this.enUS = localizedStringData.getEnUS();
        this.esMX = localizedStringData.getEsMX();
        this.ptBR = localizedStringData.getPtBR();
        this.deDE = localizedStringData.getDeDE();
        this.enGB = localizedStringData.getEnGB();
        this.esES = localizedStringData.getEsES();
        this.frFR = localizedStringData.getFrFR();
        this.itIT = localizedStringData.getItIT();
        this.ruRU = localizedStringData.getRuRU();
        this.koKR = localizedStringData.getKoKR();
        this.zhTW = localizedStringData.getZhTW();
        this.zhCN = localizedStringData.getZhCN();
    }

    @Override
    public String toString() {
        return "LocalizedModel{" +
                "enUS='" + enUS + '\'' +
                ", esMX='" + esMX + '\'' +
                ", ptBR='" + ptBR + '\'' +
                ", deDE='" + deDE + '\'' +
                ", enGB='" + enGB + '\'' +
                ", esES='" + esES + '\'' +
                ", frFR='" + frFR + '\'' +
                ", itIT='" + itIT + '\'' +
                ", ruRU='" + ruRU + '\'' +
                ", koKR='" + koKR + '\'' +
                ", zhTW='" + zhTW + '\'' +
                ", zhCN='" + zhCN + '\'' +
                '}';
    }

}
