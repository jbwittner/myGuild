package fr.opendoha.myguild.server.validation.emailvalidator;

import fr.opendoha.myguild.server.validation.ValidEmail;
import lombok.Data;

/**
 * Email data test
 */
@Data
public class EmailTest {

    @ValidEmail
    private String email;
}
