package fr.opendoha.myguild.server.exception;

import fr.opendoha.myguild.server.model.blizzard.Guild;

/**
 * Exception to signal that a guild doesn't use the application
 */
@SuppressWarnings("serial")
public class GuildDoesNotUseApplication extends FunctionalException {

    /**
     * Exception to signal that a guild doesn't use the application
     */
    public GuildDoesNotUseApplication(final Guild guild) {
        super("The guild < " + guild.getName() + " > does not use the application");
    }
}