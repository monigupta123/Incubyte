package com.stringCalculator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static String exceptionMessagePrefix = "negatives not allowed";
    private int callsCount = 0;
    public int add(String numbers) throws ExceptionsNotAllowed{
        
        callsCount++;

        if(numbers.isEmpty()){
            return 0;
        }

        int sum = 0;
        String delimeter = ",|\n";
                
        if(numbers.startsWith("//")){
            String[] splitInput = numbers.split("\n", 2);
            String newDelimeter = splitInput[0].substring(2);
            
            numbers = splitInput[1];

            if(newDelimeter.startsWith("[") && newDelimeter.endsWith("]")){
                Pattern p = Pattern.compile("\\[(.*?)\\]");
                Matcher m = p.matcher(newDelimeter);
                while(m.find()){                   
                    delimeter += "|\\Q" + m.group(1) + "\\E";
                }
            }else {
                delimeter += "|\\Q" + newDelimeter + "\\E";
            }
            
        }
        
        String[] numbersArray = numbers.split(delimeter);
        ArrayList<Integer> negativeNumbers = new ArrayList<Integer>();
        int tempNum;

        for(String number : numbersArray){
            tempNum = Integer.parseInt(number);
            if(tempNum < 0){
                negativeNumbers.add(tempNum);
                continue;
            }
            if(tempNum <= 1000)
                sum += tempNum;
        }

        if(negativeNumbers.size() > 0){
            throw new ExceptionsNotAllowed(exceptionMessagePrefix + " " + negativeNumbers.toString());
        }

        return sum;
    }

    public int getCallsCount() {
        return callsCount;
    }
}