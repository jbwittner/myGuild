package fr.opendoha.myguild.server.service.implementation;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.dto.CharacterGuildSummaryDTO;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.GuildService;
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
public class GuildServiceImpl implements GuildService {

    protected final Logger logger = LoggerFactory.getLogger(GuildService.class);

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
    public GuildServiceImpl(
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

        final Timestamp limitTimestamp = new Timestamp(System.currentTimeMillis() - TIME_BEWTEEN_CHARACTER_OBSELET);
        final Timestamp characterTimestamp = new Timestamp(characterData.getLastLoginTimestamp());

        final boolean isTooOld = limitTimestamp.after(characterTimestamp);

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

        final List<GuildMemberIndexData> guildMemberIndexDatas = guildRosterIndexData.getGuildMemberIndexDataList();

        CharacterData characterData;
        Character character;

        for(final GuildMemberIndexData guildMemberIndexData : guildMemberIndexDatas){

            final Integer rankInt = guildMemberIndexData.getRank();

            final Optional<GuildRank> optionalGuildRank = this.guildRankRepository.findByRank(rankInt);

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
    public List<CharacterGuildSummaryDTO> getGuildMembers() {
        
        final List<CharacterGuildSummaryDTO> characterGuildSummaryDTOs = new ArrayList<>();

        final Realm realm = this.realmRepository.findBySlug(this.guildRealm).get();

        final Guild guild = this.guildRepository.findByNameAndRealm(this.guildSlug, realm).get();

        final List<Character> characters = this.characterRepository.findByGuild(guild);

        CharacterGuildSummaryDTO characterGuildSummaryDTO;

        for(final Character character : characters){
            characterGuildSummaryDTO = new CharacterGuildSummaryDTO();
            characterGuildSummaryDTO.build(character);
            characterGuildSummaryDTOs.add(characterGuildSummaryDTO);
        }

        return characterGuildSummaryDTOs;

    }

}