package org.sda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConvertersTest {
    @Test
    public void stringToHexLetterToOctal2(){
        String result = Converters.stringToHexAndLettersToOctal("sokolowski");

        assertEquals("73610661026106610361067773610269",result);

    }




    @Test
    public void decimalToBinaryConverterTest() {
        List<Integer> list = Converters.decimalToBinaryConverter(124);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(1);
        expected.add(1);
        expected.add(1);
        expected.add(1);
        expected.add(0);
        expected.add(0);
        assertEquals(expected, list);
    }

    @Test
    public void stringToHexadecimalConverterTest() {
        String result = Converters.stringToHexadecimal("abcd");

        assertEquals("61626364", result);
    }

    @Test
    public void stringToSevenSystemConverterTest() {
        String result = Converters.stringToSevenSystem("a");

        assertEquals("166", result);
    }

}