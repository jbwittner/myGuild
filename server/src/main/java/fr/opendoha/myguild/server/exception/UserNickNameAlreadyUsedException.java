package fr.opendoha.myguild.server.exception;

import fr.opendoha.myguild.server.parameters.UserRegistrationParameter;

/**
 * Exception to signal a nick name already used
 */
@SuppressWarnings("serial")
public final class UserNickNameAlreadyUsedException extends FunctionalException {

    /**
     * Exception to signal a nick name already used
     */
    public UserNickNameAlreadyUsedException(final UserRegistrationParameter userRegistrationParameter) {
        super("The nick name " + userRegistrationParameter.getNickName() + " is already used by a another account");
    }

}
