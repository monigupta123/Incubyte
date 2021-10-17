package com.stringCalculator;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringCalculatorTest {
    
    private String inputString;
    private int expectedInteger;
    private String testCaseExplaination;
    private static StringCalculator stringCalculator;

    public StringCalculatorTest(String inputString, int expectedInteger, String testCaseExplaination){
        this.inputString = inputString;
        this.expectedInteger = expectedInteger;
        this.testCaseExplaination = testCaseExplaination;
    }

    @BeforeClass
    public static void initStringCalculator(){
        stringCalculator = new StringCalculator();
    }


    @Parameterized.Parameters
    public static List<Object[]> testCasesList() {
        return Arrays.asList(new Object[][] {
          { "", 0 , "Empty string (return zero)"},
          { "1", 1, "String with single number (return number)"},
          { "1,2", 3, "String with two numbers seperated by comma (return sum of two numbers)"},
          { "1,2,3", 6, "String with n+1 numbers seperated by n commas (return sum of n+1 numbers)"},
          { "//;\n1;2;3", 6, "String with custom delimiter specified between // and '\\n' at the start of the input should be accepted" },                      
          { "1001", 0 , "Numbers greater than 1000 (ignored)"},
          { "1001,1" , 1, "Numbers greater than 1000 (ignored)"},          
          { "//[***]\n1***2***3", 6, "Delimiter of any length inside [] (accepted)" },
          { "//[*]\n1*2*3", 6, "Delimiter of any length inside [] (accepted)" },
          { "//[\n1[2[3", 6, "Custom Delimiter (accepted)" },
          { "//[**][_]\n1**2_3", 6, "Delimiter of any length inside [] (accepted)" }          
        });
    }


    @Test
    public void testAdd() {
        try{
            assertEquals(testCaseExplaination, expectedInteger, stringCalculator.add(inputString));
        } catch(ExceptionsNotAllowed e){
        }
    }

    @AfterClass
    public static void getCallsCount(){
        assertEquals(testCasesList().size(), stringCalculator.getCallsCount());
    }
}