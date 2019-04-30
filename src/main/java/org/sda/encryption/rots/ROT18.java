package org.sda.encryption.rots;

public class ROT18 extends AbstractROT {

    /*public char[] encrypt(char[] text) {
        EncoderDecoder encoder = new ROT13();
        char[] result = encoder.encrypt(text);

        for (int i = 0; i < result.length; i++) {
            char symbol = result[i];
            if (Character.isDigit(symbol)) {
                result[i] += 5;
            }
        }

        return result;
    }

    public char[] decrypt(char[] text) {
        EncoderDecoder decoder = new ROT13();
        char[] result = decoder.decrypt(text);

        for (int i = 0; i < result.length; i++) {
            char symbol = result[i];
            if (Character.isDigit(symbol)) {
                result[i] -= 5;
            }
        }

        return result;
    }
*/
    private ROT13 rot13 = new ROT13();

    @Override
    protected char roatateChars(char c) {
        if (Character.isLetter(c)) {
            return rot13.roatateChars(c);
        } else {
            if (c >= '0' && c <= '4')
                c += 5;
            else if (c >= '5' && c <= '9')
                c -= 5;

        }
        return c;
    }
}
