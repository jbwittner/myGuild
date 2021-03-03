package fr.opendoha.myguild.server.service.implementation;

import fr.opendoha.myguild.server.annotation.IgnoreCheckUser;
import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.exception.UnexpectedException;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.GuildService;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.*;

/**
 * Service to manage the guilds
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
@Transactional
public class GuildServiceImpl implements GuildService {

    protected final Logger logger = LoggerFactory.getLogger(GuildService.class);

    @Value("${application.blizzard.guild.name}")
    protected String guildName;

    @Value("${application.blizzard.guild.realmslug}")
    protected String realmSlug;

    public static final Integer RANK_GUILD_MASTER = 0;
    protected static final Integer TRY_FETCH_CHARACTER = 5;

    protected UserAccountRepository userAccountRepository;
    protected GuildRepository guildRepository;
    protected CharacterRepository characterRepository;
    protected GuildRankRepository guildRankRepository;
    protected FactionRepository factionRepository;
    protected PlayableRaceRepository playableRaceRepository;
    protected PlayableClassRepository playableClassRepository;
    protected CovenantRepository covenantRepository;
    protected RealmRepository realmRepository;
    protected BlizzardAPIHelper blizzardAPIHelper;

    /**
     * Constructor
     */
    @Autowired
    public GuildServiceImpl(
            final UserAccountRepository userAccountRepository,
            final CharacterRepository characterRepository,
            final GuildRepository guildRepository,
            final GuildRankRepository guildRankRepository,
            final FactionRepository factionRepository,
            final PlayableRaceRepository playableRaceRepository,
            final PlayableClassRepository playableClassRepository,
            final CovenantRepository covenantRepository,
            final RealmRepository realmRepository,
            final BlizzardAPIHelper blizzardAPIHelper
    ) {
        this.userAccountRepository = userAccountRepository;
        this.characterRepository = characterRepository;
        this.guildRepository = guildRepository;
        this.guildRankRepository = guildRankRepository;
        this.factionRepository = factionRepository;
        this.playableRaceRepository = playableRaceRepository;
        this.playableClassRepository = playableClassRepository;
        this.covenantRepository = covenantRepository;
        this.realmRepository = realmRepository;
        this.blizzardAPIHelper = blizzardAPIHelper;
    }

    @IgnoreCheckUser
    @Override
    public void initialize() throws IOException, UnexpectedException {

        final GuildData guildData = this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName);

        final Optional<Guild> guildOptional = this.guildRepository.findById(guildData.getId());

        if( !guildOptional.isPresent()){
            Guild guild = new Guild();
            guild.setId(guildData.getId());
        
            final Faction faction = this.factionRepository.findByType(guildData.getFactionData().getType()).get();

            guild.setFaction(faction);
            guild.setName(guildData.getName());

            Realm realm;
            
            final Optional<Realm> realmOptional = this.realmRepository.findBySlug(guildData.getRealmData().getSlug());

            if(realmOptional.isPresent()){
                realm = realmOptional.get();
            } else {
                realm = new Realm();
                realm.setId(guildData.getRealmData().getId());
            }

            realm.setSlug(guildData.getRealmData().getSlug());
            realm.buildLocalizedModel(guildData.getRealmData().getLocalizedStringData());

            realm = this.realmRepository.save(realm);

            guild.setRealm(realm);

            guild.setUseApplication(true);

            guild = this.guildRepository.save(guild);

            this.fetchGuildRoster(guild);

        }

    }

    @Override
    public GuildDTO updateGuild() throws IOException, UnexpectedException {

        final GuildData guildData = this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName);

        final Optional<Guild> guildOptional = this.guildRepository.findById(guildData.getId());

        Guild guild = guildOptional.get();

        guild.setId(guildData.getId());
        
        final Faction faction = this.factionRepository.findByType(guildData.getFactionData().getType()).get();

        guild.setFaction(faction);
        guild.setName(guildData.getName());

        Realm realm;
        
        final Optional<Realm> realmOptional = this.realmRepository.findBySlug(guildData.getRealmData().getSlug());
        realm = realmOptional.get();

        realm.setSlug(guildData.getRealmData().getSlug());
        realm.buildLocalizedModel(guildData.getRealmData().getLocalizedStringData());

        realm = this.realmRepository.save(realm);

        guild = this.guildRepository.save(guild);

        final List<Character> characters = this.fetchGuildRoster(guild);

        final GuildDTO guildDTO = new GuildDTO();

        final List<CharacterSummaryDTO> characterSummaryDTOs = new ArrayList<>();
        CharacterSummaryDTO characterSummaryDTO;

        for(final Character character : characters){
            characterSummaryDTO = new CharacterSummaryDTO();
            characterSummaryDTO.build(character);
            characterSummaryDTOs.add(characterSummaryDTO);
        }

        final GuildSummaryDTO guildSummaryDTO = new GuildSummaryDTO();
        guildSummaryDTO.build(guild, guildData);

        guildDTO.setCharacterSummaryDTOs(characterSummaryDTOs);
        guildDTO.setGuildSummaryDTO(guildSummaryDTO);

        return guildDTO;

    }

    @Override
    public List<CharacterSummaryDTO> getGuildRoster() throws IOException {

        final GuildData guildData = this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName);

        final Optional<Guild> guildOptional = this.guildRepository.findById(guildData.getId());

        final Guild guild = guildOptional.get();

        final List<Character> characters = guild.getCharacterList();

        final List<CharacterSummaryDTO> characterSummaryDTOs = new ArrayList<>();
        CharacterSummaryDTO characterSummaryDTO;

        for(final Character character : characters){
            characterSummaryDTO = new CharacterSummaryDTO();
            characterSummaryDTO.build(character);
            characterSummaryDTOs.add(characterSummaryDTO);
        }

        return characterSummaryDTOs;

    }

    @Override
    public GuildSummaryDTO getGuildInformations() throws IOException {
        
        final GuildData guildData = this.blizzardAPIHelper.getGuildData(this.realmSlug, this.guildName);

        final Optional<Guild> guildOptional = this.guildRepository.findById(guildData.getId());

        final Guild guild = guildOptional.get();

        final GuildSummaryDTO guildSummaryDTO = new GuildSummaryDTO();
        guildSummaryDTO.build(guild, guildData);

        return guildSummaryDTO;
    }

    private List<Character> fetchGuildRoster(final Guild guild) throws IOException, UnexpectedException {

        final GuildRosterIndexData guildRosterIndexData = this.blizzardAPIHelper.getGuildRosterIndexData(guild.getRealm().getSlug(), guild.getName());
        final List<GuildMemberIndexData> guildMemberIndexDatas = guildRosterIndexData.getGuildMemberIndexDataList();
        final List<Character> characters = new ArrayList<>();

        for(final GuildMemberIndexData guildMemberIndexData : guildMemberIndexDatas) {

            final Integer memberRank = guildMemberIndexData.getRank();
            final GuildRank guildRank = this.updateGuildRank(memberRank);

            Integer indexTry = 0;
            CharacterData characterData = new CharacterData();
            Boolean isDownloaded = false;

            do {
                try{
                    characterData = this.blizzardAPIHelper.getCharacterData(guildMemberIndexData);
                    isDownloaded = true;
                } catch (HttpServerErrorException e) {
                    indexTry++;
                    if(indexTry >= TRY_FETCH_CHARACTER){
                        throw new UnexpectedException(e);
                    }
                }
            } while (isDownloaded == false);
            
            final Optional<Character> optionalCharacter = this.characterRepository.findById(characterData.getId());

            final Character character;
    
            if(optionalCharacter.isPresent()){
                character = optionalCharacter.get();
            } else {
                character = new Character();
                character.setId(characterData.getId());
            }

            character.setGuildRank(guildRank);
    
            character.setLevel(characterData.getLevel());
            character.setName(characterData.getName());
            character.setAverageItemLevel(characterData.getAverageItemLevel());
            character.setEquippedItemLevel(characterData.getEquippedItemLevel());
            character.setLastLoginTimestamp(characterData.getLastLoginTimestamp());
    
            character.setRealm(guild.getRealm());
    
            final Faction faction = this.factionRepository.findByType(characterData.getFactionData().getType()).get();
    
            character.setFaction(faction);
    
            final PlayableClass playableClass =
                    this.playableClassRepository.findById(characterData.getClassIndexData().getId()).get();
    
            character.setPlayableClass(playableClass);
    
            final PlayableRace playableRace =
                    this.playableRaceRepository.findById(characterData.getRaceIndexData().getId()).get();
    
            if(characterData.getCovenantProgressData() != null){
                final Covenant covenant = this.covenantRepository.findById(characterData.getCovenantProgressData().getChosenCovenantData().getId()).get();
                character.setCovenant(covenant);
    
                character.setRenownLevel(characterData.getCovenantProgressData().getRenownLevel());
            }
            
            character.setPlayableRace(playableRace);
    
            character.setGuild(guild);

            characters.add(character);

            this.characterRepository.save(character);
        }

        return characters;

    }

    private GuildRank updateGuildRank(final Integer indexGuildRank) {
        GuildRank guildRank;

        final Optional<GuildRank> guildRankOptional = this.guildRankRepository.findByRank(indexGuildRank);

        if(guildRankOptional.isPresent()){
            guildRank = guildRankOptional.get();
        } else {
            guildRank = new GuildRank();
            guildRank.setName("RANK_" + indexGuildRank);
            guildRank.setRank(indexGuildRank);
            guildRank = this.guildRankRepository.save(guildRank);
        }

        return guildRank;
    }
}