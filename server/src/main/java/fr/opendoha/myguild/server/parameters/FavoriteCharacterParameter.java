package fr.opendoha.myguild.server.parameters;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Parameter to set favortie character
 */
@Data
public class FavoriteCharacterParameter {
    
    @NotNull
    private Integer id;

    @NotNull
    private Boolean isFavorite;

}
