package fr.opendoha.myguild.server.testhelper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mother class for integrations tests
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public abstract class AbstractMotherIntegrationTest {

    protected final TestObjectFactory factory = new TestObjectFactory();

    /**
     * Method launch before each test
     */
    @BeforeEach
    public void beforeEach(){
        this.initDataBeforeEach();
    }

    /**
     * Method launch after each test
     */
    @AfterEach
    public void afterEach(){
        this.factory.resetAllList();
    }

    /**
     * Method used to prepare the data of tests
     */
    abstract protected void initDataBeforeEach();

}
