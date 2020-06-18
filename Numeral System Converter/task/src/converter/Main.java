package converter;

import java.util.Locale;
import java.util.Scanner;

public class Main {


    public static int convertIntegerToDec(String number, int radix) {
        if (radix == 1) {
            return number.length();
        } else {
            return Integer.parseInt(number, radix);
        }
    }

    public static String convertIntegerFromDec(int number, int radix) {
        if (radix == 1) {
            return "1".repeat(number);
        } else {
            return Integer.toString(number, radix);
        }
    }

    public static String convertIntegerPart(int sourceRadix, String sourceNumber, int targetRadix) {
        int number10 = convertIntegerToDec(sourceNumber, sourceRadix);
        return convertIntegerFromDec(number10, targetRadix);
    }

    public static double convertFractionalToDec(String number, int radix) {
        double decVal = 0;

        String[] digits = number.split("");

        for (int i = 0; i < digits.length; i++) {
            decVal += (double) Integer.parseInt(digits[i], radix) / Math.pow(radix, i + 1);
        }

        return decVal;
    }

    public static String convertFractionalFromDec(double number, int radix) {
        StringBuilder perfNumber = new StringBuilder();

        double cNumber = number;

        for (int i = 0; i < 5; i++) {
            cNumber *= radix;
            perfNumber.append(Integer.toString((int) cNumber, radix));
            cNumber = cNumber - (int) cNumber;
        }

        return perfNumber.toString();
    }

    public static String convertFractionalPart(int sourceRadix, String sourceNumber, int targetRadix) {
        double number = convertFractionalToDec(sourceNumber, sourceRadix);
        return convertFractionalFromDec(number, targetRadix);
    }

    public static String convert(int sourceRadix, String sourceNumber, int targetRadix) {
        String[] parts = sourceNumber.split("\\.");

        if (parts.length > 1) {
            String integerPart = convertIntegerPart(sourceRadix, parts[0], targetRadix);
            String fractionalPart = convertFractionalPart(sourceRadix, parts[1], targetRadix);

            return integerPart + "." + fractionalPart;
        } else {
            return convertIntegerPart(sourceRadix, sourceNumber, targetRadix);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);

        try {
            int sourceRadix = scanner.nextInt();
            String sourceNumber = scanner.next();
            int targetRadix = scanner.nextInt();

            if (sourceRadix >= 1 && sourceRadix <= 36 && targetRadix >= 1 && targetRadix <= 36) {
                System.out.println(convert(sourceRadix, sourceNumber, targetRadix));
            } else {
                System.out.println("error");
            }
        } catch (Exception e) {
            System.out.println("error");
        }

    }
}
