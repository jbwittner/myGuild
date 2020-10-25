package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class representation of localized values
 */
@Data
public class LocalizedStringData {

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

}
