package fr.opendoha.myguild.server.exception;

/**
 * Exception to signal a unexptected exception
 */
@SuppressWarnings("serial")
public class UnexpectedException extends FunctionalException {

    /**
     * Exception to signal a unexptected exception
     */
    public UnexpectedException(final Exception e) {
        super("An unexpected exception was throw : " + e.getMessage());
    }
    
}