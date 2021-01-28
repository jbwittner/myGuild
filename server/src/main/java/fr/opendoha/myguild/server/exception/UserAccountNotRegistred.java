package fr.opendoha.myguild.server.exception;

/**
 * Exception to signal a thaht a user are not registred
 */
@SuppressWarnings("serial")
public class UserAccountNotRegistred extends FunctionalException {

    /**
     * Exception to signal a thaht a user are not registred
     */
    public UserAccountNotRegistred(final String battletag) {
        super("The account with the battletag \"" + battletag + "\" are not registred");
    }
}
