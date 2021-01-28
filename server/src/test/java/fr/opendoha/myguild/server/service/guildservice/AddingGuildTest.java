package fr.opendoha.myguild.server.service.guildservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpServerErrorException;

import fr.opendoha.myguild.server.data.blizzardgamedata.CharacterData;
import fr.opendoha.myguild.server.data.blizzardgamedata.ChosenCovenantData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CovenantProgressData;
import fr.opendoha.myguild.server.data.blizzardgamedata.FactionData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildMemberData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildMemberIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildRosterIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.RealmData;
import fr.opendoha.myguild.server.exception.AreNotGuildMasterException;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.exception.UnexpectedException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.parameters.AddingGuildParameter;
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
 * Test class to test addingGuild method
 */
public class AddingGuildTest extends AbstractMotherIntegrationTest {

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

    protected static Integer NUMBER_CHARACTERS = 10;
    protected static Integer INDEX_MAX_RANK = 5;

    @Override
    protected void initDataBeforeEach() throws Exception {
        this.mockGuildService = new GuildServiceImpl(this.userAccountRepository, this.characterRepository,
                this.guildRepository, this.guildRankRepository, this.factionRepository, this.playableRaceRepository,
                this.playableClassRepository, this.covenantRepository, this.blizzardAPIHelper);
    }

    /**
     * Test without existing guild
     */
    @Test
    public void guildNotExistedFail() {
        final AddingGuildParameter addingGuildParameter = new AddingGuildParameter();
        addingGuildParameter.setGuildId(this.factory.getUniqueRandomInteger());

        Assertions.assertThrows(GuildNotExistedException.class,
                () -> this.guildService.addingGuild(addingGuildParameter));
    }

    /**
     * Test to add a guild where the user is not the guild master
     */
    @Test
    public void areNotGuildMasterFail() throws IOException {
        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        Guild guild = this.factory.getGuild();
        guild = this.guildRepository.save(guild);

        Character characterGuild = this.factory.getCharacter(userAccount, guild);
        characterGuild = this.characterRepository.save(characterGuild);

        final GuildMemberData guildMemberData = new GuildMemberData();
        guildMemberData.setId(this.factory.getUniqueRandomInteger());

        final GuildMemberIndexData indexGuildMaster = new GuildMemberIndexData();
        indexGuildMaster.setRank(GuildServiceImpl.RANK_GUILD_MASTER);
        indexGuildMaster.setGuildMemberData(guildMemberData);

        final List<GuildMemberIndexData> guildMemberIndexDataList = new ArrayList<>();
        guildMemberIndexDataList.add(indexGuildMaster);

        final GuildRosterIndexData guildRosterIndexData = new GuildRosterIndexData();
        guildRosterIndexData.setGuildMemberIndexDataList(guildMemberIndexDataList);

        Mockito.when(this.blizzardAPIHelper.getGuildRosterIndexData(guild)).thenReturn(guildRosterIndexData);

        final AddingGuildParameter addingGuildParameter = new AddingGuildParameter();
        addingGuildParameter.setGuildId(guild.getId());
        addingGuildParameter.setBlizzardId(userAccount.getBlizzardId());

        Assertions.assertThrows(AreNotGuildMasterException.class,
                () -> this.guildService.addingGuild(addingGuildParameter));
    }

