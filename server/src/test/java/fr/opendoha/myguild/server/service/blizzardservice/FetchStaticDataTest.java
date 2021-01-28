package fr.opendoha.myguild.server.service.blizzardservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.opendoha.myguild.server.data.blizzardgamedata.AssetMediaData;
import fr.opendoha.myguild.server.data.blizzardgamedata.CovenantIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.FactionData;
import fr.opendoha.myguild.server.data.blizzardgamedata.GameDataMediaData;
import fr.opendoha.myguild.server.data.blizzardgamedata.HrefData;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import fr.opendoha.myguild.server.data.blizzardgamedata.MediaData;
import fr.opendoha.myguild.server.data.blizzardgamedata.MediaHrefData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassesIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableRaceData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableRacesIndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationData;
import fr.opendoha.myguild.server.data.blizzardgamedata.RoleData;
import fr.opendoha.myguild.server.model.blizzard.Covenant;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.LocalizedModel;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableRace;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CovenantRepository;
import fr.opendoha.myguild.server.repository.blizzard.FactionRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableRaceRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;
import fr.opendoha.myguild.server.service.UserService;
import fr.opendoha.myguild.server.service.implementation.BlizzardServiceImpl;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;

/**
 * Test class to test fetchStaticData method
 */
public class FetchStaticDataTest extends AbstractMotherIntegrationTest {

    @Autowired
    protected OAuth2FlowHandler oAuth2FlowHandler;

    @Autowired
    protected UserAccountRepository userAccountRepository;

    @Autowired
    protected FactionRepository factionRepository;

    @Autowired
    protected PlayableRaceRepository playableRaceRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    @Autowired
    protected PlayableSpecializationRepository playableSpecializationRepository;

    @Autowired
    protected SpecializationRoleRepository specializationRoleRepository;

    @Autowired
    protected CovenantRepository covenantRepository;

    @MockBean
    protected BlizzardAPIHelper blizzardAPIHelper;

    @Autowired
    protected UserService userService;

    protected BlizzardServiceImpl mockBlizzardService;

    protected HashMap<Integer,PlayableRaceData> playableRaceDatas;
    protected HashMap<String, FactionData> factionDatas;

    protected HashMap<Integer,PlayableClassData> playableClassDatas;
    protected HashMap<Integer,PlayableSpecializationData> playableSpecializationDatas;
    protected HashMap<String, RoleData> specializationRolesDatas;

    protected HashMap<Integer,IndexData> covenantIndexDatas;

    @Override
    protected void initDataBeforeEach() {
        this.mockBlizzardService = new BlizzardServiceImpl(this.factionRepository, this.playableRaceRepository,
                this.playableClassRepository, this.playableSpecializationRepository, this.specializationRoleRepository,
                this.covenantRepository, this.blizzardAPIHelper);  
                
        this.factionDatas = new HashMap<>();
        this.playableClassDatas = new HashMap<>();
        this.playableRaceDatas = new HashMap<>();
        this.playableSpecializationDatas = new HashMap<>();
        this.specializationRolesDatas = new HashMap<>();
        this.covenantIndexDatas = new HashMap<>();
    }

    private void mockService() throws IOException {
        this.mockFetchPlayableClass();
        this.mockFetchPlayableRaces();
        this.mockFetchCovenant();
    }

