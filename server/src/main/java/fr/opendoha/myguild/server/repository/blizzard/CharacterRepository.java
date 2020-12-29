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

    List<Character> findByIsUpdatedFalse();

    List<Character> findByUserAccount(UserAccount userAccount);

    List<Character> findByUserAccountAndGuildIsNotNull(UserAccount userAccount);

    List<Character> findByUserAccountAndIsUpdatedFalse(UserAccount userAccount);

    List<Character> findByGuild(Guild guild);

}
