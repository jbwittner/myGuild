package fr.opendoha.myguild.server.service.characterservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.exception.IsNotCharacterAccountException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CharacterRepository;
import fr.opendoha.myguild.server.service.CharacterService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;

/**
 * Test class to test setFavoriteCharacter method
 */
public class SetFavoriteCharacterTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected CharacterService characterService;

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected CharacterRepository characterRepository;

    @Override
    protected void initDataBeforeEach() throws Exception {
    }

    /**
     * Test to set a favorite character to true
     */
    @Test
    public void setFavoriteTrueOk() throws CharacterNotExistedException, IsNotCharacterAccountException {

        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        Character character = this.factory.getCharacter(userAccount);
        this.characterRepository.save(character);

        final FavoriteCharacterParameter favoriteCharacterParameter = new FavoriteCharacterParameter();
        favoriteCharacterParameter.setBlizzardId(userAccount.getBlizzardId());
        favoriteCharacterParameter.setId(character.getId());
        favoriteCharacterParameter.setIsFavorite(true);

        this.characterService.setFavoriteCharacter(favoriteCharacterParameter);

        character = this.characterRepository.findById(character.getId()).get();

        Assertions.assertTrue(character.isFavorite());

    }

    /**
     * Test to set a favorite character to false
     */
    @Test
    public void setFavoriteFalseOk() throws CharacterNotExistedException, IsNotCharacterAccountException {

        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        Character character = this.factory.getCharacter(userAccount);
        character.setIsFavoritesFalse();
        this.characterRepository.save(character);

        final FavoriteCharacterParameter favoriteCharacterParameter = new FavoriteCharacterParameter();
        favoriteCharacterParameter.setBlizzardId(userAccount.getBlizzardId());
        favoriteCharacterParameter.setId(character.getId());
        favoriteCharacterParameter.setIsFavorite(false);

        this.characterService.setFavoriteCharacter(favoriteCharacterParameter);

        character = this.characterRepository.findById(character.getId()).get();

        Assertions.assertFalse(character.isFavorite());

    }

    /**
     * Test to set a non existent character failed
     */
    @Test
    public void setFavoriteCharacterNotExistedFailed() throws CharacterNotExistedException, IsNotCharacterAccountException {

        final FavoriteCharacterParameter favoriteCharacterParameter = new FavoriteCharacterParameter();
        favoriteCharacterParameter.setBlizzardId(this.factory.getRandomInteger());
        favoriteCharacterParameter.setId(this.factory.getRandomInteger());

        Assertions.assertThrows(CharacterNotExistedException.class,
                () -> this.characterService.setFavoriteCharacter(favoriteCharacterParameter));

    }

    /**
     * Test to set a character from a other account failed
     */
    @Test
    public void setFavoriteCharacterOtherUserAccountFailed() throws CharacterNotExistedException, IsNotCharacterAccountException {

        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        final Character character = this.factory.getCharacter(userAccount);
        this.characterRepository.save(character);

        final FavoriteCharacterParameter favoriteCharacterParameter = new FavoriteCharacterParameter();
        favoriteCharacterParameter.setBlizzardId(this.factory.getRandomInteger());
        favoriteCharacterParameter.setId(character.getId());

        Assertions.assertThrows(IsNotCharacterAccountException.class,
                () -> this.characterService.setFavoriteCharacter(favoriteCharacterParameter));

    }

    /**
     * Test to set a character from a non account failed
     */
    @Test
    public void setFavoriteCharacterNoUserAccountFailed() throws CharacterNotExistedException, IsNotCharacterAccountException {

        final Character character = this.factory.getCharacter();
        this.characterRepository.save(character);

        final FavoriteCharacterParameter favoriteCharacterParameter = new FavoriteCharacterParameter();
        favoriteCharacterParameter.setBlizzardId(this.factory.getRandomInteger());
        favoriteCharacterParameter.setId(character.getId());

        Assertions.assertThrows(IsNotCharacterAccountException.class,
                () -> this.characterService.setFavoriteCharacter(favoriteCharacterParameter));

    }
    
}
