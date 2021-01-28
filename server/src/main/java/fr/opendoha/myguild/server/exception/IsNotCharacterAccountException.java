package fr.opendoha.myguild.server.exception;

/**
 * Exception to signal that a character is not on a account
 */
@SuppressWarnings("serial")
public class IsNotCharacterAccountException extends FunctionalException {

    /**
     * Exception to signal that a character is not on a account
     */
    public IsNotCharacterAccountException() {
        super("The character is not a character of your account");
    }
}