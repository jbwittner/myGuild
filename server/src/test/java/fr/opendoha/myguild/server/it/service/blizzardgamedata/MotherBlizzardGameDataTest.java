package fr.opendoha.myguild.server.it.service.blizzardgamedata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassesData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationsData;
import fr.opendoha.myguild.server.data.blizzardgamedata.SpecializationRoleData;
import fr.opendoha.myguild.server.it.AbstractMotherIntegrationTest;

import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;
import fr.opendoha.myguild.server.service.BlizzardGameData;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.TestObjectFactory;
import fr.opendoha.myguild.server.tools.exceptions.DataInitialisationException;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * Mother class of BlizzardGameData service tests
 */
public class MotherBlizzardGameDataTest extends AbstractMotherIntegrationTest {

    static final Integer MIN_PLAYABLE_CLASS = 10;
    static final Integer MAX_PLAYABLE_CLASS = 30;

    static final Integer PLAYABLE_SPECIALIZATION_PER_CLASS = 3;

    static final Integer MIN_SPECILIZATION_ROLE = 3;
    static final Integer MAX_SPECILIZATION_ROLE = 5;

    @Value("${application.blizzard.wow.game-data.base-uri}")
    protected String baseUri;

    @Value("${application.blizzard.wow.game-data.namespace}")
    protected String namespace;

    @Mock
    protected HttpHelper httpHelper;

    @Mock
    protected OAuth2FlowHandler blizzaOAuth2FlowHandler;

    protected BlizzardGameData blizzardGameData;

    @Autowired
    protected SpecializationRoleRepository specializationRoleRepository;

    @Autowired
    protected PlayableSpecializationRepository playableSpecializationRepository;

    @Autowired
    protected PlayableClassRepository playableClassRepository;

    protected List<PlayableClassData> listPlayableClassData;
    protected List<PlayableSpecializationData> listPlayableSpecializationData;
    protected List<SpecializationRoleData> listSpecializationRoleData;

    protected PlayableClassesData playableClassesData;
    protected PlayableSpecializationsData playableSpecializationsData;

    protected String tokenValue;

    /**
     * Method used prepare the service
     */
    private void prepareService(){
        this.blizzardGameData = new BlizzardGameData(
            this.blizzaOAuth2FlowHandler,
            this.specializationRoleRepository,
            this.playableSpecializationRepository,
            this.playableClassRepository,
            this.httpHelper
            );
    }

    /**
     * Method used to mock the token
     */
    private void prepareTokenMock() throws IOException{
        this.tokenValue = this.factory.getUniqueRandomAsciiString(TestObjectFactory.LENGTH_ACCESS_TOKEN);
        Mockito.when(blizzaOAuth2FlowHandler.getToken()).thenReturn(tokenValue);
    }

    /**
     * Method used to mock the Data
     */
    protected void prepareDataMock() {
        ReflectionTestUtils.setField(blizzardGameData, "baseUri", baseUri);
        ReflectionTestUtils.setField(blizzardGameData, "namespace", namespace);

        String uri = this.baseUri + "/playable-class/index?namespace=" + this.namespace + "&locale=en_US&access_token=" + this.tokenValue;
        Mockito.when(httpHelper.getForObject(uri, PlayableClassesData.class)).thenReturn(this.playableClassesData);

        for(final PlayableClassData playableClassData : this.listPlayableClassData){
            uri = this.baseUri + "/playable-class/" + playableClassData.getId().toString() + "?namespace=" + this.namespace + "&locale=&access_token=" + this.tokenValue;
            Mockito.when(httpHelper.getForObject(uri, PlayableClassData.class)).thenReturn(playableClassData);
        }

        uri = this.baseUri + "/playable-specialization/index?namespace=" + this.namespace + "&locale=en_US&access_token=" + this.tokenValue;
        Mockito.when(httpHelper.getForObject(uri, PlayableSpecializationsData.class)).thenReturn(this.playableSpecializationsData);

        for(final PlayableSpecializationData playableSpecializationData: this.listPlayableSpecializationData){
            uri = this.baseUri + "/playable-specialization/" + playableSpecializationData.getId().toString() + "?namespace=" + this.namespace + "&locale=&access_token=" + this.tokenValue;
            Mockito.when(httpHelper.getForObject(uri, PlayableSpecializationData.class)).thenReturn(playableSpecializationData);
        }
    }

