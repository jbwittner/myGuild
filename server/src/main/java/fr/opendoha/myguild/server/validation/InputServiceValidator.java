package fr.opendoha.myguild.server.validation;

import fr.opendoha.myguild.server.exception.ValidationDataException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Validator to validate input data
 *
 * @param <T> class of the object to validate
 */
public class InputServiceValidator<T> {

    protected final ValidatorFactory validatorFactory;
    protected final Validator validator;

    /**
     * Constructor
     */
    public InputServiceValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * Validation method
     *
     * @param object object to validate
     * @throws ValidationDataException
     */
    public void validate(final T object) throws ValidationDataException {
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

        if (!constraintViolations.isEmpty()) {
            final StringBuilder message = new StringBuilder();

            int size = constraintViolations.size();
            int index = 0;

            for (final ConstraintViolation<T> constraints : constraintViolations) {
                message.append(constraints.getRootBeanClass().getSimpleName())
                        .append('.')
                        .append(constraints.getPropertyPath())
                        .append(' ')
                        .append(constraints.getMessage());
                
                index++;
                
                if(index < size){
                    message.append(',').append(' ');
                }

            }

            throw new ValidationDataException(message.toString());
        }
    }

}