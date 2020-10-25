package fr.opendoha.myguild.server.service.blizzardservice;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.BlizzardService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO : Refacto test
/**
 * Test of FetchAllDataCharacters
 */
public class FetchAllDataCharactersTest extends AbstractMotherIntegrationTest {

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

    final private String token = this.factory.getUniqueRandomAlphanumericString();;

    private GuildData guildData;

    private static final Integer NUMBER_SPECIALIZATION_ROLE = 3;
    private static final Integer NUMBER_MEMBER = 200;
    private static final Integer NUMBER_CLASS = 20;
    private static final Integer NUMBER_RACES = 20;
    private static final Integer NUMBER_MAX_RANK = 7;
    private static final Integer LEVEL_MAX = 50;

    private Faction faction;

    List<SpecializationRole> specializationRoleList = new ArrayList<>();
    List<PlayableRace> playableRaceList = new ArrayList<>();
    List<PlayableClass> playableClassList = new ArrayList<>();
    List<CharacterData> chararctersData = new ArrayList<>();

    @Override
    protected void initDataBeforeEach() throws Exception {

        specializationRoleList = new ArrayList<>();
        playableRaceList = new ArrayList<>();
        playableClassList = new ArrayList<>();

        this.blizzardService = new BlizzardService(this.oAuth2FlowHandler, this.userAccountRepository,
                this.characterRepository, this.guildRepository, this.guildRankRepository, this.realmRepository,
                this.factionRepository, this.playableRaceRepository, this.playableClassRepository,
                this.playableSpecializationRepository, this.specializationRoleRepository, this.httpHelper);

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

        this.createFaction();
        this.createSpecializationRole();
        this.createPlayableRace();
        this.createPlayableClass();
        this.mockFetchGuildInformation();
        this.mockRoster();

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

        this.faction = new Faction();
        this.faction.setType(this.factory.getUniqueRandomAlphanumericString());
        this.faction = this.factionRepository.save(faction);

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
            playableRace.setFaction(this.faction);
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

    private void createCharachersWithoutGuild(){

    }

    private void mockFetchGuildInformation() {

        this.guildData = new GuildData();
        this.guildData.setMemberCount(this.factory.getRandomInteger());
        this.guildData.setId(this.factory.getUniqueRandomInteger());

        final FactionData factionData = new FactionData();
        factionData.setType(this.faction.getType());

        this.guildData.setFactionData(factionData);

        final RealmData realmData = new RealmData();
        realmData.setId(this.factory.getUniqueRandomInteger());
        realmData.setSlug(this.factory.getUniqueRandomAlphanumericString());
        realmData.setLocalizedStringData(this.generateLocalizedStringData());

        this.guildData.setRealmData(realmData);

        this.guildData.setName(this.factory.getUniqueRandomAlphanumericString());

        this.guildData.setAchievementPoints(this.factory.getRandomInteger());

        final HrefData hrefData = new HrefData();
        hrefData.setHref(this.factory.getUniqueRandomURI());

        this.guildData.setRosterHrefData(hrefData);


        final String url = this.baseUriGameData + "/guild/" + this.guildRealm.toLowerCase() + "/"
                + this.guildSlug.toLowerCase() + "?namespace=" + this.namespaceProfile + "&access_token=" + token;

        Mockito.when(this.httpHelper.getForObject(url, GuildData.class)).thenReturn(this.guildData);

    }

    private void mockRoster() {

        final GuildRosterIndexData guildRosterIndexData = new GuildRosterIndexData();

        final List<GuildMemberIndexData> guildMemberIndexDataList = new ArrayList<>();

        for (int index = 0; index < NUMBER_MEMBER; index++) {
            guildMemberIndexDataList.add(this.mockGuildIndexData());
        }

        guildRosterIndexData.setGuildMemberIndexDataList(guildMemberIndexDataList);

        final String url = this.guildData.getRosterHrefData().getHref() + "&access_token=" + this.token;

        Mockito.when(this.httpHelper.getForObject(url, GuildRosterIndexData.class)).thenReturn(guildRosterIndexData);

    }

    private GuildMemberIndexData mockGuildIndexData() {
        final GuildMemberIndexData guildMemberIndexData = new GuildMemberIndexData();
        guildMemberIndexData.setRank(this.factory.getRandomInteger(NUMBER_MAX_RANK));

        final GuildMemberData guildMemberData = new GuildMemberData();
        guildMemberData.setId(this.factory.getUniqueRandomInteger());
        guildMemberData.setLevel(this.factory.getRandomInteger(LEVEL_MAX) + 1);
        guildMemberData.setName(this.factory.getUniqueRandomAlphanumericString());

        final PlayableClass playableClass = this.playableClassList.get(this.factory.getRandomInteger(NUMBER_CLASS));
        IndexData indexData = new IndexData();
        indexData.setId(playableClass.getId());

        guildMemberData.setPlayableClassIndexData(indexData);

        final PlayableRace playableRace = this.playableRaceList.get(this.factory.getRandomInteger(NUMBER_RACES));
        indexData = new IndexData();
        indexData.setId(playableRace.getId());

        guildMemberData.setPlayableRaceIndexData(indexData);

        HrefData hrefData = new HrefData();
        hrefData.setHref(this.factory.getUniqueRandomURI());

        guildMemberData.setHrefData(hrefData);

        final String url = hrefData.getHref() + "&access_token=" + this.token;

        final String url2 = this.baseUriProfile + "/wow/character/" + this.guildData.getRealmData().getSlug()
                + "/" + guildMemberData.getName() + "?namespace=" + this.namespaceProfile + "&access_token=" + token;

        if (this.factory.getRandomBoolean()) {

            final CharacterData characterData = new CharacterData();

            characterData.setId(guildMemberData.getId());
            characterData.setId(guildMemberData.getId());
            characterData.setRealmData(this.guildData.getRealmData());
            characterData.setFactionData(this.guildData.getFactionData());
            characterData.setClassIndexData(guildMemberData.getPlayableClassIndexData());
            characterData.setRaceIndexData(guildMemberData.getPlayableRaceIndexData());
            characterData.setAverageItemLevel(this.factory.getRandomInteger());
            characterData.setEquippedItemLevel(this.factory.getRandomInteger());
            characterData.setLastLoginTimestamp(Long.valueOf(this.factory.getRandomInteger()));

            hrefData = new HrefData();
            hrefData.setHref(this.factory.getUniqueRandomURI());

            final String urlCharacterMediaData = hrefData.getHref() + "&access_token=" + this.token;

            final CharacterMediaData characterMediaData = this.mockFetchCharacterMediaData();

            Mockito.when(this.httpHelper.getForObject(urlCharacterMediaData, CharacterMediaData.class)).thenReturn(characterMediaData);

            characterData.setMediaHrefData(hrefData);

            Mockito.when(this.httpHelper.getForObject(url, CharacterData.class)).thenReturn(characterData);
            Mockito.when(this.httpHelper.getForObject(url2, CharacterData.class)).thenReturn(characterData);

            this.chararctersData.add(characterData);

        } else {

            Mockito.when(this.httpHelper.getForObject(url, CharacterData.class))
                    .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

            Mockito.when(this.httpHelper.getForObject(url2, CharacterData.class))
                    .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        }

        guildMemberIndexData.setGuildMemberData(guildMemberData);

        return guildMemberIndexData;
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

    /**
     * Test to fetch the data once
     */
    @Test
    public void testFetchOnce() throws IOException {
        /*
        this.blizzardService.fetchAllDataCharacters();
        */
    }

    /**
     * Test to fetch the data twice
     */
    @Test
    public void testFetchTwice() throws IOException {
        /*
        this.blizzardService.fetchAllDataCharacters();
        this.blizzardService.fetchAllDataCharacters();
        */
    }

    /**
     * Test to fetch the data after a new set of data
     */
    @Test
    public void testFetchTwiceWithNewData() throws Exception {

        /*
        this.blizzardService.fetchAllDataCharacters();
        this.initDataBeforeEach();
        this.blizzardService.fetchAllDataCharacters();
        */

    }

}
