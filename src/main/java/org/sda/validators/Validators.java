package org.sda.validators;

public class Validators {

    public static boolean checkEmail(String email) {
        if (!containsLettersDigitsAndSomeSpecialCharacters(email)) {
            return false;
        }

        if (!containsExactlyOneMonkeyCharacter(email)) {
            return false;
        }

        if (!monkeyCharacterIsPrecededByAtLeast3Characters(email)) {
            return false;
        }

        if (!atLeast3CharactersAfterMonkey(email)) {
            return false;
        }

        if (!isLengthWithRange(email,7,20)){
            return false;
        }


        /*if (specialCharactersNextToEachOther(email)) {
            return false;
        }*/
        return true;
    }

    public static boolean isLengthWithRange(String s, int startPos, int endPos){
        if (s.length()>= startPos && s.length() <=endPos){
            return true;
        }
        return false;
    }

    private static boolean specialCharactersNextToEachOther(String email) {
        int pom = 0;
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            if (ch == '.' || ch == '-' || ch == '@') {
                if (!Character.isDigit(email.charAt(i + 1)) && !Character.isLetter(email.charAt(i + 1))){
                    pom++;
                }
            }
        }

        if (pom > 0) return true;
        return false;
    }


    private static boolean atLeast3CharactersAfterMonkey(String email) {
        boolean isAfterMonkey = false;
        int countOfCharactersAfterMonkey = 0;
        for (int i = 0; i < email.length(); i++) {
            if (isAfterMonkey) countOfCharactersAfterMonkey++;
            if (email.charAt(i) == '@') isAfterMonkey = true;
        }

        if (countOfCharactersAfterMonkey >= 3) return true;
        return false;
    }

    private static boolean monkeyCharacterIsPrecededByAtLeast3Characters(String email) {
        int countNumberOfCharactersBeforeMonkey = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') break;
            else countNumberOfCharactersBeforeMonkey++;
        }

        if (countNumberOfCharactersBeforeMonkey >= 3) return true;
        return false;
    }

    private static boolean containsExactlyOneMonkeyCharacter(String email) {
        int countNumberOfMonkeyCharacter = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') countNumberOfMonkeyCharacter++;
        }

        if (countNumberOfMonkeyCharacter == 1) return true;
        return false;
    }

    private static boolean containsLettersDigitsAndSomeSpecialCharacters(String email) {
        int pom = 0;
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            if (!Character.isLetter(ch)) {
                if (!Character.isDigit(ch)) {
                    if (ch != '.' && ch != '-' && ch != '@') {
                        pom++;
                    }
                }
            }
        }
        if (pom > 0) return false;
        return true;
    }

    public static boolean checkPassword(char[] password) {
        if (!stringOfLettersDigitsAndSomeCharacters(password)) {
            return false;
        }
        if (!containsAtLeastTwoBigLetters(password)) {
            return false;
        }

        if (!containsAtLeastTwoDigits(password)) {
            return false;
        }

        if (!containsFrom7To15Characters(password)) {
            return false;
        }
        return true;
    }

    private static boolean containsFrom7To15Characters(char[] password) {
        if (password.length < 7 || password.length > 15) return false;
        return true;
    }

    private static boolean containsAtLeastTwoDigits(char[] password) {
        int countNumberOfDigits = 0;
        for (int i = 0; i < password.length; i++) {
            if (Character.isDigit(password[i])) {
                countNumberOfDigits++;
            }
        }

        if (countNumberOfDigits < 2) return false;
        return true;
    }

    private static boolean containsAtLeastTwoBigLetters(char[] password) {
        int countOfBigLetters = 0;
        for (int i = 0; i < password.length; i++) {
            if (Character.isUpperCase(password[i])) {
                countOfBigLetters++;
            }
        }
        if (countOfBigLetters < 2) return false;
        else return true;
    }

    private static boolean stringOfLettersDigitsAndSomeCharacters(char[] password) {
        int pom = 0;
        for (int i = 0; i < password.length; i++) {
            char ch = password[i];
            if (!Character.isLetter(ch)) {
                if (!Character.isDigit(ch)) {
                    if (ch != ' ' && ch != '@' && ch != '!' && ch != '/' && ch != '^') {
                        pom++;
                    }
                }
            }
        }
        if (pom == 0) return true;
        else return false;
    }


    public static boolean checkUsername(String userName) {
        if (startsWithDigit(userName)) {
            return false;
        }

        if (stringOfLettersAndDigitsOnly(userName)) {
            return false;
        }

        if (!itsLongerThan5ShorterThan15(userName)) {
            return false;
        }

        return true;
    }

    public static boolean itsLongerThan5ShorterThan15(String userName) {
        if (userName.length() <= 5 || userName.length() > 15) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean stringOfLettersAndDigitsOnly(String userName) {
        int pom = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (!Character.isDigit(userName.charAt(i))) {
                if (!Character.isLetter(userName.charAt(i))) {
                    pom++;
                }
            }
        }
        if (pom > 0) return true;
        else return false;
    }

    public static boolean startsWithDigit(String userName) {
        return Character.isDigit(userName.charAt(0));
    }
}
