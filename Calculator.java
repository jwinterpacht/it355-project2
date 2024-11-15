import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*
*Simple calculator program meant to mitigate Common Weakness Enumerations.
* Authors: David Arellano,Addison Casner, Santiago Kipp, Suhail Tailor, Jaden Winterpacht
* This whole class demonstrates CWE-1113: Inappropriate Comment Style and CWE-1116: Inaccurate Comment Style,
* as all comments follow Javadoc conventions.
*/
class Calculator {

    // CWE-582: Array Declared Public, Final, and Static
    // CWE-500: Public Static Field Not Marked Final
    private static final int[] arr = new int[2];

    /**
     * Adds two numbers stored in the array.
     * CWE-1071: Empty Code Block
     * 
     * 
     * @return The sum of arr[0] and arr[1].
     * @throws ArithmeticException If an integer overflow occurs during the
     *                             addition.
     * 
     */
    public int add() {
        // CWE-190: Integer Overflow or Wraparound
        // CWE-396: Declaration of Generic Exception
        // If statement to check overflow
        if ((arr[0] > 0 && arr[1] > Integer.MAX_VALUE - arr[0]) ||
                (arr[0] < 0 && arr[1] < Integer.MIN_VALUE - arr[0])) {
            throw new ArithmeticException("Integer overflow detected.");
        }
        return arr[0] + arr[1];
    }

    /**
     * Subtracts the second number from the first number stored in the array.
     * CWE-1071: Empty Code Block
     *
     * @return The result of arr[0] - arr[1].
     */
    public int subtract() {

        return arr[0] - arr[1];
    }

    /**
     * Multiplies two numbers stored in the array.
     * CWE-1071: Empty Code Block
     *
     * @return The product of arr[0] and arr[1].
     * 
     */
    public int multiple() {
        return arr[0] * arr[1];
    }

    /**
     * Divides the first number by the second number stored in the array.
     * CWE-1071: Empty Code Block
     * CWE –209: Generation of error messages containing sensitive information.
     * CWE-248: Uncaught Exception
     * 
     * @return The result of arr[0] / arr[1].
     * @throws ArithmeticException If an attempt is made to divide by zero.
     */
    public int divide() {
        // CWE-369: Divide by zero
        try {
            return arr[0] / arr[1];
        } catch (ArithmeticException e) {
            // Prints error if division cannot be done since denominator is zero
            System.err.println("Cannot divide by zero!");
            return 0;
        }
    }

    /**
     * Calculates and prints the square root of the two numbers stored in the array.
     * CWE-1071: Empty Code Block*
     * 
     * @throws IllegalArgumentException If either arr[0] or arr[1] is negative.
     */
    public void squareRoot() {
        if (arr[0] < 0 || arr[1] < 0) {
            throw new IllegalArgumentException(
                    "One of the integers is negative. Please try again with positive integers");
        }
        double sqrt1 = Math.sqrt(arr[0]);
        double sqrt2 = Math.sqrt(arr[1]);

        // CWE-681: Incorrect Conversion between Numeric Types
        if (sqrt1 > Integer.MAX_VALUE || sqrt2 > Integer.MAX_VALUE) {
            System.err.println("Error: Result exceeds the range of an integer.");
        } else {
            // CWE-1109: Use of same variable for multiple purposes(created new int variable
            // for printing)
            int sqrt1Result = (int) sqrt1;
            int sqrt2Result = (int) sqrt2;

            System.out.println("Value 1 Square Root: " + sqrt1Result);
            System.out.println("Value 1 Square Root: " + sqrt2Result);
        }

    }

    /**
     * Checks if the first number in the array is even.
     * CWE-1071: Empty Code Block*
     * 
     * @return True if arr[0] is even, false otherwise.
     */
    public boolean isEvenValue1() {

        return (arr[0] % 2 == 0);
    }

    /**
     * Checks if the second number in the array is even.
     *
     * @return True if arr[0] is even, false otherwise.
     */
    public boolean isEvenValue2() {

        return (arr[1] % 2 == 0);
    }

    public void generateRandomValues() {
        // CWE-334: Small Space of Random Values
        // Use a larger range for the random values to avoid predictability.
        Random r = new Random();
        arr[0] = r.nextInt(1000) - 500; // Random integer between -500 and 499
        arr[1] = r.nextInt(1000) - 500; // Random integer between -500 and 499
        System.out.println("Generated Random Values: Value 1 = " + arr[0] + ", Value 2 = " + arr[1]);
    }

    /**
     * Reads two integer values from the provided Scanner object and stores them in
     * the array.
     *
     * @param scan A valid and open Scanner object for reading input.
     * @return The array containing the two integers entered by the user.
     * @throws IllegalArgumentException If the Scanner object is null.
     * @throws IllegalStateException    If the Scanner object is closed.
     * @throws InputMismatchException   If the user inputs a non-integer value.
     */
    public int[] getValues(Scanner scan) {

        // CWE-233: Improper Handling of Parameters
        // CWE-248: Uncaught Exception
        // CWE-396: Declaration of Generic Exception
        // throw IllegalArgumentException if scanner is null
        // CWE –209: Generation of error messages containing sensitive information.
        try {
            System.out.println("Value 1: ");

            if (scan == null) {
                throw new IllegalArgumentException("Error: Scanner cannnot be null.");
            }
            // CWE-233: Improper Handling of Parameters
            // CWE-396: Declaration of Generic Exception(throw IllegalStateException)
            // throw IllegalStateException if scanner is closed.
            if (!scan.hasNext()) {
                throw new IllegalStateException("Error: Scanner object is closed.");
            }

            int num1 = 0;

            int num2 = 0;

            // CWE-1287: Improper Validation of Specified Type of Input
            if (scan.hasNextInt()) {

                num1 = scan.nextInt();
            } else {
                throw new InputMismatchException("The input is not an integer.");
            }
            System.out.println("Value 2: ");

            if (scan.hasNextInt()) {
                num2 = scan.nextInt();
            } else {
                throw new InputMismatchException("The input is not an integer.");
            }
            arr[0] = num1;
            arr[1] = num2;

        } catch (Exception e) {
            System.out.println("Invalid input");
        }
        return arr;

    }

    // CWE-835: Loop with unreachable exit condition (infinite loop)
    // CWE-570: Expression is Always False
    public static void main(String[] args) throws Exception {

        // CWE-665: Improper Initailization
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println(" Calculator Running");

        int option;

        do {
            calc.getValues(scanner);
            System.out.println("1. Add two values");
            System.out.println("2. Subtract two values");
            System.out.println("3. Mutiply two values ");
            System.out.println("4. Divide two values");
            System.out.println("5. Square Root two values");
            System.out.println("6. Check Odd or Even");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Result: " + calc.add());
                    break;
                case 2:
                    System.out.println("Result: " + calc.subtract());
                    break;
                case 3:
                    System.out.println("Result: " + calc.multiple());
                    break;
                case 4:
                    System.out.println("Result: " + calc.divide());
                    break;
                case 5:
                    calc.squareRoot();
                    break;
                case 6:

                    // CWE-252: Unchecked Return Value
                    // CWE-570: Expression is Always False
                    boolean result1 = calc.isEvenValue1();
                    if (result1) {
                        System.out.println("Even");
                    } else {
                        System.out.println("Odd");
                    }

                    boolean result2 = calc.isEvenValue2();
                    if (result2) {
                        System.out.println("Even");
                    } else {
                        System.out.println("Odd");
                    }

                    break;

            }
        } while (option != 0);

        scanner.close();
    }
}
