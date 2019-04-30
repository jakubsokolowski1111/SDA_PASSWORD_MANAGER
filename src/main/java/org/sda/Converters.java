package org.sda;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Converters {
    private static final Logger logger = Logger.getLogger(Converters.class);

    public static String stringToHexAndLettersToOctal(String word){
        String hexString = stringToHexadecimal(word);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hexString.length(); i++) {
            if (Character.isLetter(hexString.charAt(i))){
                 int decimal = hexString.charAt(i);
                 StringBuilder octal =new StringBuilder();
                 while (decimal!=0) {
                     int mod = decimal % 8;
                     decimal /= 8;
                     octal.append(mod);
                 }
                 octal.reverse();
                 result.append(octal);
            }else {
                result.append(hexString.charAt(i));
            }
        }
        logger.info("Convarted word: " + word + " to hex: " + result.toString());
        return result.toString();
    }


    public static String stringToSevenSystem(String word) {
        int[] decimalArr = stringToIntArr(word);
        String sevenSystem = "";
        for (int i = 0; i < decimalArr.length; i++) {
            int decimal = decimalArr[i];
            while (decimal != 0) {
                int mod = decimal % 7;
                decimal /= 7;
                sevenSystem += mod;
            }

        }

        return new StringBuilder(sevenSystem).reverse().toString();
    }

    public static List<Integer> decimalToBinaryConverter(int decimal) {
        List<Integer> binary = new ArrayList<>();
        while (decimal != 0) {
            int mod = decimal % 2;
            decimal /= 2;
            binary.add(mod);
        }

        Collections.reverse(binary);
        return binary;
    }

    public static String stringToHexadecimal(String word) {
        int[] stringsAsDecimal = stringToIntArr(word);
        StringBuilder hexadecimal = new StringBuilder();

        for (int i = 0; i < stringsAsDecimal.length; i++) {
            int decimal = stringsAsDecimal[i];
            hexadecimal.append(integerToHex(decimal));
        }
        return hexadecimal.toString();
    }

    private static String integerToHex(int decimal) {
        String hexadecimal = "";
        while (decimal != 0) {
            int mod = decimal % 16;
            decimal /= 16;
            if (mod <= 9) {
                hexadecimal += mod;
            } else if (mod == 10) {
                hexadecimal += "A";
            } else if (mod == 11) {
                hexadecimal += "B";
            } else if (mod == 12) {
                hexadecimal += "C";
            } else if (mod == 13) {
                hexadecimal += "D";
            } else if (mod == 14) {
                hexadecimal += "E";
            } else if (mod == 15) {
                hexadecimal += "F";
            }
        }
        return new StringBuilder(hexadecimal).reverse().toString();
    }

    private static int[] stringToIntArr(String word) {
        int[] stringsAsDecimal = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            stringsAsDecimal[i] = word.charAt(i);
        }
        return stringsAsDecimal;
    }
}
