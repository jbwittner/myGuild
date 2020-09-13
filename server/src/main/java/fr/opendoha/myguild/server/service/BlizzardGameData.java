package fr.opendoha.myguild.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import fr.opendoha.myguild.server.model.blizzard.LocalizedString;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.data.blizzardgamedata.IndexData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableClassesData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationData;
import fr.opendoha.myguild.server.data.blizzardgamedata.PlayableSpecializationsData;
import fr.opendoha.myguild.server.data.blizzardgamedata.SpecializationRoleData;
import fr.opendoha.myguild.server.repository.blizzard.PlayableClassRepository;
import fr.opendoha.myguild.server.repository.blizzard.PlayableSpecializationRepository;
import fr.opendoha.myguild.server.repository.blizzard.SpecializationRoleRepository;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to manage the blizzard game data
 */
@Service
@Transactional
public class BlizzardGameData implements IBlizzardGameData{

    protected OAuth2FlowHandler blizzaOAuth2FlowHandler;

    @Value("${application.blizzard.wow.game-data.base-uri}")
    protected String baseUri;

    @Value("${application.blizzard.wow.game-data.namespace}")
    protected String namespace;

    protected SpecializationRoleRepository specializationRoleRepository;

    protected PlayableSpecializationRepository playableSpecializationRepository;

    protected PlayableClassRepository playableClassRepository;

    protected Logger logger = LoggerFactory.getLogger(BlizzardGameData.class);

    protected HttpHelper httpHelper;

