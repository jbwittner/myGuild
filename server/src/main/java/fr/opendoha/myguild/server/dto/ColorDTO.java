package fr.opendoha.myguild.server.dto;

import fr.opendoha.myguild.server.data.blizzardgamedata.ColorData;
import lombok.Data;

@Data
public class ColorDTO {
    private int r;
    private int g;
    private int b;
    private int a;

    /**
     * DTO Builder
     */
    public void build(final ColorData colorData){
        this.r = colorData.getRgbaData().getR();
        this.g = colorData.getRgbaData().getG();
        this.b = colorData.getRgbaData().getB();
        this.a = colorData.getRgbaData().getA();
    }
}
