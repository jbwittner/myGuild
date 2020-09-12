package fr.opendoha.myguild.server.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import fr.opendoha.myguild.server.dto.blizzard.LocalizedValuesDTO;
import fr.opendoha.myguild.server.dto.blizzard.PlayableClassDTO;
import fr.opendoha.myguild.server.dto.blizzard.PlayableSpecializationDTO;
import fr.opendoha.myguild.server.dto.blizzard.SpecializationRoleDTO;
import fr.opendoha.myguild.server.model.UserAccount;
import fr.opendoha.myguild.server.model.blizzard.LocalizedString;
import fr.opendoha.myguild.server.model.blizzard.PlayableClass;
import fr.opendoha.myguild.server.model.blizzard.PlayableSpecialization;
import fr.opendoha.myguild.server.model.blizzard.SpecializationRole;
import fr.opendoha.myguild.server.tools.oauth2.models.TokenResponse;

/**
 * Factory to help during tests
 */
public class TestObjectFactory {

    public static final Integer NUMBER_MAX = 2000;
    public static final Integer NUMBER_MAX_BLIZZARD_ID = 100_000_000;
    public static final Integer NUMBER_DURATION_ACCESS_TOKEN = 100_000;
    
    public static final int NUMBER_MIN_PLAYABLE_SPECIALIZATION = 1;
    public static final int NUMBER_MAX_PLAYABLE_SPECIALIZATION = 4;

    public static final int LENGTH_EMAIL_NAME = 20;
    public static final int LENGTH_DOMAINE = 10;
    public static final int LENGTH_NICKNAME = 10;
    public static final int LENGTH_BATTLETAG = 20;
    public static final int LENGTH_LOCALIZED_STRING = 20;
    public static final int LENGTH_SPECILIZATION_TYPE = 20;
    public static final int LENGTH_ACCESS_TOKEN = 20;
    public static final int LENGTH_TOKEN_TYPE = 10;
    public static final int LENGTH_URI = 10;

    private List<String> listRandomString = new ArrayList<>();
    private List<String> listRandomEmail = new ArrayList<>();
    private List<Integer> listRandomNumber = new ArrayList<>();

    /**
     * Method to call when we need to reset the list of random strings
     */
    public void resetListString(){
        this.listRandomString = new ArrayList<>();
    }

    /**
     * Method to call when we need to reset the list of random integer
     */
    public void resetListInteger(){
        this.listRandomNumber = new ArrayList<>();
    }

    /**
     * Method to call when we need to reset the list of random email
     */
    public void resetListEmail(){
        this.listRandomEmail = new ArrayList<>();
    }

    /**
     * Method to call when we need to reset all lists
     */
    public void resetAllList(){
        this.resetListInteger();
        this.resetListString();
        this.resetListEmail();
    }

    /**
     * Generate a random ascii string.
     * When a new string is generated.
     * It is stored on a list used to check that the same chain is not obtained after reuse
     * @param length Length of the string
     * @return String generated
     */
    public String getUniqueRandomAsciiString(final int length){
        boolean isNotUnique = true;
        String randomString = "";

        while (isNotUnique){
            randomString = RandomStringUtils.randomAscii(length);
            isNotUnique = listRandomString.contains(randomString);
        }

        listRandomString.add(randomString);

        return randomString;
    }

    /**
     * Generate a random ascii string.
     * @param length Length of the string
     * @return String generated
     */
    public String getRandomAsciiString(final int length){

        final String randomString = RandomStringUtils.randomAscii(length);

        return randomString;
    }

    /**
     * Generate a random Alphanumeric string.
     * @param length Length of the string
     * @return String generated
     */
    public String getRandomAlphanumericString(final int length){

        final String randomString = RandomStringUtils.randomAlphanumeric(length);

        return randomString;
    }

    /**
     * Generate a random email.
     * When a new email is generated.
     * It is stored on a list used to check that the same email is not obtained after reuse
     * @return email generated
     */
    public String getUniqueRandomEmail(){
        boolean isNotUnique = true;
        String email = "";

        while (isNotUnique){
            email = RandomStringUtils.randomAlphabetic(LENGTH_EMAIL_NAME);
            email = email + "@" + RandomStringUtils.randomAlphabetic(LENGTH_DOMAINE) + ".com";
            isNotUnique = listRandomEmail.contains(email);
        }

        listRandomEmail.add(email);

        return email;

    }

    /**
     * Generate a random integer.
     * When a new integer is generated.
     * It is stored on a list used to check that the integer chain is not obtained after reuse
     * @param min Min value of random integer
     * @param max Max value of random integer
     * @return integer generated
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
     * Generate a random integer.
     * When a new integer is generated.
     * It is stored on a list used to check that the integer chain is not obtained after reuse
     * @param max Max value of random integer
     * @return integer generated
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
     * Generate a random integer.
     * When a new integer is generated.
     * It is stored on a list used to check that the integer chain is not obtained after reuse
     * @return integer generated
     */
    public Integer getUniqueRandomInteger(){
        return this.getUniqueRandomInteger(NUMBER_MAX);
    }

