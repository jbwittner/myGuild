package fr.opendoha.myguild.server.parameters;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Parameter used to add a guild
 */
@Data
public class AddingGuildParameter {

    @NotNull
    private Integer guildId;

    @NotNull
    private Integer guildMasterId;

    @NotNull
    private Integer blizzardId;

    @NotNull
    @NotEmpty
    private String Token;

}
