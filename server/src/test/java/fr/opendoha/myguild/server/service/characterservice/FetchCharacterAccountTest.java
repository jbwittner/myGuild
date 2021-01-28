package fr.opendoha.myguild.server.service.characterservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import fr.opendoha.myguild.server.data.blizzardgamedata.AccountProfileSummaryBlizzardData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CharacterData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CharacterSummaryData;
import fr.opendoha.myguild.server.data.blizzardgamedata.ChosenCovenantData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CovenantProgressData;
import fr.opendoha.myguild.server.data.blizzardgamedata.FactionData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GuildIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.HrefData;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.RealmData;
import fr.opendoha.myguild.server.data.blizzardgamedata.WowAccountData;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Covenant;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CharacterRepository;
import fr.opendoha.myguild.server.repository.blizzard.CovenantRepository;
import fr.opendoha.myguild.server.repository.blizzard.FactionRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableRaceRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.RealmRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;
import fr.opendoha.myguild.server.service.implementation.CharacterServiceImpl;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;

/**
 * Test class to test fetchCharacterAccount method
 */
public class FetchCharacterAccountTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected GuildRepository guildRepository;

    @Autowired
    protected CharacterRepository characterRepository;

    @Autowired
    protected RealmRepository realmRepository;

    @Autowired
    protected FactionRepository factionRepository;

    @Autowired
    protected PlayableRaceRepository playableRaceRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected SpecializationRoleRepository specializationRoleRepository;

    @Autowired
    protected CovenantRepository covenantRepository;

    @Autowired
    protected PlayableSpecializationRepository playableSpecializationRepository;

    @MockBean
    protected BlizzardAPIHelper blizzardAPIHelper;

    protected CharacterServiceImpl mockCharacterService;

    protected List<RealmData> realmDataList;
    protected List<GuildIndexData> guildIndexDataList;

    private static Integer NUMBER_CHARACTERS = 100;
    private static Integer NUMBER_GUILD = 2;
    private static Integer ITEM_LEVEV_MAX = 300;
    private static Integer RENOWN_LEVEV_MAX = 50;
    private static Integer LVL_MAX = 60;
    private static Integer NUMBER_REALM = 2;

    @Override
    protected void initDataBeforeEach() {
        this.mockCharacterService = new CharacterServiceImpl(userAccountRepository, characterRepository,
                guildRepository, realmRepository, factionRepository, playableRaceRepository, playableClassRepository,
                covenantRepository, blizzardAPIHelper);

    }

    /**
     * Test to fetch characters without wow account
     */
    @Test
    public void testWithoutWowAccount() throws IOException {
        final UserAccount userAccount = this.factory.getUserAccount();
        this.userAccountRepository.save(userAccount);
        final BlizzardAccountParameter blizzardAccountParameter = this.factory.getBlizzardAccountParameter(userAccount);

        final List<WowAccountData> wowAccountDatas = new ArrayList<>();

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData = new AccountProfileSummaryBlizzardData();
        accountProfileSummaryBlizzardData.setWowAccountsData(wowAccountDatas);

        Mockito.when(this.blizzardAPIHelper.getAccountProfileSummaryBlizzardData(blizzardAccountParameter))
            .thenReturn(accountProfileSummaryBlizzardData);

        final List<CharacterSummaryDTO> characterDTOs = this.mockCharacterService.fetchCharacterAccount(blizzardAccountParameter);

        Assertions.assertEquals(0, characterDTOs.size());
        Assertions.assertEquals(0, this.characterRepository.findAll().size());
        Assertions.assertEquals(0, this.guildRepository.findAll().size());
        Assertions.assertEquals(0, this.realmRepository.findAll().size());
    }

    /**
     * Test to fetch characters without characters
     */
    @Test
    public void testWithoutCharacters() throws IOException {
        final UserAccount userAccount = this.factory.getUserAccount();
        this.userAccountRepository.save(userAccount);
        final BlizzardAccountParameter blizzardAccountParameter = this.factory.getBlizzardAccountParameter(userAccount);

        final List<WowAccountData> wowAccountDatas = new ArrayList<>();

        final WowAccountData wowAccountData = new WowAccountData();

        final List<CharacterSummaryData> characterSummaryData = new ArrayList<>();

        wowAccountData.setCharacterSummaryData(characterSummaryData);

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData = new AccountProfileSummaryBlizzardData();
        accountProfileSummaryBlizzardData.setWowAccountsData(wowAccountDatas);

        Mockito.when(this.blizzardAPIHelper.getAccountProfileSummaryBlizzardData(blizzardAccountParameter))
            .thenReturn(accountProfileSummaryBlizzardData);

        final List<CharacterSummaryDTO> characterDTOs = this.mockCharacterService.fetchCharacterAccount(blizzardAccountParameter);

        Assertions.assertEquals(0, characterDTOs.size());
        Assertions.assertEquals(0, this.characterRepository.findAll().size());
        Assertions.assertEquals(0, this.guildRepository.findAll().size());
        Assertions.assertEquals(0, this.realmRepository.findAll().size());
    }

    /**
     * Test to retrieve characters once
     */
    @Test
    public void testWithCharactersOneFetch() throws IOException {

        final BlizzardAccountParameter blizzardAccountParameter = this.prepareCharacters();
        final List<CharacterSummaryDTO> characterDTOs = this.mockCharacterService.fetchCharacterAccount(blizzardAccountParameter);

        Assertions.assertEquals(NUMBER_CHARACTERS, characterDTOs.size());
        Assertions.assertEquals(NUMBER_CHARACTERS, this.characterRepository.findAll().size());
        Assertions.assertEquals(NUMBER_GUILD, this.guildRepository.findAll().size());
        Assertions.assertEquals(NUMBER_REALM, this.realmRepository.findAll().size());
    }

    /**
     * Test to retrieve characters twice
     */
    @Test
    public void testWithCharactersTwoFetch() throws IOException {

        final BlizzardAccountParameter blizzardAccountParameter = this.prepareCharacters();
        List<CharacterSummaryDTO> characterDTOs = this.mockCharacterService.fetchCharacterAccount(blizzardAccountParameter);
        characterDTOs = this.mockCharacterService.fetchCharacterAccount(blizzardAccountParameter);

        Assertions.assertEquals(NUMBER_CHARACTERS, characterDTOs.size());
        Assertions.assertEquals(NUMBER_CHARACTERS, this.characterRepository.findAll().size());
        Assertions.assertEquals(NUMBER_GUILD, this.guildRepository.findAll().size());
        Assertions.assertEquals(NUMBER_REALM, this.realmRepository.findAll().size());
    }

    private BlizzardAccountParameter prepareCharacters() throws IOException {

        this.prepareStaticData();

        this.realmDataList = new ArrayList<>();
        this.guildIndexDataList = new ArrayList<>();

        for(Integer index = 0 ; index < NUMBER_REALM; index++){
            final RealmData realmData = new RealmData();
            realmData.setId(this.factory.getUniqueRandomInteger());
            realmData.setSlug(this.factory.getUniqueRandomAlphanumericString());
            realmData.setLocalizedStringData(this.factory.generateLocalizedStringData());
            this.realmDataList.add(realmData);
        }

        for(Integer index = 0 ; index < NUMBER_GUILD; index++){
            final GuildIndexData guildIndexData = new GuildIndexData();
            guildIndexData.setId(this.factory.getUniqueRandomInteger());
            guildIndexData.setName(this.factory.getUniqueRandomAlphanumericString());

            final FactionData factionData = new FactionData();
            final Integer randomFactionIdex = this.factory.getRandomInteger(NUMBER_FACTION);
            final List<Faction> factions = this.factionRepository.findAll();
            final Faction faction = factions.get(randomFactionIdex);

            factionData.setType(faction.getType());

            guildIndexData.setFactionData(factionData);

            final Integer randomRealmIdex = this.factory.getRandomInteger(NUMBER_REALM);
            final RealmData realmData = this.realmDataList.get(randomRealmIdex);
            guildIndexData.setRealmData(realmData);
            
            this.guildIndexDataList.add(guildIndexData);
        }

        final UserAccount userAccount = this.factory.getUserAccount();
        this.userAccountRepository.save(userAccount);
        final BlizzardAccountParameter blizzardAccountParameter = this.factory.getBlizzardAccountParameter(userAccount);

        final List<WowAccountData> wowAccountDatas = new ArrayList<>();

        final WowAccountData wowAccountData = new WowAccountData();

        final List<CharacterSummaryData> characterSummaryDatas = new ArrayList<>();

        CharacterSummaryData characterSummaryData;
        for(Integer index = 0; index < NUMBER_CHARACTERS; index++){
            characterSummaryData = this.getCharacterSummaryData();
            characterSummaryDatas.add(characterSummaryData);
            final CharacterData characterData = this.getCharacterData(characterSummaryData);
            Mockito.when(this.blizzardAPIHelper.getCharacterData(characterSummaryData))
                .thenReturn(characterData);
        }

        characterSummaryData = this.getCharacterSummaryData();
        characterSummaryDatas.add(characterSummaryData);
        Mockito.when(this.blizzardAPIHelper.getCharacterData(characterSummaryData))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "HttpStatus.NOT_FOUND"));

        wowAccountData.setCharacterSummaryData(characterSummaryDatas);
        wowAccountDatas.add(wowAccountData);

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData = new AccountProfileSummaryBlizzardData();
        accountProfileSummaryBlizzardData.setWowAccountsData(wowAccountDatas);

        Mockito.when(this.blizzardAPIHelper.getAccountProfileSummaryBlizzardData(blizzardAccountParameter))
            .thenReturn(accountProfileSummaryBlizzardData);

        
        return blizzardAccountParameter;
    }

    private CharacterSummaryData getCharacterSummaryData(){
        final Integer id = this.factory.getUniqueRandomInteger();
        final String hrefData = this.factory.getUniqueRandomURI();
        final CharacterSummaryData characterSummaryData = new CharacterSummaryData();
        characterSummaryData.setId(id);
        final HrefData characterHrefData = new HrefData();
        characterHrefData.setHref(hrefData);
        characterSummaryData.setCharacterHrefData(characterHrefData);
        return characterSummaryData;
    }

    private CharacterData getCharacterData(final CharacterSummaryData characterSummaryData){
        final CharacterData characterData = new CharacterData();

        characterData.setId(characterSummaryData.getId());

        characterData.setAverageItemLevel(this.factory.getRandomInteger(ITEM_LEVEV_MAX));

        final Integer randomIndexPlayableClass = this.factory.getRandomInteger(NUMBER_PLAYABLE_CLASS);
        final List<PlayableClass> playableClassList = this.playableClassRepository.findAll();
        final PlayableClass playableClass = playableClassList.get(randomIndexPlayableClass);
        final IndexData indexData = new IndexData();
        indexData.setId(playableClass.getId());
        characterData.setClassIndexData(indexData);

        final Boolean haveCovenant = this.factory.getRandomBoolean();

        if(haveCovenant){

            final CovenantProgressData covenantProgressData = new CovenantProgressData();
            final Integer randomRenownLevel = this.factory.getRandomInteger(RENOWN_LEVEV_MAX);
            covenantProgressData.setRenownLevel(randomRenownLevel);
    
            final ChosenCovenantData chosenCovenantData = new ChosenCovenantData();
            final Integer randomCovenantIdex = this.factory.getRandomInteger(NUMBER_COVENANT);
            final List<Covenant> covenantList = this.covenantRepository.findAll();
            final Covenant covenant = covenantList.get(randomCovenantIdex);
    
            chosenCovenantData.setId(covenant.getId());
            covenantProgressData.setChosenCovenantData(chosenCovenantData);
            characterData.setCovenantProgressData(covenantProgressData);
    
        }

        characterData.setEquippedItemLevel(this.factory.getRandomInteger(ITEM_LEVEV_MAX));

        final Date currentDate = new Date();
        
        characterData.setLastLoginTimestamp(currentDate.getTime());
        characterData.setLevel(this.factory.getRandomInteger(LVL_MAX));

        final HrefData mediaHrefData = new HrefData();
        mediaHrefData.setHref(this.factory.getUniqueRandomURI());
        characterData.setMediaHrefData(mediaHrefData);

        characterData.setName(this.factory.getUniqueRandomAlphanumericString());
        
        final FactionData factionData;
        final Faction faction;

        final Boolean isInGuild = this.factory.getRandomBoolean();

        if(isInGuild){

            final Integer randomGuildId = this.factory.getRandomInteger(NUMBER_GUILD);
            final GuildIndexData guildIndexData = this.guildIndexDataList.get(randomGuildId);
            factionData = guildIndexData.getFactionData();
            characterData.setRealmData(guildIndexData.getRealmData());
            faction = this.factionRepository.findByType(factionData.getType()).get();

            characterData.setGuildIndexData(guildIndexData);

        } else {

            final Integer randomFactionIdex = this.factory.getRandomInteger(NUMBER_FACTION);
            final List<Faction> factions = this.factionRepository.findAll();
            faction = factions.get(randomFactionIdex);

            factionData = new FactionData();
            factionData.setType(faction.getType());

            final Integer randomRealmIdex = this.factory.getRandomInteger(NUMBER_REALM);
            final RealmData realmData = this.realmDataList.get(randomRealmIdex);
            characterData.setRealmData(realmData);

        }

        characterData.setFactionData(factionData);

        final List<PlayableRace> playableRaces = this.playableRaceRepository.findByFaction(faction);
        final Integer randomRaceId = this.factory.getRandomInteger(playableRaces.size());
        final PlayableRace playableRace = playableRaces.get(randomRaceId);
        final IndexData raceIndexData = new IndexData();
        raceIndexData.setId(playableRace.getId());
        characterData.setRaceIndexData(raceIndexData);

        return characterData;
    }


}
