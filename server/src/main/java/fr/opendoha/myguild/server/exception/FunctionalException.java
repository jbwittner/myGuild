package fr.opendoha.myguild.server.exception;

/**
 * Abstract functional exception used to pass the exception to the frontend
 */
@SuppressWarnings("serial")
public abstract class FunctionalException extends Exception{

    /**
     * Constructeur
     * @param message the message parameter
     */
    public FunctionalException(final String message){
        super(message);
    }

    /**
     * Constructeur
     */
    public FunctionalException(){
        super();
    }
    
}