    /**
     * Generate a random integer.
     * @param max Max value of random integer
     * @return integer generated
     */
    public int getRandomInteger(final Integer max){
        final double random = Math.random() * max;
        final Integer value = (int) random;
        return value;
    }

    /**
     * Generate a random integer.
     * @return integer generated
     */
    public Integer getRandomInteger(){
        return this.getRandomInteger(NUMBER_MAX);
    }

    /**
     * Generate a random integer.
     * @param min Min value of random integer
     * @param max Max value of random integer
     * @return integer generated
     */
    public Integer getRandomInteger(final Integer min, final Integer max){
        return (int) (min + (Math.random() * (max - min)));
    }

    /**
     * Generate a new user account.
     * @param blizzardId Blizzard Id of the account
     * @param battleTag BattleTag of the account
     * @param email email of the account
     * @param nickName nickName of the account
     * @return user account generated
     */
    public UserAccount createUserAccount(final Integer blizzardId, final String battleTag, final String email, final String nickName){
        
        final UserAccount userAccount = new UserAccount(blizzardId, battleTag, email, nickName);

        return userAccount;
    }

    /**
     * Generate a new user account with a random values.
     * @return user account generated
     */
    public UserAccount createUserAccount(){

        final String email = this.getUniqueRandomEmail();
        final String nickName = this.getUniqueRandomAsciiString(LENGTH_NICKNAME);
        final String battleTag = this.getUniqueRandomAsciiString(LENGTH_BATTLETAG);
        final Integer blizzardId = this.getRandomInteger(NUMBER_MAX_BLIZZARD_ID);
                
        final UserAccount userAccount = new UserAccount(blizzardId, battleTag, email, nickName);

        return userAccount;
    }

    /**
     * Generate a new localized string with a random values.
     * @return localized string generated
     */
    public LocalizedString createLocalizedString(){
        final LocalizedString localizedString = new LocalizedString();
        localizedString.setEn_US(this.getUniqueRandomAsciiString(LENGTH_LOCALIZED_STRING));
        localizedString.setFr_FR(this.getUniqueRandomAsciiString(LENGTH_LOCALIZED_STRING));

        return localizedString;
    }

    /**
     * Generate a new localized values DTO with a random values.
     * @return localized values generated
     */
    public LocalizedValuesDTO createLocalizedValuesDTO(){
        final LocalizedValuesDTO localizedValuesDTO = new LocalizedValuesDTO();
        localizedValuesDTO.setEn_US(this.getUniqueRandomAsciiString(LENGTH_LOCALIZED_STRING));
        localizedValuesDTO.setFr_FR(this.getUniqueRandomAsciiString(LENGTH_LOCALIZED_STRING));

        return localizedValuesDTO;
    }

    /**
     * Generate a new playable class DTO with a random values without a specialization.
     * @return playable class generated
     */
    public PlayableClassDTO createPlayableClassDTOWithoutPlayableSpecialization(){
                
        final PlayableClassDTO playableClassDTO = new PlayableClassDTO();

        playableClassDTO.setName(this.createLocalizedValuesDTO());
        playableClassDTO.setId(this.getUniqueRandomInteger());

        return playableClassDTO;
    }

    /**
     * Generate a new playable specialization DTO with a random values.
     * @return playable specialization generated
     */
    public PlayableSpecializationDTO createPlayableSpecializationDTOWithoutSpecializationRoleAndPlayableClass(){

        final PlayableSpecializationDTO playableSpecializationDTO = new PlayableSpecializationDTO();
        playableSpecializationDTO.setId(this.getUniqueRandomInteger());
        playableSpecializationDTO.setName(this.createLocalizedValuesDTO());
                
        return playableSpecializationDTO;
    }

    /**
     * Generate a new playable class with a random values with a specialization.
     * @return playable class generated
     */
    public PlayableClass createPlayableClassWithSpecialization(){
                
        final PlayableClass playableClass = new PlayableClass();

        playableClass.setNames(this.createLocalizedString());
        playableClass.setId(this.getUniqueRandomInteger());

        final int numberOfSpecializationRole = this.getRandomInteger(NUMBER_MIN_PLAYABLE_SPECIALIZATION, NUMBER_MAX_PLAYABLE_SPECIALIZATION);

        SpecializationRole specializationRole;
        PlayableSpecialization playableSpecialization;

        final List<PlayableSpecialization> playableSpecializations = new ArrayList<>();

        for(int index = 0 ; index <= numberOfSpecializationRole ; index++){
            specializationRole = this.createSpecilizationRoleWithoutPlayableSpecialization();
            playableSpecialization = new PlayableSpecialization();
            playableSpecialization.setId(this.getUniqueRandomInteger());
            playableSpecialization.setNames(this.createLocalizedString());
            playableSpecialization.setSpecializationRole(specializationRole);
            playableSpecializations.add(playableSpecialization);
        }

        playableClass.setPlayableSpecializations(playableSpecializations);

        return playableClass;
    }

