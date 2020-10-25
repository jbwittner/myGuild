package fr.opendoha.myguild.server.service.blizzardservice;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.BlizzardService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

//TODO : Refacto test
/**
 * Test of fetchAccountCharacter
 */
public class FetchAccountCharacterTest extends AbstractMotherIntegrationTest {

    @Value("${application.guild.slug}")
    protected String guildSlug;

    @Value("${application.guild.realm}")
    protected String guildRealm;

    @Value("${application.blizzard.wow.profile.base-uri}")
    protected String baseUriProfile;

    @Value("${application.blizzard.wow.profile.namespace}")
    protected String namespaceProfile;

    @Value("${application.blizzard.wow.game-data.base-uri}")
    protected String baseUriGameData;

    @Value("${application.blizzard.wow.game-data.namespace}")
    protected String namespaceGameData;

    private BlizzardService blizzardService;

    @Mock
    private HttpHelper httpHelper;

    @Mock
    private OAuth2FlowHandler oAuth2FlowHandler;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private GuildRepository guildRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private GuildRankRepository guildRankRepository;

    @Autowired
    private RealmRepository realmRepository;

    @Autowired
    private PlayableRaceRepository playableRaceRepository;

    @Autowired
    private FactionRepository factionRepository;

    @Autowired
    private PlayableClassRepository playableClassRepository;

    @Autowired
    private SpecializationRoleRepository specializationRoleRepository;

    @Autowired
    private PlayableSpecializationRepository playableSpecializationRepository;

    private String token;

    private static final Integer NUMBER_SPECIALIZATION_ROLE = 3;
    private static final Integer NUMBER_REALM = 3;
    private static final Integer NUMBER_CHARACTER_PER_ACCOUNT = 100;
    private static final Integer NUMBER_CLASS = 20;
    private static final Integer NUMBER_RACES = 20;
    private static final Integer NUMBER_FACTION = 3;
    private static final Integer NUMBER_GUILD = 3;
    private static final Integer LEVEL_MAX = 50;
    private static final Integer NUMBER_WOW_ACCOUNT = 2;

    private List<SpecializationRole> specializationRoleList = new ArrayList<>();
    private List<PlayableRace> playableRaceList = new ArrayList<>();
    private List<PlayableClass> playableClassList = new ArrayList<>();
    private List<Faction> factionList = new ArrayList<>();
    private List<RealmData> realmDataList = new ArrayList<>();
    private List<GuildIndexData> guildIndexDataList = new ArrayList<>();
    private List<CharacterData> characterDataList = new ArrayList<>();

    private UserAccount userAccount;

    private BlizzardAccountParameter blizzardAccountParameter;

    @Override
    protected void initDataBeforeEach() throws Exception {

        this.specializationRoleList = new ArrayList<>();
        this.playableRaceList = new ArrayList<>();
        this.playableClassList = new ArrayList<>();
        this.factionList = new ArrayList<>();
        this.realmDataList = new ArrayList<>();
        this.guildIndexDataList = new ArrayList<>();

        this.blizzardService = new BlizzardService(this.oAuth2FlowHandler, this.userAccountRepository,
                this.characterRepository, this.guildRepository, this.guildRankRepository, this.realmRepository,
                this.factionRepository, this.playableRaceRepository, this.playableClassRepository,
                this.playableSpecializationRepository, this.specializationRoleRepository, this.httpHelper);

        this.token = this.factory.getUniqueRandomAlphanumericString();

        Mockito.when(this.oAuth2FlowHandler.getToken()).thenReturn(this.token);

        FieldSetter.setField(this.blizzardService,
                this.blizzardService.getClass().getDeclaredField("guildSlug"), this.guildSlug);

        FieldSetter.setField(this.blizzardService,
                this.blizzardService.getClass().getDeclaredField("guildRealm"), this.guildRealm);

        FieldSetter.setField(this.blizzardService,
                this.blizzardService.getClass().getDeclaredField("baseUriProfile"), this.baseUriProfile);

        FieldSetter.setField(this.blizzardService,
                this.blizzardService.getClass().getDeclaredField("namespaceProfile"), this.namespaceProfile);

        FieldSetter.setField(this.blizzardService,
                this.blizzardService.getClass().getDeclaredField("baseUriGameData"), this.baseUriGameData);

        FieldSetter.setField(this.blizzardService,
                this.blizzardService.getClass().getDeclaredField("namespaceGameData"), this.namespaceGameData);

        this.prepareAccount();

        this.blizzardAccountParameter = new BlizzardAccountParameter();
        this.blizzardAccountParameter.setBlizzardId(this.userAccount.getBlizzardId());
        this.blizzardAccountParameter.setToken(this.token);

        this.prepareRealmData();

        this.createFaction();
        this.createSpecializationRole();
        this.createPlayableRace();
        this.createPlayableClass();

        this.prepareGuildData();

        this.mockFetchAccountCharacter();

    }

