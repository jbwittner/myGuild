package fr.opendoha.myguild.server.exception;

import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;

/**
 * Exception to signal a email already used
 */
@SuppressWarnings("serial")
public final class UserEmailAlreadyUsedException extends FunctionalException {

    /**
     * Exception to signal a email already used
     */
    public UserEmailAlreadyUsedException(final UserRegistrationParameter userRegistrationParameter) {
        super("The email " + userRegistrationParameter.getEmail() + " is already used by a another account");
    }

}
