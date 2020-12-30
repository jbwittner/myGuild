package fr.opendoha.myguild.server.service.implementation;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.dto.FactionDTO;
import fr.opendoha.myguild.server.dto.PlayableClassDTO;
import fr.opendoha.myguild.server.dto.PlayableRaceDTO;
import fr.opendoha.myguild.server.dto.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.dto.SpecializationRoleDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.BlizzardService;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${application.blizzard.wow.profile.base-uri}")
    protected String baseUriProfile;

    @Value("${application.blizzard.wow.profile.namespace}")
    protected String namespaceProfile;

    @Value("${application.blizzard.wow.game-data.base-uri}")
    protected String baseUriGameData;

    @Value("${application.blizzard.wow.game-data.namespace}")
    protected String namespaceGameData;

    @Value("${application.guild.slug}")
    protected String guildSlug;

    @Value("${application.guild.realm}")
    protected String guildRealm;

    protected static final long TIME_BEWTEEN_CHARACTER_OBSELET = 5_184_000_000L;

    protected final OAuth2FlowHandler oAuth2FlowHandler;
    protected final UserAccountRepository userAccountRepository;
    protected final GuildRepository guildRepository;
    protected final CharacterRepository characterRepository;
    protected final GuildRankRepository guildRankRepository;
    protected final RealmRepository realmRepository;
    protected final FactionRepository factionRepository;
    protected final PlayableRaceRepository playableRaceRepository;
    protected final PlayableClassRepository playableClassRepository;
    protected final PlayableSpecializationRepository playableSpecializationRepository;
    protected final SpecializationRoleRepository specializationRoleRepository;
    protected final BlizzardAPIHelper blizzardAPIHelper;

    public static final String AVATAR_KEY = "avatar";
    public static final String INSET_KEY = "inset";

    /**
     * Constructor
     */
    @Autowired
    public BlizzardServiceImpl(
            final OAuth2FlowHandler oAuth2FlowHandler,
            final UserAccountRepository userAccountRepository,
            final CharacterRepository characterRepository,
            final GuildRepository guildRepository,
            final GuildRankRepository guildRankRepository,
            final RealmRepository realmRepository,
            final FactionRepository factionRepository,
            final PlayableRaceRepository playableRaceRepository,
            final PlayableClassRepository playableClassRepository,
            final PlayableSpecializationRepository playableSpecializationRepository,
            final SpecializationRoleRepository specializationRoleRepository,
            final BlizzardAPIHelper blizzardAPIHelper
    ) {
        this.oAuth2FlowHandler = oAuth2FlowHandler;
        this.userAccountRepository = userAccountRepository;
        this.characterRepository = characterRepository;
        this.guildRepository = guildRepository;
        this.guildRankRepository = guildRankRepository;
        this.realmRepository = realmRepository;
        this.factionRepository = factionRepository;
        this.playableRaceRepository = playableRaceRepository;
        this.playableClassRepository = playableClassRepository;
        this.playableSpecializationRepository = playableSpecializationRepository;
        this.specializationRoleRepository = specializationRoleRepository;
        this.blizzardAPIHelper = blizzardAPIHelper;
    }

    @Override
    public void fetchStaticData() throws IOException {
        this.fetchPlayableClass();
        this.fetchPlayableRaces();
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

        faction.setIsUpdatedTrue();

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

        specializationRole.setIsUpdatedTrue();
        specializationRole.buildLocalizedModel(roleData.getLocalizedStringData());
        specializationRole.setType(roleData.getType());

        specializationRole = this.specializationRoleRepository.save(specializationRole);

        return specializationRole;

    }

    @Override
    public StaticDataDTO getStaticData(){
        
        final List<PlayableClass> playableClasses = this.playableClassRepository.findByIsUpdatedTrue();
        final List<PlayableClassDTO> playableClassDTOs = new ArrayList<>();

        PlayableClassDTO playableClassDTO;

        for(final PlayableClass playableClass : playableClasses){
            playableClassDTO = new PlayableClassDTO();
            playableClassDTO.build(playableClass);
            playableClassDTOs.add(playableClassDTO);
        }

        final List<PlayableRace> playableRaces = this.playableRaceRepository.findByIsUpdatedTrue();
        final List<PlayableRaceDTO> playableRaceDTOs = new ArrayList<>();

        PlayableRaceDTO playableRaceDTO;

        for(final PlayableRace playableRace : playableRaces){
            playableRaceDTO = new PlayableRaceDTO();
            playableRaceDTO.build(playableRace);
            playableRaceDTOs.add(playableRaceDTO);
        }

        final List<PlayableSpecialization> playableSpecializations = this.playableSpecializationRepository.findByIsUpdatedTrue();
        final List<PlayableSpecializationDTO> playableSpecializationDTOs = new ArrayList<>();

        PlayableSpecializationDTO playableSpecializationDTO;

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            playableSpecializationDTO = new PlayableSpecializationDTO();
            playableSpecializationDTO.build(playableSpecialization);
            playableSpecializationDTOs.add(playableSpecializationDTO);
        }

        final List<SpecializationRole> specializationRoles = this.specializationRoleRepository.findByIsUpdatedTrue();
        final List<SpecializationRoleDTO> specializationRoleDTOs = new ArrayList<>();

        SpecializationRoleDTO specializationRoleDTO;

        for(final SpecializationRole specializationRole : specializationRoles){
            specializationRoleDTO = new SpecializationRoleDTO();
            specializationRoleDTO.build(specializationRole);
            specializationRoleDTOs.add(specializationRoleDTO);
        }

        final List<Faction> factions= this.factionRepository.findByIsUpdatedTrue();
        final List<FactionDTO> factionDTOs = new ArrayList<>();

        FactionDTO factionDTO;

        for(final Faction faction : factions){
            factionDTO = new FactionDTO();
            factionDTO.build(faction);
            factionDTOs.add(factionDTO);
        }

        final StaticDataDTO staticDataDTO = new StaticDataDTO();
        staticDataDTO.setFactionDTOs(factionDTOs);
        staticDataDTO.setPlayableClassDTOs(playableClassDTOs);
        staticDataDTO.setPlayableRaceDTOs(playableRaceDTOs);
        staticDataDTO.setPlayableSpecializationDTOs(playableSpecializationDTOs);
        staticDataDTO.setSpecializationRoleDTOs(specializationRoleDTOs);

        return staticDataDTO;
    }

}