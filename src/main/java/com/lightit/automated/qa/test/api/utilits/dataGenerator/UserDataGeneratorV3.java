package com.lightit.automated.qa.test.api.utilits.dataGenerator;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Random;

public class UserDataGeneratorV3 {

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+=/";
    private static final String HEX_STRING = "ABCDEF0123456789";


    private static Faker faker = new Faker();

    /**
     * Generate randomize letters
     * @param len length string of letters which you want to get
     * @return a randomize string "rygf"
     */
    public static String genRandomLetter(int len) {
        String s = "";
        for (int i = 1 ; i < len ; i++)
            s += (char)(Math.random() * ('z' - 'a' + 1) + 'a');
        return s;
    }

    /**
     * Generate faker first name
     * @return a randomize string "Jamienpb"
     */
    public static String getFakerFirstName() {
        return faker.name().firstName() + genRandomLetter(4);
    }

    /**
     * Generate faker last name
     * @return a randomize string "Jaskolski"
     */
    public static String getFakerLastName() {
        return faker.name().lastName() + genRandomLetter(6);
    }

    /**
     * Generate faker email address with valid format
     * @return a randomize string email "Joyegracie.hayes@yahoo.com"
     */
    public static String getFakerEmailAddress() {
        return faker.name().firstName() + faker.internet().emailAddress();
    }

    /**
     * Generate faker email address without at symbol
     * @return a randomize string email "agustin.handgmail.com"
     */
    public static String getFakerInvalidEmailAddressWithoutDog() {
        return faker.internet().emailAddress().replace("@", "");
    }

    /**
     * Generate faker email address without dot symbol
     * @return a randomize string email "marishasipes@hotmailcom"
     */
    public static String getFakerInvalidEmailAddressWithoutCom() {
        return faker.internet().emailAddress().replace(".", "");
    }

    /**
     * Generate faker date of birthday with time
     * @return a randomize string date "Fri Feb 15 05:14:21 MSK 1985"
     */
    public static String getFakerDateOfBirthString() {
        return faker.date().birthday().toString();
    }

    /**
     * Generate faker date of birthday in format year-month-day
     * @return a randomize string date "1960-09-24"
     */
    public static String getFakerDateOfBirthString1() {
        return getFakerRandomIntNumberBetween(1960, 2019) + "-0" +
                getFakerRandomIntNumberBetween(1, 9) + "-" +
                getFakerRandomIntNumberBetween(0, 3) +
                getFakerRandomIntNumberBetween(1, 9);
    }

    /**
     * Generate faker date of birthday with time, used Date class
     * @return a randomize date "Mon Jan 18 07:27:44 MSK 1993"
     */
    public static Date getFakerDateOfBirthDate() {
        return faker.date().birthday();
    }

    /**
     * Generate faker card holder name
     * @return a randomize string "Lead Communications Manager"
     */
    public static String getFakerCardHolderName() {
        return faker.name().title();
    }

    /**
     * Generate faker summary
     * @return a randomize string "Xenophilius Lovegood"
     */
    public static String getFakerSummary() {
        return faker.harryPotter().character();
    }

    /**
     * Generate faker street address
     * @return a randomize string "0158 Anderson Place"
     */
    public static String getFakerAddress() {
        return faker.address().streetAddress();
    }

    /**
     * Generate faker job
     * @return a randomize string "Regional Farming Administratorfci"
     */
    public static String getFakerJob() {
        return faker.job().title() + genRandomLetter(4);
    }

    /**
     * Generate faker postal code
     * @return a randomize string "A8B 5C4"
     */
    public static String getFakerPostalCode() {
        return "A" + faker.number().digits(1) + "B" + " " + faker.number().digits(1) + "C" +
                faker.number().digits(1);
    }

    /**
     * Generate faker city
     * @return a randomize string "Wilkinsonview"
     */
    public static String getFakerCity() {
        return faker.address().city();
    }

