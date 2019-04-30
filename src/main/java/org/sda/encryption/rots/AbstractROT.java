package org.sda.encryption.rots;

import org.sda.encryption.EncoderDecoder;

abstract class AbstractROT implements EncoderDecoder {
    public char[] encrypt(char[] text) {
        return init(text);
    }

    public char[] decrypt(char[] text) {
        return init(text);
    }

    private char[] init(char[] text) {
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            result[i] = roatateChars(text[i]);
        }
        return result;
    }

    protected abstract char roatateChars(char c);
}