package fr.opendoha.myguild.server.it.service.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.service.oauth2.BlizzardOAuth2FlowHandler;
import fr.opendoha.myguild.server.service.oauth2.models.TokenResponse;
import fr.opendoha.myguild.server.tools.HttpHelper;

/**
 * Test for BlizzardOAuth2FlowHandler
 */
@RunWith(MockitoJUnitRunner.class)
public class OAuth2FlowHandlerImplTest extends AbstractMotherIntegrationTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private BlizzardOAuth2FlowHandler blizzardOAuth2FlowHandler;

    @Override
    public void initDataBeforeEach(){}

    /**
     * Test to get a new token
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws Exception
     */
    @Test
    public void testGetNewTokenOk() throws IOException, NoSuchFieldException, Exception {
        final String token = "exampleToken";
        final OutputStream outputStream = mock(OutputStream.class);
        final TokenResponse mockTokenResponse = mock(TokenResponse.class);
        final HttpURLConnection mockUrlConnection = mock(HttpURLConnection.class);
        final URL mockUrl = mock(URL.class);
        final HttpHelper mockHttpHelper = mock(HttpHelper.class);

        final String clientId = "someClientId";
        final String clientSecret = "someClientSecret";
        final String encodeFormat = "UTF-8";
        final int responseCode = 200;

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
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
    
    /**
     * Test to get the current valid token
     * @throws NoSuchFieldException
     * @throws IOException
     */
    @Test
    public void testGetNotExpiredTokenOk() throws NoSuchFieldException, IOException {
        final String token = "myCachedToken";
        // Cached token condition and setting the token
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), Instant.now().plus(5, ChronoUnit.MINUTES));
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), token);

        Assert.assertEquals(token, blizzardOAuth2FlowHandler.getToken());
    }

    /**
     * Test to check if the token is valid because it has not expired 
     * @throws NoSuchFieldException
     */
    @Test
    public void testIsTokenInvalidWithValidTokenOk() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), Instant.now().plus(5, ChronoUnit.MINUTES));
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), "SomeSampleToken");
        Assert.assertFalse(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    /**
     * Test to check if the token is not valid
     * @throws NoSuchFieldException
     */
    @Test
    public void testIsTokenInvalidWithNullTokenOk() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), null);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), null);
        Assert.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    /**
     * Test to check if the token is valid
     * @throws NoSuchFieldException
     */
    @Test
    public void testIsTokenInvalidExpiredTokenExpiryOk() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), Instant.EPOCH);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), "SomeSampleToken");
        Assert.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }

    /**
     * Test to check if the token is valid
     * @throws NoSuchFieldException
     */
    @Test
    public void testIsTokenInvalidNullTokenExpiryOk() throws NoSuchFieldException {
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("tokenExpiry"), null);
        FieldSetter.setField(blizzardOAuth2FlowHandler, blizzardOAuth2FlowHandler.getClass().getDeclaredField("token"), "SomeSampleToken");
        Assert.assertTrue(blizzardOAuth2FlowHandler.isTokenInvalid());
    }
}