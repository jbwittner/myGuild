package fr.opendoha.myguild.server.parameters;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Parameter used for the get user account informations
 */
@Data
public class FetchingUserAccountParameter {

    @NotNull
    @NotEmpty
    private String battleTag;

    @NotNull
    private Integer blizzardId;

}