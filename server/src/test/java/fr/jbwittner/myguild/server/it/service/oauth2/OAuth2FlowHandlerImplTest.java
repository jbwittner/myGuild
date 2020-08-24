package fr.jbwittner.myguild.server.it.service.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

import fr.jbwittner.myguild.server.service.oauth2.BlizzardOAuth2FlowHandler;
import fr.jbwittner.myguild.server.service.oauth2.models.TokenResponse;
import fr.jbwittner.myguild.server.tools.HttpHelper;

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

import fr.jbwittner.myguild.server.it.AbstractMotherIntegrationTest;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OAuth2FlowHandlerImplTest extends AbstractMotherIntegrationTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpURLConnection mockHttpConnection;

    @InjectMocks
    private BlizzardOAuth2FlowHandler blizzardOAuth2FlowHandler;

    @Override
    public void initDataBeforeEach(){}

    @Test
    public void getTokenFreshGoldenPath() throws IOException, NoSuchFieldException, Exception {
        final String token = "exampleToken";
        final OutputStream outputStream = mock(OutputStream.class);
        final TokenResponse mockTokenResponse = mock(TokenResponse.class);
        final HttpURLConnection mockUrlConnection = mock(HttpURLConnection.class);
        final URL mockUrl = mock(URL.class);
        final HttpHelper mockHttpHelper = mock(HttpHelper.class);

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
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("clientId"), clientId);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("clientSecret"), clientSecret);

        blizzardOAuth2FlowHandler.setHttpHelper(mockHttpHelper);

        doReturn(mockUrl).when(mockHttpHelper).getUrl(anyString());
        doReturn(mockUrlConnection).when(mockUrl).openConnection();
        doReturn(byteArrayInputStream).when(mockUrlConnection).getInputStream();
        doReturn(outputStream).when(mockUrlConnection).getOutputStream();
        doReturn(responseCode).when(mockUrlConnection).getResponseCode();
        doReturn(mockTokenResponse).when(objectMapper).readValue(anyString(), eq(TokenResponse.class));
        doReturn(token).when(mockTokenResponse).getAccess_token();

        Assert.assertEquals(token, blizzardOAuth2FlowHandler.getToken());

        verify(mockUrlConnection, times(1)).setRequestMethod("POST");
        verify(mockUrlConnection, times(1))
            .setRequestProperty(
                "Authorization",
                String.format("Basic %s",
                    Base64.getEncoder().encodeToString(
                        String.format("%s:%s", clientId, clientSecret).getBytes(encodeFormat)
                    )
                )
            );
        verify(mockUrlConnection, times(1)).setDoOutput(true);
        verify(outputStream, times(1)).write("grant_type=client_credentials".getBytes(encodeFormat));
        verify(mockUrlConnection, times(1)).getResponseCode();


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