    /**
     * Generate faker state
     * @return a randomize string "Louisiana"
     */
    public static String getFakerStateProvance() {
        return faker.address().state();
    }

    /**
     * Generate faker invalid email address without at and dot symbols
     * @return a randomize string "julianemooreyahoocom"
     */
    public static String getFakerInvalidEmailAddressWithoutDogAndCom() {
        return faker.internet().emailAddress().replace("@", "").replace(".", "");
    }

    /**
     * Generate faker username
     * @return a randomize string "gradyboehmiytu"
     */
    public static String getFakerUserName() {
        return faker.name().username().replace(".", "") +genRandomLetter(3);
    }

    /**
     * Generate faker some sentence
     * @return a randomize string "When Chuck Norris presses Ctrl+Alt+Delete, worldwide computer restart is initiated."
     */
    public static String getChuckNorrisFact() {
        return faker.chuckNorris().fact();
    }

    /**
     * Generate faker some sentence
     * @return a randomize string "Hmm. In the end, cowards are those who follow the dark side."
     */
    public static String getYodaQuote() {
        return faker.yoda().quote();
    }

    /**
     * Generate faker company name
     * @return a randomize string "Hessel Groupgmy"
     */
    public static String getCompanyName() {
        return faker.company().name() + genRandomLetter(4);
    }

    /**
     * Generate faker industry name
     * @return a randomize string "Design"
     */
    public static String getIndustryName() {
        return faker.company().industry();
    }

    /**
     * Generate faker password (string)
     * @param length length string of digits which you want to get
     * @return a randomize string "3361332211"
     */
    public static String getFakerPasswordByLength(int length) {
        return faker.number().digits(length);
    }

