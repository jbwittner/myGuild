package fr.opendoha.myguild.server.testhelper;

import org.apache.commons.lang3.RandomStringUtils;

import fr.opendoha.myguild.server.data.blizzardgamedata.CharacterSummaryData;
import fr.opendoha.myguild.server.data.blizzardgamedata.LocalizedStringData;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.Character;
import fr.opendoha.myguild.server.model.blizzard.Faction;
import fr.opendoha.myguild.server.model.blizzard.Guild;
import fr.opendoha.myguild.server.model.blizzard.LocalizedModel;
import fr.opendoha.myguild.server.model.blizzard.Realm;
import fr.opendoha.myguild.server.parameters.BlizzardAccountParameter;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

/**
 * Factory to help during tests
 */
public class TestObjectFactory {

    public static final Integer NUMBER_MAX = 20_000_000;
    public static final Integer NUMBER_MAX_BLIZZARD_ID = 20_000_000;

    public static final int LENGTH_STANDARD = 30;
    public static final int LENGTH_URI = 15;
    public static final int LENGTH_EMAIL_NAME = 10;
    public static final int LENGTH_DOMAIN = 10;

    public static final int LENGTH_BATTLETAG = 15;
    public static final int LENGTH_NICKNAME = 10;
    public static final int LENGTH_TOKEN = 10;

    private List<String> listRandomString = new ArrayList<>();
    private List<String> listRandomEmail = new ArrayList<>();
    private List<Integer> listRandomNumber = new ArrayList<>();

    protected final Faker faker = new Faker();
    
    /**
     * Methode to reset all list of data
     */
    public void resetAllList(){
        
        this.listRandomString = new ArrayList<>();
        this.listRandomNumber = new ArrayList<>();
        this.listRandomEmail = new ArrayList<>();
    }

    /**
     * Method to get a random boolean
     */
    public Boolean getRandomBoolean(){

        return this.faker.random().nextBoolean();
    }

    /**
     * Method to get a unique random alphanumeric string
     * @param length of the string
     */
    public String getUniqueRandomAlphanumericString(final int length){

        boolean isNotUnique = true;
        String randomString = "";

        while (isNotUnique){
            randomString = RandomStringUtils.randomAlphanumeric(length);
            isNotUnique = listRandomString.contains(randomString);
        }

        listRandomString.add(randomString);

        return randomString;
    }

    /**
     * Method to get a unique random alphanumeric string
     */
    public String getUniqueRandomAlphanumericString(){

        boolean isNotUnique = true;
        String randomString = "";

        while (isNotUnique){
            randomString = this.getRandomAlphanumericString();
            isNotUnique = listRandomString.contains(randomString);
        }

        listRandomString.add(randomString);

        return randomString;
    }

    /**
     * Method to get a random alphanumeric string
     */
    public String getRandomAlphanumericString(){

        return RandomStringUtils.randomAlphanumeric(LENGTH_STANDARD);
    }

    /**
     * Method to get a unique random URI
     */
    public String getUniqueRandomURI(){

        boolean isNotUnique = true;
        String randomUri = "";

        while (isNotUnique){
            randomUri = "http://" + RandomStringUtils.randomAlphanumeric(LENGTH_URI) + ".com";
            isNotUnique = listRandomString.contains(randomUri);
        }

        listRandomString.add(randomUri);

        return randomUri;
    }

    /**
     * Method to get a unique random email address
     */
    public String getUniqueRandomEmail(){
        boolean isNotUnique = true;
        String email = "";

        while (isNotUnique){
            email = RandomStringUtils.randomAlphabetic(LENGTH_EMAIL_NAME);
            email = email + "@" + RandomStringUtils.randomAlphabetic(LENGTH_DOMAIN) + ".com";
            isNotUnique = listRandomEmail.contains(email);
        }

        listRandomEmail.add(email);

        return email;

    }

    /**
     * Method to get a unique random Integer between two values
     * @param min value
     * @param max value
     */
    public Integer getUniqueRandomInteger(final Integer min, final Integer max){
        boolean isNotUnique = true;
        Integer randomNumber = 0;

        while (isNotUnique){
            randomNumber = this.getRandomInteger(min, max);
            isNotUnique = listRandomNumber.contains(randomNumber);
        }

        listRandomNumber.add(randomNumber);

        return randomNumber;
    }

    /**
     * Method to get a unique random Integer between 0 and max
     * @param max value
     */
    public Integer getUniqueRandomInteger(final Integer max){
        boolean isNotUnique = true;
        Integer randomNumber = 0;

        while (isNotUnique){
            randomNumber = this.getRandomInteger(max);
            isNotUnique = listRandomNumber.contains(randomNumber);
        }

        listRandomNumber.add(randomNumber);

        return randomNumber;
    }

    /**
     * Method to get a unique random Integer between 0 and NUMBER_MAX
     */
    public Integer getUniqueRandomInteger(){
        return this.getUniqueRandomInteger(NUMBER_MAX);
    }

    /**
     * Method to get a random Integer between 0 and max
     */
    public int getRandomInteger(final Integer max){
        final double random = Math.random() * max;
        return (int) random;
    }

    /**
     * Method to get a random Long between 0 and max
     */
    public long getRandomLong(final Integer max){
        final double random = Math.random() * max;
        return (long) random;
    }

    /**
     * Method to get a random Integer between 0 and NUMBER_MAX
     */
    public Integer getRandomInteger(){
        return this.getRandomInteger(NUMBER_MAX);
    }

