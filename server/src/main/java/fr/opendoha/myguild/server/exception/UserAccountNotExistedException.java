package fr.opendoha.myguild.server.exception;

/**
 * Exception used when the user account doesn't exist
 */
public class UserAccountNotExistedException extends RuntimeException {

    /**
     * Exception used when the user account doesn't exist
     */
    public UserAccountNotExistedException(final Integer blizzardId) {
        super("No user account exist with the Blizzard ID : " + blizzardId);
    }
}
