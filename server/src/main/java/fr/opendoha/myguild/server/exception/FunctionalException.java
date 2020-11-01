package fr.opendoha.myguild.server.exception;

public abstract class FunctionalException extends Exception{

    private static final long serialVersionUID = 1L;

    public FunctionalException() {
    }

    public FunctionalException(String message){
        super(message);
    }

    public FunctionalException(String message, Throwable cause){
        super(message, cause);
    }
    
}
