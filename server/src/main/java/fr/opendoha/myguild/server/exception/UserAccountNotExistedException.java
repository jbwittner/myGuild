package fr.opendoha.myguild.server.exception;

/**
 * Exception used when the user account doesn't exist
 */
public class UserAccountNotExistedException extends FunctionalException {

    /**
     *
     */
    private static final long serialVersionUID = 6753551758001742394L;

    /**
     * Exception used when the user account doesn't exist
     */
    public UserAccountNotExistedException(final Integer blizzardId) {
        super("No user account exist with the Blizzard ID : " + blizzardId);
    }
}
