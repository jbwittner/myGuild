package fr.opendoha.myguild.server.tools.httphelper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.helper.implementation.HttpHelperImpl;

/**
 * Class to check the getForObject method of HttpHelper
 */
public class GetForObjectTest extends AbstractMotherIntegrationTest {

    @Mock
    protected RestTemplate restTemplateMock;

    @InjectMocks
    protected HttpHelperImpl httpHelper;

    @Override
    protected void initDataBeforeEach(){}

    /**
     * Test to check the getForObject method
     */
    @Test
    public void testGetForObjectOk(){
        final String expected = this.factory.getUniqueRandomURI();

        Mockito.when(restTemplateMock.getForObject(anyString(), eq(String.class)))
            .thenReturn(expected);

        final String actual = this.httpHelper.getForObject(expected, String.class);

        Assertions.assertEquals(expected, actual);
    }

}
