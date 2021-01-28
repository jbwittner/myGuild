package fr.opendoha.myguild.server.service.implementation;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.dto.CovenantDTO;
import fr.opendoha.myguild.server.dto.FactionDTO;
import fr.opendoha.myguild.server.dto.PlayableClassDTO;
import fr.opendoha.myguild.server.dto.PlayableRaceDTO;
import fr.opendoha.myguild.server.dto.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.dto.SpecializationRoleDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.BlizzardService;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * Service to manage the blizzard game data
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
@Transactional
public class BlizzardServiceImpl implements BlizzardService {

    protected final Logger logger = LoggerFactory.getLogger(BlizzardService.class);

    protected FactionRepository factionRepository;
    protected PlayableRaceRepository playableRaceRepository;
    protected PlayableClassRepository playableClassRepository;
    protected PlayableSpecializationRepository playableSpecializationRepository;
    protected SpecializationRoleRepository specializationRoleRepository;
    protected CovenantRepository covenantRepository;
    protected BlizzardAPIHelper blizzardAPIHelper;

    /**
     * Constructor
     */
    @Autowired
    public BlizzardServiceImpl(
            final FactionRepository factionRepository,
            final PlayableRaceRepository playableRaceRepository,
            final PlayableClassRepository playableClassRepository,
            final PlayableSpecializationRepository playableSpecializationRepository,
            final SpecializationRoleRepository specializationRoleRepository,
            final CovenantRepository covenantRepository,
            final BlizzardAPIHelper blizzardAPIHelper
    ) {
        this.factionRepository = factionRepository;
        this.playableRaceRepository = playableRaceRepository;
        this.playableClassRepository = playableClassRepository;
        this.playableSpecializationRepository = playableSpecializationRepository;
        this.specializationRoleRepository = specializationRoleRepository;
        this.covenantRepository = covenantRepository;
        this.blizzardAPIHelper = blizzardAPIHelper;
    }

    @Override
    public void fetchStaticData() throws IOException {
        this.fetchPlayableClass();
        this.fetchPlayableRaces();
        this.fetchCovenant();
    }

    private void fetchPlayableRaces() throws IOException {

        final PlayableRacesIndexData playableRacesIndexData = this.blizzardAPIHelper.getPlayableRacesIndexData();

        for(final IndexData indexData : playableRacesIndexData.getIndexDataList()){

            final PlayableRaceData playableRaceData = this.blizzardAPIHelper.getPlayableRaceData(indexData);

            final Faction faction = this.fetchFaction(playableRaceData.getFactionData());

            final Optional<PlayableRace> optionalPlayableRace =
                    this.playableRaceRepository.findById(playableRaceData.getId());

            PlayableRace playableRace;

            if(optionalPlayableRace.isPresent()){
                playableRace = optionalPlayableRace.get();
            } else {
                playableRace = new PlayableRace();
                playableRace.setId(playableRaceData.getId());
            }

            playableRace.setFaction(faction);
            playableRace.buildLocalizedModel(playableRaceData.getLocalizedStringData());

            this.playableRaceRepository.save(playableRace);

        }

    }

    private Faction fetchFaction(final FactionData factionData){

        final Optional<Faction> optionalFaction =
                this.factionRepository.findByType(factionData.getType());

        Faction faction = optionalFaction.orElseGet(Faction::new);

        faction.updateLocalizedValue(factionData.getLocalizedStringData());
        faction.setType(factionData.getType());

        faction = this.factionRepository.save(faction);

        return faction;

    }

    private void fetchPlayableClass() throws IOException {

        final PlayableClassesIndexData playableClassesIndexData =
                this.blizzardAPIHelper.getPlayableClassesIndexData();

        for(final IndexData indexData : playableClassesIndexData.getIndexDataList()){

            final PlayableClassData playableClassData = this.blizzardAPIHelper.getPlayableClassData(indexData);
            final GameDataMediaData gameDataMediaData = this.blizzardAPIHelper.getGameDataMediaData(playableClassData);

            final Optional<PlayableClass> optionalPlayableClass =
                    this.playableClassRepository.findById(playableClassData.getId());

            PlayableClass playableClass;

            if(optionalPlayableClass.isPresent()){
                playableClass = optionalPlayableClass.get();
            } else {
                playableClass = new PlayableClass();
                playableClass.setId(playableClassData.getId());
            }

            playableClass.setMediaURL(gameDataMediaData.getAssetMediaDataList().get(0).getValue());

            playableClass.buildLocalizedModel(playableClassData.getLocalizedStringData());

            playableClass = this.playableClassRepository.save(playableClass);

            this.fetchPlayableSpecializations(playableClassData, playableClass);

        }

    }

