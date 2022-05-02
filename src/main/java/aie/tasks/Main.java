package aie.tasks;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private int[] matrix;
    private int n;
    private int m;
    private int p;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        init();
    }

    /**
     * Start The Application by requesting the main length for matrix in [n,m,p]
     * and also start applicatoin main looping
     */
    private void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter Number of rows: ");
        n = Integer.parseInt(scanner.nextLine());
        if (!isValid(n)) {
            System.out.print("number of rows should be positive");
            init();
            return;
        }
        System.out.print("Please Enter Number of columns: ");
        m = Integer.parseInt(scanner.nextLine());
        if (!isValid(m)) {
            System.out.print("number of columns should be positive");
            init();
            return;
        }
        System.out.print("Please Enter Depth: ");
        p = Integer.parseInt(scanner.nextLine());
        if (!isValid(p)) {
            System.out.print("depth should be positive");
            init();
            return;
        }
        int length = n * m * p;
        if (length == 0) {
            System.out.println("Length is 0 aborting");
            System.exit(0);
        }
        matrix = new int[length];

        start(scanner);
    }

    /**
     * A function contains the app looping
     *
     * @param scanner the main application scanner for read inputs
     */
    private void start(Scanner scanner) {
        while (!Thread.interrupted()) {
            printUsageMessage();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> addToMatrix(scanner);
                case 2 -> getFromMatrix(scanner);
                default -> System.out.println("Invalid option");
            }
        }
    }

    /**
     * Grab value from the matrix by generate the index {@code #generateIndex} and print its value
     *
     * @param scanner
     */
    private void getFromMatrix(Scanner scanner) {

        int index = generateIndex(scanner);
        if (index == -1) {
            return;
        }
        System.out.printf("Value: %d%n", matrix[index]);
    }

    /**
     * add new value to matrix
     *
     * @param scanner
     */
    private void addToMatrix(Scanner scanner) {
        int index = generateIndex(scanner);
        if (index == -1) return;
        System.out.print("Enter the value to add: ");
        int value = Integer.parseInt(scanner.nextLine());
        matrix[index] = value;

    }

    /**
     * Read index values from user, and check it
     *
     * @param scanner the main application scanner object
     * @return the generated index
     */
    private int generateIndex(Scanner scanner) {
        System.out.print("Please Enter i,j,k. Note , between numbers: ");
        String s = scanner.nextLine();
        String[] values = s.split(",");

        if (values.length != 3) {
            System.out.println("Invalid Values");
            return -1;
        }
        final boolean[] valid = {true};
        int[] ints = Arrays.stream(values).flatMapToInt(v -> {
            int value = Integer.parseInt(v);
            if (!isValid(value)) {
                valid[0] = false;
                System.out.println("Invalid Value: " + value);
            }
            return IntStream.of(value);
        }).toArray();
        if (!valid[0]) {
            return -1;
        }
        int index = getIndex(ints[0], ints[1], ints[2]);
        if (index >= matrix.length) {
            System.out.println("Invalid Index Values");
            return -1;
        }
        return index;
    }

    private void printUsageMessage() {
        System.out.println("1- Add Values To Matrix");
        System.out.println("2- Get Value From Matrix");
        System.out.print("-> ");
    }

    /**
     * generate the actual list index based on i, j, and k
     *
     * @param i the index in x-axis
     * @param j index in y-axis
     * @param k index in z-axis
     * @return the actual index of the list
     */
    private int getIndex(int i, int j, int k) {
        return (k * n * m) + (j * m) + i;
    }

    /**
     * Check whether value is valid or not by checking if it is bigger or equal 0
     *
     * @param value the value to be checked
     * @return true is valid otherwise false
     */
    private static boolean isValid(int value) {
        return value >= 0;
    }
}
