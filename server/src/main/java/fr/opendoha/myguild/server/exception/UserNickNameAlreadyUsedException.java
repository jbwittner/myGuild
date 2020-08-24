package fr.opendoha.myguild.server.exception;

import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;

/**
 * Exception to signal a nick name already used
 */
@SuppressWarnings("serial")
public final class UserNickNameAlreadyUsedException extends RuntimeException {

    /**
     * Exception to signal a nick name already used
     * @param userRegistrationParameter userRegistrationParameter who throw the exception
     */
    public UserNickNameAlreadyUsedException(final UserRegistrationParameter userRegistrationParameter) {
        super("The nick name " + userRegistrationParameter.getNickName() + " is already used by a another account");
    }

}
