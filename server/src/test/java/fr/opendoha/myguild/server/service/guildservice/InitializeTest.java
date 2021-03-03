package fr.opendoha.myguild.server.service.guildservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpServerErrorException;

import fr.opendoha.myguild.server.data.blizzardgamedata.CharacterData;
import fr.opendoha.myguild.server.data.blizzardgamedata.ChosenCovenantData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CovenantProgressData;
import fr.opendoha.myguild.server.data.blizzardgamedata.FactionData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildMemberData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildMemberIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildRosterIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.RealmData;
import fr.opendoha.myguild.server.exception.UnexpectedException;
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
import fr.opendoha.myguild.server.service.implementation.GuildServiceImpl;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;

/**
 * Test of initialize
 */
public class InitializeTest extends AbstractMotherIntegrationTest {

    @Value("${application.blizzard.guild.name}")
    protected String guildName;

    @Value("${application.blizzard.guild.realmslug}")
    protected String realmSlug;

    protected GuildServiceImpl mockGuildServiceImpl;

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected GuildRepository guildRepository;

    @Autowired
    protected CharacterRepository characterRepository;

    @Autowired
    protected GuildRankRepository guildRankRepository;

    @Autowired
    protected FactionRepository factionRepository;

    @Autowired
    protected PlayableRaceRepository playableRaceRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected CovenantRepository covenantRepository;

    @Autowired
    protected RealmRepository realmRepository;

    @MockBean
    protected BlizzardAPIHelper blizzardAPIHelper;

    protected static Integer NUMBER_CHARACTERS = 10;
    protected static Integer INDEX_MAX_RANK = 5;

    protected List<GuildMemberIndexData> guildMemberIndexDataList;
    protected List<CharacterData> charactersData;

    @Override
    protected void initDataBeforeEach() throws Exception {
        this.mockGuildServiceImpl = new GuildServiceImpl(this.userAccountRepository, this.characterRepository,
                this.guildRepository, this.guildRankRepository, this.factionRepository, this.playableRaceRepository,
                this.playableClassRepository, this.covenantRepository, this.realmRepository, this.blizzardAPIHelper);

        ReflectionTestUtils.setField(this.mockGuildServiceImpl, "guildName", this.guildName);
        ReflectionTestUtils.setField(this.mockGuildServiceImpl, "realmSlug", this.realmSlug);
    }

