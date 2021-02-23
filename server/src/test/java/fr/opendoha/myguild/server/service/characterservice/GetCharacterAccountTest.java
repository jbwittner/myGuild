package fr.opendoha.myguild.server.service.characterservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CharacterRepository;
import fr.opendoha.myguild.server.repository.blizzard.RealmRepository;
import fr.opendoha.myguild.server.service.CharacterService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;

/**
 * Test class to test getCharacterAccount method
 */
public class GetCharacterAccountTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected CharacterService characterService;

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected CharacterRepository characterRepository;

    @Autowired
    protected RealmRepository realmRepository;

    Realm characterRealm;

    @Override
    protected void initDataBeforeEach() throws Exception {
        this.prepareStaticData();
        final Realm realm = this.factory.getRealm();
        this.characterRealm = this.realmRepository.save(realm);
    }

    private Character generateCharacter(final UserAccount userAccount){
        final Character character = this.factory.getCharacter(userAccount);
        final Faction faction = this.getRandomFaction();
        final PlayableClass playableClass = this.getRandomPlayableClass();
        final PlayableRace playableRace = this.getRandomPlayableRace(faction);

        character.setFaction(faction);
        character.setPlayableClass(playableClass);
        character.setPlayableRace(playableRace);
        character.setRealm(this.characterRealm);

        this.characterRepository.save(character);
        return character;
    }

    /**
     * Test to get character from account
     */
    @Test
    public void testGetCharacterAccountOk() {
        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        final int numberCharacters = this.factory.getRandomInteger(10,50);
        final List<Character> charactersList = new ArrayList<>();

        for(int index = 0; index < numberCharacters; index++){
            final Character character = this.generateCharacter(userAccount);
            charactersList.add(character);
        }

        final BlizzardAccountParameter blizzardAccountParameter = this.factory.getBlizzardAccountParameter(userAccount);

        final List<CharacterSummaryDTO> result = this.characterService.getCharacterAccount(blizzardAccountParameter);

        for(final CharacterSummaryDTO characterSummaryDTO : result) {
            boolean isPresent = false;

            for(final Character character : charactersList){

                if(character.getId().equals(characterSummaryDTO.getId())){
                    isPresent = true;
                    break;
                }
            }

            Assertions.assertTrue(isPresent);

            final Character character = this.characterRepository.findById(characterSummaryDTO.getId()).get();

            Assertions.assertEquals(character.getName(), characterSummaryDTO.getName());
            Assertions.assertEquals(character.getPlayableClass().getId(), characterSummaryDTO.getIndexPlayableClass());
            Assertions.assertEquals(character.getPlayableRace().getId(), characterSummaryDTO.getIndexPlayableRace());
            Assertions.assertEquals(character.getFaction().getId(), characterSummaryDTO.getIndexFaction());
            Assertions.assertEquals(this.characterRealm.getSlug(), characterSummaryDTO.getRealmDTO().getSlug());

        }

    }
}
