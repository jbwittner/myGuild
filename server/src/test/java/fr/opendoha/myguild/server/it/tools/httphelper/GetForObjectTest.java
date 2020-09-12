package fr.opendoha.myguild.server.it.tools.httphelper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.HttpHelper;

/**
 * Class to check the getForObject method of HttpHelper
 */
@RunWith(MockitoJUnitRunner.class)
public class GetForObjectTest extends AbstractMotherIntegrationTest {

    @Mock
    protected RestTemplate restTemplate;

    @InjectMocks
    @Spy
    protected HttpHelper httpHelper;

    protected static Integer LENGTH_RANDOM_STRING = 20;
    
    @Override
    protected void initDataBeforeEach(){}

    /**
     * Test to check the getForObject method
     */
    @Test
    public void testGetForObjectOk(){
        final String expected = this.factory.getRandomAsciiString(LENGTH_RANDOM_STRING);

        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class)))
            .thenReturn(expected);

        final String actual = this.httpHelper.getForObject(expected, String.class);

        Assertions.assertEquals(expected, actual);
    }

}