    /**
     * Test to add a guild where whe can't to get a character
     */
    @Test
    public void unexpectedExceptionFail() throws IOException, GuildNotExistedException, AreNotGuildMasterException,
            UnexpectedException {
        this.prepareStaticData();

        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        Realm realm = this.factory.getRealm();
        realm = this.realmRepository.save(realm);

        final Faction faction = this.getRandomFaction();

        Guild guild = this.factory.getGuild(faction, realm);
        guild = this.guildRepository.save(guild);

        Character characterGuild = this.factory.getCharacter(userAccount, guild);
        characterGuild = this.characterRepository.save(characterGuild);
        
        final GuildMemberData guildMemberData = new GuildMemberData();
        guildMemberData.setId(characterGuild.getId());

        final GuildMemberIndexData guildMemberIndexData = new GuildMemberIndexData();
        guildMemberIndexData.setRank(GuildServiceImpl.RANK_GUILD_MASTER);
        guildMemberIndexData.setGuildMemberData(guildMemberData);

        Mockito.when(this.blizzardAPIHelper.getCharacterData(guildMemberIndexData))
            .thenThrow(HttpServerErrorException.class);

        final List<GuildMemberIndexData> guildMemberIndexDataList = new ArrayList<>();
        guildMemberIndexDataList.add(guildMemberIndexData);

        final GuildRosterIndexData guildRosterIndexData = new GuildRosterIndexData();
        guildRosterIndexData.setGuildMemberIndexDataList(guildMemberIndexDataList);

        Mockito.when(this.blizzardAPIHelper.getGuildRosterIndexData(guild))
            .thenReturn(guildRosterIndexData);

        final AddingGuildParameter addingGuildParameter = new AddingGuildParameter();
        addingGuildParameter.setGuildId(guild.getId());
        addingGuildParameter.setBlizzardId(userAccount.getBlizzardId());

        Assertions.assertThrows(UnexpectedException.class,
                () -> this.guildService.addingGuild(addingGuildParameter));
    }