    /**
     * Method used to prepare the datas
     */
    protected void prepareDatas(){

        this.listPlayableClassData = new ArrayList<>();
        this.listPlayableSpecializationData = new ArrayList<>();
        this.listSpecializationRoleData = new ArrayList<>();

        final Integer numberPlayableClass = this.factory.getRandomInteger(MIN_PLAYABLE_CLASS, MAX_PLAYABLE_CLASS);
        final Integer numberPlayableSpecialiaztion = numberPlayableClass * PLAYABLE_SPECIALIZATION_PER_CLASS;
        final Integer numberSpecializationRole = this.factory.getRandomInteger(MIN_SPECILIZATION_ROLE, MAX_SPECILIZATION_ROLE);

        PlayableClassData playableClassData;

        SpecializationRoleData specializationRoleData;

        PlayableSpecializationData playableSpecializationData;
        PlayableSpecializationData[] playableSpecializationDatas;

        this.playableClassesData = new PlayableClassesData();
        this.playableSpecializationsData = new PlayableSpecializationsData();

        for (int i = 0; i < numberSpecializationRole; i++) {
            specializationRoleData = this.factory.createSpecilizationRoleDataWithoutPlayableSpecialization();
            this.listSpecializationRoleData.add(specializationRoleData);
        }

        IndexData indexDataPlayableClass;
        IndexData[] indexDataPlayableClasses = new IndexData[numberPlayableClass];

        IndexData indexDataPlayableSpecialization;
        IndexData[] indexDataPlayableSpecializations = new IndexData[numberPlayableSpecialiaztion];

        int indexPlayableSpecialization = 0;

        for (int i = 0; i < numberPlayableClass; i++) {
            playableClassData = this.factory.createPlayableClassDataWithoutPlayableSpecialization();
            indexDataPlayableClass = new IndexData();
            indexDataPlayableClass.setId(playableClassData.getId());
            indexDataPlayableClass.setName(playableClassData.getName().getEn_US());
            indexDataPlayableClasses[i] = indexDataPlayableClass;

            playableSpecializationDatas = new PlayableSpecializationData[PLAYABLE_SPECIALIZATION_PER_CLASS];

            for (int j = 0; j < PLAYABLE_SPECIALIZATION_PER_CLASS; j++) {
                playableSpecializationData = this.factory.createPlayableSpecializationDataWithoutSpecializationRoleAndPlayableClass();
                playableSpecializationData.setPlayable_class(playableClassData);
                final Integer randomIdSpecializationRole = this.factory.getRandomInteger(numberSpecializationRole);
                playableSpecializationData.setRole(this.listSpecializationRoleData.get(randomIdSpecializationRole));
                playableSpecializationDatas[j] = playableSpecializationData;
                indexDataPlayableSpecialization = new IndexData();
                indexDataPlayableSpecialization.setId(playableSpecializationData.getId());
                indexDataPlayableSpecialization.setName(playableSpecializationData.getName().getEn_US());
                this.listPlayableSpecializationData.add(playableSpecializationData);
                indexDataPlayableSpecializations[indexPlayableSpecialization] = indexDataPlayableSpecialization;
                indexPlayableSpecialization++ ;
            }

            playableClassData.setSpecializations(playableSpecializationDatas);

            this.listPlayableClassData.add(playableClassData);
            
        }

        this.playableClassesData.setClasses(indexDataPlayableClasses);
        this.playableSpecializationsData.setCharacter_specializations(indexDataPlayableSpecializations);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void initDataBeforeEach() throws DataInitialisationException{

        this.prepareService();

        try{
            this.prepareTokenMock();
        }catch(IOException e){
            throw (DataInitialisationException) new DataInitialisationException().initCause(e);
        }

        this.prepareDatas();

        this.prepareDataMock();

    }
    
}
