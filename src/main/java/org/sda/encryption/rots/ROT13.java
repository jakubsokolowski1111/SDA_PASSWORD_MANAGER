package org.sda.encryption.rots;

public class ROT13 extends AbstractROT {


    @Override
    protected char roatateChars(char c) {
        char result = '.';
        if ((c >= 110 && c <= 122) || (c >= 78 && c <= 90)) {
            result = (char) (c - 13);
        } else {
            if ((c >= 'a' || c <= 'z') && (c >= 'A' || c >= 'Z') || c == ' ' || c == '.') {
                if (c != ' ' && c != '.') {
                    result = (char) (c + 13);
                } else {
                    if (c == ' ')
                        result = ' ';
                    if (c == '.')
                        result = '.';
                }
            } else {
                result = c;
            }
        }
        return result;
    }




    /*private char[] encryptDecryptMethod(char[] text) {
        char [] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            char letter = text[i];
            if ((letter >= 110 && letter <= 122) || (letter >= 78 && letter <= 90)) {
                result[i] = (char) (letter - 13);
            } else {
                if((letter >= 'a' || letter <= 'z') && (letter >= 'A' || letter >= 'Z') || letter == ' ' || letter == '.') {
                    if (letter != ' ' && letter != '.') {
                        result[i] = (char) (letter + 13);
                    } else {
                        if (letter == ' ')
                            result[i] = ' ';
                        if (letter == '.')
                            result[i] = '.';
                    }
                }else{
                    result[i] = letter;
                }
            }
        }   //Red fox jumpes a lot.
        return result;
    }*/
}
