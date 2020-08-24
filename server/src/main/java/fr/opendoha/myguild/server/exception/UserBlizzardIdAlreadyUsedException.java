package fr.opendoha.myguild.server.exception;

import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;

/**
 * Exception to signal a BlizzardId already used
 */
@SuppressWarnings("serial")
public final class UserBlizzardIdAlreadyUsedException extends RuntimeException {

    /**
     * Exception to signal a BlizzardId already used
     * @param userRegistrationParameter userRegistrationParameter who throw the exception
     */
    public UserBlizzardIdAlreadyUsedException(final UserRegistrationParameter userRegistrationParameter) {
        super("The BlizzardId " + userRegistrationParameter.getBlizzardId() + " is already used by a another account");
    }

}