    private void fetchPlayableSpecializations(final PlayableClassData playableClassData,
                                              final PlayableClass playableClass) throws IOException {

        for(final IndexData indexData : playableClassData.getSpecializationIndexDataList()){

            final PlayableSpecializationData playableSpecializationData =
                    this.blizzardAPIHelper.getPlayableSpecializationData(indexData);

            final GameDataMediaData gameDataMediaData = this.blizzardAPIHelper.getGameDataMediaData(playableSpecializationData);

            final Optional<PlayableSpecialization> optionalPlayableSpecialization =
                    this.playableSpecializationRepository.findById(playableSpecializationData.getId());

            PlayableSpecialization playableSpecialization;

            if(optionalPlayableSpecialization.isPresent()){
                playableSpecialization = optionalPlayableSpecialization.get();
            } else {
                playableSpecialization = new PlayableSpecialization();
                playableSpecialization.setId(playableSpecializationData.getId());
            }

            playableSpecialization.buildLocalizedModel(playableSpecializationData.getLocalizedStringData());
            playableSpecialization.setUrlMedia(gameDataMediaData.getAssetMediaDataList().get(0).getValue());

            playableSpecialization.setPlayableClass(playableClass);

            final SpecializationRole specializationRole =
                    this.fetchSpecializationRole(playableSpecializationData.getRoleData());

            playableSpecialization.setSpecializationRole(specializationRole);

            this.playableSpecializationRepository.save(playableSpecialization);

        }

    }

    private SpecializationRole fetchSpecializationRole(final RoleData roleData){

        final Optional<SpecializationRole> optionalSpecializationRole =
                this.specializationRoleRepository.findByType(roleData.getType());

        SpecializationRole specializationRole;

        specializationRole = optionalSpecializationRole.orElseGet(SpecializationRole::new);

        specializationRole.buildLocalizedModel(roleData.getLocalizedStringData());
        specializationRole.setType(roleData.getType());

        specializationRole = this.specializationRoleRepository.save(specializationRole);

        return specializationRole;

    }

    private void fetchCovenant() throws IOException {
        final CovenantIndexData covenantIndexData = this.blizzardAPIHelper.getCovenantIndexData();

        final List<IndexData> indexDatas = covenantIndexData.getIndexDataList();

        for(final IndexData indexData : indexDatas){
            Covenant covenant;

            final Optional<Covenant> optionalCovenant = this.covenantRepository.findById(indexData.getId());

            if(optionalCovenant.isPresent()){
                covenant = optionalCovenant.get();
            } else {
                covenant = new Covenant();
                covenant.setId(indexData.getId());
            }

            covenant.buildLocalizedModel(indexData.getLocalizedStringData());

            this.covenantRepository.save(covenant);

        }
    }

    @Override
    public StaticDataDTO getStaticData(){
        
        final List<PlayableClass> playableClasses = this.playableClassRepository.findAll();
        final List<PlayableClassDTO> playableClassDTOs = new ArrayList<>();

        PlayableClassDTO playableClassDTO;

        for(final PlayableClass playableClass : playableClasses){
            playableClassDTO = new PlayableClassDTO();
            playableClassDTO.build(playableClass);
            playableClassDTOs.add(playableClassDTO);
        }

        final List<PlayableRace> playableRaces = this.playableRaceRepository.findAll();
        final List<PlayableRaceDTO> playableRaceDTOs = new ArrayList<>();

        PlayableRaceDTO playableRaceDTO;

        for(final PlayableRace playableRace : playableRaces){
            playableRaceDTO = new PlayableRaceDTO();
            playableRaceDTO.build(playableRace);
            playableRaceDTOs.add(playableRaceDTO);
        }

        final List<PlayableSpecialization> playableSpecializations = this.playableSpecializationRepository.findAll();
        final List<PlayableSpecializationDTO> playableSpecializationDTOs = new ArrayList<>();

        PlayableSpecializationDTO playableSpecializationDTO;

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            playableSpecializationDTO = new PlayableSpecializationDTO();
            playableSpecializationDTO.build(playableSpecialization);
            playableSpecializationDTOs.add(playableSpecializationDTO);
        }

        final List<SpecializationRole> specializationRoles = this.specializationRoleRepository.findAll();
        final List<SpecializationRoleDTO> specializationRoleDTOs = new ArrayList<>();

        SpecializationRoleDTO specializationRoleDTO;

        for(final SpecializationRole specializationRole : specializationRoles){
            specializationRoleDTO = new SpecializationRoleDTO();
            specializationRoleDTO.build(specializationRole);
            specializationRoleDTOs.add(specializationRoleDTO);
        }

        final List<Faction> factions= this.factionRepository.findAll();
        final List<FactionDTO> factionDTOs = new ArrayList<>();

        FactionDTO factionDTO;

        for(final Faction faction : factions){
            factionDTO = new FactionDTO();
            factionDTO.build(faction);
            factionDTOs.add(factionDTO);
        }

        final List<Covenant> covenants = this.covenantRepository.findAll();
        final List<CovenantDTO> covenantDTOs = new ArrayList<>();

        CovenantDTO covenantDTO;

        for(final Covenant covenant : covenants){
            covenantDTO = new CovenantDTO();
            covenantDTO.build(covenant);
            covenantDTOs.add(covenantDTO);
        }

        final StaticDataDTO staticDataDTO = new StaticDataDTO();
        staticDataDTO.setFactionDTOs(factionDTOs);
        staticDataDTO.setPlayableClassDTOs(playableClassDTOs);
        staticDataDTO.setPlayableRaceDTOs(playableRaceDTOs);
        staticDataDTO.setPlayableSpecializationDTOs(playableSpecializationDTOs);
        staticDataDTO.setSpecializationRoleDTOs(specializationRoleDTOs);
        staticDataDTO.setCovenantDTOs(covenantDTOs);

        return staticDataDTO;
    }

}