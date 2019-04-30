package org.sda.encryption;

public interface EncoderDecoder {
    public char[] encrypt(char[] text);
    public char[] decrypt(char[] text);
}
