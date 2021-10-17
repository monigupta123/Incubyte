package com.stringCalculator;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.print("Enter as many as string(seperated by comma): ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();

        input = input.replace("\\n", "\n");

        StringCalculator stringCalculator = new StringCalculator();

        int sum;
		try {
			sum = stringCalculator.add(input);
            System.out.println("Sum: " + sum);
		} catch (ExceptionsNotAllowed e) {
            System.out.println(e.getMessage());
		}

    }
}
