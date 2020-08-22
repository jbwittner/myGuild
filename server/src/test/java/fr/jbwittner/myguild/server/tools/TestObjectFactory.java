package fr.jbwittner.myguild.server.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import fr.jbwittner.myguild.server.model.UserAccount;

/**
 * Factory to help during tests
 */
public class TestObjectFactory {

    public static final Integer NUMBER_MAX = 200;
    public static final Integer NUMBER_MAX_BLIZZARD_ID = 100_000_000;

    public static final int LENGTH_EMAIL_NAME = 20;
    public static final int LENGTH_DOMAINE = 10;
    public static final int LENGTH_NICKNAME = 10;
    public static final int LENGTH_BATTLETAG = 20;

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

}