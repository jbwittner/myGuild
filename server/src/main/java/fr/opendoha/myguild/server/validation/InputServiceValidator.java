package fr.opendoha.myguild.server.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import fr.opendoha.myguild.server.exception.ValidationDataException;

/**
 * Validator to validate input data
 * @param <T> class of the object to validate
 */
public class InputServiceValidator<T> {

    ValidatorFactory validatorFactory;
    Validator validator;

    /**
     * Constructor
     */
    public InputServiceValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * Validation method
     * @param object object to validate
     */
    public void validate(final T object){
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        
        if (!constraintViolations.isEmpty()) {
            String message = "";
            
            for (final ConstraintViolation<T> contraintes : constraintViolations) {
                message = message + contraintes.getRootBeanClass().getSimpleName()+ "." + contraintes.getPropertyPath() + " " + contraintes.getMessage();
            }

            throw new ValidationDataException(message);
        }
    }
    
}