    private void checkData() {

        final List<Character> characters = this.characterRepository.findAll();

        Assertions.assertEquals(NUMBER_CHARACTERS + 1, characters.size());

        for(final Character character : characters){

            Boolean isExist = false;

            for(final GuildMemberIndexData expectedGuildMemeberIndexData : this.guildMemberIndexDataList){
                if(expectedGuildMemeberIndexData.getGuildMemberData().getId().equals(character.getId())){
                    Assertions.assertEquals(expectedGuildMemeberIndexData.getRank(), character.getGuildRank().getRank());
                    isExist = true;
                }
            }
            Assertions.assertTrue(isExist);

            isExist = false;

            for(final CharacterData expectedCharacterData : this.charactersData){
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

    private void generateData() throws IOException {
        
        this.prepareStaticData();

        final GuildData guildData = new GuildData();

        final Guild guild = this.factory.getGuild();

        guildData.setId(guild.getId());
        guildData.setAchievementPoints(this.factory.getRandomInteger());
        guildData.setCreatedTimestamp(Long.valueOf(this.factory.getRandomInteger()));

        final FactionData factionData = new FactionData();
        final Faction faction = this.getRandomFaction();
        factionData.setType(faction.getType());
        guildData.setFactionData(factionData);

        guildData.setMemberCount(this.factory.getRandomInteger());

        guildData.setName(this.guildName);

        final RealmData realmData = new RealmData();
        realmData.setId(this.factory.getRandomInteger());
        realmData.setSlug(this.realmSlug);
        realmData.setLocalizedStringData(this.factory.generateLocalizedStringData());
        guildData.setRealmData(realmData);

        Mockito.when(this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName))
            .thenReturn(guildData);

        final Character characterGuild = this.factory.getCharacter();        
        GuildMemberData guildMemberData = new GuildMemberData();
        guildMemberData.setId(characterGuild.getId());

        GuildMemberIndexData guildMemberIndexData = new GuildMemberIndexData();
        guildMemberIndexData.setRank(GuildServiceImpl.RANK_GUILD_MASTER);
        guildMemberIndexData.setGuildMemberData(guildMemberData);

        CharacterData characterData = new CharacterData();
        characterData.setId(characterGuild.getId());

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

        this.charactersData = new ArrayList<>();
        this.charactersData.add(characterData);

        Mockito.when(this.blizzardAPIHelper.getCharacterData(guildMemberIndexData))
            .thenReturn(characterData);

        this.guildMemberIndexDataList = new ArrayList<>();
        this.guildMemberIndexDataList.add(guildMemberIndexData);

        for(Integer index = 0; index < NUMBER_CHARACTERS; index++){
            guildMemberData = new GuildMemberData();
            guildMemberData.setId(this.factory.getUniqueRandomInteger());

            guildMemberIndexData = new GuildMemberIndexData();
            guildMemberIndexData.setRank(this.factory.getRandomInteger(INDEX_MAX_RANK));
            guildMemberIndexData.setGuildMemberData(guildMemberData);
            this.guildMemberIndexDataList.add(guildMemberIndexData);

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

            this.charactersData.add(characterData);

            Mockito.when(this.blizzardAPIHelper.getCharacterData(guildMemberIndexData))
            .thenReturn(characterData);

        }

        final GuildRosterIndexData guildRosterIndexData = new GuildRosterIndexData();
        guildRosterIndexData.setGuildMemberIndexDataList(this.guildMemberIndexDataList);

        Mockito.when(this.blizzardAPIHelper.getGuildRosterIndexData(realmSlug, guildName))
            .thenReturn(guildRosterIndexData);

    }

    /**
     * Test with guild already exist
     */
    @Test
    public void testGuildAlreadyExistOk() throws IOException, UnexpectedException {

        final GuildData guildData = new GuildData();

        final Guild guild = this.factory.getGuild();
        this.guildRepository.save(guild);

        guildData.setId(guild.getId());

        Mockito.when(this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName))
            .thenReturn(guildData);

        this.mockGuildServiceImpl.initialize();
    }

    /**
     * Test with first call
     */
    @Test
    public void testFirstTimeOk() throws IOException, UnexpectedException {

        this.generateData();

        this.mockGuildServiceImpl.initialize();

        this.checkData();

    }

    /**
     * Test with first call and existed realm
     */
    @Test
    public void testFirstTimeWithExistedRealmOk() throws IOException, UnexpectedException {

        final Realm realm = new Realm();
        realm.setId(this.factory.getRandomInteger());
        realm.setSlug(this.realmSlug);
        this.realmRepository.save(realm);

        this.generateData();

        this.mockGuildServiceImpl.initialize();

        this.checkData();

    }

    /**
     * Test with fail of update
     */
    @Test
    public void testFailFetchCharacters() throws IOException, UnexpectedException {

        this.prepareStaticData();

        final GuildData guildData = new GuildData();

        final Guild guild = this.factory.getGuild();

        guildData.setId(guild.getId());
        guildData.setAchievementPoints(this.factory.getRandomInteger());
        guildData.setCreatedTimestamp(Long.valueOf(this.factory.getRandomInteger()));

        final FactionData factionData = new FactionData();
        final Faction faction = this.getRandomFaction();
        factionData.setType(faction.getType());
        guildData.setFactionData(factionData);

        guildData.setMemberCount(this.factory.getRandomInteger());

        guildData.setName(this.guildName);

        final RealmData realmData = new RealmData();
        realmData.setId(this.factory.getRandomInteger());
        realmData.setSlug(this.realmSlug);
        realmData.setLocalizedStringData(this.factory.generateLocalizedStringData());
        guildData.setRealmData(realmData);

        Mockito.when(this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName))
            .thenReturn(guildData);

        final IndexData classIndexData  = new IndexData();
        PlayableClass playableClass = this.getRandomPlayableClass();
        classIndexData.setId(playableClass.getId());

        this.charactersData = new ArrayList<>();
        this.guildMemberIndexDataList = new ArrayList<>();

        final GuildMemberData guildMemberData = new GuildMemberData();
        guildMemberData.setId(this.factory.getUniqueRandomInteger());

        final GuildMemberIndexData guildMemberIndexData = new GuildMemberIndexData();
        guildMemberIndexData.setRank(this.factory.getRandomInteger(INDEX_MAX_RANK));
        guildMemberIndexData.setGuildMemberData(guildMemberData);
        this.guildMemberIndexDataList.add(guildMemberIndexData);

        playableClass = this.getRandomPlayableClass();
        classIndexData.setId(playableClass.getId());

        final CharacterData characterData = new CharacterData();
        characterData.setId(guildMemberData.getId());
        characterData.setRealmData(realmData);

        this.charactersData.add(characterData);

        Mockito.when(this.blizzardAPIHelper.getCharacterData(guildMemberIndexData))
            .thenThrow(new HttpServerErrorException(HttpStatus.NOT_FOUND, "HttpStatus.NOT_FOUND"));

        final GuildRosterIndexData guildRosterIndexData = new GuildRosterIndexData();
        guildRosterIndexData.setGuildMemberIndexDataList(this.guildMemberIndexDataList);

        Mockito.when(this.blizzardAPIHelper.getGuildRosterIndexData(realmSlug, guildName))
            .thenReturn(guildRosterIndexData);

        Assertions.assertThrows(UnexpectedException.class,
            () -> this.mockGuildServiceImpl.initialize());

    }

}
