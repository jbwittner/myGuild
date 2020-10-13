package fr.opendoha.myguild.server.testhelper;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Factory to help during tests
 */
public class TestObjectFactory {

    public static final Integer NUMBER_MAX = 20_000;
    public static final Integer NUMBER_MAX_BLIZZARD_ID = 20_000_000;

    public static final int LENGTH_STANDARD = 20;
    public static final int LENGTH_URI = 15;
    public static final int LENGTH_EMAIL_NAME = 10;
    public static final int LENGTH_DOMAIN = 10;

    public static final int LENGTH_BATTLETAG = 15;
    public static final int LENGTH_NICKNAME = 10;
    public static final int LENGTH_TOKEN = 10;

    private List<String> listRandomString = new ArrayList<>();
    private List<String> listRandomEmail = new ArrayList<>();
    private List<Integer> listRandomNumber = new ArrayList<>();

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

        final Random rd = new Random();

        return rd.nextBoolean();
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
            randomString = RandomStringUtils.randomAlphanumeric(LENGTH_STANDARD);
            isNotUnique = listRandomString.contains(randomString);
        }

        listRandomString.add(randomString);

        return randomString;
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
     * Method to get a random Integer between 0 and NUMBER_MAX
     */
    public Integer getRandomInteger(){
        return this.getRandomInteger(NUMBER_MAX);
    }

    /**
     * Method to get a random Integer between two values
     */
    public Integer getRandomInteger(final Integer min, final Integer max){
        return (int) (min + (Math.random() * (max - min)));
    }

}