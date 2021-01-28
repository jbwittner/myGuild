package fr.opendoha.myguild.server.parameters;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import fr.opendoha.myguild.server.annotation.ValidEmail;

/**
 * Parameter used for the registration of new user account
 */
@Data
public class UserRegistrationParameter {

    @NotNull
    @NotEmpty
    private String nickName;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String battleTag;

    @NotNull
    private Integer blizzardId;

}