    private void prepareAccount(){

        if(this.userAccount == null){
            this.userAccount = new UserAccount();
            userAccount.setEmail(this.factory.getUniqueRandomEmail());
            userAccount.setBlizzardId(this.factory.getUniqueRandomInteger());
            userAccount.setNickName(this.factory.getRandomAlphanumericString());
            userAccount.setBattleTag(this.factory.getRandomAlphanumericString());
            this.userAccount = this.userAccountRepository.save(userAccount);
        }
        
    }

    private LocalizedStringData generateLocalizedStringData() {
        final LocalizedStringData localizedStringData = new LocalizedStringData();

        localizedStringData.setEnUS(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setEsES(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setFrFR(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setItIT(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setDeDE(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setEnGB(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setEsMX(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setKoKR(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setPtBR(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setRuRU(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setZhCN(this.factory.getUniqueRandomAlphanumericString());
        localizedStringData.setZhTW(this.factory.getUniqueRandomAlphanumericString());

        return localizedStringData;
    }

    private void createFaction() {

        for (int index = 0; index < NUMBER_FACTION; index++) {
            Faction faction = new Faction();
            faction.setType(this.factory.getUniqueRandomAlphanumericString());
            faction = this.factionRepository.save(faction);
            this.factionList.add(faction);
        }

    }

    private void createSpecializationRole() {

        for (int index = 0; index < NUMBER_SPECIALIZATION_ROLE; index++) {
            SpecializationRole specializationRole = new SpecializationRole();
            specializationRole.setType(this.factory.getUniqueRandomAlphanumericString());
            specializationRole = this.specializationRoleRepository.save(specializationRole);
            this.specializationRoleList.add(specializationRole);
        }

    }

    private void createPlayableRace() {

        for (int index = 0; index < NUMBER_RACES; index++) {
            PlayableRace playableRace = new PlayableRace();
            playableRace.setId(this.factory.getUniqueRandomInteger());
            final Faction faction = this.factionList.get(this.factory.getRandomInteger(NUMBER_FACTION));
            playableRace.setFaction(faction);
            playableRace = this.playableRaceRepository.save(playableRace);
            playableRaceList.add(playableRace);
        }

    }

    private void createPlayableClass() {

        for (int index = 0; index < NUMBER_CLASS; index++) {
            PlayableClass playableClass = new PlayableClass();
            playableClass.setId(this.factory.getUniqueRandomInteger());
            playableClass = this.playableClassRepository.save(playableClass);
            this.playableClassList.add(playableClass);
        }

    }

    private void mockFetchAccountCharacter() {

        final String url = this.baseUriProfile + "/user/wow?namespace=" +
                this.namespaceProfile + "&access_token=" + this.token;

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData =
                new AccountProfileSummaryBlizzardData();

        final List<WowAccountData> wowAccountDataList = new ArrayList<>();

        for(int indexAccount = 0; indexAccount < NUMBER_WOW_ACCOUNT; indexAccount++){
            final WowAccountData wowAccountData = new WowAccountData();

            final List<CharacterSummaryData> characterSummaryDataList = new ArrayList<>();

            for(int indexCharacter = 0; indexCharacter < NUMBER_CHARACTER_PER_ACCOUNT; indexCharacter++){
                final CharacterSummaryData characterSummaryData = this.createCharacterSummaryData();
                characterSummaryDataList.add(characterSummaryData);
            }

            wowAccountData.setCharacterSummaryData(characterSummaryDataList);
            wowAccountDataList.add(wowAccountData);

        }

        accountProfileSummaryBlizzardData.setWowAccountsData(wowAccountDataList);

        Mockito.when(this.httpHelper.getForObject(url, AccountProfileSummaryBlizzardData.class))
                .thenReturn(accountProfileSummaryBlizzardData);

    }

    private CharacterSummaryData createCharacterSummaryData(){

        final CharacterSummaryData characterSummaryData = new CharacterSummaryData();

        final HrefData hrefData = new HrefData();
        hrefData.setHref(this.factory.getUniqueRandomURI());

        characterSummaryData.setCharacterHrefData(hrefData);

        final String url = characterSummaryData.getCharacterHrefData().getHref() + "&access_token=" + token;

        final CharacterData characterData = this.createCharacterData();

        final String url2 = this.baseUriProfile + "/wow/character/" + characterData.getRealmData().getSlug()
                + "/" + characterData.getName() + "?namespace=" + this.namespaceProfile + "&access_token=" + token;

        if(this.factory.getRandomBoolean()){
            this.characterDataList.add(characterData);
            Mockito.when(this.httpHelper.getForObject(url, CharacterData.class)).thenReturn(characterData);
            Mockito.when(this.httpHelper.getForObject(url2, CharacterData.class)).thenReturn(characterData);
        } else {
            Mockito.when(this.httpHelper.getForObject(url, CharacterData.class)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
            Mockito.when(this.httpHelper.getForObject(url2, CharacterData.class)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        }

        return characterSummaryData;
    }

    private void prepareRealmData(){
        for(int index = 0; index < NUMBER_REALM; index ++) {
            final RealmData realmData = new RealmData();
            realmData.setId(this.factory.getUniqueRandomInteger());
            realmData.setSlug(this.factory.getUniqueRandomAlphanumericString());
            realmData.setLocalizedStringData(this.generateLocalizedStringData());
            final HrefData hrefData = new HrefData();
            hrefData.setHref(this.factory.getUniqueRandomURI());
            realmData.setKeyHrefData(hrefData);
            this.realmDataList.add(realmData);
        }
    }

    private void prepareGuildData(){
        for(int index = 0; index < NUMBER_GUILD; index++) {
            final GuildIndexData guildIndexData = new GuildIndexData();

            guildIndexData.setName(this.factory.getUniqueRandomAlphanumericString());
            guildIndexData.setId(this.factory.getUniqueRandomInteger());

            final RealmData realmData = this.realmDataList.get(this.factory.getRandomInteger(NUMBER_REALM));
            guildIndexData.setRealmData(realmData);

            final FactionData factionData = new FactionData();
            final Faction faction = this.factionList.get(this.factory.getRandomInteger(NUMBER_FACTION));
            factionData.setType(faction.getType());

            guildIndexData.setFactionData(factionData);
            this.guildIndexDataList.add(guildIndexData);
        }
    }

    private CharacterData createCharacterData(){

        final CharacterData characterData = new CharacterData();

        characterData.setLastLoginTimestamp(Long.valueOf(this.factory.getRandomInteger()));
        characterData.setEquippedItemLevel(this.factory.getRandomInteger());
        characterData.setAverageItemLevel(this.factory.getRandomInteger());

        characterData.setName(this.factory.getUniqueRandomAlphanumericString());
        characterData.setId(this.factory.getUniqueRandomInteger());
        characterData.setLevel(this.factory.getRandomInteger(LEVEL_MAX));

        final RealmData realmData = this.realmDataList.get(this.factory.getRandomInteger(NUMBER_REALM));

        characterData.setRealmData(realmData);

        IndexData indexData = new IndexData();
        final PlayableClass playableClass = this.playableClassList.get(this.factory.getRandomInteger(NUMBER_CLASS));
        indexData.setId(playableClass.getId());

        characterData.setClassIndexData(indexData);

        indexData = new IndexData();
        final PlayableRace playableRace = this.playableRaceList.get(this.factory.getRandomInteger(NUMBER_RACES));
        indexData.setId(playableRace.getId());

        characterData.setRaceIndexData(indexData);

        final FactionData factionData = new FactionData();
        final Faction faction = this.factionList.get(this.factory.getRandomInteger(NUMBER_FACTION));
        factionData.setType(faction.getType());

        characterData.setFactionData(factionData);

        if(this.factory.getRandomBoolean()) {
            final GuildIndexData guildIndexData =
                    this.guildIndexDataList.get(this.factory.getRandomInteger(NUMBER_GUILD));
            characterData.setGuildIndexData(guildIndexData);
        }

        final HrefData hrefData = new HrefData();
        hrefData.setHref(this.factory.getUniqueRandomURI());

        final String url = hrefData.getHref() + "&access_token=" + token;

        characterData.setMediaHrefData(hrefData);

        final CharacterMediaData characterMediaData = this.mockFetchCharacterMediaData();

        Mockito.when(this.httpHelper.getForObject(url, CharacterMediaData.class)).thenReturn(characterMediaData);

        return characterData;

    }

    private CharacterMediaData mockFetchCharacterMediaData() {
        final CharacterMediaData characterMediaData = new CharacterMediaData();

        if (this.factory.getRandomBoolean()) {
            characterMediaData.setAvatarUrl(this.factory.getUniqueRandomURI());
            characterMediaData.setBustUrl(this.factory.getUniqueRandomURI());
        } else {
            final List<AssetMediaData> assetMediaDataList = new ArrayList<>();

            AssetMediaData assetMediaData = new AssetMediaData();
            assetMediaData.setKey(BlizzardService.AVATAR_KEY);
            assetMediaData.setValue(this.factory.getUniqueRandomURI());
            assetMediaDataList.add(assetMediaData);

            assetMediaData = new AssetMediaData();
            assetMediaData.setKey(BlizzardService.INSET_KEY);
            assetMediaData.setValue(this.factory.getUniqueRandomURI());
            assetMediaDataList.add(assetMediaData);

            assetMediaData = new AssetMediaData();
            assetMediaData.setKey(this.factory.getRandomAlphanumericString());
            assetMediaData.setValue(this.factory.getUniqueRandomURI());
            assetMediaDataList.add(assetMediaData);

            characterMediaData.setAssetMediaDataList(assetMediaDataList);
        }

        return characterMediaData;

    }

    private void checkData(){

        Assertions.assertEquals(this.characterDataList.size(),this.characterRepository.count());

        for(final CharacterData characterData : this.characterDataList){
            final Character character = this.characterRepository.findById(characterData.getId()).get();

            Assertions.assertEquals(character.getAverageItemLevel(), characterData.getAverageItemLevel());
            Assertions.assertEquals(character.getEquippedItemLevel(), characterData.getEquippedItemLevel());
            Assertions.assertEquals(character.getLastLoginTimestamp(), characterData.getLastLoginTimestamp());
            Assertions.assertEquals(character.getLevel(), characterData.getLevel());
            Assertions.assertEquals(character.getFaction().getType(), characterData.getFactionData().getType());
            Assertions.assertEquals(character.getName(), characterData.getName());
            Assertions.assertEquals(character.getRealm().getSlug(), characterData.getRealmData().getSlug());
            Assertions.assertNull(character.getGuildRank());

            if(characterData.getGuildIndexData() == null){
                Assertions.assertNull(character.getGuild());
            } else {
                Assertions.assertEquals(character.getGuild().getId(), characterData.getGuildIndexData().getId());
            }

            Assertions.assertEquals(character.getPlayableRace().getId(), characterData.getRaceIndexData().getId());
            Assertions.assertEquals(character.getPlayableClass().getId(), characterData.getClassIndexData().getId());
            Assertions.assertEquals(character.getUserAccount().getBlizzardId(), this.userAccount.getBlizzardId());

        }

    }

    /**
     * Test to fetch the data once
     */
    @Test
    public void testFetchOnce() {
        /*
        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);
        this.checkData();
        */
    }

    /**
     * Test to fetch the data twice
     */
    @Test
    public void testFetchTwice() {
        /*
        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);
        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);
        this.checkData();
        */
    }

    /**
     * Test to fetch the data after a new set of data
     */
    @Test
    public void testFetchTwiceWithNewData() throws Exception {

        /*
        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);
        this.initDataBeforeEach();
        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);
        this.checkData();
        */

    }
}
