package fr.opendoha.myguild.server.tools.handler;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import fr.opendoha.myguild.server.exception.FunctionalException;

@ControllerAdvice
public class GlobalExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FunctionalException.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) throws Exception {

        if(ex instanceof FunctionalException){
            
            this.logger.info("Handling - exception : " + ex.getClass().getSimpleName() + " / message : " + ex.getMessage());

            ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getSimpleName());
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        } else if(ex instanceof UndeclaredThrowableException) {

            UndeclaredThrowableException undeclaredThrowableException = (UndeclaredThrowableException) ex;
            
            Throwable throwable = undeclaredThrowableException.getUndeclaredThrowable();

            if(throwable instanceof FunctionalException){
                this.logger.info("Handling - exception : " + throwable.getClass().getSimpleName() + " / message : " + throwable.getMessage());
                ErrorDetails errorDetails = new ErrorDetails(new Date(), throwable.getMessage(), request.getDescription(false), throwable.getClass().getSimpleName());
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
            }else {
                this.logger.info("Handling - exception : " + ex.getClass().getSimpleName() + " / message : " + ex.getMessage());
                throw ex;
            }
            
        } else {
            throw ex;
        }
        
    }
}