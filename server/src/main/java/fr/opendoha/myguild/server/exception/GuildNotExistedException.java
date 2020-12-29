package fr.opendoha.myguild.server.exception;

/**
 * Exception used when the guild doesn't exist
 */
public class GuildNotExistedException extends FunctionalException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception used when the guild doesn't exist
     */
    public GuildNotExistedException(final Integer guildId) {
        super("No guild exist with the ID : " + guildId);
    }
}