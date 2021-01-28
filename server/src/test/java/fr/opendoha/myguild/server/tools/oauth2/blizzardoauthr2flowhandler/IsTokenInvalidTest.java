package fr.opendoha.myguild.server.tools.oauth2.blizzardoauthr2flowhandler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.oauth2.BlizzardOAuth2FlowHandler;

/**
 * Test class to test isTokenInvalid method
 */
public class IsTokenInvalidTest extends AbstractMotherIntegrationTest {

    @InjectMocks
    private BlizzardOAuth2FlowHandler blizzardOAuth2FlowHandler;

    @Override
    public void initDataBeforeEach(){}
    
    /**
     * Test to check if the token is valid because it has not expired 
     */
    @Test
    public void testIsTokenInvalidWithValidTokenOk() throws NoSuchFieldException {

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenExpiry", 
            Instant.now().plus(BlizzardOAuth2FlowHandler.MINUTES_MARGIN + 1, ChronoUnit.MINUTES));

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "token", "SomeSampleToken");

        Assertions.assertFalse(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    /**
     * Test to check if the token is valid because it has not expired 
     */
    @Test
    public void testIsTokenInvalidWithInValidTokenOk() throws NoSuchFieldException {

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenExpiry", 
            Instant.now().plus(BlizzardOAuth2FlowHandler.MINUTES_MARGIN - 1, ChronoUnit.MINUTES));

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "token", "SomeSampleToken");

        Assertions.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    /**
     * Test to check if the token is not valid
     */
    @Test
    public void testIsTokenInvalidWithNullTokenOk() throws NoSuchFieldException {

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenExpiry", null);
        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "token", null);

        Assertions.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    /**
     * Test to check if the token is valid
     */
    @Test
    public void testIsTokenInvalidExpiredTokenExpiryOk() throws NoSuchFieldException {

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenExpiry", Instant.EPOCH);
        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "token", "SomeSampleToken");

        Assertions.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    /**
     * Test to check if the token is valid
     */
    @Test
    public void testIsTokenInvalidNullTokenExpiryOk() throws NoSuchFieldException {

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenExpiry", null);
        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "token", "SomeSampleToken");

        Assertions.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

}
