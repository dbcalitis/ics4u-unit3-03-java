/*
* This program generates 250 random numbers in an array
* and allows the user to search the array for a number.
*
* @author  Daria Bernice Calitis
* @version 0.5
* @since   2022-11-10
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class.
 */
final class BinarySearch {
    /**
     * The number of elements in the array.
     */
    public static final int ARRAY_SIZE = 250;

    /**
     * The number of elements in the array.
     */
    public static final int MAX = 999;

    /**
     * The min number for array.
     */
    public static final int MIN = 0;

    /**
     * BinarySearch.
     *
     * <p>
     * Prevent instantiation.
     * </p>
     *
     * @throws IllegalStateException - an error.
     */
    private BinarySearch() {
        // Prevent instantiation
        // Optional: throw an exception e.g. AssertionError
        // if this ever *is* called
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * The binarySearch function.
     *
     * <p>
     * Finds the index of a numbers
     * </p>
     *
     * @param userArray - The array of numbers.
     * @param userNumber - the number given by the user.
     * @param lowIndex - the lowest index.
     * @param highIndex - the highest index.
     * @return binarySearch - the return value.
     */
    static int binarySearch(final int[] userArray, final int userNumber,
            final int lowIndex, final int highIndex) {
        final int returnValue;
        // Restrict the boundary of right index
        // and the left index to prevent
        // overflow of indices
        if (highIndex >= lowIndex && lowIndex <= userArray.length - 1) {

            final int middle = lowIndex + (highIndex - lowIndex) / 2;

            // If the element is present
            // at the middle itself
            if (userArray[middle] == userNumber) {
                returnValue = middle;
            } else if (userArray[middle] > userNumber) {
                // If element is smaller than mid, then it can
                // only be present in left subarray

                returnValue = binarySearch(
                    userArray, userNumber, lowIndex, middle - 1);
            } else {
                // Else the element can only be present
                // in right subarray

                returnValue = binarySearch(
                    userArray, userNumber, middle + 1, highIndex);
            }
        } else {
            // We reach here when element is not present in
            // array
            returnValue = -1;
        }

        return returnValue;
    }

    /**
     * Main.
     *
     * @param args - no arguments
     */
    public static void main(final String[] args) {
        System.out.println("Binary Search Program");
        try {
            // Initializing the random class
            final Random randNumber = new Random();

            // Initializing array of numbers
            final int[] randomNumberArray = new int[ARRAY_SIZE];

            // Adding numbers to the array
            for (
                int counter = 0; counter < randomNumberArray.length;
                counter++) {
                randomNumberArray[counter] = randNumber.nextInt(MAX) + 1;
            }

            // Sorting the array
            final int[] numberArray = randomNumberArray;
            Arrays.sort(numberArray);

            System.out.print("\nSorted list of numbers:\n");
            for (int element : numberArray) {
                final String padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }
            System.out.print("\n\n");

            // Getting user input as to what number they wish to search for
            final Scanner userInput = new Scanner(System.in);
            System.out.print("What number are you searching for in the array");
            System.out.print(" (integer between 0 and 999): ");
            final int searchNumber = userInput.nextInt();
            userInput.close();
            System.out.println();

            // Ensuring the user inputs an appropriate integer
            if (searchNumber > MAX || searchNumber < MIN) {
                // throw new Exception();
            } else {
                // Using binary search to find
                // the user's chosen number in the array
                final int searchResult = binarySearch(numberArray, searchNumber,
                        0, numberArray.length - 1);

                // Outputing the results of the search
                System.out.println("Your number is in index: " + searchResult);
                System.out.println("\nDone.");
            }

            // Catches and tells the user that an error occured
        } catch (java.util.InputMismatchException error) {
            System.out.println(error);
            System.out.println("ERROR: Invalid Input");
        }
    }
}

