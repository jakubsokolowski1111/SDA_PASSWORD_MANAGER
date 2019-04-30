
import org.junit.Test;
import org.sda.encryption.CustomCode;
import org.sda.encryption.EncoderDecoder;
import org.sda.encryption.rots.ROT13;
import org.sda.encryption.rots.ROT18;

import static org.junit.Assert.*;

public class EncoderDecoderTest {

    @Test
    public void rot13EncryptionTest() {
        char[] text = "Red fox jumpes a lot.".toCharArray();
        EncoderDecoder encoder = new ROT13();
        char[] output = encoder.encrypt(text);

        assertEquals("Text doesn't match expected result", "Erq sbk whzcrf n ybg.", new String(output));

    }

    @Test
    public void ReturnTextWhenRunMethodTwice(){
        char[] text = "Red fox jumpes a lot.".toCharArray();
        EncoderDecoder encoder = new ROT13();
        char[] output = encoder.encrypt(text);
        text = encoder.encrypt(output);

        assertEquals("Text doesn't match expected result", "Red fox jumpes a lot.", new String(text));
    }

    @Test
    public void ROT13DecryptionTest() {
        char[] text = "Erq sbk whzcrf n ybg.".toCharArray();
        EncoderDecoder decoder = new ROT13();
        char[] output = decoder.decrypt(text);

        assertEquals("Text doesn't match expected result", "Red fox jumpes a lot.", new String(output));
    }

    @Test
    public void ROT18EnscryptionTest() {
        char[] text = "Red fox jumpes a lot. 1234".toCharArray();
        EncoderDecoder encoder = new ROT18();
        char[] output = encoder.encrypt(text);

        assertEquals("Text doesn't match expected result", "Erq sbk whzcrf n ybg. 6789", new String(output));
    }

    @Test
    public void ROT18DescryptionTest() {
        char[] text = "Erq sbk whzcrf n ybg. 6789".toCharArray();
        EncoderDecoder encoder = new ROT18();
        char[] output = encoder.decrypt(text);

        assertEquals("Text doesn't match expected result", "Red fox jumpes a lot. 1234", new String(output));
    }

   /* @Test
    public void CustomCodeEncryptionTest() {
        char[] passPharse = "passphrase".toCharArray();
        EncoderDecoder encoder = new CustomCode(passPharse);

        char[] output = encoder.encrypt("Red fox jumpe a lot".toCharArray());

        assertEquals("Text doesn't match expected result", "Â×ﾭÖê﾿ÝÝØﾭÑﾸÞç", new String(output));
    }*/

   /* @Test
    public void CustomCodeDecryptionTest() {
        char[] passPharse = "passphrase".toCharArray();
        EncoderDecoder decoder = new CustomCode(passPharse);

        char[] output = decoder.decrypt("Â×ﾭÖê﾿ÝÝØﾭÑﾸÞç".toCharArray());

        assertEquals("Text doesn't match expected result", "Red fox jumpe a lot", new String(output));
    }*/



    @Test
    public void ReturnDifrentTextWhenRunMethodTwiceCustomCode(){
        char[] passPharse = "passphrase".toCharArray();
        EncoderDecoder encoder = new CustomCode(passPharse);

        char[] output = encoder.encrypt("Red fox jumpe a lot".toCharArray());
        passPharse = encoder.encrypt(output);


        assertNotEquals("Red fox jumpe a lot", passPharse);

    }
}