    private void mockFetchPlayableClass() throws IOException {

        final PlayableClassesIndexData playableClassesIndexData = this.getPlayableClassesIndexData();
        Mockito.when(this.blizzardAPIHelper.getPlayableClassesIndexData()).thenReturn(playableClassesIndexData);

        final List<RoleData> roleDatas = this.getRolesData();

        for(final RoleData roleData : roleDatas){
            this.specializationRolesDatas.put(roleData.getType(), roleData);
        }

        for(final IndexData classIndexData : playableClassesIndexData.getIndexDataList()){

            final PlayableClassData playableClassData = this.getPlayableClassData(classIndexData);

            this.playableClassDatas.put(playableClassData.getId(), playableClassData);

            final List<IndexData> specializationIndexDataList = this.getSpecializationIndexDataList();

            playableClassData.setSpecializationIndexDataList(specializationIndexDataList);

            Mockito.when(this.blizzardAPIHelper.getPlayableClassData(classIndexData)).thenReturn(playableClassData);

            GameDataMediaData gameDataMediaData = this.getGameDataMediaData(playableClassData);

            Mockito.when(this.blizzardAPIHelper.getGameDataMediaData(playableClassData)).thenReturn(gameDataMediaData);

            for(final IndexData specializationIndexData : specializationIndexDataList){

                final PlayableSpecializationData playableSpecialization = this.getPlayableSpecializationData(specializationIndexData);
                this.playableSpecializationDatas.put(playableSpecialization.getId(), playableSpecialization);

                final Integer randomNumber = this.factory.getRandomInteger(AbstractMotherIntegrationTest.NUMBER_SPECIALIZATION_ROLE);
                
                final RoleData roleData = roleDatas.get(randomNumber);

                playableSpecialization.setRoleData(roleData);

                Mockito.when(this.blizzardAPIHelper.getPlayableSpecializationData(specializationIndexData)).thenReturn(playableSpecialization);

                gameDataMediaData = this.getGameDataMediaData(playableSpecialization);

                Mockito.when(this.blizzardAPIHelper.getGameDataMediaData(playableSpecialization)).thenReturn(gameDataMediaData);
    
            }

        }

    }

    private void mockFetchPlayableRaces() throws IOException {
        final PlayableRacesIndexData playableRacesIndexData = this.getPlayableRacesIndexData();
        Mockito.when(this.blizzardAPIHelper.getPlayableRacesIndexData()).thenReturn(playableRacesIndexData);

        final List<FactionData> factionDatas = this.getFactionsData();

        for(final FactionData factionData : factionDatas){
            this.factionDatas.put(factionData.getType(), factionData);
        }

        for(final IndexData classIndexData : playableRacesIndexData.getIndexDataList()){

            final PlayableRaceData playableRaceData = this.getPlayableRaceData(classIndexData);
            this.playableRaceDatas.put(playableRaceData.getId(), playableRaceData);

            final Integer randomNumber = this.factory.getRandomInteger(AbstractMotherIntegrationTest.NUMBER_FACTION);
            final FactionData factionData = factionDatas.get(randomNumber);
            playableRaceData.setFactionData(factionData);

            Mockito.when(this.blizzardAPIHelper.getPlayableRaceData(classIndexData)).thenReturn(playableRaceData);

        }

    }

    private void mockFetchCovenant() throws IOException {
        final CovenantIndexData covenantIndexData = this.getCovenantIndexData();

        final List<IndexData> indexDatas = covenantIndexData.getIndexDataList();

        for(final IndexData indexData : indexDatas){
            this.covenantIndexDatas.put(indexData.getId(), indexData);
        }

        Mockito.when(this.blizzardAPIHelper.getCovenantIndexData()).thenReturn(covenantIndexData);
    }

    private void checkLocalizedData(final LocalizedStringData localizedStringData, final LocalizedModel localizedModel){
        Assertions.assertEquals(localizedStringData.getDeDE(), localizedModel.getDeDE());
        Assertions.assertEquals(localizedStringData.getEnGB(), localizedModel.getEnGB());
        Assertions.assertEquals(localizedStringData.getEnUS(), localizedModel.getEnUS());
        Assertions.assertEquals(localizedStringData.getEsES(), localizedModel.getEsES());
        Assertions.assertEquals(localizedStringData.getEsMX(), localizedModel.getEsMX());
        Assertions.assertEquals(localizedStringData.getFrFR(), localizedModel.getFrFR());
        Assertions.assertEquals(localizedStringData.getItIT(), localizedModel.getItIT());
        Assertions.assertEquals(localizedStringData.getKoKR(), localizedModel.getKoKR());
        Assertions.assertEquals(localizedStringData.getPtBR(), localizedModel.getPtBR());
        Assertions.assertEquals(localizedStringData.getRuRU(), localizedModel.getRuRU());
        Assertions.assertEquals(localizedStringData.getZhCN(), localizedModel.getZhCN());
        Assertions.assertEquals(localizedStringData.getZhTW(), localizedModel.getZhTW());
    }

