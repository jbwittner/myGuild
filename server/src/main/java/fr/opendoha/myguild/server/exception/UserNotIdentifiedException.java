package fr.opendoha.myguild.server.exception;

/**
 * Exception to signal a user not identified
 */
@SuppressWarnings("serial")
public class UserNotIdentifiedException extends FunctionalException {

    /**
     * Exception to signal a user not identified
     */
    public UserNotIdentifiedException() {
        super("Can not identified the user");
    }
}