package fr.opendoha.myguild.server.parameters;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FavoriteCharacterParameter {
    
    @NotNull
    private Integer id;

    @NotNull
    private Boolean isFavorite;

    public void setIsFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }
}
