package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.data.blizzardgamedata.CrestData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GameDataMediaData;
import lombok.Data;

@Data
public class CrestDTO {
    private CrestPartDTO emblem;
    private CrestPartDTO border;
    private ColorDTO background;

    /**
     * DTO Builder
     */
    public void build(final CrestData crestData, GameDataMediaData emblem, GameDataMediaData border){
        this.emblem = new CrestPartDTO();
        this.emblem.build(crestData.getEmblem(), emblem);

        this.border = new CrestPartDTO();
        this.border.build(crestData.getBorder(), border);

        this.background = new ColorDTO();
        this.background.build(crestData.getBackground().getColor());
    }
}
