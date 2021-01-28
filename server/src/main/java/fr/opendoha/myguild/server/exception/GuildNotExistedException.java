package fr.opendoha.myguild.server.exception;

/**
 * Exception to signal a violation of data validation
 */
@SuppressWarnings("serial")
public class GuildNotExistedException extends FunctionalException {

    /**
     * Exception used when the guild doesn't exist
     */
    public GuildNotExistedException(final Integer guildId) {
        super("No guild exist with the ID : " + guildId);
    }
}