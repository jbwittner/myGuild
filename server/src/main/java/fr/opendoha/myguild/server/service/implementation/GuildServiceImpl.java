package fr.opendoha.myguild.server.service.implementation;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.dto.GuildDTO;
import fr.opendoha.myguild.server.dto.GuildSummaryDTO;
import fr.opendoha.myguild.server.exception.AreNotGuildMasterException;
import fr.opendoha.myguild.server.exception.GuildDoesNotUseApplication;
import fr.opendoha.myguild.server.exception.GuildNotExistedException;
import fr.opendoha.myguild.server.exception.UnexpectedException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.parameters.AddingGuildParameter;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.GuildService;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        this.blizzardAPIHelper = blizzardAPIHelper;
    }

    @Override
    public List<GuildSummaryDTO> getGuildFromAccount(final BlizzardAccountParameter blizzardAccountParameter)
            throws IOException {

        final UserAccount userAccount =
            this.userAccountRepository.findByBlizzardId(blizzardAccountParameter.getBlizzardId());

        final List<Character> characters = this.characterRepository.findByUserAccountAndGuildIsNotNull(userAccount);

        final List<Integer> guildIndex = new ArrayList<>();

        final List<GuildSummaryDTO> guildSummaryDTOs = new ArrayList<>();
        
        for(final Character character : characters){
            final Guild guild = character.getGuild();

            final Boolean containGuild = guildIndex.contains(guild.getId());

            if(!containGuild){
                guildIndex.add(guild.getId());
                final GuildSummaryDTO guildSummaryDTO = new GuildSummaryDTO();
                final GuildData guildData = this.blizzardAPIHelper.getGuildData(guild);
                guildSummaryDTO.build(guild, guildData);
                guildSummaryDTOs.add(guildSummaryDTO);
            }
        }

        return guildSummaryDTOs;

    }

    @Override
    public void addingGuild(final AddingGuildParameter addingGuildParameter) throws IOException, GuildNotExistedException,
            AreNotGuildMasterException, UnexpectedException {

        final UserAccount userAccount =
            this.userAccountRepository.findByBlizzardId(addingGuildParameter.getBlizzardId());

        final Optional<Guild> guildOptional = this.guildRepository.findById(addingGuildParameter.getGuildId());

        if(guildOptional.isEmpty()){
            throw new GuildNotExistedException(addingGuildParameter.getGuildId());
        }

        final Guild guild = guildOptional.get();

        this.checkAreGuildMaster(userAccount, guild);

        guild.setUseApplication(true);

        this.fetchGuildRoster(guild);

    }

    private void fetchGuildRoster(final Guild guild) throws IOException, UnexpectedException {

        final GuildRosterIndexData guildRosterIndexData = this.blizzardAPIHelper.getGuildRosterIndexData(guild);
        final List<GuildMemberIndexData> guildMemberIndexDatas = guildRosterIndexData.getGuildMemberIndexDataList();

        for(final GuildMemberIndexData guildMemberIndexData : guildMemberIndexDatas) {

            final Integer memberRank = guildMemberIndexData.getRank();
            final GuildRank guildRank = this.updateGuildRank(guild, memberRank);

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

            this.characterRepository.save(character);
        }

    }

    private GuildRank updateGuildRank(final Guild guild, final Integer indexGuildRank) {
        GuildRank guildRank;

        final Optional<GuildRank> guildRankOptional = this.guildRankRepository.findByRankAndGuild(indexGuildRank, guild);

        if(guildRankOptional.isPresent()){
            guildRank = guildRankOptional.get();
        } else {
            guildRank = new GuildRank();
            guildRank.setGuild(guild);
            guildRank.setName("RANK_" + indexGuildRank);
            guildRank.setRank(indexGuildRank);
            guildRank = this.guildRankRepository.save(guildRank);
        }

        return guildRank;
    }

    private void checkAreGuildMaster(final UserAccount userAccount, final Guild guild) throws IOException,
            AreNotGuildMasterException {
        Boolean areGuildMaster = false;

        final GuildRosterIndexData guildRosterIndexData = this.blizzardAPIHelper.getGuildRosterIndexData(guild);
        final List<GuildMemberIndexData> guildMemberIndexDatas = guildRosterIndexData.getGuildMemberIndexDataList();

        final GuildMemberIndexData guildMasterIndex = guildMemberIndexDatas.stream().filter(item -> RANK_GUILD_MASTER.equals(item.getRank())).findFirst().get();
        final GuildMemberData guildMasterData = guildMasterIndex.getGuildMemberData();

        final List<Character> characters = this.characterRepository.findByUserAccountAndGuild(userAccount, guild);

        for(final Character character : characters){
            final Integer characterId = character.getId();

            if(characterId.equals(guildMasterData.getId())){
                areGuildMaster = true;
                break;
            }
        }

        if(areGuildMaster == false){
            throw new AreNotGuildMasterException(guild.getName());
        }

    }

    private Guild guildCheck(final Integer guildId) throws GuildNotExistedException, GuildDoesNotUseApplication {

        final Optional<Guild> optionalGuild = this.guildRepository.findById(guildId);

        if(optionalGuild.isEmpty()){
            throw new GuildNotExistedException(guildId);
        }

        final Guild guild = optionalGuild.get();

        if(guild.getUseApplication() == false){
            throw new GuildDoesNotUseApplication(guild);
        }

        return guild;

    }

    @Override
    public GuildDTO getGuild(final Integer guildId) throws GuildNotExistedException, GuildDoesNotUseApplication,
            IOException {
        final Guild guild = this.guildCheck(guildId);
        final GuildDTO guildDTO = new GuildDTO();

        final List<CharacterSummaryDTO> characterSummaryDTOs = this.getGuildMembers(guild);
        final GuildSummaryDTO guildSummaryDTO = this.getGuildInformations(guild);

        guildDTO.setCharacterSummaryDTOs(characterSummaryDTOs);
        guildDTO.setGuildSummaryDTO(guildSummaryDTO);

        return guildDTO;
    }

    private List<CharacterSummaryDTO> getGuildMembers(final Guild guild) throws GuildNotExistedException,
            GuildDoesNotUseApplication {

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

    private GuildSummaryDTO getGuildInformations(final Guild guild) throws GuildNotExistedException,
            GuildDoesNotUseApplication, IOException {

        final GuildSummaryDTO guildSummaryDTO = new GuildSummaryDTO();
        final GuildData guildData = this.blizzardAPIHelper.getGuildData(guild);
        guildSummaryDTO.build(guild, guildData);
        
        return guildSummaryDTO;

    }
}