    private void checkData(){
        this.checkCovenant();
        this.checkFactionAndPlayableRace();
        this.checkSpecializationTypeAndPlayableSpecializationAndPlayableClass();
    }

    private void checkCovenant() {
        
        final List<Covenant> covenants = this.covenantRepository.findAll();

        Assertions.assertEquals(this.covenantIndexDatas.size(), covenants.size());

        for(final Covenant covenant : covenants){

            final Boolean contain = this.covenantIndexDatas.containsKey(covenant.getId());
            Assertions.assertTrue(contain);

            final IndexData indexCovenantData = this.covenantIndexDatas.get(covenant.getId());

            this.checkLocalizedData(indexCovenantData.getLocalizedStringData(), covenant.getLocalizedModel());
        }
    }

    private void checkFactionAndPlayableRace() {
        final List<Faction> factions = this.factionRepository.findAll();

        Assertions.assertEquals(this.factionDatas.size(), factions.size());

        for(final Faction faction : factions){

            final Boolean contain = this.factionDatas.containsKey(faction.getType());
            Assertions.assertTrue(contain);

            final FactionData factionData = this.factionDatas.get(faction.getType());

            this.checkLocalizedData(factionData.getLocalizedStringData(), faction.getLocalizedModel());
        }

        final List<PlayableRace> playableRaces = this.playableRaceRepository.findAll();

        Assertions.assertEquals(this.playableRaceDatas.size(), playableRaces.size());

        for(final PlayableRace playableRace : playableRaces){

            final Boolean contain = this.playableRaceDatas.containsKey(playableRace.getId());
            Assertions.assertTrue(contain);

            final PlayableRaceData playableRaceData = this.playableRaceDatas.get(playableRace.getId());

            this.checkLocalizedData(playableRaceData.getLocalizedStringData(), playableRace.getLocalizedModel());
            Assertions.assertEquals(playableRaceData.getFactionData().getType(), playableRace.getFaction().getType());
        }

    }

    private void checkSpecializationTypeAndPlayableSpecializationAndPlayableClass() {

        final List<SpecializationRole> specializationRoles = this.specializationRoleRepository.findAll();

        Assertions.assertEquals(this.specializationRolesDatas.size(), specializationRoles.size());

        for(final SpecializationRole specializationRole : specializationRoles){

            final Boolean contain = this.specializationRolesDatas.containsKey(specializationRole.getType());
            Assertions.assertTrue(contain);

            final RoleData roleData = this.specializationRolesDatas.get(specializationRole.getType());

            this.checkLocalizedData(roleData.getLocalizedStringData(), specializationRole.getLocalizedModel());
        }

        final List<PlayableSpecialization> playableSpecializations = this.playableSpecializationRepository.findAll();

        Assertions.assertEquals(this.playableSpecializationDatas.size(), playableSpecializations.size());

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){

            final Boolean contain = this.playableSpecializationDatas.containsKey(playableSpecialization.getId());
            Assertions.assertTrue(contain);

            final PlayableSpecializationData playableSpecializationData = this.playableSpecializationDatas.get(playableSpecialization.getId());

            this.checkLocalizedData(playableSpecializationData.getLocalizedStringData(), playableSpecialization.getLocalizedModel());
            Assertions.assertEquals(playableSpecializationData.getMediaData().getHrefData().getHref(), playableSpecialization.getUrlMedia());
            Assertions.assertEquals(playableSpecializationData.getRoleData().getType(), playableSpecialization.getSpecializationRole().getType());
            
        }

        final List<PlayableClass> playableclasses = this.playableClassRepository.findAll();

        Assertions.assertEquals(this.playableClassDatas.size(), playableclasses.size());

