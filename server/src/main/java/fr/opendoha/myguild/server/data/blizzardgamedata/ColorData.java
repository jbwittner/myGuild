package fr.opendoha.myguild.server.data.blizzardgamedata;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ColorData {
    
    private Integer id;

    @JsonProperty("rgba")
    private RGBAData rgbaData;

}
