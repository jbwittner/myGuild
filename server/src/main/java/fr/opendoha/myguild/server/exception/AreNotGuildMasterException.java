package fr.opendoha.myguild.server.exception;

/**
 * Exception used to signal that the user is not the guild master
 */
@SuppressWarnings("serial")
public class AreNotGuildMasterException extends FunctionalException {

    /**
     * Exception used to signal that the user is not the guild master
     */
    public AreNotGuildMasterException(final String guildName) {
        super("You are not the guild master of " + guildName);
    }
}