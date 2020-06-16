package fr.jbwittner.myguild.server.ut;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import fr.jbwittner.myguild.server.tools.TestObjectFactory;
import fr.jbwittner.myguild.server.tools.exceptions.DataInitialisationException;

/**
 * Mother class for integrations tests
 */
public abstract class AbstractMotherUnitTest {

    protected TestObjectFactory factory = new TestObjectFactory();

    /**
     * Method launch before each test
     */
    @BeforeEach
    public void beforeEach() throws DataInitialisationException{
        this.initDataBeforeEach();
    }

    /**
     * Method launch after each test
     */
    @AfterEach
    public void afterEach(){
        this.factory.resetListString();
    }

    /**
     * Method used to prepare the data of tests
     */
    abstract protected void initDataBeforeEach() throws DataInitialisationException;

    /**
     * Method used to check the data of tests
     */
    protected void checkDataCount(final long count) throws DataInitialisationException {
        if(0 >= count){
            throw new DataInitialisationException();
        }
    }

}
