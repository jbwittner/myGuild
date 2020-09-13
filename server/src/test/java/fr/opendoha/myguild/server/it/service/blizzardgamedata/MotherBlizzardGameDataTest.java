package fr.opendoha.myguild.server.it.service.blizzardgamedata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import fr.opendoha.myguild.server.parameters.blizzardgamedata.IndexDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableClassDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableClassesDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.PlayableSpecializationsDTO;
import fr.opendoha.myguild.server.parameters.blizzardgamedata.SpecializationRoleDTO;
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

    protected List<PlayableClassDTO> listPlayableClassDTO;
    protected List<PlayableSpecializationDTO> listPlayableSpecializationDTO;
    protected List<SpecializationRoleDTO> listSpecializationRoleDTO;

    protected PlayableClassesDTO playableClassesDTO;
    protected PlayableSpecializationsDTO playableSpecializationsDTO;

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
     * Method used to mock the DTO
     */
    protected void prepareDTOMock() {
        ReflectionTestUtils.setField(blizzardGameData, "baseUri", baseUri);
        ReflectionTestUtils.setField(blizzardGameData, "namespace", namespace);

        String uri = this.baseUri + "/playable-class/index?namespace=" + this.namespace + "&locale=en_US&access_token=" + this.tokenValue;
        Mockito.when(httpHelper.getForObject(uri, PlayableClassesDTO.class)).thenReturn(this.playableClassesDTO);

        for(final PlayableClassDTO playableClassDTO : this.listPlayableClassDTO){
            uri = this.baseUri + "/playable-class/" + playableClassDTO.getId().toString() + "?namespace=" + this.namespace + "&locale=&access_token=" + this.tokenValue;
            Mockito.when(httpHelper.getForObject(uri, PlayableClassDTO.class)).thenReturn(playableClassDTO);
        }

        uri = this.baseUri + "/playable-specialization/index?namespace=" + this.namespace + "&locale=en_US&access_token=" + this.tokenValue;
        Mockito.when(httpHelper.getForObject(uri, PlayableSpecializationsDTO.class)).thenReturn(this.playableSpecializationsDTO);

        for(final PlayableSpecializationDTO playableSpecializationDTO: this.listPlayableSpecializationDTO){
            uri = this.baseUri + "/playable-specialization/" + playableSpecializationDTO.getId().toString() + "?namespace=" + this.namespace + "&locale=&access_token=" + this.tokenValue;
            Mockito.when(httpHelper.getForObject(uri, PlayableSpecializationDTO.class)).thenReturn(playableSpecializationDTO);
        }
    }

    /**
     * Method used to prepare the datas
     */
    protected void prepareDatas(){

        this.listPlayableClassDTO = new ArrayList<>();
        this.listPlayableSpecializationDTO = new ArrayList<>();
        this.listSpecializationRoleDTO = new ArrayList<>();

        final Integer numberPlayableClass = this.factory.getRandomInteger(MIN_PLAYABLE_CLASS, MAX_PLAYABLE_CLASS);
        final Integer numberPlayableSpecialiaztion = numberPlayableClass * PLAYABLE_SPECIALIZATION_PER_CLASS;
        final Integer numberSpecializationRole = this.factory.getRandomInteger(MIN_SPECILIZATION_ROLE, MAX_SPECILIZATION_ROLE);

        PlayableClassDTO playableClassDTO;

        SpecializationRoleDTO specializationRoleDTO;

        PlayableSpecializationDTO playableSpecializationDTO;
        PlayableSpecializationDTO[] playableSpecializationDTOs;

        this.playableClassesDTO = new PlayableClassesDTO();
        this.playableSpecializationsDTO = new PlayableSpecializationsDTO();

        for (int i = 0; i < numberSpecializationRole; i++) {
            specializationRoleDTO = this.factory.createSpecilizationRoleDTOWithoutPlayableSpecialization();
            this.listSpecializationRoleDTO.add(specializationRoleDTO);
        }

        IndexDTO indexDTOPlayableClass;
        IndexDTO[] indexDTOPlayableClasses = new IndexDTO[numberPlayableClass];

        IndexDTO indexDTOPlayableSpecialization;
        IndexDTO[] indexDTOPlayableSpecializations = new IndexDTO[numberPlayableSpecialiaztion];

        int indexPlayableSpecialization = 0;

        for (int i = 0; i < numberPlayableClass; i++) {
            playableClassDTO = this.factory.createPlayableClassDTOWithoutPlayableSpecialization();
            indexDTOPlayableClass = new IndexDTO();
            indexDTOPlayableClass.setId(playableClassDTO.getId());
            indexDTOPlayableClass.setName(playableClassDTO.getName().getEn_US());
            indexDTOPlayableClasses[i] = indexDTOPlayableClass;

            playableSpecializationDTOs = new PlayableSpecializationDTO[PLAYABLE_SPECIALIZATION_PER_CLASS];

            for (int j = 0; j < PLAYABLE_SPECIALIZATION_PER_CLASS; j++) {
                playableSpecializationDTO = this.factory.createPlayableSpecializationDTOWithoutSpecializationRoleAndPlayableClass();
                playableSpecializationDTO.setPlayable_class(playableClassDTO);
                final Integer randomIdSpecializationRole = this.factory.getRandomInteger(numberSpecializationRole);
                playableSpecializationDTO.setRole(this.listSpecializationRoleDTO.get(randomIdSpecializationRole));
                playableSpecializationDTOs[j] = playableSpecializationDTO;
                indexDTOPlayableSpecialization = new IndexDTO();
                indexDTOPlayableSpecialization.setId(playableSpecializationDTO.getId());
                indexDTOPlayableSpecialization.setName(playableSpecializationDTO.getName().getEn_US());
                this.listPlayableSpecializationDTO.add(playableSpecializationDTO);
                indexDTOPlayableSpecializations[indexPlayableSpecialization] = indexDTOPlayableSpecialization;
                indexPlayableSpecialization++ ;
            }

            playableClassDTO.setSpecializations(playableSpecializationDTOs);

            this.listPlayableClassDTO.add(playableClassDTO);
            
        }

        this.playableClassesDTO.setClasses(indexDTOPlayableClasses);
        this.playableSpecializationsDTO.setCharacter_specializations(indexDTOPlayableSpecializations);

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

        this.prepareDTOMock();

    }
    
}
