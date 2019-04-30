package org.sda.authentication.hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {
    @Test
    public  void SHA256Test(){
        String hash = new SHA256().hash("kubasokolowski".toCharArray());

        assertEquals("7AF7FB77C1FA64048F9114070C03305AF093481F0ED932A62ABAFC8AA626ACB0".toLowerCase(),hash);
    }

}