package fr.opendoha.myguild.server.parameters;

import fr.opendoha.myguild.server.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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