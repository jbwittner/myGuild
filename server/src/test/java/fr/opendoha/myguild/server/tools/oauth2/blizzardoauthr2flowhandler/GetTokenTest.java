package fr.opendoha.myguild.server.tools.oauth2.blizzardoauthr2flowhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

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
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import fr.opendoha.myguild.server.tools.helper.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.BlizzardOAuth2FlowHandler;
import fr.opendoha.myguild.server.tools.oauth2.models.TokenResponse;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;

/**
 * Test class to test getToken method
 */
public class GetTokenTest extends AbstractMotherIntegrationTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private BlizzardOAuth2FlowHandler blizzardOAuth2FlowHandler;

    @Override
    public void initDataBeforeEach(){}

    /**
     * Test to get a new token
     */
    @Test
    public void testGetNewTokenOk() throws Exception {
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
            String.format("{'access_token':'%s', 'expires_in':'1'}", token).getBytes(StandardCharsets.UTF_8)
        );

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenExpiry", null);
        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "token", null);
        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenUrl", "https://google.com");
        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "clientId", clientId);
        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "clientSecret", clientSecret);

        blizzardOAuth2FlowHandler.setHttpHelper(mockHttpHelper);

        doReturn(mockUrl).when(mockHttpHelper).getUrl(anyString());
        doReturn(mockUrlConnection).when(mockUrl).openConnection();
        doReturn(byteArrayInputStream).when(mockUrlConnection).getInputStream();
        doReturn(outputStream).when(mockUrlConnection).getOutputStream();
        doReturn(responseCode).when(mockUrlConnection).getResponseCode();
        doReturn(mockTokenResponse).when(objectMapper).readValue(anyString(), eq(TokenResponse.class));
        doReturn(token).when(mockTokenResponse).getAccess_token();

        Assertions.assertEquals(token, blizzardOAuth2FlowHandler.getToken());

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
     */
    @Test
    public void testGetNotExpiredTokenOk() throws NoSuchFieldException, IOException {
        final String token = "myCachedToken";
        // Cached token condition and setting the token

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "tokenExpiry", 
            Instant.now().plus(BlizzardOAuth2FlowHandler.MINUTES_MARGIN + 1, ChronoUnit.MINUTES));

        ReflectionTestUtils.setField(blizzardOAuth2FlowHandler, "token", token);

        Assertions.assertEquals(token, blizzardOAuth2FlowHandler.getToken());
    }

}
