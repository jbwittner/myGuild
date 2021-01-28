package fr.opendoha.myguild.server.parameters;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Parameter to set favortie character
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class FavoriteCharacterParameter extends BlizzardAccountParameter {
    
    @NotNull
    private Integer id;

    @NotNull
    private Boolean isFavorite;

}
