package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.FactionDTO;
import fr.opendoha.myguild.server.dto.PlayableClassDTO;
import fr.opendoha.myguild.server.dto.PlayableRaceDTO;
import fr.opendoha.myguild.server.dto.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.dto.SpecializationRoleDTO;
import fr.opendoha.myguild.server.dto.StaticDataDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Service to manage the blizzard game data
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
@Transactional
public class BlizzardService implements IBlizzardService {

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

    protected static final long TIME_BEWTEEN_CHARACTER_OBSELET = 5184000000L;

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
    public BlizzardService(
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
    public List<CharacterSummaryDTO> fetchCharacterAccount(final BlizzardAccountParameter blizzardAccountParameter)
            throws IOException {

        final UserAccount userAccount =
                this.userAccountRepository.findByBlizzardId(blizzardAccountParameter.getBlizzardId());

        List<Character> characters = this.characterRepository.findByUserAccount(userAccount);

        for(final Character character : characters){
            character.setIsUpdatedFalse();
            this.characterRepository.save(character);
        }

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData =
                this.blizzardAPIHelper.getAccountProfileSummaryBlizzardData(blizzardAccountParameter);

        for(final WowAccountData wowAccountData : accountProfileSummaryBlizzardData.getWowAccountsData()){
            for(final CharacterSummaryData characterSummaryData : wowAccountData.getCharacterSummaryData()){
                this.fetchCharacterFromAccount(characterSummaryData, userAccount);
            }
        }

        characters = this.characterRepository.findByUserAccount(userAccount);

        final List<CharacterSummaryDTO> characterDTOs = new ArrayList<>();
        CharacterSummaryDTO characterDTO;

        for(final Character character : characters){
            if(character.getIsUpdated() == false){
                this.characterRepository.delete(character);
            } else {
                characterDTO = new CharacterSummaryDTO();
                characterDTO.build(character);
                characterDTOs.add(characterDTO);
            }
            
        }

        return characterDTOs;
    }

    private void fetchCharacterFromAccount(final CharacterSummaryData characterSummaryData,
                                           final UserAccount userAccount) throws IOException {

        try{
            final CharacterData characterData = this.blizzardAPIHelper.getCharacterData(characterSummaryData);

            final Character character = this.updateCharacter(characterData);
            character.setUserAccount(userAccount);

            this.characterRepository.save(character);

        } catch (HttpClientErrorException e){
            this.logger.debug(e.getMessage());
        }

    }

    private Character updateCharacterWithoutMedia(final CharacterData characterData) throws HttpClientErrorException, IOException {
        
        final Optional<Character> optionalCharacter = this.characterRepository.findById(characterData.getId());

        final Character character;

        if(optionalCharacter.isPresent()){
            character = optionalCharacter.get();
        } else {
            character = new Character();
            character.setId(characterData.getId());
        }

        character.setIsUpdatedTrue();

        character.setLevel(characterData.getLevel());
        character.setName(characterData.getName());
        character.setAverageItemLevel(characterData.getAverageItemLevel());
        character.setEquippedItemLevel(characterData.getEquippedItemLevel());
        character.setLastLoginTimestamp(characterData.getLastLoginTimestamp());

        Timestamp limitTimestamp = new Timestamp(System.currentTimeMillis() - TIME_BEWTEEN_CHARACTER_OBSELET);
        Timestamp characterTimestamp = new Timestamp(characterData.getLastLoginTimestamp());

        boolean isTooOld = limitTimestamp.after(characterTimestamp);

        character.setIsTooOld(isTooOld);

        character.setRealm(this.fetchRealmFromCharacter(characterData));

        final Faction faction = this.factionRepository.findByType(characterData.getFactionData().getType()).get();

        character.setFaction(faction);

        final PlayableClass playableClass =
                this.playableClassRepository.findById(characterData.getClassIndexData().getId()).get();

        character.setPlayableClass(playableClass);

        final PlayableRace playableRace =
                this.playableRaceRepository.findById(characterData.getRaceIndexData().getId()).get();

        character.setPlayableRace(playableRace);

        character.setGuild(this.fetchGuildFromCharacter(characterData));

        return character;
    }

    private Character updateCharacter(final CharacterData characterData) throws HttpClientErrorException, IOException {
        
        final Character character = this.updateCharacterWithoutMedia(characterData);

        this.fetchCharacterMediaData(character, characterData);

        return character;
    }

    private Realm fetchRealmFromCharacter(final CharacterData characterData){
        Realm realm;

        final RealmData realmData = characterData.getRealmData();

        final Optional<Realm> optionalRealm = this.realmRepository.findBySlug(characterData.getRealmData().getSlug());

        if(optionalRealm.isPresent()){
            realm = optionalRealm.get();
        }else {
            realm = new Realm();
            realm.setId(realmData.getId());
        }

        realm.setIsUpdatedTrue();

        realm.buildLocalizedModel(realmData.getLocalizedStringData());
        realm.setSlug(realmData.getSlug());

        realm = this.realmRepository.save(realm);

        return realm;

    }

    private void fetchCharacterMediaData(final Character character, final CharacterData characterData)
            throws HttpClientErrorException, IOException {

        final CharacterMediaData characterMediaData = this.blizzardAPIHelper.getCharacterMediaData(characterData);

        if(characterMediaData.getBustUrl() == null){

            for (final AssetMediaData assetMediaData : characterMediaData.getAssetMediaDataList()){
                if(AVATAR_KEY.equals(assetMediaData.getKey())){
                    character.setAvatarUrl(assetMediaData.getValue());
                } else if(INSET_KEY.equals(assetMediaData.getKey())){
                    character.setInsetUrl(assetMediaData.getValue());
                }
            }

        } else {

            character.setAvatarUrl(characterMediaData.getAvatarUrl());
            character.setInsetUrl(characterMediaData.getBustUrl());
        }

    }

    private Guild fetchGuildFromCharacter(final CharacterData characterData){
        Guild guild = null;

        final GuildIndexData guildIndexData = characterData.getGuildIndexData();

        if(guildIndexData != null){

            final Optional<Guild> optionalGuild = this.guildRepository.findById(guildIndexData.getId());

            if(optionalGuild.isPresent()){
                guild = optionalGuild.get();

            }else {
                guild = new Guild();
                guild.setId(guildIndexData.getId());
            }

            final Optional<Realm> optionalRealm = this.realmRepository.findBySlug(characterData.getRealmData().getSlug());

            final Realm realm = optionalRealm.get();

            guild.setRealm(realm);

            final Optional<Faction> optionalFaction =
                    this.factionRepository.findByType(guildIndexData.getFactionData().getType());

            final Faction faction = optionalFaction.get();
            guild.setFaction(faction);

            guild.setIsUpdatedTrue();

            guild.setName(guildIndexData.getName());

            guild = this.guildRepository.save(guild);
        }

        return guild;

    }

    @Override
    public StaticDataDTO getStaticData(){
        
        final List<PlayableClass> playableClasses = this.playableClassRepository.findByIsUpdatedTrue();
        final List<PlayableClassDTO> playableClassDTOs = new ArrayList<>();

        for(final PlayableClass playableClass : playableClasses){
            PlayableClassDTO playableClassDTO = new PlayableClassDTO();
            playableClassDTO.build(playableClass);
            playableClassDTOs.add(playableClassDTO);
        }

        final List<PlayableRace> playableRaces = this.playableRaceRepository.findByIsUpdatedTrue();
        final List<PlayableRaceDTO> playableRaceDTOs = new ArrayList<>();

        for(final PlayableRace playableRace : playableRaces){
            PlayableRaceDTO playableRaceDTO = new PlayableRaceDTO();
            playableRaceDTO.build(playableRace);
            playableRaceDTOs.add(playableRaceDTO);
        }


        final List<PlayableSpecialization> playableSpecializations = this.playableSpecializationRepository.findByIsUpdatedTrue();
        final List<PlayableSpecializationDTO> playableSpecializationDTOs = new ArrayList<>();

        for(final PlayableSpecialization playableSpecialization : playableSpecializations){
            PlayableSpecializationDTO playableSpecializationDTO = new PlayableSpecializationDTO();
            playableSpecializationDTO.build(playableSpecialization);
            playableSpecializationDTOs.add(playableSpecializationDTO);
        }

        final List<SpecializationRole> specializationRoles = this.specializationRoleRepository.findByIsUpdatedTrue();
        final List<SpecializationRoleDTO> specializationRoleDTOs = new ArrayList<>();

        for(final SpecializationRole specializationRole : specializationRoles){
            SpecializationRoleDTO specializationRoleDTO = new SpecializationRoleDTO();
            specializationRoleDTO.build(specializationRole);
            specializationRoleDTOs.add(specializationRoleDTO);
        }

        final List<Faction> factions= this.factionRepository.findByIsUpdatedTrue();
        final List<FactionDTO> factionDTOs = new ArrayList<>();

        for(final Faction faction : factions){
            FactionDTO factionDTO = new FactionDTO();
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

    @Override
    public void setFavoriteCharacter(final BlizzardAccountParameter blizzardAccountParameter, final FavoriteCharacterParameter favoriteCharacterParameter)
            throws IOException, CharacterNotExistedException {

        final Optional<Character> optionalCharacter = this.characterRepository.findById(favoriteCharacterParameter.getId());

        if(optionalCharacter.isPresent()){

            final Character character = optionalCharacter.get();

            character.setFavorite(favoriteCharacterParameter.getIsFavorite());
            
            this.characterRepository.save(character);

        } else {
            throw new CharacterNotExistedException(favoriteCharacterParameter.getId());
        }

    }

    @Override
    public void fetchPrincipalGuild() throws IOException {
        final GuildData guildData = this.blizzardAPIHelper.getGuildData(this.guildRealm, this.guildSlug);

        final Guild guild = new Guild();

        guild.setId(guildData.getId());

        guild.setName(guildData.getName());
        guild.setMemberCount(guildData.getMemberCount());

        final Faction faction = this.factionRepository.findByType(guildData.getFactionData().getType()).get();

        guild.setFaction(faction);

        Realm realm = new Realm();

        realm.setId(guildData.getRealmData().getId());
        realm.setSlug(guildData.getRealmData().getSlug());
        realm.buildLocalizedModel(guildData.getRealmData().getLocalizedStringData());

        realm = this.realmRepository.save(realm);

        guild.setRealm(realm);

        guild.setUseApplication(true);

        this.guildRepository.save(guild);
        
    }

    @Override
    public void fetchGuildMembers() throws IOException {
        final GuildRosterIndexData guildRosterIndexData = this.blizzardAPIHelper.getGuildRosterIndexData(this.guildRealm, this.guildSlug);

        List<GuildMemberIndexData> guildMemberIndexDatas = guildRosterIndexData.getGuildMemberIndexDataList();

        CharacterData characterData;
        Character character;

        for(final GuildMemberIndexData guildMemberIndexData : guildMemberIndexDatas){

            Integer rankInt = guildMemberIndexData.getRank();

            Optional<GuildRank> optionalGuildRank = this.guildRankRepository.findByRank(rankInt);

            GuildRank guildRank;
            
            if(optionalGuildRank.isPresent()){
                guildRank = optionalGuildRank.get();
            } else {
                guildRank = new GuildRank();
                guildRank.setRank(rankInt);
                guildRank.setName("RANK_" + rankInt);
                guildRank = this.guildRankRepository.save(guildRank);
            }

            characterData = this.blizzardAPIHelper.getCharacterData(guildMemberIndexData);
            character = this.updateCharacterWithoutMedia(characterData);
            character.setGuildRank(guildRank);

            this.characterRepository.save(character);
        }

    }

    @Override
    public List<CharacterSummaryDTO> fetchCharacterRoster() {


        return characterDTOs;
    }

}