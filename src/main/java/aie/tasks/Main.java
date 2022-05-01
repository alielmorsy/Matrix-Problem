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
        matrix = new int[n * m * p];
        start(scanner);
    }

    private void start(Scanner scanner) {
        while (Thread.interrupted()) {
            printUsageMessage();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> getFromMatrix(scanner);
                case 2 -> addToMatrix(scanner);
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void getFromMatrix(Scanner scanner) {

        int index = generateIndex(scanner);
        if (index == -1) {
            return;
        }
        System.out.printf("Value: %d%n", matrix[index]);
    }

    private void addToMatrix(Scanner scanner) {
        int index = generateIndex(scanner);
        if (index == -1) return;
        System.out.print("Enter the value to add: ");
        int value = Integer.parseInt(scanner.nextLine());
        matrix[index] = value;

    }

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
        return index >= matrix.length ? -1 : index;
    }

    private void printUsageMessage() {
        System.out.println("1- Add Values To Matrix");
        System.out.println("2- Get Value From Matrix");
        System.out.println("3- Display Matrix");
        System.out.print("-> ");
    }

    private int getIndex(int i, int j, int k) {
        return (k * n * m) + (j * m) + i;
    }

    private static boolean isValid(int value) {
        return value > 0;
    }
}
