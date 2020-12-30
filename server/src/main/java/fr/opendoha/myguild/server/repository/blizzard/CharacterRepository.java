package fr.opendoha.myguild.server.repository.blizzard;

import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.repository.AbstractRepository;

import java.util.List;


/**
 * Repository for Character
 */
public interface CharacterRepository extends AbstractRepository<Character, Integer> {

    /**
     * Method to get all characters who are not updated
     */
    List<Character> findByIsUpdatedFalse();

    /**
     * Method to get all characters from a account
     */
    List<Character> findByUserAccount(UserAccount userAccount);

    /**
     * Method to get all characters from a account and who have a guild
     */
    List<Character> findByUserAccountAndGuildIsNotNull(UserAccount userAccount);

    /**
     * Method to get all characters from a account and who is not updated
     */
    List<Character> findByUserAccountAndIsUpdatedFalse(UserAccount userAccount);

    /**
     * Method to get all characters from a guild
     */
    List<Character> findByGuild(Guild guild);

}
