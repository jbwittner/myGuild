package fr.jbwittner.myguild.server.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Email validator used to check the validity of email adress
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
     
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
                                                + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    /**
     * Initialization of the validator
     * @param constraintAnnotation constraint annotation 
     */
    @Override
    public void initialize(final ValidEmail constraintAnnotation) {}

    /**
     * Method used to check the validity of email adress
     * @param email email who need to check
     * @param context Provides contextual data and operation when applying a given constraint validator
     * @return true if the email is valid otherwise false
     */
    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context){   
        return validateEmail(email);
    } 

    /**
     * Method used to check the validity of email adress
     * @param email email who need to check
     * @return true if the email is valid otherwise false
     */
    private boolean validateEmail(final String email) {
        boolean result;
        pattern = Pattern.compile(EMAIL_PATTERN);

        if (null == email){
            result = false;
        } else {
            matcher = pattern.matcher(email);
            result = matcher.matches();
        }

        return result;
        
    }
}