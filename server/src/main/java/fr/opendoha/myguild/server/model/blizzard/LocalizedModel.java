package fr.opendoha.myguild.server.model.blizzard;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embeddable model
 */
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

    @Override
    public String toString() {
        return "LocalizedModel{" +
                "enUS='" + enUS + '\'' +
                ", esES='" + esES + '\'' +
                ", frFR='" + frFR + '\'' +
                ", itIT='" + itIT + '\'' +
                '}';
    }
}
