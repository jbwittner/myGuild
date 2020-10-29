package fr.opendoha.myguild.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.opendoha.myguild.server.model.blizzard.LocalizedModel;
import lombok.Data;

/**
 * DTO for localized string
 */
@Data
public class LocalizedStringDTO {

    @JsonProperty("en_US")
    private String enUS;

    @JsonProperty("es_MX")
    private String esMX;

    @JsonProperty("pt_BR")
    private String ptBR;

    @JsonProperty("de_DE")
    private String deDE;

    @JsonProperty("en_GB")
    private String enGB;

    @JsonProperty("es_ES")
    private String esES;

    @JsonProperty("fr_FR")
    private String frFR;

    @JsonProperty("it_IT")
    private String itIT;

    @JsonProperty("ru_RU")
    private String ruRU;

    @JsonProperty("ko_KR")
    private String koKR;

    @JsonProperty("zh_TW")
    private String zhTW;

    @JsonProperty("zh_CN")
    private String zhCN;

    /**
     * DTO Builder
     */
    public void build(final LocalizedModel localizedModel){
        this.enUS = localizedModel.getEnUS();
        this.esMX = localizedModel.getEsMX();
        this.ptBR = localizedModel.getPtBR();
        this.deDE = localizedModel.getDeDE();
        this.enGB = localizedModel.getEnGB();
        this.esES = localizedModel.getEsES();
        this.frFR = localizedModel.getFrFR();
        this.itIT = localizedModel.getItIT();
        this.ruRU = localizedModel.getRuRU();
        this.koKR = localizedModel.getKoKR();
        this.zhTW = localizedModel.getZhTW();
        this.zhCN = localizedModel.getZhCN();
    
    }

}
