package fr.opendoha.myguild.server.exception;

/**
 * Exception to signal a violation of data validation
 */
@SuppressWarnings("serial")
public class ValidationDataException extends RuntimeException {

    /**
     * Exception to signal a violation of data validation
     * @param message to signal the violation
     */
    public ValidationDataException(final String message) {
        super(message);
    }

}