    /**
     * Generate faker randomize number (string)
     * @param length length string of digits which you want to get
     * @return a randomize string "1113167882"
     */
    public static String getFakerRandomNumberString(int length) {
        String number = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            number += String.valueOf(rand.nextInt(8) + 1);
        }
        return number;
    }

    /**
     * Generate faker randomize number (int)
     * @param length length string of digits which you want to get
     * @return a randomize int "1113167882"
     */
    public static int getFakerRandomNumberInt(int length) {
        String number = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            number += String.valueOf(rand.nextInt(8) + 1);
        }
        return Integer.parseInt(number);
    }

    public static String getFakerRandomNumber(int length){
        String number = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            number += String.valueOf(rand.nextInt(8) + 1);
        }
        return number;
    }

    /**
     * Generate faker randomize number with setting the minimum and maximum limits of values (int)
     * @param min minimum limit of number which you want to get
     * @param max maximum limit of number which you want to get
     * @return a randomize string "1384"
     */
    public static int getFakerRandomIntNumberBetween(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    /**
     * Applied in random getTimezone
     * Generate faker randomize number with setting the minimum and maximum limits of values (int)
     * @param max maximum limit of number which you want to get
     * @return a randomize string "1384"
     */
    public static int getFakerRandomIntNumberBetweenSelectTimezone(int max) {
        return faker.number().numberBetween(0, max);
    }

    /**
     * Generate faker randomize digit (int)
     * @return a randomize string "8"
     */
    public static int getFakerRanddomDigit() {
        return faker.number().randomDigit();
    }

    /**
     * Generate faker randomize number with setting the minimum and maximum limits of values (String)
     * @param min minimum limit of number which you want to get
     * @param max maximum limit of number which you want to get
     * @return a randomize string "1588"
     */
    public static String getFakerRandomStringNumberBetween(int min, int max) {
        return String.valueOf(faker.number().numberBetween(min, max));
    }

    /**
     * Generate faker randomize number with decimals (double)
     * @param maxNumberOfDecimals maximum number of decimals which you want to get
     * @param min minimum limit of number which you want to get
     * @param max maximum limit of number which you want to get
     * @return a randomize string "2615.379"
     */
    public static double getFakerRandomDoubleNumberBetween(int maxNumberOfDecimals, int min, int max) {
        return faker.number().randomDouble(maxNumberOfDecimals, min, max);
    }

    /**
     * Generate faker alpha numeric value
     * @param count length string of symbol which you want to get
     * @return a randomize string "xd9lhx4ixg"
     */
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Generate faker hex numeric value
     * @param count length string of symbol which you want to get
     * @return a randomize string "AB123A342"
     */
    public static String randomHexNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * HEX_STRING.length());
            builder.append(HEX_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Generate faker randomize X-Auth-Token
     * @return a randomize string "81a99345-da82-40fa-a9da-79e9df7c11d1"
     */
    public static String getRandomXAuthToken() {
        return faker.internet().uuid();
//                (randomAlphaNumeric(8) + "-"
//                + randomAlphaNumeric(4) +"-"
//                + randomAlphaNumeric(4) + "-"
//                + randomAlphaNumeric(4) + "-"
//                + randomAlphaNumeric(12));
    }

    /**
     * Generate faker randomize X-Api-Key
     * @return a randomize string "CIoa2k6geA1nz3mSas/5V9K02yjAWhkiSyyiC9f"
     */
    public static String getRandomXApiKey() {
        int count = 39;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHANUMERIC_STRING.length());
            builder.append(ALPHANUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Generate faker randomize valid password with properties
     * @param includeUppercase responds for uppercase
     * @param includeSpecial responds for special symbols
     * @param minimumLength minimum length of number which you want to get
     * @param maxLength maximum length of number which you want to get
     * @return a randomize string "@jl4%I$@6RW@"
     */
    public static String getFakerValidPasswordWithProperties(int minimumLength, int maxLength, boolean includeUppercase, boolean includeSpecial) {
        return faker.internet().password(minimumLength, maxLength, includeUppercase, includeSpecial);
    }

    /**
     * Generate faker randomize MAC address
     * @return a randomize string "ee:91:41:0e:e3:b8"
     */
    public static String getRandomMacAddress() {
        return faker.internet().macAddress();
    }

    /**
     * Generate faker randomize valid MSNemb
     * @return a randomize string "0000000000000001325f"
     */
    public static String getValidMsnemb() {
        return StringUtils.leftPad(Integer.toHexString(getFakerRandomNumberInt(5)), 20, "0");
    }

    /**
     * Generate randomize TimeZone
     * @return a randomize string "Asia/Singapore"
     */
    private static String []timeZone = DataForGenerator.timeZoneList;

    public static String getRandomTimeZone(){
        return timeZone[getFakerRandomIntNumberBetweenSelectTimezone(timeZone.length)];
    }

    /**
     * Generate randomize Parking Dashboard Settings Messages Id
     * @return a randomize string "F0"
     */
    private static String []parkingDashboardMsgId = DataForGenerator.parkingDashboardMsgId;

    public static String getRandomParkingMsgId(){
        return parkingDashboardMsgId[getFakerRandomIntNumberBetweenSelectTimezone(parkingDashboardMsgId.length -1 )];
    }

    /**
     * Generate randomize Parking Display Settings Messages Id
     * @return a randomize string "F0"
     */
    private static String []parkingDisplaySettingsLinesList = DataForGenerator.parkingDisplaySettingsLinesList;

    public static String getRandomParkingSettingsDisplayMsgId(){
        return parkingDisplaySettingsLinesList[getFakerRandomIntNumberBetweenSelectTimezone(parkingDisplaySettingsLinesList.length -1)];
    }

    /**
     * Generate randomize User Locale
     * @return a randomize string "en"
     */
//    private static String []userLocaleList = DataForGenerator.userLocaleList;

//    public static String getRandomUserLocale(){

//        return userLocaleList[getFakerRandomIntNumberBetweenSelectTimezone(userLocaleList.length -1)];
//    }
}