    /**
     * Constructor
     * @param blizzaOAuth2FlowHandler Token manager
     * @param specializationRoleRepository Specialization role repository
     * @param playableSpecializationRepository Playable specialization repository
     * @param playableClassRepository Playable class repository
     */
    @Autowired
    public BlizzardGameData(
        final OAuth2FlowHandler blizzaOAuth2FlowHandler,
        final SpecializationRoleRepository specializationRoleRepository,
        final PlayableSpecializationRepository playableSpecializationRepository,
        final PlayableClassRepository playableClassRepository,
        final HttpHelper httpHelper
        ) {
        this.blizzaOAuth2FlowHandler = blizzaOAuth2FlowHandler;
        this.specializationRoleRepository = specializationRoleRepository;
        this.playableSpecializationRepository = playableSpecializationRepository;
        this.playableClassRepository = playableClassRepository;
        this.httpHelper = httpHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData() throws IOException{

        setIsNotUpdated();

        updateClasses();
        updateSpecializations();

        deleteUnusedData();
        
    }

    private void deleteUnusedData() {

        final List<PlayableClass> listPlayableClass = this.playableClassRepository.findAll();
        final List<PlayableSpecialization> listPlayableSpecialization = this.playableSpecializationRepository.findAll();
        final List<SpecializationRole> listSpecializationRole = this.specializationRoleRepository.findAll();

        Boolean isUpdated;

        for(final PlayableSpecialization playableSpecialization : listPlayableSpecialization){
            isUpdated = playableSpecialization.getIsUdpated();
            if(isUpdated == false){
                this.playableSpecializationRepository.delete(playableSpecialization);
            }
        }

        for(final PlayableClass playableClass : listPlayableClass){
            isUpdated = playableClass.getIsUdpated();
            if(isUpdated == false){
                this.playableClassRepository.delete(playableClass);
            }
        }

        for(final SpecializationRole specializationRole : listSpecializationRole){
            isUpdated = specializationRole.getIsUdpated();
            if(isUpdated == false){
                this.specializationRoleRepository.delete(specializationRole);
            }
        }

    }

    private void setIsNotUpdated() {

        final List<PlayableClass> listPlayableClass = this.playableClassRepository.findAll();

        for(final PlayableClass playableClass : listPlayableClass){
            playableClass.setIsUdpated(false);
            this.playableClassRepository.save(playableClass);
        }

        final List<PlayableSpecialization> listPlayableSpecialization = this.playableSpecializationRepository.findAll();

        for(final PlayableSpecialization playableSpecialization : listPlayableSpecialization){
            playableSpecialization.setIsUdpated(false);
            this.playableSpecializationRepository.save(playableSpecialization);
        }

        final List<SpecializationRole> listSpecializationRole = this.specializationRoleRepository.findAll();

        for(final SpecializationRole specializationRole : listSpecializationRole){
            specializationRole.setIsUdpated(false);
            this.specializationRoleRepository.save(specializationRole);
        }

    }

    private void updateClasses() throws IOException {
        String token = this.blizzaOAuth2FlowHandler.getToken();
        String uri = this.baseUri + "/playable-class/index?namespace=" + this.namespace + "&locale=en_US&access_token=" + token;

        final PlayableClassesData playableClassesData = this.httpHelper.getForObject(uri,PlayableClassesData.class);

        for(final IndexData indexData : playableClassesData.getClasses()){
            
            token = this.blizzaOAuth2FlowHandler.getToken();
            uri = this.baseUri + "/playable-class/" + indexData.getId().toString() + "?namespace=" + this.namespace + "&locale=&access_token=" + token;

            final PlayableClassData playableClassData = this.httpHelper.getForObject(uri,PlayableClassData.class);

            updateClass(playableClassData);

        }

    }

    private void updateClass(final PlayableClassData playableClassData) {
        final LocalizedString localizedString = new LocalizedString();
        PlayableClass playableClass;
        Optional<PlayableClass> optionalPlayableClass;

        //Get the optional for the playable class
        optionalPlayableClass = this.playableClassRepository.findById(playableClassData.getId());

        //If the id is already used, we update the model, otherwise we create model
        if(optionalPlayableClass.isPresent()){
            playableClass = optionalPlayableClass.get();
            playableClass.setIsUdpated(true);
        } else {
            playableClass = new PlayableClass();
            playableClass.setId(playableClassData.getId());
        }

        //We already update the localized string
        localizedString.builderFromData(playableClassData.getName());
        playableClass.setNames(localizedString);
        
        this.playableClassRepository.save(playableClass);

    }

        private void updateSpecializations() throws IOException {
        String token = this.blizzaOAuth2FlowHandler.getToken();
        String uri = this.baseUri + "/playable-specialization/index?namespace=" + this.namespace + "&locale=en_US&access_token=" + token;

        final PlayableSpecializationsData playableSpecializationsData = this.httpHelper.getForObject(uri,PlayableSpecializationsData.class);

        PlayableSpecializationData playableSpecializationData;

        for(final IndexData indexData : playableSpecializationsData.getCharacter_specializations()){
            
            token = this.blizzaOAuth2FlowHandler.getToken();
            uri = this.baseUri + "/playable-specialization/" + indexData.getId().toString() + "?namespace=" + this.namespace + "&locale=&access_token=" + token;

            playableSpecializationData = this.httpHelper.getForObject(uri,PlayableSpecializationData.class);

            updateSpecializationRole(playableSpecializationData.getRole());
            updateSpecialization(playableSpecializationData);

        }

    }

    private void updateSpecializationRole(final SpecializationRoleData specializationRoleData){
        final LocalizedString localizedString = new LocalizedString();
        SpecializationRole specializationRole;
        Boolean dataAlreadyExist;
        

        //Get the optional for the playable class
        dataAlreadyExist = this.specializationRoleRepository.existsByType(specializationRoleData.getType());
        

        //If the id is already used, we update the model, otherwise we create model
        if(dataAlreadyExist){
            specializationRole = this.specializationRoleRepository.findByType(specializationRoleData.getType());
            specializationRole.setIsUdpated(true);
        } else {
            specializationRole = new SpecializationRole();
            Integer index;

            if(this.specializationRoleRepository.findTopByOrderByIdDesc() == null){
                index = 0;
            } else {
                index = this.specializationRoleRepository.findTopByOrderByIdDesc().getId();
            }
            index = index + 1;
            specializationRole.setId(index);
        }

        //We already update the localized string
        localizedString.builderFromData(specializationRoleData.getName());
            
        specializationRole.setNames(localizedString);
        specializationRole.setType(specializationRoleData.getType());
        
        this.specializationRoleRepository.save(specializationRole);

    }

    private void updateSpecialization(final PlayableSpecializationData playableSpecializationData) {
        final LocalizedString localizedString = new LocalizedString();
        
        PlayableSpecialization playableSpecialization;
        Optional<PlayableSpecialization> optionalPlayableSpecialization;
        PlayableClass playableClass;
        SpecializationRole specializationRole;

        //Get the optional for the playable specialization class
        optionalPlayableSpecialization = this.playableSpecializationRepository.findById(playableSpecializationData.getId());

        //If the id is already used, we update the model, otherwise we create model
        if(optionalPlayableSpecialization.isPresent()){
            playableSpecialization = optionalPlayableSpecialization.get();
            playableSpecialization.setIsUdpated(true);
        } else {
            playableSpecialization = new PlayableSpecialization();
            playableSpecialization.setId(playableSpecializationData.getId());
        }

        //We already update the localized string
        localizedString.builderFromData(playableSpecializationData.getName());
        playableSpecialization.setNames(localizedString);

        playableClass = this.playableClassRepository.findById(playableSpecializationData.getPlayable_class().getId()).get();

        playableSpecialization.setPlayableClass(playableClass);

        specializationRole = this.specializationRoleRepository.findByType(playableSpecializationData.getRole().getType());

        playableSpecialization.setSpecializationRole(specializationRole);
        
        this.playableSpecializationRepository.save(playableSpecialization);

    }
    
}