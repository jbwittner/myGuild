package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.*;

/**
 * Service to manage the blizzard game data
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
@Transactional
public class BlizzardService implements IBlizzardService {

    protected final Logger logger = LoggerFactory.getLogger(BlizzardService.class);

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
    protected final HttpHelper httpHelper;

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
            final HttpHelper httpHelper
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
        this.httpHelper = httpHelper;
    }

    @Override
    public void fetchAccountCharacter(final BlizzardAccountParameter blizzardAccountParameter){

        final String url = this.baseUriProfile + "/user/wow?namespace=" +
                this.namespaceProfile + "&access_token=" + blizzardAccountParameter.getToken();

        final UserAccount userAccount =
                this.userAccountRepository.findByBlizzardId(blizzardAccountParameter.getBlizzardId());

        List<Character> characters = this.characterRepository.findByUserAccount(userAccount);

        for(final Character character : characters){
            character.setIsUpdatedFalse();
            this.characterRepository.save(character);
        }

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData =
                this.httpHelper.getForObject(url, AccountProfileSummaryBlizzardData.class);

        for(final WowAccountData wowAccountData : accountProfileSummaryBlizzardData.getWowAccountsData()){
            for(final CharacterSummaryData characterSummaryData : wowAccountData.getCharacterSummaryData()){
                this.fetchCharacterFromAccount(characterSummaryData, userAccount, blizzardAccountParameter.getToken());
            }
        }

        characters = this.characterRepository.findByUserAccountAndIsUpdatedFalse(userAccount);

        for(final Character character : characters){
            this.characterRepository.delete(character);
        }
    }

    @Override
    public void fetchStaticData() throws IOException {
        this.setAllPlayableClassNotUpdated();
        this.setAllPlayableRaceNotUpdated();
        this.setAllPlayableSpecializationNotUpdated();
        this.setAllSpecializationRoleNotUpdated();
        this.setAllFactionNotUpdated();

        this.fetchPlayableRaces();
        this.fetchPlayableClass();

    }

    @Override
    public void fetchAllDataCharacters() throws IOException {
        this.setAllGuildRankNotUpdated();
        this.setAllGuildNotUpdated();
        this.setAllCharacterNotUpdated();

        this.fetchGuild();
        this.updateAllOtherCharacters();

        this.deleteUnusedCharacter();
    }

    @Override
    public void fetchAllData() throws IOException {
        this.setAllCharacterNotUpdated();
        this.setAllFactionNotUpdated();
        this.setAllGuildRankNotUpdated();
        this.setAllGuildNotUpdated();
        this.setAllPlayableClassNotUpdated();
        this.setAllPlayableRaceNotUpdated();
        this.setAllPlayableSpecializationNotUpdated();
        this.setAllSpecializationRoleNotUpdated();

        this.fetchPlayableRaces();
        this.fetchPlayableClass();
        this.fetchGuild();
        this.updateAllOtherCharacters();

        this.deleteUnusedCharacter();

    }

    private void deleteUnusedCharacter(){
        final List<Character> characters = this.characterRepository.findByIsUpdatedFalse();

        for(final Character character : characters){
            this.characterRepository.delete(character);
        }

    }

    private void fetchCharacterFromAccount(final CharacterSummaryData characterSummaryData,
                                           final UserAccount userAccount,
                                           final String token){

        final String url = characterSummaryData.getCharacterHrefData().getHref() + "&access_token=" + token;

        try{
            final CharacterData characterData = this.httpHelper.getForObject(url, CharacterData.class);

            final Character character;

            final Optional<Character> optionalCharacter = this.characterRepository.findById(characterData.getId());

            if(optionalCharacter.isPresent()){
                character = optionalCharacter.get();
            } else {
                character = new Character();
                character.setId(characterData.getId());
            }

            character.setIsUpdatedTrue();

            character.setUserAccount(userAccount);
            character.setLevel(characterData.getLevel());
            character.setName(characterData.getName());
            character.setAverageItemLevel(characterData.getAverageItemLevel());
            character.setEquippedItemLevel(characterData.getEquippedItemLevel());
            character.setLastLoginTimestamp(characterData.getLastLoginTimestamp());

            character.setRealm(this.fetchRealmFromCharacter(characterData));

            final Faction faction = this.factionRepository.findByType(characterData.getFactionData().getType()).get();

            character.setFaction(faction);

            final PlayableClass playableClass =
                    this.playableClassRepository.findById(characterData.getClassIndexData().getId()).get();

            character.setPlayableClass(playableClass);

            final PlayableRace playableRace =
                    this.playableRaceRepository.findById(characterData.getRaceIndexData().getId()).get();

            character.setPlayableRace(playableRace);

            final CharacterMediaData characterMediaData = this.fetchCharacterMediaData(characterData);

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

            character.setGuild(this.fetchGuildFromCharacter(characterData));

            this.characterRepository.save(character);

        } catch (HttpClientErrorException | IOException e){
            this.logger.debug(e.getMessage());
        }

    }

    private void updateAllOtherCharacters() throws IOException {
        final List<Character> characterList = this.characterRepository.findByIsUpdatedFalse();

        for (final Character character : characterList){

            this.updateCharacter(character);

        }

    }

    private void updateCharacter(final Character inputCharacter) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriProfile + "/wow/character/" + inputCharacter.getRealm().getSlug()
                + "/" + inputCharacter.getName() + "?namespace=" + this.namespaceProfile + "&access_token=" + token;

        try{
            final CharacterData characterData = this.httpHelper.getForObject(url, CharacterData.class);

            final Character character;

            final Optional<Character> optionalCharacter = this.characterRepository.findById(characterData.getId());

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

            character.setRealm(this.fetchRealmFromCharacter(characterData));

            final Faction faction = this.factionRepository.findByType(characterData.getFactionData().getType()).get();

            character.setFaction(faction);

            final PlayableClass playableClass =
                    this.playableClassRepository.findById(characterData.getClassIndexData().getId()).get();

            character.setPlayableClass(playableClass);

            final PlayableRace playableRace =
                    this.playableRaceRepository.findById(characterData.getRaceIndexData().getId()).get();

            character.setPlayableRace(playableRace);

            final CharacterMediaData characterMediaData = this.fetchCharacterMediaData(characterData);

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

            character.setGuild(this.fetchGuildFromCharacter(characterData));

            this.characterRepository.save(character);

        } catch (HttpClientErrorException | IOException e){
            this.logger.debug(e.getMessage());
        }

    }

    private void fetchCharacterFromGuild(final GuildMemberIndexData guildMemberIndexData,final Guild guild)
            throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final Character character;

        final GuildMemberData guildMemberData = guildMemberIndexData.getGuildMemberData();

        final Optional<Character> optionalCharacter = this.characterRepository.findById(guildMemberData.getId());

        if(optionalCharacter.isPresent()){
            character = optionalCharacter.get();
        } else {
            character = new Character();
            character.setId(guildMemberData.getId());
        }

        character.setIsUpdatedTrue();

        character.setRealm(guild.getRealm());

        character.setFaction(guild.getFaction());

        character.setName(guildMemberData.getName());
        character.setLevel(guildMemberData.getLevel());

        character.setRealm(guild.getRealm());
        character.setFaction(guild.getFaction());

        final PlayableClass playableClass = this.playableClassRepository.findById(guildMemberData.getPlayableClassIndexData().getId()).get();

        character.setPlayableClass(playableClass);

        final PlayableRace playableRace = this.playableRaceRepository.findById(guildMemberData.getPlayableRaceIndexData().getId()).get();

        character.setPlayableRace(playableRace);
        character.setGuild(guild);

        final GuildRank guildRank = this.updateGuildRank(guildMemberIndexData.getRank());
        character.setGuildRank(guildRank);

        try{

            final String url = guildMemberIndexData.getGuildMemberData().getHrefData().getHref() + "&access_token=" + token;

            final CharacterData characterData = this.httpHelper.getForObject(url, CharacterData.class);

            character.setAverageItemLevel(characterData.getAverageItemLevel());
            character.setEquippedItemLevel(characterData.getEquippedItemLevel());
            character.setLastLoginTimestamp(characterData.getLastLoginTimestamp());

            final CharacterMediaData characterMediaData = this.fetchCharacterMediaData(characterData);

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

        } catch (HttpClientErrorException e){
            this.logger.debug(e.getMessage());
        }

        this.characterRepository.save(character);

    }

    private CharacterMediaData fetchCharacterMediaData(final CharacterData characterData) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = characterData.getMediaHrefData().getHref() + "&access_token=" + token;

        return this.httpHelper.getForObject(url, CharacterMediaData.class);

    }

    private GuildRank updateGuildRank(final Integer guildRankId){
        GuildRank guildRank;

        final Optional<GuildRank> guildRankOptional = this.guildRankRepository.findByRank(guildRankId);

        if(guildRankOptional.isPresent()){
            guildRank = guildRankOptional.get();
        } else {
            guildRank = new GuildRank();
            guildRank.setRank(guildRankId);
            guildRank.setName("RANK_" + guildRankId);
        }

        guildRank.setIsUpdatedTrue();

        guildRank = this.guildRankRepository.save(guildRank);

        return guildRank;
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

    private Realm fetchRealmFromGuild(final GuildData guildData){

        Realm realm;

        final RealmData realmData = guildData.getRealmData();

        final Optional<Realm> optionalRealm = this.realmRepository.findBySlug(guildData.getRealmData().getSlug());

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

    private void setAllCharacterNotUpdated(){
        final List<Character> characterList = this.characterRepository.findAll();

        for(final Character character : characterList){
            character.setIsUpdatedFalse();
            this.characterRepository.save(character);
        }
    }

    private void setAllFactionNotUpdated(){
        final List<Faction> factionList = this.factionRepository.findAll();

        for(final Faction faction : factionList){
            faction.setIsUpdatedFalse();
            this.factionRepository.save(faction);
        }
    }

    private void setAllGuildRankNotUpdated(){
        final List<GuildRank> guildRankList = this.guildRankRepository.findAll();

        for(final GuildRank guildRank : guildRankList){
            guildRank.setIsUpdatedFalse();
            this.guildRankRepository.save(guildRank);
        }
    }

    private void setAllGuildNotUpdated(){
        final List<Guild> guildList = this.guildRepository.findAll();

        for(final Guild guild : guildList){
            guild.setIsUpdatedFalse();
            this.guildRepository.save(guild);
        }
    }

    private void setAllPlayableClassNotUpdated(){
        final List<PlayableClass> playableClassList = this.playableClassRepository.findAll();

        for(final PlayableClass playableClass : playableClassList){
            playableClass.setIsUpdatedFalse();
            this.playableClassRepository.save(playableClass);
        }
    }

    private void setAllPlayableRaceNotUpdated(){
        final List<PlayableRace> playableRaceList = this.playableRaceRepository.findAll();

        for(final PlayableRace playableRace : playableRaceList){
            playableRace.setIsUpdatedFalse();
            this.playableRaceRepository.save(playableRace);
        }
    }

    private void setAllPlayableSpecializationNotUpdated(){
        final List<PlayableSpecialization> playableSpecializationList = this.playableSpecializationRepository.findAll();

        for(final PlayableSpecialization playableSpecialization : playableSpecializationList){
            playableSpecialization.setIsUpdatedFalse();
            this.playableSpecializationRepository.save(playableSpecialization);
        }
    }

    private void setAllSpecializationRoleNotUpdated(){
        final List<SpecializationRole> specializationRoles = this.specializationRoleRepository.findAll();

        for(final SpecializationRole specializationRole : specializationRoles){
            specializationRole.setIsUpdatedFalse();
            this.specializationRoleRepository.save(specializationRole);
        }
    }

    private void fetchGuild() throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = this.baseUriGameData + "/guild/" + this.guildRealm.toLowerCase()
                + "/" + this.guildSlug.toLowerCase() + "?namespace=" + this.namespaceProfile + "&access_token=" + token;

        final GuildData guildData = this.httpHelper.getForObject(url, GuildData.class);

        final Optional<Guild> optionalGuild = this.guildRepository.findById(guildData.getId());

        Guild guild;

        if(optionalGuild.isPresent()){
            guild = optionalGuild.get();
        }else {
            guild = new Guild();
            guild.setId(guildData.getId());
        }

        final Faction faction = this.factionRepository.findByType(guildData.getFactionData().getType()).get();

        guild.setFaction(faction);

        final Realm realm = this.fetchRealmFromGuild(guildData);

        guild.setName(guildData.getName());

        guild.setIsMainGuild(true);

        guild.setRealm(realm);

        guild.setAchievementPoints(guildData.getAchievementPoints());

        guild.setMemberCount(guildData.getMemberCount());

        guild = this.guildRepository.save(guild);

        this.fetchGuildRoster(guildData, guild);

    }

    private void fetchGuildRoster(final GuildData guildData, final Guild guild) throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        final String url = guildData.getRosterHrefData().getHref() + "&access_token=" + token;

        final GuildRosterIndexData guildRosterIndexData = this.httpHelper.getForObject(url, GuildRosterIndexData.class);

        for(final GuildMemberIndexData guildMemberIndexData : guildRosterIndexData.getGuildMemberIndexDataList()){

            this.fetchCharacterFromGuild(guildMemberIndexData, guild);

        }

    }

    private void fetchPlayableRaces() throws IOException {

        final String token = this.oAuth2FlowHandler.getToken();

        String url = this.baseUriGameData + "/playable-race/index?namespace=" + this.namespaceGameData
                + "&access_token=" + token;

        final PlayableRacesIndexData playableRacesIndexData =
                this.httpHelper.getForObject(url, PlayableRacesIndexData.class);

        for(final IndexData indexData : playableRacesIndexData.getIndexDataList()){
            url = indexData.getHrefData().getHref() + "&access_token=" + token;

            final PlayableRaceData playableRaceData = this.httpHelper.getForObject(url, PlayableRaceData.class);

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

        final String token = this.oAuth2FlowHandler.getToken();

        String url = this.baseUriGameData + "/playable-class/index?namespace=" + this.namespaceGameData
                + "&access_token=" + token;

        final PlayableClassesIndexData playableClassesIndexData =
                this.httpHelper.getForObject(url, PlayableClassesIndexData.class);

        for(final IndexData indexData : playableClassesIndexData.getIndexDataList()){
            url = indexData.getHrefData().getHref() + "&access_token=" + token;

            final PlayableClassData playableClassData = this.httpHelper.getForObject(url, PlayableClassData.class);

            url = playableClassData.getMediaHrefData().getHrefData().getHref() + "&access_token=" + token;

            final GameDataMediaData gameDataMediaData = this.httpHelper.getForObject(url, GameDataMediaData.class);

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

        final String token = this.oAuth2FlowHandler.getToken();

        for(final IndexData indexData : playableClassData.getSpecializationIndexDataList()){

            String url = indexData.getHrefData().getHref() + "&access_token=" + token;

            final PlayableSpecializationData playableSpecializationData =
                    this.httpHelper.getForObject(url, PlayableSpecializationData.class);

            url = playableSpecializationData.getMediaData().getHrefData().getHref() + "&access_token=" + token;

            final GameDataMediaData gameDataMediaData = this.httpHelper.getForObject(url, GameDataMediaData.class);

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

}