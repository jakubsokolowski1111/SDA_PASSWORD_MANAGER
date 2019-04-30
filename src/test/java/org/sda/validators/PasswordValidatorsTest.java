package org.sda.validators;

import org.junit.*;

import static org.junit.Assert.*;
public class PasswordValidatorsTest {
    @BeforeClass
    public static void beforeClass(){
        System.out.println("before class");
    }

    @Test
    public void passwordTest(){
        char[] password = "KKuubb112".toCharArray();
        boolean result = Validators.checkPassword(password);

        assertEquals(true,result);
    }

    @Test
    public void passwordTest2(){
        char[] password = "kKuba213@wpa".toCharArray();
        boolean result = Validators.checkPassword(password);

        assertEquals(false,result);
    }

    @AfterClass
    public static void afterCalss(){
        System.out.println("after class");
    }

    @Before
    public  void beforeEach(){
        System.out.println("before each");
    }

    @After
    public  void afterEach(){
        System.out.println("after each");
    }

}
