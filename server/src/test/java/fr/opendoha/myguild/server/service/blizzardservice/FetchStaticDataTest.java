package fr.opendoha.myguild.server.service.blizzardservice;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.model.blizzard.*;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO : Refacto test
/**
 * Test of fetchStaticData
 */
public class FetchStaticDataTest extends AbstractMotherIntegrationTest {

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
    private static final Integer NUMBER_SPECIALIZATION_PER_CLASS = 4;
    private static final Integer NUMBER_FACTION = 3;
    private static final Integer NUMBER_CLASS = 20;
    private static final Integer NUMBER_RACES = 20;

    private List<RoleData> roleDataList = new ArrayList<>();
    private List<PlayableSpecializationData> playableSpecializationDataList = new ArrayList<>();
    private List<PlayableClassData> playableClassDataList = new ArrayList<>();
    private List<FactionData> factionDataList = new ArrayList<>();
    private List<PlayableRaceData> playableRaceDataList = new ArrayList<>();

    @Override
    protected void initDataBeforeEach() throws Exception {

        this.blizzardService = new BlizzardService(this.oAuth2FlowHandler, this.userAccountRepository,
                this.characterRepository, this.guildRepository, this.guildRankRepository, this.realmRepository,
                this.factionRepository, this.playableRaceRepository, this.playableClassRepository,
                this.playableSpecializationRepository, this.specializationRoleRepository, this.httpHelper);

        this.token = this.factory.getUniqueRandomAlphanumericString();

        Mockito.when(this.oAuth2FlowHandler.getToken())
                .thenReturn(this.token);

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

        this.resetList();

        this.preparePlayableClassesIndex();

        this.preparePlayableRacesIndex();

    }

    private void resetList(){
        this.roleDataList = new ArrayList<>();
        this.playableSpecializationDataList = new ArrayList<>();
        this.playableClassDataList = new ArrayList<>();
        this.factionDataList = new ArrayList<>();
        this.playableRaceDataList = new ArrayList<>();
    }

