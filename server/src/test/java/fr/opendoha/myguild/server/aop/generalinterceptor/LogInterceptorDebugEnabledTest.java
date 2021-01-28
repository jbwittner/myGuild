package fr.opendoha.myguild.server.aop.generalinterceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import fr.opendoha.myguild.server.aop.interceptor.GeneralInterceptor;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;

/**
 * Test class to test logInterceptorDebugEnabled method with log enabled
 */
@ActiveProfiles("aoptestenabled")
public class LogInterceptorDebugEnabledTest extends AbstractMotherIntegrationTest {

    @Mock
    protected ProceedingJoinPoint joinPoint;


    @Override
    protected void initDataBeforeEach() throws Exception {}

    /**
     * Test log execution
     */
    @Test
    public void logExecutionTimeTestDebugEnabled() throws Throwable {
        final GeneralInterceptor generalInterceptor = new GeneralInterceptor();

        final ObjectToLog objectToLog = new ObjectToLog();

        final Object[] objects = new Object[2];
        objects[0] = objectToLog;
        objects[1] = null;
        Mockito.when(joinPoint.getArgs()).thenReturn(objects);

        final SignatureImpl signature = new SignatureImpl();

        Mockito.when(joinPoint.getSignature()).thenReturn(signature);

        generalInterceptor.logInterceptor(joinPoint);

    }

    /**
     * Custom implementation of Signature
     */
    private class SignatureImpl implements Signature {

        @Override
        public String toShortString() {
            return null;
        }
    
        @Override
        public String toLongString() {
            return null;
        }
    
        @Override
        public String getName() {
            return null;
        }
    
        @Override
        public int getModifiers() {
            return 0;
        }
    
        @Override
        public Class<?> getDeclaringType() {
            return SignatureImpl.class;
        }
    
        @Override
        public String getDeclaringTypeName() {
            return "SignatureImpl";
        }
    
    }
    
    /**
     * Fake object to log
     */
    class ObjectToLog {
    
        /**
         * Random method without argument
         */
        public void randomMethod(){
    
        }
    
        /**
         * Random method with argument
         */
        public String randomMethodWithArgument(final String input){
            return input;
        }
    }    

}

