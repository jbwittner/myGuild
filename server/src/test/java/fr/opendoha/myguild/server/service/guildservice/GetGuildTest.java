package fr.opendoha.myguild.server.service.guildservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.opendoha.myguild.server.data.blizzardgamedata.GuildData;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildDTO;
import fr.opendoha.myguild.server.exception.GuildDoesNotUseApplication;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CharacterRepository;
import fr.opendoha.myguild.server.repository.blizzard.CovenantRepository;
import fr.opendoha.myguild.server.repository.blizzard.FactionRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRankRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableRaceRepository;
import fr.opendoha.myguild.server.repository.blizzard.RealmRepository;
import fr.opendoha.myguild.server.service.GuildService;
import fr.opendoha.myguild.server.service.implementation.GuildServiceImpl;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;

/**
 * Test class to test getGuild method
 */
public class GetGuildTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected GuildRepository guildRepository;

    @Autowired
    protected GuildService guildService;

    @Autowired
    protected CharacterRepository characterRepository;

    @Autowired
    protected FactionRepository factionRepository;

    @Autowired
    protected RealmRepository realmRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected PlayableRaceRepository playableRaceRepository;

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected GuildRankRepository guildRankRepository;

    @Autowired
    protected CovenantRepository covenantRepository;

    @MockBean
    protected BlizzardAPIHelper blizzardAPIHelper;

    protected GuildServiceImpl mockGuildService;

    protected static Integer NUMBER_CHARACTER = 5;

    @Override
    protected void initDataBeforeEach() throws Exception {
        this.mockGuildService = new GuildServiceImpl(this.userAccountRepository, this.characterRepository, this.guildRepository,
            this.guildRankRepository, this.factionRepository, this.playableRaceRepository, this.playableClassRepository,
            this.covenantRepository, this.blizzardAPIHelper);
    }

    /**
     * Test with with a guild who doen't used the application
     */
    @Test
    public void guildNotUseApplicationFail() {
        final Guild guild = this.factory.getGuild();
        this.guildRepository.save(guild);

        Assertions.assertThrows(GuildDoesNotUseApplication.class,
            () -> this.guildService.getGuild(guild.getId()));
    }

    /**
     * Test with without guild in the repository
     */
    @Test
    public void guildNotExistedFail() {
        final Guild guild = this.factory.getGuild();

        Assertions.assertThrows(GuildNotExistedException.class,
            () -> this.guildService.getGuild(guild.getId()));
    }

    /**
     * Test with with a guild who used the application
     */
    @Test
    public void testGetGuildOk() throws GuildNotExistedException, GuildDoesNotUseApplication, IOException {
        this.prepareStaticData();

        final Guild guild = this.factory.getGuild();

        final List<Faction> factions = this.factionRepository.findAll();
        final List<PlayableClass> playableClasses = this.playableClassRepository.findAll();
        final List<PlayableRace> playableRaces = this.playableRaceRepository.findAll();

        final Faction faction = factions.get(this.factory.getRandomInteger(NUMBER_FACTION));
        Realm realm = this.factory.getRealm();
        realm = this.realmRepository.save(realm);

        guild.setFaction(faction);
        guild.setRealm(realm);
        guild.setUseApplication(true);

        final List<Character> characterList = new ArrayList<>();

        for(Integer index = 0; index < NUMBER_CHARACTER; index++){
            Character character = this.factory.getCharacter();
            character.setFaction(faction);
            character.setRealm(realm);
            
            final PlayableClass playableClass = playableClasses.get(this.factory.getRandomInteger(NUMBER_PLAYABLE_CLASS));
            character.setPlayableClass(playableClass);

            final PlayableRace playableRace = playableRaces.get(this.factory.getRandomInteger(NUMBER_PLAYABLE_RACE));
            character.setPlayableRace(playableRace);

            character = this.characterRepository.save(character);
            characterList.add(character);
        }

        guild.setCharacterList(characterList);

        this.guildRepository.save(guild);

        final GuildData guildData = new GuildData();
        guildData.setId(guild.getId());
        guildData.setName(guild.getName());
        guildData.setMemberCount(NUMBER_CHARACTER);

        Mockito.when(this.blizzardAPIHelper.getGuildData(guild))
            .thenReturn(guildData);

        final GuildDTO guildDTO = this.guildService.getGuild(guild.getId());

        Assertions.assertEquals(guild.getId(), guildDTO.getGuildSummaryDTO().getId());
        Assertions.assertEquals(guild.getFaction().getId(), guildDTO.getGuildSummaryDTO().getIndexFaction());
        Assertions.assertEquals(guild.getUseApplication(), guildDTO.getGuildSummaryDTO().getUseApplication());
        Assertions.assertEquals(guild.getCharacterList().size(), guildDTO.getGuildSummaryDTO().getMemberCount());
        Assertions.assertEquals(guild.getName(), guildDTO.getGuildSummaryDTO().getName());

        final List<CharacterSummaryDTO> characterSummaryDTOs = guildDTO.getCharacterSummaryDTOs();

        Assertions.assertEquals(NUMBER_CHARACTER,characterSummaryDTOs.size());

        for(final CharacterSummaryDTO characterSummaryDTO : characterSummaryDTOs){
            boolean isPresent = false;
            for(final Character character : characterList){
                if(character.getId().equals(characterSummaryDTO.getId())){
                    isPresent = true;
                }
            }
            
            if(!isPresent){
                Assertions.fail("Character not existed");
            }
        }
        
    }
    
}
