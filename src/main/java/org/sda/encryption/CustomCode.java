package org.sda.encryption;

public class CustomCode implements EncoderDecoder {
    private char[] passPharse;

    public CustomCode(char[] passPharse) {
        this.passPharse = passPharse;
    }


    public char[] encrypt(char[] text) {
        char[] output = new char[text.length];
        int j = 0;

        for (int i = 0; i < text.length; i++) {
            char textChar = text[i];


            if (i % 2 == 0) {
                if (j < getPassPharse().length) {
                    output[i] = addChars(textChar, passPharse[j]);//(char) (text[i] + getPassPharse()[j]);
                } else {
                    j = 0;
                    output[i] = addChars(textChar, passPharse[j]);

                }

            } else {
                if (j < getPassPharse().length) {
                    output[i] = subtractChars( textChar, passPharse[j]);//(char) (text[i] - getPassPharse()[j]);

                } else {
                    j = 0;
                    output[i] = subtractChars( textChar, passPharse[j]);

                }

            }

            j++;
        }
        return output;
    }



    public char[] decrypt(char[] text) {
        char[] output = new char[text.length];
        int j = 0;

        for (int i = 0; i < text.length; i++) {
            char textChar = text[i];


            if (i % 2 == 0) {
                if (j < getPassPharse().length) {
                    output[i] = subtractChars( textChar, passPharse[j]);

                } else {
                    j = 0;
                    output[i] = subtractChars( textChar, passPharse[j]);

                }

            } else {
                if (j < getPassPharse().length) {
                    output[i] = addChars(textChar, passPharse[j]);
                } else {
                    j = 0;
                    output[i] = addChars(textChar, passPharse[j]);

                }

            }

            j++;
        }
        return output;
    }

    private char subtractChars(char textChar, char passChar) {
        return (char) (textChar - passChar);
    }

    private char addChars(char textChar, char passChar) {
        return (char) (textChar + passChar);
    }

    public char[] getPassPharse() {
        return passPharse;
    }
}