    /**
     * Generate a new playable class with a random values without a specialization.
     * @return playable class generated
     */
    public PlayableClass createPlayableClassWithoutSpecialization(){
                
        final PlayableClass playableClass = new PlayableClass();

        playableClass.setNames(this.createLocalizedString());
        playableClass.setId(this.getUniqueRandomInteger());

        return playableClass;
    }

    /**
     * Generate a new specialization role with a random values.
     * @return specialization role generated
     */
    public SpecializationRole createSpecilizationRoleWithPlayableSpecialization(){

        final SpecializationRole specializationRole = new SpecializationRole();
        specializationRole.setId(this.getUniqueRandomInteger());
        specializationRole.setNames(this.createLocalizedString());
        specializationRole.setType(this.getUniqueRandomAsciiString(LENGTH_SPECILIZATION_TYPE));

        final int numberOfSpecializationRole = this.getRandomInteger(NUMBER_MIN_PLAYABLE_SPECIALIZATION, NUMBER_MAX_PLAYABLE_SPECIALIZATION);

        PlayableSpecialization playableSpecialization;

        final List<PlayableSpecialization> playableSpecializations = new ArrayList<>();

        for(int index = 0 ; index <= numberOfSpecializationRole ; index++){
            playableSpecialization = this.createPlayableSpecializationWithoutSpecializationRoleAndPlayableClass();
            playableSpecializations.add(playableSpecialization);
        }

        specializationRole.setPlayableSpecializations(playableSpecializations);
                
        return specializationRole;
    }

    /**
     * Generate a new specialization role DTO with a random values.
     * @return specialization role generated
     */
    public SpecializationRoleDTO createSpecilizationRoleDTOWithoutPlayableSpecialization(){

        final SpecializationRoleDTO specializationRoleDTO = new SpecializationRoleDTO();
        specializationRoleDTO.setName(this.createLocalizedValuesDTO());
        specializationRoleDTO.setType(this.getUniqueRandomAsciiString(LENGTH_SPECILIZATION_TYPE));
                
        return specializationRoleDTO;
    }

    /**
     * Generate a new specialization role with a random values.
     * @return specialization role generated
     */
    public SpecializationRole createSpecilizationRoleWithoutPlayableSpecialization(){

        final SpecializationRole specializationRole = new SpecializationRole();
        specializationRole.setId(this.getUniqueRandomInteger());
        specializationRole.setNames(this.createLocalizedString());
        specializationRole.setType(this.getUniqueRandomAsciiString(LENGTH_SPECILIZATION_TYPE));
                
        return specializationRole;
    }

    /**
     * Generate a new playable specialization with a random values.
     * @return playable specialization generated
     */
    public PlayableSpecialization createPlayableSpecializationWithSpecializationRole(){

        final PlayableSpecialization playableSpecialization = new PlayableSpecialization();
        playableSpecialization.setId(this.getUniqueRandomInteger());
        playableSpecialization.setNames(this.createLocalizedString());
        playableSpecialization.setPlayableClass(this.createPlayableClassWithoutSpecialization());
        playableSpecialization.setSpecializationRole(this.createSpecilizationRoleWithoutPlayableSpecialization());
                
        return playableSpecialization;
    }

    /**
     * Generate a new playable specialization with a random values.
     * @return playable specialization generated
     */
    public PlayableSpecialization createPlayableSpecializationWithoutSpecializationRole(){

        final PlayableSpecialization playableSpecialization = new PlayableSpecialization();
        playableSpecialization.setId(this.getUniqueRandomInteger());
        playableSpecialization.setNames(this.createLocalizedString());
        playableSpecialization.setPlayableClass(this.createPlayableClassWithoutSpecialization());
                
        return playableSpecialization;
    }

    /**
     * Generate a new playable specialization with a random values.
     * @return playable specialization generated
     */
    public PlayableSpecialization createPlayableSpecializationWithoutSpecializationRoleAndPlayableClass(){

        final PlayableSpecialization playableSpecialization = new PlayableSpecialization();
        playableSpecialization.setId(this.getUniqueRandomInteger());
        playableSpecialization.setNames(this.createLocalizedString());
                
        return playableSpecialization;
    }

    /**
     * Generate a new token response with a random values.
     * @return playable specialization generated
     */
    public TokenResponse createTokenResponse(){
        final TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccess_token(this.getUniqueRandomAsciiString(LENGTH_ACCESS_TOKEN));
        tokenResponse.setExpires_in((long) this.getUniqueRandomInteger(NUMBER_DURATION_ACCESS_TOKEN));
        tokenResponse.setToken_type(this.getUniqueRandomAsciiString(LENGTH_TOKEN_TYPE));
        return tokenResponse;
    }
}