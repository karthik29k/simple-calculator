package karthik.com.calculator;

import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * Created by ankireds on 5/8/18.
 */

public class CalculatorBrain {

    private static String digits = "0123456789";
    private static String divide = "/";
    private static String multiply = "X";
    private static String plus = "+";
    private static String minus = "-";

    @NonNull private static String performCalculationAction(@NonNull String result, @NonNull String operator) {
        while(result.contains(operator)) {
            float res = 0;
            // include the operators in the split array
            String[] resultArray = result.split("((?<=[-+X/])|(?=[-+X/]))");
            int index = Arrays.asList(resultArray).indexOf(operator);

            float number = Integer.valueOf(resultArray[index - 1]);
            float number2 = Integer.valueOf(resultArray[index + 1]);

            if (operator.equals(plus)) {
                res = number + number2;
            } else if (operator.equals(divide)) {
                res = number / number2;
            } else if (operator.equals(minus)) {
                res = number - number2;
            } else if (operator.equals(multiply)) {
                res = number * number2;
            }

            result = result.replace("" + resultArray[index-1]+ resultArray[index] + resultArray[index+1], String.valueOf(res))
            .replaceFirst(".0", "");

            if (result.contains("Infinity")) {
                return String.valueOf(0);
            }
        }
        return result;
    }

    public static boolean isOperationAppendValid(@NonNull final String keysEntered, @NonNull String operator) {
        if (keysEntered.equalsIgnoreCase("ready")) {
            return false;
        }

        if (keysEntered.isEmpty()) {
            return operator.equalsIgnoreCase(minus);
        }

        String lastChar = "" + keysEntered.charAt(keysEntered.length()-1);
        return digits.contains(lastChar);
    }

    @NonNull public static String performCalculation(String userEntered) {
        if (userEntered.contains(divide)) {
            userEntered = performCalculationAction(userEntered, divide);
        }

        if (userEntered.contains(multiply)) {
            userEntered = performCalculationAction(userEntered, multiply);
        }

        if (userEntered.contains(plus)){
            userEntered = performCalculationAction(userEntered, plus);
        }

        if (userEntered.contains(minus)) {
            userEntered = performCalculationAction(userEntered, minus);
        }
        return userEntered;
    }
}
