package fr.opendoha.myguild.server.parameters;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FavoriteGuildParameter {
    
    @NotNull
    private Integer id;

    @NotNull
    private Boolean isFavorite;

    public void setIsFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }
}
