package fr.opendoha.myguild.server.it.tools.httphelper;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.TestObjectFactory;

/**
 * Class to check the getUrl method of HttpHelper
 */
public class GetUrlTest extends AbstractMotherIntegrationTest {

    @Autowired
    HttpHelper httpHelper;

    protected static Integer LENGTH_RANDOM_URI = 20;

    @Override
    protected void initDataBeforeEach(){}

    /**
     * Test to check to get a new url
     * @throws MalformedURLException Thrown to indicate that a malformed URL has occurred 
     */
    @Test
    public void testGetUrlOk() throws MalformedURLException{
        final String expectedUri = "http://" + this.factory.getRandomAlphanumericString(TestObjectFactory.LENGTH_URI) + ".com";

        final URL url = httpHelper.getUrl(expectedUri);

        final String actual = url.getProtocol() + "://" + url.getHost();

        Assertions.assertEquals(expectedUri, actual);

    }
    
}