    /**
     * Test to add a guild
     */
    @Test
    public void testGetGuildOk()
            throws IOException, GuildNotExistedException, AreNotGuildMasterException, UnexpectedException {

        this.prepareStaticData();

        final List<CharacterData> charactersData = new ArrayList<>();

        UserAccount userAccount = this.factory.getUserAccount();
        userAccount = this.userAccountRepository.save(userAccount);

        Realm realm = this.factory.getRealm();
        realm = this.realmRepository.save(realm);

        final Faction faction = this.getRandomFaction();

        Guild guild = this.factory.getGuild(faction, realm);
        guild = this.guildRepository.save(guild);

        Character characterGuild = this.factory.getCharacter(userAccount, guild);
        characterGuild = this.characterRepository.save(characterGuild);
        
        GuildMemberData guildMemberData = new GuildMemberData();
        guildMemberData.setId(characterGuild.getId());

        GuildMemberIndexData guildMemberIndexData = new GuildMemberIndexData();
        guildMemberIndexData.setRank(GuildServiceImpl.RANK_GUILD_MASTER);
        guildMemberIndexData.setGuildMemberData(guildMemberData);

        CharacterData characterData = new CharacterData();
        characterData.setId(characterGuild.getId());
        final RealmData realmData = new RealmData();
        realmData.setSlug(realm.getSlug());

        final FactionData factionData = new FactionData();
        factionData.setType(faction.getType());

        IndexData raceIndexData = new IndexData();
        PlayableRace playableRace = this.getRandomPlayableRace(faction);
        raceIndexData.setId(playableRace.getId());

        final IndexData classIndexData  = new IndexData();
        PlayableClass playableClass = this.getRandomPlayableClass();
        classIndexData.setId(playableClass.getId());

        characterData.setRealmData(realmData);
        characterData.setName(this.factory.getRandomAlphanumericString());
        characterData.setFactionData(factionData);
        characterData.setRaceIndexData(raceIndexData);
        characterData.setClassIndexData(classIndexData);

        charactersData.add(characterData);

        Mockito.when(this.blizzardAPIHelper.getCharacterData(guildMemberIndexData))
            .thenReturn(characterData);

        final List<GuildMemberIndexData> guildMemberIndexDataList = new ArrayList<>();
        guildMemberIndexDataList.add(guildMemberIndexData);

        for(Integer index = 0; index < NUMBER_CHARACTERS; index++){
            guildMemberData = new GuildMemberData();
            guildMemberData.setId(this.factory.getUniqueRandomInteger());

            guildMemberIndexData = new GuildMemberIndexData();
            guildMemberIndexData.setRank(this.factory.getRandomInteger(INDEX_MAX_RANK));
            guildMemberIndexData.setGuildMemberData(guildMemberData);
            guildMemberIndexDataList.add(guildMemberIndexData);

            raceIndexData = new IndexData();
            playableRace = this.getRandomPlayableRace(faction);
            raceIndexData.setId(playableRace.getId());

            playableClass = this.getRandomPlayableClass();
            classIndexData.setId(playableClass.getId());

            characterData = new CharacterData();
            characterData.setId(guildMemberData.getId());
            characterData.setRealmData(realmData);
            characterData.setFactionData(factionData);
            characterData.setRaceIndexData(raceIndexData);
            characterData.setClassIndexData(classIndexData);

            final CovenantProgressData covenantProgressData = new CovenantProgressData();
            final ChosenCovenantData chosenCovenantData = new ChosenCovenantData();
            chosenCovenantData.setId(this.getRandomCovenant().getId());
            covenantProgressData.setChosenCovenantData(chosenCovenantData);
            covenantProgressData.setRenownLevel(this.factory.getRandomInteger());

            characterData.setCovenantProgressData(covenantProgressData);

            charactersData.add(characterData);

            Mockito.when(this.blizzardAPIHelper.getCharacterData(guildMemberIndexData))
            .thenReturn(characterData);

        }

        final GuildRosterIndexData guildRosterIndexData = new GuildRosterIndexData();
        guildRosterIndexData.setGuildMemberIndexDataList(guildMemberIndexDataList);

        Mockito.when(this.blizzardAPIHelper.getGuildRosterIndexData(guild))
            .thenReturn(guildRosterIndexData);

        final AddingGuildParameter addingGuildParameter = new AddingGuildParameter();
        addingGuildParameter.setGuildId(guild.getId());
        addingGuildParameter.setBlizzardId(userAccount.getBlizzardId());

        this.guildService.addingGuild(addingGuildParameter);

        final List<Character> characters = this.characterRepository.findAll();

        Assertions.assertEquals(NUMBER_CHARACTERS + 1, characters.size());

        for(final Character character : characters){

            Boolean isExist = false;

            for(final GuildMemberIndexData expectedGuildMemeberIndexData : guildMemberIndexDataList){
                if(expectedGuildMemeberIndexData.getGuildMemberData().getId().equals(character.getId())){
                    Assertions.assertEquals(expectedGuildMemeberIndexData.getRank(), character.getGuildRank().getRank());
                    isExist = true;
                }
            }
            Assertions.assertTrue(isExist);

            isExist = false;

            for(final CharacterData expectedCharacterData : charactersData){
                if(expectedCharacterData.getId().equals(character.getId())){
                    Assertions.assertEquals(expectedCharacterData.getAverageItemLevel(), character.getAverageItemLevel());
                    Assertions.assertEquals(expectedCharacterData.getEquippedItemLevel(), character.getEquippedItemLevel());
                    Assertions.assertEquals(expectedCharacterData.getLastLoginTimestamp(), character.getLastLoginTimestamp());
                    Assertions.assertEquals(expectedCharacterData.getLevel(), character.getLevel());
                    Assertions.assertEquals(expectedCharacterData.getClassIndexData().getId(), character.getPlayableClass().getId());
                    Assertions.assertEquals(expectedCharacterData.getRaceIndexData().getId(), character.getPlayableRace().getId());

                    if(expectedCharacterData.getCovenantProgressData() != null){
                        Assertions.assertEquals(expectedCharacterData.getCovenantProgressData().getRenownLevel(), character.getRenownLevel());
                        Assertions.assertEquals(expectedCharacterData.getCovenantProgressData().getChosenCovenantData().getId(), character.getCovenant().getId());
                    }

                    Assertions.assertEquals(expectedCharacterData.getFactionData().getType(), character.getFaction().getType());
                    Assertions.assertEquals(expectedCharacterData.getName(), character.getName());
                    Assertions.assertEquals(expectedCharacterData.getRealmData().getSlug(), character.getRealm().getSlug());
                    isExist = true;
                }
            }
            Assertions.assertTrue(isExist);


        }

    }

}
