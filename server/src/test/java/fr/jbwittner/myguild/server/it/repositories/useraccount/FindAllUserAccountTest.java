package fr.jbwittner.myguild.server.it.repositories.useraccount;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.jbwittner.myguild.server.model.UserAccount;

/**
 * Class to check the findAll method of UserAccountRepository
 */
public class FindAllUserAccountTest extends MotherUserAccountRepositoryTest {

    /**
     * Test to verify the findAll method
     */
    @Test
    public void findAllTestOk() {
        final Iterable<UserAccount> UserAccountIterable = this.userAccountRepository.findAll();
        final List<UserAccount> resultsList = new ArrayList<>();
        
        UserAccountIterable.forEach(resultsList::add);

        Assertions.assertEquals(this.userAccountList.size(), resultsList.size());

        Boolean listContains;

        for (final UserAccount UserAccount : this.userAccountList) {
            listContains = resultsList.contains(UserAccount);
            Assertions.assertTrue(listContains);
        }
    }

}