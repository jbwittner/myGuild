package fr.jbwittner.myguild.server.tools.exceptions;

/**
 * Exption throw when a problem occurend during data initialization
 */
@SuppressWarnings("serial")
public final class DataInitialisationException extends RuntimeException {

    /**
     * Constructor or DataInitialisationException
     */
    public DataInitialisationException() {
        super("A problem occurred during data initialization");
    }

}
