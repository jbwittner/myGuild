package fr.opendoha.myguild.server.parameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Parameter used to add a guild
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AddingGuildParameter extends BlizzardAccountParameter {

    @NotNull
    private Integer guildId;

}
