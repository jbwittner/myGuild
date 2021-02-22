package fr.opendoha.myguild.server.service.implementation;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.dto.CharacterSummaryDTO;
import fr.opendoha.myguild.server.exception.CharacterNotExistedException;
import fr.opendoha.myguild.server.exception.IsNotCharacterAccountException;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.*;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.parameters.FavoriteCharacterParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.CharacterService;
import fr.opendoha.myguild.server.tools.api.BlizzardAPIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.*;

/**
 * Service to manage the characters
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    protected final Logger logger = LoggerFactory.getLogger(CharacterService.class);

    protected UserAccountRepository userAccountRepository;
    protected GuildRepository guildRepository;
    protected CharacterRepository characterRepository;
    protected RealmRepository realmRepository;
    protected FactionRepository factionRepository;
    protected PlayableRaceRepository playableRaceRepository;
    protected PlayableClassRepository playableClassRepository;
    protected SpecializationRoleRepository specializationRoleRepository;
    protected CovenantRepository covenantRepository;
    protected BlizzardAPIHelper blizzardAPIHelper;

    /**
     * Constructor
     */
    @Autowired
    public CharacterServiceImpl(
            final UserAccountRepository userAccountRepository,
            final CharacterRepository characterRepository,
            final GuildRepository guildRepository,
            final RealmRepository realmRepository,
            final FactionRepository factionRepository,
            final PlayableRaceRepository playableRaceRepository,
            final PlayableClassRepository playableClassRepository,
            final CovenantRepository covenantRepository,
            final BlizzardAPIHelper blizzardAPIHelper
    ) {
        this.userAccountRepository = userAccountRepository;
        this.characterRepository = characterRepository;
        this.guildRepository = guildRepository;
        this.realmRepository = realmRepository;
        this.factionRepository = factionRepository;
        this.playableRaceRepository = playableRaceRepository;
        this.playableClassRepository = playableClassRepository;
        this.covenantRepository = covenantRepository;
        this.blizzardAPIHelper = blizzardAPIHelper;
    }

    @Override
    public List<CharacterSummaryDTO> fetchCharacterAccount(final BlizzardAccountParameter blizzardAccountParameter)
            throws IOException {

        final UserAccount userAccount =
                this.userAccountRepository.findByBlizzardId(blizzardAccountParameter.getBlizzardId());

        final List<Character> characters = new ArrayList<>();

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData =
                this.blizzardAPIHelper.getAccountProfileSummaryBlizzardData(blizzardAccountParameter);

        for(final WowAccountData wowAccountData : accountProfileSummaryBlizzardData.getWowAccountsData()){
            for(final CharacterSummaryData characterSummaryData : wowAccountData.getCharacterSummaryData()){
                try{
                    final Character character = this.fetchCharacterFromAccount(characterSummaryData, userAccount);
                    characters.add(character);
                } catch (HttpClientErrorException e){
                    this.logger.debug(e.getMessage());
                }
            }
        }

        final List<CharacterSummaryDTO> characterDTOs = new ArrayList<>();
        
        CharacterSummaryDTO characterDTO;

        for(final Character character : characters){
            characterDTO = new CharacterSummaryDTO();
            characterDTO.build(character);
            characterDTOs.add(characterDTO);
        }

        return characterDTOs;
    }

    @Override
    public List<CharacterSummaryDTO> getCharacterAccount(final BlizzardAccountParameter blizzardAccountParameter)
            throws IOException {

        final UserAccount userAccount =
                this.userAccountRepository.findByBlizzardId(blizzardAccountParameter.getBlizzardId());

        final List<Character> characters = this.characterRepository.findByUserAccount(userAccount);

        final List<CharacterSummaryDTO> characterDTOs = new ArrayList<>();
        
        CharacterSummaryDTO characterDTO;

        for(final Character character : characters){
            characterDTO = new CharacterSummaryDTO();
            characterDTO.build(character);
            characterDTOs.add(characterDTO);
        }

        return characterDTOs;
    }

    private Character fetchCharacterFromAccount(final CharacterSummaryData characterSummaryData,
                                           final UserAccount userAccount) throws IOException {

        final CharacterData characterData = this.blizzardAPIHelper.getCharacterData(characterSummaryData);

        Character character = this.updateCharacter(characterData);
        character.setUserAccount(userAccount);

        character = this.characterRepository.save(character);
            
        return character;
    }

    private Character updateCharacter(final CharacterData characterData) throws HttpClientErrorException, IOException {
        
        final Optional<Character> optionalCharacter = this.characterRepository.findById(characterData.getId());

        final Character character;

        if(optionalCharacter.isPresent()){
            character = optionalCharacter.get();
        } else {
            character = new Character();
            character.setId(characterData.getId());
        }

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

        if(characterData.getCovenantProgressData() != null){
            final Covenant covenant = this.covenantRepository.findById(characterData.getCovenantProgressData().getChosenCovenantData().getId()).get();
            character.setCovenant(covenant);

            character.setRenownLevel(characterData.getCovenantProgressData().getRenownLevel());
        }
        
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

            guild.setName(guildIndexData.getName());

            guild = this.guildRepository.save(guild);
        }

        return guild;

    }

    @Override
    public void setFavoriteCharacter(final FavoriteCharacterParameter favoriteCharacterParameter)
            throws CharacterNotExistedException, IsNotCharacterAccountException {

        final Optional<Character> optionalCharacter = this.characterRepository.findById(favoriteCharacterParameter.getId());

        if(optionalCharacter.isPresent()){

            final Character character = optionalCharacter.get();

            final UserAccount userAccount = character.getUserAccount();

            if(userAccount == null){
                throw new IsNotCharacterAccountException();
            } else {
                if(userAccount.getBlizzardId().equals(favoriteCharacterParameter.getBlizzardId())){
                    character.setFavorite(favoriteCharacterParameter.getIsFavorite());
                    this.characterRepository.save(character);
                } else {
                    throw new IsNotCharacterAccountException();
                }
            }

        } else {
            throw new CharacterNotExistedException(favoriteCharacterParameter.getId());
        }

    }

}