package fr.opendoha.myguild.server.tools.handler.globalexceptionhandler;

import java.lang.reflect.UndeclaredThrowableException;
import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import fr.opendoha.myguild.server.exception.FunctionalException;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.handler.ErrorDetails;
import fr.opendoha.myguild.server.tools.handler.GlobalExceptionHandler;

/**
 * Test class to test globleExceptionHandler method
 */
public class GlobleExceptionHandlerTest extends AbstractMotherIntegrationTest {

    @Override
    protected void initDataBeforeEach() throws Exception {}

    /**
     * Test with a Exception
     */
    @Test
    public void ExceptionFail() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final Exception exception = new Exception();

        Assertions.assertThrows(Exception.class,
            () -> globalExceptionHandler.globleExcpetionHandler(exception, fakeWebRequest));
        
    }

    /**
     * Test with a UndeclaredThrowableException and a Exception
     */
    @Test
    public void UndeclaredThrowableExceptionWithExceptionFail() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final Exception exception = new Exception();
        final UndeclaredThrowableException undeclaredThrowableException = new UndeclaredThrowableException(exception);

        Assertions.assertThrows(Exception.class,
            () -> globalExceptionHandler.globleExcpetionHandler(undeclaredThrowableException, fakeWebRequest));
        
    }

    /**
     * Test with a UndeclaredThrowableException and a FunctionalException
     */
    @Test
    public void UndeclaredThrowableExceptionWithFunctionalExceptionOk() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final MockFunctionalException exception = new MockFunctionalException();
        final UndeclaredThrowableException undeclaredThrowableException = new UndeclaredThrowableException(exception);

        final Date dateBefore = new Date();
        final ResponseEntity<?> test = globalExceptionHandler.globleExcpetionHandler(undeclaredThrowableException, fakeWebRequest);

        Thread.sleep(1000);

        final Date dateAfter = new Date();

        final ErrorDetails errorDetails = (ErrorDetails) test.getBody();

        final Date dateException = errorDetails.getTimestamp();

        Assertions.assertEquals(MockFunctionalException.class.getSimpleName(), errorDetails.getException());
        Assertions.assertEquals(exception.getMessage(), errorDetails.getMessage());
        Assertions.assertEquals(fakeWebRequest.getDescription(false), errorDetails.getDetails());
        Assertions.assertTrue(dateBefore.before(dateException));
        Assertions.assertTrue(dateAfter.after(dateException));
        
    }

    /**
     * Test with a FunctionalException
     */
    @Test
    public void FunctionalExceptionOk() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final MockFunctionalException exception = new MockFunctionalException();

        final Date dateBefore = new Date();
        final ResponseEntity<?> test = globalExceptionHandler.globleExcpetionHandler(exception, fakeWebRequest);

        Thread.sleep(100);

        final Date dateAfter = new Date();

        final ErrorDetails errorDetails = (ErrorDetails) test.getBody();

        final Date dateException = errorDetails.getTimestamp();

        Assertions.assertEquals(MockFunctionalException.class.getSimpleName(), errorDetails.getException());
        Assertions.assertEquals(exception.getMessage(), errorDetails.getMessage());
        Assertions.assertEquals(fakeWebRequest.getDescription(false), errorDetails.getDetails());
        Assertions.assertTrue(dateBefore.before(dateException));
        Assertions.assertTrue(dateAfter.after(dateException));
        
    }

    /**
     * Custom FunctionalException
     */
    class MockFunctionalException extends FunctionalException {

        private static final long serialVersionUID = 1L;

        /**
         * Constructor
         */
        public MockFunctionalException() {
            super("CustomMessage");
        }

    }

    /**
     * Fake WebRequest
     */
    class FakeWebRequest implements WebRequest {

        @Override
        public Object getAttribute(final String name, final int scope) {
            return null;
        }
    
        @Override
        public void setAttribute(final String name, final Object value, final int scope) {
    
        }
    
        @Override
        public void removeAttribute(final String name, final int scope) {
    
        }
    
        @Override
        public String[] getAttributeNames(final int scope) {
            return new String[0];
        }
    
        @Override
        public void registerDestructionCallback(final String name, final Runnable callback, final int scope) {
    
        }
    
        @Override
        public Object resolveReference(final String key) {
            return null;
        }
    
        @Override
        public String getSessionId() {
            return null;
        }
    
        @Override
        public Object getSessionMutex() {
            return null;
        }
    
        @Override
        public String getHeader(final String headerName) {
            return null;
        }
    
        @Override
        public String[] getHeaderValues(final String headerName) {
            return new String[0];
        }
    
        @Override
        public Iterator<String> getHeaderNames() {
            return null;
        }
    
        @Override
        public String getParameter(final String paramName) {
            return null;
        }
    
        @Override
        public String[] getParameterValues(final String paramName) {
            return new String[0];
        }
    
        @Override
        public Iterator<String> getParameterNames() {
            return null;
        }
    
        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }
    
        @Override
        public Locale getLocale() {
            return null;
        }
    
        @Override
        public String getContextPath() {
            return null;
        }
    
        @Override
        public String getRemoteUser() {
            return null;
        }
    
        @Override
        public Principal getUserPrincipal() {
            return null;
        }
    
        @Override
        public boolean isUserInRole(final String role) {
            return false;
        }
    
        @Override
        public boolean isSecure() {
            return false;
        }
    
        @Override
        public boolean checkNotModified(final long lastModifiedTimestamp) {
            return false;
        }
    
        @Override
        public boolean checkNotModified(final String etag) {
            return false;
        }
    
        @Override
        public boolean checkNotModified(final String etag, final long lastModifiedTimestamp) {
            return false;
        }
    
        @Override
        public String getDescription(final boolean includeClientInfo) {
            return "CustomDescritpion";
        }
        
    }
    

}