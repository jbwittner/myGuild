package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Class representation of covenant progress
 */
@Data
public class CovenantProgressData {
    
    @JsonProperty("chosen_covenant")
    private ChosenCovenantData chosenCovenantData;

    @JsonProperty("renown_level")
    private Integer renownLevel;
}
