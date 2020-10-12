package fr.opendoha.myguild.server.model.blizzard;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class LocalizedModel {

    @Column(name ="en_US")
    private String enUS;

    @Column(name ="es_ES")
    private String esES;

    @Column(name ="fr_FR")
    private String frFR;

    @Column(name ="it_IT")
    private String itIT;

}
