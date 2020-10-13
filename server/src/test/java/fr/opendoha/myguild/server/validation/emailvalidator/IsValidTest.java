package fr.opendoha.myguild.server.validation.emailvalidator;

import fr.opendoha.myguild.server.exception.ValidationDataException;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.validation.InputServiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of validate
 */
public class IsValidTest extends AbstractMotherIntegrationTest {

    final InputServiceValidator<Object> validator = new InputServiceValidator<>();

    @Override
    protected void initDataBeforeEach() {}

    /**
     * Test with a valid email
     */
    @Test
    public void testWithValidEmailOk(){
        final EmailTest emailTest = new EmailTest();
        emailTest.setEmail(this.factory.getUniqueRandomEmail());

        validator.validate(emailTest);
    }

    /**
     * Test with a invalid email
     */
    @Test
    public void testWithInvalidEmailOk(){
        final EmailTest emailTest = new EmailTest();
        emailTest.setEmail(this.factory.getUniqueRandomAlphanumericString(10));

        Assertions.assertThrows(ValidationDataException.class, () -> validator.validate(emailTest));
    }

    /**
     * test with a null email
     */
    @Test
    public void testWithNullEmailOk(){
        final EmailTest emailTest = new EmailTest();
        emailTest.setEmail(null);

        Assertions.assertThrows(ValidationDataException.class, () -> validator.validate(emailTest));
    }

}
