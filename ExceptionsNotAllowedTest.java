package com.stringCalculator;
import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ExceptionsNotAllowedTest {
    
    private String inputString;
    private String expectedString;
    private static StringCalculator stringCalculator;

    private static String exceptionMessagePrefix = "negatives not allowed";
    public ExceptionsNotAllowedTest(String inputString , String expectedString){
        this.inputString = inputString;
        this.expectedString = expectedString;
    }

    @BeforeClass
    public static void initStringCalculator(){
        stringCalculator = new StringCalculator();
    }

    @Parameterized.Parameters
    public static List<Object[]> testCasesList() {
        return Arrays.asList(new Object[][] {
            { "-1", exceptionMessagePrefix + " " + "[-1]" },
            { "-1,2", exceptionMessagePrefix + " " + "[-1]" },
            { "1,-2,3", exceptionMessagePrefix + " " + "[-2]" },
            { "1,-2,-3", exceptionMessagePrefix + " " + "[-2, -3]" },
            { "//_\n1_-2_-3", exceptionMessagePrefix + " " + "[-2, -3]" },
            { "//;\n1;-2\n-3,4", exceptionMessagePrefix + " " + "[-2, -3]" },
        });
    }

    @Test(expected = ExceptionsNotAllowed.class)
    public void testNegtiveNumbers() throws ExceptionsNotAllowed{
        stringCalculator.add(inputString);
    }

    @Test
    public void testNegtiveNumbersExceptionMessage() {
        try {
            stringCalculator.add(inputString);
        } catch(ExceptionsNotAllowed e){
            assertEquals(expectedString, e.getMessage());
        }
    }

}