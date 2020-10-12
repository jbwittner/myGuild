package fr.opendoha.myguild.server.service;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.AbstractBlizzardModel;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.CharacterRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRankRepository;
import fr.opendoha.myguild.server.repository.blizzard.GuildRepository;
import fr.opendoha.myguild.server.repository.blizzard.RealmRepository;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
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
            final HttpHelper httpHelper
    ) {

        this.oAuth2FlowHandler = oAuth2FlowHandler;
        this.userAccountRepository = userAccountRepository;
        this.characterRepository = characterRepository;
        this.guildRepository = guildRepository;
        this.guildRankRepository = guildRankRepository;
        this.realmRepository = realmRepository;
        this.httpHelper = httpHelper;
    }

    public void fetchAccountCharacter(final BlizzardAccountParameter blizzardAccountParameter){

        String url = this.baseUriProfile + "/user/wow?namespace=" + this.namespaceProfile + "&access_token=" + blizzardAccountParameter.getToken();

        UserAccount userAccount = this.userAccountRepository.findByBlizzardId(blizzardAccountParameter.getBlizzardId());

        AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData =
                this.httpHelper.getForObject(url, AccountProfileSummaryBlizzardData.class);

        for(WowAccountData wowAccountData : accountProfileSummaryBlizzardData.getWowAccountsData()){
            for(CharacterSummaryData characterSummaryData : wowAccountData.getCharacterSummaryData()){
                this.fetchCharacterFromAccount(characterSummaryData, userAccount, blizzardAccountParameter.getToken());
            }
        }
    }

    private void fetchCharacterFromAccount(final CharacterSummaryData characterSummaryData,
                                           final UserAccount userAccount,
                                           final String token){

        String url = characterSummaryData.getCharacterHrefData().getHref() + "&access_token=" + token;

        try{
            CharacterData characterData = this.httpHelper.getForObject(url, CharacterData.class);

            Character character;

            Optional<Character> optionalCharacter = this.characterRepository.findById(characterData.getId());

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

            Realm realm = this.fetchRealmFromCharacter(characterData);
            character.setRealm(realm);

            Guild guild = this.fetchGuildFromCharacter(characterData);
            character.setGuild(guild);

            this.characterRepository.save(character);

            System.out.println(character);
        } catch (HttpClientErrorException e){
            System.out.println(e);
        }

    }

    private Guild fetchGuildFromCharacter(final CharacterData characterData){
        Guild guild = null;

        GuildIndexData guildIndexData = characterData.getGuildIndexData();

        if(guildIndexData != null){

            Optional<Guild> optionalGuild = this.guildRepository.findById(guildIndexData.getId());

            if(optionalGuild.isPresent()){
                guild = optionalGuild.get();
            }else {
                guild = new Guild();
                guild.setId(guildIndexData.getId());
            }

            Optional<Realm> optionalRealm = this.realmRepository.findBySlug(characterData.getRealmData().getSlug());

            if(optionalRealm.isPresent()){
                Realm realm = optionalRealm.get();
                guild.setRealm(realm);
            }

            guild.setIsUpdatedTrue();

            guild.setName(guildIndexData.getName());

            guild = this.guildRepository.save(guild);
        }

        return guild;

    }

    private Realm fetchRealmFromCharacter(final CharacterData characterData){
        Realm realm = null;

        RealmData realmData = characterData.getRealmData();

        if(realmData != null){

            Optional<Realm> optionalRealm = this.realmRepository.findBySlug(characterData.getRealmData().getSlug());

            if(optionalRealm.isPresent()){
                realm = optionalRealm.get();
            }else {
                realm = new Realm();
            }

            realm.setIsUpdatedTrue();

            realm.updateLocalizedValue(realmData.getLocalizedStringData());
            realm.setSlug(realmData.getSlug());

            realm = this.realmRepository.save(realm);
        }

        return realm;

    }

}