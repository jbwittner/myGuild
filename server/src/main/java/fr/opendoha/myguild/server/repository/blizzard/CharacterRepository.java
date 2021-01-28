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
     * Method to get all characters from a account
     */
    List<Character> findByUserAccount(UserAccount userAccount);

    /**
     * Method to get all characters from a account and who have a guild
     */
    List<Character> findByUserAccountAndGuildIsNotNull(UserAccount userAccount);

    /**
     * Method to get all characters of a account and from the guild
     */
    List<Character> findByUserAccountAndGuild(UserAccount userAccount, Guild guild);

    /**
     * Method to get all characters from a guild
     */
    List<Character> findByGuild(Guild guild);

}
