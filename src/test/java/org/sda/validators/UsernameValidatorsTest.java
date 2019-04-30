package org.sda.validators;


import org.junit.Test;

import static org.junit.Assert.*;

public class UsernameValidatorsTest {

    @Test
    public void UserNameValidatorsTest(){
        String userName = "sokol123123";
        boolean result = Validators.checkUsername(userName);
        assertEquals(true,result);
    }

    @Test
    public void UserNameValidatorsTest2(){
        String userName = "sokol";
        boolean result = Validators.checkUsername(userName);
        assertEquals(false,result);
    }

    @Test
    public void UserNameValidatorsTest3(){
        String userName = "sokol!212";
        boolean result = Validators.checkUsername(userName);
        assertEquals(false,result);
    }

    @Test
    public void UserNameValidatorsTest4(){
        String userName = "2aksieea";
        boolean result = Validators.checkUsername(userName);
        assertEquals(false,result);
    }

    @Test
    public void UserNameValidatorsTest5(){
        String userName = "aksieeaakakakak";
        boolean result = Validators.checkUsername(userName);
        assertEquals(true,result);
    }
}
