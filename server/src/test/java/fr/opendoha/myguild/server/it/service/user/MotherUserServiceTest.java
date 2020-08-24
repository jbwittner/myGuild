package fr.opendoha.myguild.server.it.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.tools.exceptions.DataInitialisationException;


/**
 * Class to prepare test of user account repository
 */
public class MotherUserServiceTest extends AbstractMotherIntegrationTest {

    static final Integer MIN_ACCOUNT_TYPE = 25;
    static final Integer MAX_ACCOUNT_TYPE = 50;

    final protected List<UserAccount> userAccountList = new ArrayList<>();

    protected UserAccount randomUserAccount;

    @Autowired
    protected UserAccountRepository userAccountRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDataBeforeEach() throws DataInitialisationException{

        final Integer numberUserAccount = this.factory.getRandomInteger(MIN_ACCOUNT_TYPE, MAX_ACCOUNT_TYPE);

        UserAccount userAccount;

        for (int i = 0; i < numberUserAccount; i++) {
            userAccount = this.factory.createUserAccount();
            this.userAccountList.add(userAccount);
            this.userAccountRepository.save(userAccount);
        }

        this.randomUserAccount = this.userAccountList.get(this.factory.getRandomInteger(0, numberUserAccount));

        this.userAccountRepository.flush();

        this.checkDataCount(this.userAccountRepository.count());

    }

}