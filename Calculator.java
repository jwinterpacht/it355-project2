import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*
* Simple calculator program meant to mitigate Common Weakness Enumerations.
* Authors: David Arellano, Addison Casner, Santiago Kipp, Suhail Tailor, Jaden Winterpacht
* This whole class demonstrates CWE-1113: Inappropriate Comment Style, CWE-1116: Inaccurate Comment Style,
* CWE-1114: Inappropriate Whitespace Style
* as all comments follow JavaDoc conventions.
*/
class Calculator {
    // CWE-582: Array Declared Public, Final, and Static
    // CWE-500: Public Static Field Not Marked Final
    private static final int[] arr = new int[2];

    /**
     * CWE-466: Return of Pointer Value Outside of Expected Range
     * 
     * @param index The index to access in the array.
     * @return The value at the specified index if within range, or -1 if outside of
     *         the valid range.
     */
    public int returnOutOfRangeValue(int index) {
        // CWE-466: Return of Pointer Value Outside of Expected Range
        if (index < 0 || index >= arr.length) {
            System.err.println("Accessing index outside of expected range.");
            return -1; // Indicating an invalid access
        }
        return arr[index];
    }

    /**
     * Adds two numbers stored in the array.
     * 
     * CWE-1071: Empty Code Block
     * 
     * @return The sum of arr[0] and arr[1].
     * @throws ArithmeticException If an integer overflow occurs during the
     *                             addition.
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
     * 
     * CWE-1071: Empty Code Block
     *
     * @return The result of arr[0] - arr[1].
     * @throws ArithmeticException If an integer underflow occurs during the
     *                             subtraction.
     */
    public int subtract() {
        // CWE-191: Integer Underflow (Wrap or Wraparound)
        if ((arr[1] > 0 && arr[0] < Integer.MIN_VALUE + arr[1]) || // Check for integer underflow
                (arr[1] < 0 && arr[0] > Integer.MAX_VALUE + arr[1])) {
            throw new ArithmeticException("Integer underflow detected.");
        }
        return arr[0] - arr[1];
    }

    /**
     * Multiplies two numbers stored in the array.
     * 
     * CWE-1071: Empty Code Block
     *
     * @return The product of arr[0] and arr[1].
     */
    public int multiple() {
        return arr[0] * arr[1];
    }

    /**
     * Divides the first number by the second number stored in the array.
     * 
     * CWE-1071: Empty Code Block
     * CWE-209: Generation of error messages containing sensitive information
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
     * 
     * CWE-1071: Empty Code Block
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
            // CWE-1109: Use of same variable for multiple purposes (created new int
            // variable for printing)
            int sqrt1Result = (int) sqrt1;
            int sqrt2Result = (int) sqrt2;

            System.out.println("Value 1 Square Root: " + sqrt1Result);
            System.out.println("Value 2 Square Root: " + sqrt2Result);
        }

    }

    /**
     * Checks if the first number in the array is even.
     * 
     * CWE-1071: Empty Code Block
     * CWE-570: Expression is Always False
     * 
     * @return True if arr[0] is even, false otherwise.
     */
    public boolean isEvenValue1() {
        // CWE-480: Use of Incorrect Operator
        return (arr[0] % 2 == 0); // Ensure that the operator being used to check for even values is modulus
    }

    /**
     * Checks if the second number in the array is even.
     * 
     * CWE-570: Expression is Always False
     * 
     * @return True if arr[0] is even, false otherwise.
     */
    public boolean isEvenValue2() {
        return (arr[1] % 2 == 0);
    }

    /**
     * Generates two random values between 0 and 1000, then prints the two values to
     * the console.
     * CWE-406: Missing Authentication for Critical Functions
     */
    public void generateRandomValues() {
        // CWE-334: Small Space of Random Values
        Random r = new Random();
        arr[0] = r.nextInt(1001);
        arr[1] = r.nextInt(1001);
        System.out.println("Generated Random Values: Value 1 = " + arr[0] + ", Value 2 = " + arr[1]);
    }

    /**
     * Reads two integer values from the provided Scanner object and stores them in
     * the array.
     * 
     * CWE-233: Improper Handling of Parameters
     * CWE-248: Uncaught Exception
     * CWE-396: Declaration of Generic Exception
     * CWE-547: Use of Hard-coded, Security-relevant Constants
     * CWE-120: Buffer Copy without Checking Size of Input
     *
     * @param scan A valid and open Scanner object for reading input.
     * @return The array containing the two integers entered by the user.
     * @throws IllegalArgumentException If the Scanner object is null.
     * @throws IllegalStateException    If the Scanner object is closed.
     * @throws InputMismatchException   If the user inputs a non-integer value.
     */
    public int[] getValues(Scanner scan) {
        try {
            System.out.println("Value 1: ");
            if (scan == null) {
                throw new IllegalArgumentException("Error: Scanner cannot be null.");
            }
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

            arr[0] = num1; // Storing values without bounds check
            arr[1] = num2;
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
        return arr;
    }

    /**
     * Main method is the driver of the calculator app.
     * Contains user interface elements and calls Calculator methods.
     * 
     * CWE-835: Loop with unreachable exit condition (infinite loop)
     * CWE-606: Unchecked Input for Loop Condition
     * CWE-570: Expression is Always False
     * CWE-1124: Excessively Deep Nesting
     * CWE-586: Explicit Call to Finalize
     * CWE-134: Use of Externally Controlled Format String
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // CWE-665: Improper Initialization
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println("Calculator Running");

        int option;
        boolean useRandomValues = false;

        do {
            System.out.println("1. Provide two values");
            System.out.println("2. Generate random values");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int inputOption = scanner.nextInt();

            if (inputOption == 1) {
                calc.getValues(scanner);
                useRandomValues = false;
            } else if (inputOption == 2) {
                calc.generateRandomValues();
                useRandomValues = true;
            } else if (inputOption == 0) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
                continue;
            }
            do {
                System.out.println("1. Add two values");
                System.out.println("2. Subtract two values");
                System.out.println("3. Multiply two values");
                System.out.println("4. Divide two values");
                System.out.println("5. Square Root two values");
                System.out.println("6. Check Odd or Even");
                System.out.println("7. Generate random numbers");
                System.out.println("8. Test out-of-range access"); // New option added
                System.out.println("0. Exit to main menu");
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
                        boolean result1 = calc.isEvenValue1();
                        System.out.println("Value 1 is " + (result1 ? "Even" : "Odd"));

                        boolean result2 = calc.isEvenValue2();
                        System.out.println("Value 2 is " + (result2 ? "Even" : "Odd"));
                        break;
                    case 7:
                        if (!useRandomValues) {
                            calc.generateRandomValues();
                            useRandomValues = true;
                        } else {
                            System.out.println("Random values already in use. Re-generating.");
                            calc.generateRandomValues();
                        }
                        break;
                    case 8: // New case for testing out-of-range access
                        System.out.print("Enter an index to access (0 or 1 is valid): ");
                        int index = scanner.nextInt();
                        int value = calc.returnOutOfRangeValue(index);
                        System.out.println("Value returned: " + value);
                        break;
                    case 0:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while (option != 0);

        } while (true);

        scanner.close();
    }
}
