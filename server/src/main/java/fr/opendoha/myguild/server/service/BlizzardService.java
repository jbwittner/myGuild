package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.Realm;
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

import java.util.Optional;

/**
 * Service to manage the blizzard game data
 */
@Service
@Transactional
public class BlizzardService implements IBlizzardService {

    protected final Logger logger = LoggerFactory.getLogger(BlizzardService.class);

    @Value("${application.blizzard.wow.profile.base-uri}")
    protected String baseUriProfile;

    @Value("${application.blizzard.wow.profile.namespace}")
    protected String namespaceProfile;

    protected final OAuth2FlowHandler oAuth2FlowHandler;
    protected final UserAccountRepository userAccountRepository;
    protected final GuildRepository guildRepository;
    protected final CharacterRepository characterRepository;
    protected final GuildRankRepository guildRankRepository;
    protected final RealmRepository realmRepository;
    protected final FactionRepository factionRepository;
    protected final HttpHelper httpHelper;

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
            final HttpHelper httpHelper
    ) {

        this.oAuth2FlowHandler = oAuth2FlowHandler;
        this.userAccountRepository = userAccountRepository;
        this.characterRepository = characterRepository;
        this.guildRepository = guildRepository;
        this.guildRankRepository = guildRankRepository;
        this.realmRepository = realmRepository;
        this.factionRepository = factionRepository;
        this.httpHelper = httpHelper;
    }

    @Override
    public void fetchAccountCharacter(final BlizzardAccountParameter blizzardAccountParameter){

        final String url = this.baseUriProfile + "/user/wow?namespace=" +
                this.namespaceProfile + "&access_token=" + blizzardAccountParameter.getToken();

        final UserAccount userAccount =
                this.userAccountRepository.findByBlizzardId(blizzardAccountParameter.getBlizzardId());

        final AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData =
                this.httpHelper.getForObject(url, AccountProfileSummaryBlizzardData.class);

        for(final WowAccountData wowAccountData : accountProfileSummaryBlizzardData.getWowAccountsData()){
            for(final CharacterSummaryData characterSummaryData : wowAccountData.getCharacterSummaryData()){
                this.fetchCharacterFromAccount(characterSummaryData, userAccount, blizzardAccountParameter.getToken());
            }
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
            character.setFaction(this.fetchFactionFromCharacter(characterData));

            character.setGuild(this.fetchGuildFromCharacter(characterData));

            this.characterRepository.save(character);

            this.logger.debug(character.toString());
        } catch (HttpClientErrorException e){
            this.logger.debug(e.getMessage());
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

        realm.updateLocalizedValue(realmData.getLocalizedStringData());
        realm.setSlug(realmData.getSlug());

        realm = this.realmRepository.save(realm);

        return realm;

    }

    private Faction fetchFactionFromCharacter(final CharacterData characterData){

        final FactionData factionData = characterData.getFactionData();

        final Optional<Faction> optionalFaction =
                this.factionRepository.findByType(characterData.getFactionData().getType());

        Faction faction = optionalFaction.orElseGet(Faction::new);

        faction.setIsUpdatedTrue();

        faction.updateLocalizedValue(factionData.getLocalizedStringData());
        faction.setType(factionData.getType());

        faction = this.factionRepository.save(faction);

        return faction;

    }

}