package fr.opendoha.myguild.server.service.blizzardservice;

import fr.opendoha.myguild.server.data.blizzardgamedata.*;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;
import fr.opendoha.myguild.server.repository.UserAccountRepository;
import fr.opendoha.myguild.server.repository.blizzard.*;
import fr.opendoha.myguild.server.service.BlizzardService;
import fr.opendoha.myguild.server.testhelper.AbstractMotherIntegrationTest;
import fr.opendoha.myguild.server.testhelper.TestObjectFactory;
import fr.opendoha.myguild.server.tools.HttpHelper;
import fr.opendoha.myguild.server.tools.oauth2.OAuth2FlowHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class fetchAccountCharacterTest extends AbstractMotherIntegrationTest {

    @Value("${application.blizzard.wow.profile.base-uri}")
    protected String baseUriProfile;

    @Value("${application.blizzard.wow.profile.namespace}")
    protected String namespaceProfile;

    private UserAccount userAccount;

    private BlizzardService blizzardService;

    @Mock
    private HttpHelper httpHelper;

    @Autowired
    private OAuth2FlowHandler oAuth2FlowHandler;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private GuildRepository guildRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private GuildRankRepository guildRankRepository;

    @Autowired
    private RealmRepository realmRepository;

    @Autowired
    private FactionRepository factionRepository;

    private BlizzardAccountParameter blizzardAccountParameter;

    private static final int NUMBER_REALM = 4;
    private static final int NUMBER_FACTION = 2;
    private static final int NUMBER_GUILD = 2;
    private static final int NUMBER_CHARACTER = 100;

    private final List<RealmData> realmDataList = new ArrayList<>();
    private final List<FactionData> factionDataList = new ArrayList<>();
    private final List<GuildIndexData> guildIndexDataList = new ArrayList<>();
    private final List<CharacterData> characterDataList = new ArrayList<>();

    @Override
    protected void initDataBeforeEach() throws NoSuchFieldException {
        this.blizzardService = new BlizzardService(this.oAuth2FlowHandler, this.userAccountRepository,
                this.characterRepository, this.guildRepository, this.guildRankRepository, this.realmRepository,
                this.factionRepository, this.httpHelper);

        // Preparation of the account
        this.userAccount = new UserAccount();

        this.userAccount.setBattleTag(this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_BATTLETAG));
        this.userAccount.setNickName(this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_NICKNAME));
        this.userAccount.setBlizzardId(this.factory.getRandomInteger(TestObjectFactory.NUMBER_MAX_BLIZZARD_ID));
        this.userAccount.setEmail(this.factory.getUniqueRandomEmail());

        this.userAccountRepository.save(userAccount);

        // Creation of the parameter
        this.blizzardAccountParameter = new BlizzardAccountParameter();

        this.blizzardAccountParameter.setBlizzardId(this.userAccount.getBlizzardId());
        this.blizzardAccountParameter.setToken(
                this.factory.getUniqueRandomAlphanumericString(TestObjectFactory.LENGTH_TOCKEN));


        // Creation of realm list
        this.createListRealmData();
        this.createFactionData();
        this.creationGuild();

        String url;

        List<CharacterSummaryData> characterSummaryDataList = new ArrayList<>();

        for(int index = 0; index <= NUMBER_CHARACTER; index ++){

            url = this.factory.getUniqueRandomURI();

            CharacterSummaryData characterSummaryData = new CharacterSummaryData();

            HrefData hrefData = new HrefData();
            hrefData.setHref(url);

            CharacterData characterData = this.getCharacterData();

            characterSummaryData.setCharacterHrefData(hrefData);
            characterSummaryData.setId(characterData.getId());

            characterSummaryDataList.add(characterSummaryData);

            url = url + "&access_token=" + blizzardAccountParameter.getToken();

            if(index != NUMBER_CHARACTER){
                characterDataList.add(characterData);

                Mockito.when(this.httpHelper.getForObject(url, CharacterData.class))
                        .thenReturn(characterData);
            } else {
                Mockito.when(this.httpHelper.getForObject(url, CharacterData.class))
                        .thenThrow(HttpClientErrorException.class);
            }

        }

        WowAccountData wowAccountData = new WowAccountData();
        wowAccountData.setCharacterSummaryData(characterSummaryDataList);

        List<WowAccountData> wowAccountDataList = new ArrayList<>();
        wowAccountDataList.add(wowAccountData);

        AccountProfileSummaryBlizzardData accountProfileSummaryBlizzardData = new AccountProfileSummaryBlizzardData();
        accountProfileSummaryBlizzardData.setWowAccountsData(wowAccountDataList);

        FieldSetter.setField(this.blizzardService, this.blizzardService.getClass().getDeclaredField("baseUriProfile"), this.baseUriProfile);
        FieldSetter.setField(this.blizzardService, this.blizzardService.getClass().getDeclaredField("namespaceProfile"), this.namespaceProfile);

        // Mock of the get account
        url = this.baseUriProfile + "/user/wow?namespace=" +
                this.namespaceProfile + "&access_token=" + blizzardAccountParameter.getToken();

        Mockito.when(this.httpHelper.getForObject(url, AccountProfileSummaryBlizzardData.class))
                .thenReturn(accountProfileSummaryBlizzardData);

    }

    private void createListRealmData(){
        RealmData realmData;

        for(int index = 0; index < NUMBER_REALM; index++){
            realmData = new RealmData();

            realmData.setSlug(this.factory.getUniqueRandomAlphanumericString());

            HrefData hrefData = new HrefData();

            hrefData.setHref(this.factory.getUniqueRandomURI());
            realmData.setKeyHrefData(hrefData);

            realmData.setId(this.factory.getUniqueRandomInteger());

            LocalizedStringData localizedStringData = new LocalizedStringData();

            localizedStringData.setItIT(this.factory.getUniqueRandomAlphanumericString());
            localizedStringData.setFrFR(this.factory.getUniqueRandomAlphanumericString());
            localizedStringData.setEsES(this.factory.getUniqueRandomAlphanumericString());
            localizedStringData.setEnUS(this.factory.getUniqueRandomAlphanumericString());

            realmData.setLocalizedStringData(localizedStringData);

            this.realmDataList.add(realmData);
        }
    }

    private void createFactionData(){
        FactionData factionData;

        for(int index = 0; index < NUMBER_FACTION; index++){
            factionData = new FactionData();

            factionData.setType(this.factory.getUniqueRandomAlphanumericString());

            LocalizedStringData localizedStringData = new LocalizedStringData();

            localizedStringData.setItIT(this.factory.getUniqueRandomAlphanumericString());
            localizedStringData.setFrFR(this.factory.getUniqueRandomAlphanumericString());
            localizedStringData.setEsES(this.factory.getUniqueRandomAlphanumericString());
            localizedStringData.setEnUS(this.factory.getUniqueRandomAlphanumericString());

            factionData.setLocalizedStringData(localizedStringData);

            this.factionDataList.add(factionData);
        }
    }

    private void creationGuild(){
        GuildIndexData guildIndexData;

        for(int index = 0; index < NUMBER_FACTION; index++){
            guildIndexData = new GuildIndexData();

            int indexRealmData = this.factory.getRandomInteger(NUMBER_REALM);
            RealmData realmData = this.realmDataList.get(indexRealmData);

            guildIndexData.setRealmData(realmData);

            guildIndexData.setName(this.factory.getUniqueRandomAlphanumericString());

            HrefData hrefData = new HrefData();
            hrefData.setHref(this.factory.getUniqueRandomURI());
            guildIndexData.setKeyHrefData(hrefData);

            guildIndexData.setId(this.factory.getUniqueRandomInteger());

            int indexFactionData = this.factory.getRandomInteger(NUMBER_FACTION);
            FactionData factionData = this.factionDataList.get(indexFactionData);
            guildIndexData.setFactionData(factionData);

            this.guildIndexDataList.add(guildIndexData);
        }
    }

    private CharacterData getCharacterData(){
        CharacterData characterData = new CharacterData();
        characterData.setLevel(this.factory.getUniqueRandomInteger());

        int index;

        if(this.factory.getRandomBoolean()){
            index = this.factory.getRandomInteger(NUMBER_GUILD);
            GuildIndexData guildIndexData = this.guildIndexDataList.get(index);
            characterData.setGuildIndexData(guildIndexData);

            characterData.setFactionData(guildIndexData.getFactionData());
            characterData.setRealmData(guildIndexData.getRealmData());
        }else{
            index = this.factory.getRandomInteger(NUMBER_FACTION);
            FactionData factionData = this.factionDataList.get(index);
            characterData.setFactionData(factionData);

            index = this.factory.getRandomInteger(NUMBER_REALM);
            RealmData realmData = this.realmDataList.get(index);
            characterData.setRealmData(realmData);
        }

        characterData.setId(this.factory.getUniqueRandomInteger());

        characterData.setName(this.factory.getUniqueRandomAlphanumericString());

        characterData.setAverageItemLevel(this.factory.getUniqueRandomInteger());
        characterData.setEquippedItemLevel(this.factory.getUniqueRandomInteger());
        characterData.setLastLoginTimestamp(Long.valueOf(this.factory.getUniqueRandomInteger()));


        return characterData;
    }

    private void checkData(){

        // Check guilds
        Assertions.assertEquals(NUMBER_GUILD, this.guildRepository.count());

        for(GuildIndexData guildIndexData : this.guildIndexDataList){

            Optional<Guild> optionalGuild = this.guildRepository.findById(guildIndexData.getId());

            Assertions.assertTrue(optionalGuild.isPresent());

            Guild guild = optionalGuild.get();

            Assertions.assertEquals(guildIndexData.getId(), guild.getId());
            Assertions.assertEquals(guildIndexData.getName(), guild.getName());
            Assertions.assertEquals(guildIndexData.getRealmData().getId(), guild.getRealm().getId());
            Assertions.assertEquals(guildIndexData.getFactionData().getType(), guild.getFaction().getType());
        }

        // Check factions
        Assertions.assertEquals(NUMBER_FACTION, this.factionRepository.count());

        for(FactionData factionData : this.factionDataList){

            Optional<Faction> optionalFaction = this.factionRepository.findByType(factionData.getType());

            Assertions.assertTrue(optionalFaction.isPresent());

            Faction faction = optionalFaction.get();

            Assertions.assertEquals(factionData.getType(), faction.getType());
        }

        // Check realms
        Assertions.assertEquals(NUMBER_REALM, this.realmRepository.count());

        for(RealmData realmData : this.realmDataList){

            Optional<Realm> optionalRealm = this.realmRepository.findBySlug(realmData.getSlug());

            Assertions.assertTrue(optionalRealm.isPresent());

            Realm realm = optionalRealm.get();

            Assertions.assertEquals(realmData.getId(), realm.getId());
            Assertions.assertEquals(realmData.getSlug(), realm.getSlug());
        }

        // Check characters
        Assertions.assertEquals(NUMBER_CHARACTER, this.characterRepository.count());

        for(CharacterData characterData : this.characterDataList){

            Optional<Character> optionalCharacter = this.characterRepository.findById(characterData.getId());

            Assertions.assertTrue(optionalCharacter.isPresent());

            Character character = optionalCharacter.get();

            Assertions.assertNull(character.getGuildRank());

            if(character.getGuild() != null){
                Assertions.assertEquals(characterData.getGuildIndexData().getId() ,character.getGuild().getId());
            }else {
                Assertions.assertNull(character.getGuild());
            }

            Assertions.assertEquals(characterData.getRealmData().getId(), character.getRealm().getId());
            Assertions.assertEquals(this.userAccount.getBlizzardId(), character.getUserAccount().getBlizzardId());
            Assertions.assertEquals(characterData.getName(), character.getName());
            Assertions.assertEquals(characterData.getAverageItemLevel(), character.getAverageItemLevel());
            Assertions.assertEquals(characterData.getEquippedItemLevel(), character.getEquippedItemLevel());
            Assertions.assertEquals(characterData.getLastLoginTimestamp(), character.getLastLoginTimestamp());
            Assertions.assertEquals(characterData.getLevel(), character.getLevel());
            Assertions.assertEquals(characterData.getFactionData().getType(), character.getFaction().getType());

        }

    }

    @Test
    public void TestOneFetch(){

        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);

        this.checkData();

    }

    @Test
    public void TestTwoFetch(){

        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);
        this.blizzardService.fetchAccountCharacter(this.blizzardAccountParameter);

        this.checkData();

    }

}