    private LocalizedStringData generateLocalizedStringData(){
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

    private void prepareSpecializationRole(){

        for(int index = 0; index < NUMBER_SPECIALIZATION_ROLE; index++){

            final RoleData roleData = new RoleData();

            roleData.setLocalizedStringData(this.generateLocalizedStringData());
            roleData.setType(this.factory.getUniqueRandomAlphanumericString());

            this.roleDataList.add(roleData);

        }

    }

    private IndexData preparePlayableSpecialization(){

        final PlayableSpecializationData playableSpecializationData = new PlayableSpecializationData();

        playableSpecializationData.setId(this.factory.getUniqueRandomInteger());
        playableSpecializationData.setLocalizedStringData(this.generateLocalizedStringData());

        final RoleData roleData = roleDataList.get(this.factory.getRandomInteger(NUMBER_SPECIALIZATION_ROLE));

        playableSpecializationData.setRoleData(roleData);

        HrefData hrefData = new HrefData();
        hrefData.setHref(this.factory.getUniqueRandomURI());

        final MediaData mediaData = new MediaData();
        mediaData.setId(this.factory.getUniqueRandomInteger());
        mediaData.setHrefData(hrefData);

        playableSpecializationData.setMediaData(mediaData);

        final String randomUri = this.factory.getUniqueRandomURI();

        hrefData = new HrefData();
        hrefData.setHref(randomUri);

        final IndexData indexData = new IndexData();
        indexData.setId(playableSpecializationData.getId());
        indexData.setLocalizedStringData(playableSpecializationData.getLocalizedStringData());
        indexData.setHrefData(hrefData);

        final GameDataMediaData gameDataMediaData = new GameDataMediaData();
        gameDataMediaData.setId(this.factory.getUniqueRandomInteger());
        final List<AssetMediaData> assetMediaDataList = new ArrayList<>();
        final AssetMediaData assetMediaData = new AssetMediaData();
        assetMediaData.setKey(this.factory.getUniqueRandomAlphanumericString());
        assetMediaData.setValue(this.factory.getUniqueRandomAlphanumericString());
        assetMediaData.setFileDataId(this.factory.getUniqueRandomInteger());
        assetMediaDataList.add(assetMediaData);
        gameDataMediaData.setAssetMediaDataList(assetMediaDataList);

        String mockUri = mediaData.getHrefData().getHref() + "&access_token=" + this.token;

        Mockito.when(this.httpHelper.getForObject(mockUri, GameDataMediaData.class))
                .thenReturn(gameDataMediaData);

        this.playableSpecializationDataList.add(playableSpecializationData);

        mockUri = randomUri + "&access_token=" + this.token;

        Mockito.when(this.httpHelper.getForObject(mockUri, PlayableSpecializationData.class))
                .thenReturn(playableSpecializationData);

        return indexData;

    }

    private IndexData preparePlayableClass(){

        final PlayableClassData playableClassData = new PlayableClassData();

        final List<IndexData> indexDataList = new ArrayList<>();

        for(int indexSpecialization = 0;
            indexSpecialization < NUMBER_SPECIALIZATION_PER_CLASS;
            indexSpecialization++){
            indexDataList.add(this.preparePlayableSpecialization());
        }

        playableClassData.setSpecializationIndexDataList(indexDataList);

        playableClassData.setId(this.factory.getUniqueRandomInteger());
        playableClassData.setLocalizedStringData(this.generateLocalizedStringData());

        HrefData hrefData = new HrefData();
        hrefData.setHref(this.factory.getUniqueRandomURI());

        final MediaHrefData mediaHrefData = new MediaHrefData();
        mediaHrefData.setId(this.factory.getUniqueRandomInteger());
        mediaHrefData.setHrefData(hrefData);

        final GameDataMediaData gameDataMediaData = new GameDataMediaData();
        gameDataMediaData.setId(this.factory.getUniqueRandomInteger());
        final List<AssetMediaData> assetMediaDataList = new ArrayList<>();
        final AssetMediaData assetMediaData = new AssetMediaData();
        assetMediaData.setKey(this.factory.getUniqueRandomAlphanumericString());
        assetMediaData.setValue(this.factory.getUniqueRandomAlphanumericString());
        assetMediaData.setFileDataId(this.factory.getUniqueRandomInteger());
        assetMediaDataList.add(assetMediaData);
        gameDataMediaData.setAssetMediaDataList(assetMediaDataList);

        String mockUri = hrefData.getHref() + "&access_token=" + this.token;

        Mockito.when(this.httpHelper.getForObject(mockUri, GameDataMediaData.class))
                .thenReturn(gameDataMediaData);

        playableClassData.setMediaHrefData(mediaHrefData);

        this.playableClassDataList.add(playableClassData);

        final String randomUri = this.factory.getUniqueRandomURI();

        mockUri = randomUri + "&access_token=" + this.token;

        Mockito.when(this.httpHelper.getForObject(mockUri, PlayableClassData.class))
                .thenReturn(playableClassData);

        hrefData = new HrefData();
        hrefData.setHref(randomUri);

        final IndexData indexData = new IndexData();
        indexData.setId(playableClassData.getId());
        indexData.setLocalizedStringData(playableClassData.getLocalizedStringData());
        indexData.setHrefData(hrefData);

        return indexData;

    }

    private void prepareFactionData(){

        for(int index = 0; index < NUMBER_FACTION; index++){

            final FactionData factionData = new FactionData();
            factionData.setType(this.factory.getUniqueRandomAlphanumericString());
            factionData.setLocalizedStringData(this.generateLocalizedStringData());

            this.factionDataList.add(factionData);

        }

    };

    private IndexData preparePlayableRace(){

        final PlayableRaceData playableRaceData = new PlayableRaceData();

        playableRaceData.setId(this.factory.getUniqueRandomInteger());
        playableRaceData.setLocalizedStringData(this.generateLocalizedStringData());
        playableRaceData.setFactionData(this.factionDataList.get(this.factory.getRandomInteger(NUMBER_FACTION)));

        final String randomUri = this.factory.getUniqueRandomURI();

        final String mockUri = randomUri + "&access_token=" + this.token;

        Mockito.when(this.httpHelper.getForObject(mockUri, PlayableRaceData.class))
                .thenReturn(playableRaceData);

        final HrefData hrefData = new HrefData();
        hrefData.setHref(randomUri);

        final IndexData indexData = new IndexData();
        indexData.setId(playableRaceData.getId());
        indexData.setLocalizedStringData(playableRaceData.getLocalizedStringData());
        indexData.setHrefData(hrefData);

        this.playableRaceDataList.add(playableRaceData);

        return indexData;

    }

    private void preparePlayableClassesIndex(){

        this.prepareSpecializationRole();

        final List<IndexData> indexDataList = new ArrayList<>();

        for(int index =0 ; index < NUMBER_CLASS; index ++){

            indexDataList.add(this.preparePlayableClass());

        }

        final PlayableClassesIndexData playableClassesIndexData = new PlayableClassesIndexData();

        playableClassesIndexData.setIndexDataList(indexDataList);

        final String url = this.baseUriGameData + "/playable-class/index?namespace=" + this.namespaceGameData
                + "&access_token=" + token;

        Mockito.when(this.httpHelper.getForObject(url, PlayableClassesIndexData.class))
                .thenReturn(playableClassesIndexData);

    }

    private void preparePlayableRacesIndex(){

        this.prepareFactionData();

        final List<IndexData> indexDataList = new ArrayList<>();

        for(int index =0 ; index < NUMBER_RACES; index ++){

            indexDataList.add(this.preparePlayableRace());

        }

        final PlayableRacesIndexData playableRacesIndexData = new PlayableRacesIndexData();

        playableRacesIndexData.setIndexDataList(indexDataList);

        final String url = this.baseUriGameData + "/playable-race/index?namespace=" + this.namespaceGameData
                + "&access_token=" + token;

        Mockito.when(this.httpHelper.getForObject(url, PlayableRacesIndexData.class))
                .thenReturn(playableRacesIndexData);

    }

    private void compareLocalizedModelAndLocalizedStringData(final LocalizedModel localizedModel, final LocalizedStringData localizedStringData){
        Assertions.assertEquals(localizedStringData.getDeDE() ,localizedModel.getDeDE());
        Assertions.assertEquals(localizedStringData.getEnGB(),localizedModel.getEnGB());
        Assertions.assertEquals(localizedStringData.getEnUS(),localizedModel.getEnUS());
        Assertions.assertEquals(localizedStringData.getEsES(),localizedModel.getEsES());
        Assertions.assertEquals(localizedStringData.getEsMX(),localizedModel.getEsMX());
        Assertions.assertEquals(localizedStringData.getFrFR(),localizedModel.getFrFR());
        Assertions.assertEquals(localizedStringData.getItIT(),localizedModel.getItIT());
        Assertions.assertEquals(localizedStringData.getKoKR(),localizedModel.getKoKR());
        Assertions.assertEquals(localizedStringData.getPtBR(),localizedModel.getPtBR());
        Assertions.assertEquals(localizedStringData.getRuRU(),localizedModel.getRuRU());
        Assertions.assertEquals(localizedStringData.getZhCN(),localizedModel.getZhCN());
        Assertions.assertEquals(localizedStringData.getZhTW(),localizedModel.getZhTW());
    }

    private void checkData(final Integer numberOfPrepareDataCall) {

        Assertions.assertEquals(NUMBER_FACTION * numberOfPrepareDataCall,this.factionRepository.count());
        Assertions.assertEquals(NUMBER_RACES * numberOfPrepareDataCall,this.playableRaceRepository.count());
        Assertions.assertEquals(NUMBER_SPECIALIZATION_ROLE * numberOfPrepareDataCall,this.specializationRoleRepository.count());
        Assertions.assertEquals(NUMBER_CLASS * NUMBER_SPECIALIZATION_PER_CLASS * numberOfPrepareDataCall,this.playableSpecializationRepository.count());
        Assertions.assertEquals(NUMBER_CLASS * numberOfPrepareDataCall, this.playableClassRepository.count());


        for(final FactionData factionData : this.factionDataList){
            final Faction faction = this.factionRepository.findByType(factionData.getType()).get();

            this.compareLocalizedModelAndLocalizedStringData(faction.getLocalizedModel(),
                    factionData.getLocalizedStringData());

        }

        for(final PlayableRaceData playableRaceData : this.playableRaceDataList){
            final PlayableRace playableRace = this.playableRaceRepository.findById(playableRaceData.getId()).get();

            Assertions.assertEquals(playableRaceData.getFactionData().getType(), playableRace.getFaction().getType());

            this.compareLocalizedModelAndLocalizedStringData(playableRace.getLocalizedModel(),
                    playableRaceData.getLocalizedStringData());

        }

        for(final RoleData roleData : this.roleDataList){
            final SpecializationRole specializationRole = this.specializationRoleRepository.findByType(roleData.getType()).get();

            this.compareLocalizedModelAndLocalizedStringData(specializationRole.getLocalizedModel(),
                    roleData.getLocalizedStringData());
        }

        for(final PlayableSpecializationData playableSpecializationData : this.playableSpecializationDataList){
            final PlayableSpecialization playableSpecialization = this.playableSpecializationRepository.findById(playableSpecializationData.getId()).get();

            this.compareLocalizedModelAndLocalizedStringData(playableSpecialization.getLocalizedModel(),
                    playableSpecializationData.getLocalizedStringData());

            Assertions.assertEquals(playableSpecializationData.getRoleData().getType(), playableSpecialization.getSpecializationRole().getType());

        }

        for(final PlayableClassData playableClassData : this.playableClassDataList){
            final PlayableClass playableClass = this.playableClassRepository.findById(playableClassData.getId()).get();

            this.compareLocalizedModelAndLocalizedStringData(playableClass.getLocalizedModel(),
                    playableClassData.getLocalizedStringData());

        }

    }

    /**
     * Test to fetch the data once
     */
    @Test
    public void testFetchOnce() throws IOException {

        this.blizzardService.fetchStaticData();

        this.checkData(1);

    }

    /**
     * Test to fetch the data twice
     */
    @Test
    public void testFetchTwice() throws IOException {

        /*
        this.blizzardService.fetchStaticData();
        this.blizzardService.fetchStaticData();

        this.checkData(1);
        */

    }

    /**
     * Test to fetch the data after a new set of data
     */
    @Test
    public void testFetchTwiceWithNewData() throws Exception {

        /*
        this.blizzardService.fetchStaticData();
        this.initDataBeforeEach();
        this.blizzardService.fetchStaticData();

        this.checkData(2);
        */

    }


}
