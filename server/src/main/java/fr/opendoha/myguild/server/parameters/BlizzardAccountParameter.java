package fr.opendoha.myguild.server.parameters;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Parameter used to get data from blizzard account
 */
@Data
public class BlizzardAccountParameter {

    @NotNull
    @NotEmpty
    private String Token;

    @NotNull
    private Integer blizzardId;

}