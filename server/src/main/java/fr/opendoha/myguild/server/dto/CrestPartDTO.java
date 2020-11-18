package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.data.blizzardgamedata.GameDataMediaData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PartCrestData;
import lombok.Data;

@Data
public class CrestPartDTO {
    private String url;
    private ColorDTO color;

    /**
     * DTO Builder
     */
    public void build(final PartCrestData partCrestData, GameDataMediaData gameDataMediaData){
        this.url = gameDataMediaData.getAssetMediaDataList().get(0).getValue();
        this.color = new ColorDTO();
        this.color.build(partCrestData.getColor());
    }
}