        for(final PlayableClass playableclass : playableclasses){

            final Boolean contain = this.playableClassDatas.containsKey(playableclass.getId());
            Assertions.assertTrue(contain);

            final PlayableClassData playableClassData = this.playableClassDatas.get(playableclass.getId());

            this.checkLocalizedData(playableClassData.getLocalizedStringData(), playableclass.getLocalizedModel());
            Assertions.assertEquals(playableClassData.getMediaHrefData().getHrefData().getHref(), playableclass.getMediaURL());

        }

    }

    /**
     * Test to fetch data once
     */
    @Test
    public void testOneFetch() throws IOException {

        this.mockService();
        this.mockBlizzardService.fetchStaticData();
        this.checkData();
    }

    /**
     * Test to fetch data twice
     */
    @Test
    public void testTwoFetch() throws IOException {

        this.mockService();
        this.mockBlizzardService.fetchStaticData();
        this.mockBlizzardService.fetchStaticData();
        this.checkData();

    }

    /**
     * Test to fetch new data between twice
     */
    @Test
    public void testNewDataBetweenTwoFetch() throws IOException {

        this.mockService();
        this.mockBlizzardService.fetchStaticData();
        this.mockService();
        this.mockBlizzardService.fetchStaticData();
        this.checkData();

    }

    private PlayableClassesIndexData getPlayableClassesIndexData(){
        final PlayableClassesIndexData playableClassesIndexData = new PlayableClassesIndexData();

        final List<IndexData> indexDataList = new ArrayList<>();

        for(int index = 0; index < AbstractMotherIntegrationTest.NUMBER_PLAYABLE_CLASS; index ++){

            final IndexData indexData = new IndexData();
            indexData.setId(this.factory.getUniqueRandomInteger());
            indexData.setLocalizedStringData(this.factory.generateLocalizedStringData());
            final HrefData hrefData = new HrefData();
            hrefData.setHref("href");
            indexData.setHrefData(hrefData);

            indexDataList.add(indexData);

        }

        playableClassesIndexData.setIndexDataList(indexDataList);

        return playableClassesIndexData;
    }

    private PlayableClassData getPlayableClassData(final IndexData indexData){
        final PlayableClassData playableClassData = new PlayableClassData();

        playableClassData.setId(indexData.getId());
        playableClassData.setLocalizedStringData(indexData.getLocalizedStringData());

        final MediaHrefData mediaHrefData = new MediaHrefData();
        final HrefData hrefData = new HrefData();
        hrefData.setHref(AbstractMotherIntegrationTest.BASE_URI + indexData.getId());
        mediaHrefData.setHrefData(hrefData);

        playableClassData.setMediaHrefData(mediaHrefData);

        return playableClassData;
    }

    private GameDataMediaData getGameDataMediaData(final PlayableClassData playableClassData){

        final GameDataMediaData gameDataMediaData = new GameDataMediaData();

        final List<AssetMediaData> assetMediaDataList = new ArrayList<>();

        final AssetMediaData assetMediaData = new AssetMediaData();
        assetMediaData.setValue(BASE_URI + playableClassData.getId());

        assetMediaDataList.add(assetMediaData);

        gameDataMediaData.setAssetMediaDataList(assetMediaDataList);

        return gameDataMediaData;
    }
    
    private List<IndexData> getSpecializationIndexDataList(){
        final List<IndexData> indexDataList = new ArrayList<>();

        for(int index = 0; index < AbstractMotherIntegrationTest.NUMBER_PLAYABLE_SPECIALIZATION_PER_CLASS; index ++){

            final IndexData indexData = new IndexData();
            indexData.setId(this.factory.getUniqueRandomInteger());
            indexData.setLocalizedStringData(this.factory.generateLocalizedStringData());
            final HrefData hrefData = new HrefData();
            hrefData.setHref("href");
            indexData.setHrefData(hrefData);

            indexDataList.add(indexData);

        }

        return indexDataList;
    }

    private PlayableSpecializationData getPlayableSpecializationData(final IndexData indexData){
        final PlayableSpecializationData playableSpecializationData = new PlayableSpecializationData();

        playableSpecializationData.setId(indexData.getId());
        playableSpecializationData.setLocalizedStringData(indexData.getLocalizedStringData());
        
        final MediaData mediaData = new MediaData();
        final HrefData hrefData = new HrefData();
        hrefData.setHref(AbstractMotherIntegrationTest.BASE_URI + indexData.getId());
        mediaData.setHrefData(hrefData);

        playableSpecializationData.setMediaData(mediaData);

        return playableSpecializationData;
    }

    private GameDataMediaData getGameDataMediaData(final PlayableSpecializationData playableSpecializationData){

        final GameDataMediaData gameDataMediaData = new GameDataMediaData();

        final List<AssetMediaData> assetMediaDataList = new ArrayList<>();

        final AssetMediaData assetMediaData = new AssetMediaData();
        assetMediaData.setValue(AbstractMotherIntegrationTest.BASE_URI + playableSpecializationData.getId());

        assetMediaDataList.add(assetMediaData);

        gameDataMediaData.setAssetMediaDataList(assetMediaDataList);

        return gameDataMediaData;
    }

    private List<RoleData> getRolesData(){

        final List<RoleData> roleDatas = new ArrayList<>();

        for(Integer index = 0; index < AbstractMotherIntegrationTest.NUMBER_SPECIALIZATION_ROLE; index ++){
            final RoleData roleData = new RoleData();
            roleData.setType(this.factory.getUniqueRandomAlphanumericString());
            roleData.setLocalizedStringData(this.factory.generateLocalizedStringData());
            roleDatas.add(roleData);
        }

        return roleDatas;

    }

    private List<FactionData> getFactionsData(){

        final List<FactionData> factionDatas = new ArrayList<>();

        for(Integer index = 0; index < AbstractMotherIntegrationTest.NUMBER_FACTION; index ++){
            final FactionData factionData = new FactionData();
            factionData.setType(this.factory.getUniqueRandomAlphanumericString());
            factionData.setLocalizedStringData(this.factory.generateLocalizedStringData());
            factionDatas.add(factionData);
        }

        return factionDatas;

    }

    private PlayableRacesIndexData getPlayableRacesIndexData(){
        final PlayableRacesIndexData playableRacesIndexData = new PlayableRacesIndexData();

        final List<IndexData> indexDataList = new ArrayList<>();

        for(int index = 0; index < AbstractMotherIntegrationTest.NUMBER_PLAYABLE_RACE; index ++){

            final IndexData indexData = new IndexData();
            indexData.setId(this.factory.getUniqueRandomInteger());
            indexData.setLocalizedStringData(this.factory.generateLocalizedStringData());
            final HrefData hrefData = new HrefData();
            hrefData.setHref("href");
            indexData.setHrefData(hrefData);

            indexDataList.add(indexData);

        }

        playableRacesIndexData.setIndexDataList(indexDataList);

        return playableRacesIndexData;
    }

    private PlayableRaceData getPlayableRaceData(final IndexData indexData){
        final PlayableRaceData playableRaceData = new PlayableRaceData();

        playableRaceData.setId(indexData.getId());
        playableRaceData.setLocalizedStringData(indexData.getLocalizedStringData());
        
        final MediaData mediaData = new MediaData();
        final HrefData hrefData = new HrefData();
        hrefData.setHref(AbstractMotherIntegrationTest.BASE_URI + indexData.getId());
        mediaData.setHrefData(hrefData);

        return playableRaceData;
    }

    private CovenantIndexData getCovenantIndexData(){

        final CovenantIndexData covenantIndexData = new CovenantIndexData();

        final List<IndexData> indexDataList = new ArrayList<>();

        for(int index = 0; index < AbstractMotherIntegrationTest.NUMBER_COVENANT; index ++){

            final IndexData indexData = new IndexData();
            indexData.setId(this.factory.getUniqueRandomInteger());
            indexData.setLocalizedStringData(this.factory.generateLocalizedStringData());
            final HrefData hrefData = new HrefData();
            hrefData.setHref("href");
            indexData.setHrefData(hrefData);

            indexDataList.add(indexData);

        }

        covenantIndexData.setIndexDataList(indexDataList);

        return covenantIndexData;
    }
    
}