    /**
     * Method to get a random Integer between 0 and NUMBER_MAX
     */
    public Long getRandomLong(){
        return this.getRandomLong(NUMBER_MAX);
    }

    /**
     * Method to get a random Integer between two values
     */
    public Integer getRandomInteger(final Integer min, final Integer max){
        return (int) (min + (Math.random() * (max - min)));
    }

    /**
     * Method to get a blizzardAccountParameter
     */
    public BlizzardAccountParameter getBlizzardAccountParameter(final UserAccount userAccount){
        final String token = this.getUniqueRandomAlphanumericString();

        final BlizzardAccountParameter blizzardAccountParameter = new BlizzardAccountParameter();
        blizzardAccountParameter.setBlizzardId(userAccount.getBlizzardId());
        blizzardAccountParameter.setToken(token);
        return blizzardAccountParameter;
    }

    /**
     * Method to get a UserAccount
     */
    public UserAccount getUserAccount(final Integer blizzardId, final String battleTag, final String email, final String nickName){
        final UserAccount userAccount = new UserAccount();
        userAccount.setBlizzardId(blizzardId);
        userAccount.setBattleTag(battleTag);
        userAccount.setEmail(email);
        userAccount.setNickName(nickName);
        return userAccount;
    }

    /**
     * Method to get a UserAccount
     */
    public UserAccount getUserAccount(){
        final Integer blizzardId = this.getRandomInteger();
        final String battleTag = this.getRandomAlphanumericString();
        final String email = this.getRandomAlphanumericString();
        final String nickName = this.getRandomAlphanumericString();
        final UserAccount userAccount = this.getUserAccount(blizzardId, battleTag, email, nickName);
        return userAccount;
    }

    /**
     * Method to get a Character
     */
    public Character getCharacter(){
        final Character character = new Character();
        character.setName(this.getRandomAlphanumericString());
        character.setId(this.getUniqueRandomInteger());
        return character;
    }

    /**
     * Method to get a Character
     */
    public Character getCharacter(final UserAccount userAccount){
        final Character character = this.getCharacter();
        character.setUserAccount(userAccount);
        return character;
    }

    /**
     * Method to get a Character
     */
    public Character getCharacter(final UserAccount userAccount, final Guild guild){
        final Character character = this.getCharacter(userAccount);
        character.setGuild(guild);
        return character;
    }

    /**
     * Method to get a Guild
     */
    public final Guild getGuild(){
        final Guild guild = new Guild();
        guild.setId(this.getUniqueRandomInteger());
        guild.setName(this.getUniqueRandomAlphanumericString());
        return guild;
    }

    /**
     * Method to get a Guild
     */
    public Guild getGuild(final Faction faction, final Realm realm){
        final Guild guild = this.getGuild();
        guild.setFaction(faction);
        guild.setRealm(realm);
        return guild;
    }

    /**
     * Method to get a Realm
     */
    public Realm getRealm(){
        final Realm realm = new Realm();
        realm.setId(this.getUniqueRandomInteger());
        realm.setSlug(this.getUniqueRandomAlphanumericString());
        realm.setLocalizedModel(this.getLocalizedModel());
        return realm;
    }

    /**
     * Method to get a LocalizedStringData
     */
    public LocalizedStringData generateLocalizedStringData(){

        final LocalizedStringData localizedStringData = new LocalizedStringData();

        localizedStringData.setDeDE(this.getRandomAlphanumericString());
        localizedStringData.setEnGB(this.getRandomAlphanumericString());
        localizedStringData.setEnUS(this.getRandomAlphanumericString());
        localizedStringData.setEsES(this.getRandomAlphanumericString());
        localizedStringData.setEsMX(this.getRandomAlphanumericString());
        localizedStringData.setFrFR(this.getRandomAlphanumericString());
        localizedStringData.setItIT(this.getRandomAlphanumericString());
        localizedStringData.setKoKR(this.getRandomAlphanumericString());
        localizedStringData.setPtBR(this.getRandomAlphanumericString());
        localizedStringData.setRuRU(this.getRandomAlphanumericString());
        localizedStringData.setZhCN(this.getRandomAlphanumericString());
        localizedStringData.setZhTW(this.getRandomAlphanumericString());

        return localizedStringData;
    }

    /**
     * Method to get a LocalizedModel
     */
    protected LocalizedModel getLocalizedModel(){
        final LocalizedModel localizedModel = new LocalizedModel();
        localizedModel.setDeDE(this.getRandomAlphanumericString());
        localizedModel.setEnGB(this.getRandomAlphanumericString());
        localizedModel.setEnUS(this.getRandomAlphanumericString());
        localizedModel.setEsES(this.getRandomAlphanumericString());
        localizedModel.setEsMX(this.getRandomAlphanumericString());
        localizedModel.setFrFR(this.getRandomAlphanumericString());
        localizedModel.setItIT(this.getRandomAlphanumericString());
        localizedModel.setKoKR(this.getRandomAlphanumericString());
        localizedModel.setPtBR(this.getRandomAlphanumericString());
        localizedModel.setRuRU(this.getRandomAlphanumericString());
        localizedModel.setZhCN(this.getRandomAlphanumericString());
        localizedModel.setZhTW(this.getRandomAlphanumericString());
        return localizedModel;
    }

    /**
     * Method to get a CharacterSummaryData
     */
    public CharacterSummaryData getCharacterSummaryData(){
        final  CharacterSummaryData characterSummaryData = new CharacterSummaryData();

        return characterSummaryData;
    }


}