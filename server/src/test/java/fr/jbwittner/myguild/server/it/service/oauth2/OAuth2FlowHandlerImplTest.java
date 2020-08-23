package fr.jbwittner.myguild.server.it.service.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.jbwittner.myguild.server.service.oauth2.BlizzardOAuth2FlowHandler;
import fr.jbwittner.myguild.server.service.oauth2.models.TokenResponse;

import org.mockito.internal.util.reflection.FieldSetter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OAuth2FlowHandlerImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private BlizzardOAuth2FlowHandler blizzardOAuth2FlowHandler;

    
    @Test
    public void getTokenFreshGoldenPath() throws IOException, NoSuchFieldException {
        final String token = "exampleToken";
        final OutputStream outputStream = mock(OutputStream.class);
        final TokenResponse mockTokenResponse = mock(TokenResponse.class);

        String clientId = "someClientId";
        String clientSecret = "someClientSecret";
        String encodeFormat = "UTF-8";
        int responseCode = 200;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
            String.format("{'access_token':'%s', 'expires_in':'1'}", token).getBytes("UTF-8")
        );

        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), null);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), null);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tockenUrl"), "https://google.com/");
        
        doReturn(mockTokenResponse).when(objectMapper).readValue(anyString(), eq(TokenResponse.class));
        Assert.assertEquals("token", blizzardOAuth2FlowHandler.getToken());
    }
    

    @Test
    public void getTokenCachedGoldenPath() throws NoSuchFieldException, IOException {
        final String token = "myCachedToken";
        // Cached token condition and setting the token
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), Instant.now().plus(5, ChronoUnit.MINUTES));
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), token);

        Assert.assertEquals(token, blizzardOAuth2FlowHandler.getToken());
    }

    @Test
    public void isTokenInvalidGoldenPath() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), Instant.now().plus(5, ChronoUnit.MINUTES));
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), "SomeSampleToken");
        Assert.assertFalse(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    @Test
    public void isTokenInvalidNullToken() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), null);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), null);
        Assert.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    @Test
    public void isTokenInvalidExpiredTokenExpiry() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), Instant.EPOCH);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), "SomeSampleToken");
        Assert.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    @Test
    public void isTokenInvalidNullTokenExpiry() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), null);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), "SomeSampleToken");
        Assert.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }
}