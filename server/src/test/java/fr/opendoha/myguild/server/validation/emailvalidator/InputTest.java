package fr.opendoha.myguild.server.validation.emailvalidator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import fr.opendoha.myguild.server.annotation.ValidEmail;
import lombok.Data;

/**
 * Email data test
 */
@Data
public class InputTest {

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

}
