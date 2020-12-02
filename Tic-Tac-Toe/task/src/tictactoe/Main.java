package tictactoe;


import java.util.Scanner;

public class Main {
    static int count = 1;
    static boolean xWinners = false;
    static boolean oWinners = false;
    static boolean draw = false;

    public static void main(String[] args) {
        Character[][] matrix = new Character[3][3];
        startGame(matrix);
    }
    public static void startGame (Character[][] matrix) {
        createEmptyField(matrix);
        printField(matrix);
        enterCoordinates(matrix);
    }

    public static void createEmptyField (Character[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = '_';
            }
        }
    }
    
    public static void enterCells(Character[][] matrix) {
        System.out.print("Enter cells: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        char[] elements = str.toCharArray();
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = elements[count];
                count++;
            }
        }

    }

    public static void printField(Character[][] matrix) {
        System.out.println("---------");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static void enterCoordinates(Character[][] matrix) {
        System.out.print("Enter the coordinates: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int firstCoor = Integer.parseInt(scanner.next());
            int secondCoor = Integer.parseInt(scanner.next());
            if (firstCoor > 3 || firstCoor < 1 || secondCoor > 3 || secondCoor < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                enterCoordinates(matrix);
            }
            else if (matrix[Math.abs(secondCoor - 3)][firstCoor - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                enterCoordinates(matrix);
            }
            else {
                if (count % 2 != 0) {
                    matrix[Math.abs(secondCoor - 3)][firstCoor - 1] = 'X';
                }
                else {
                    matrix[Math.abs(secondCoor - 3)][firstCoor - 1] = 'O';
                }
                count++;
                printField(matrix);
                checkGameStatus(matrix);
                if (xWinners || oWinners || draw) {
                    return;
                }
                enterCoordinates(matrix);

            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            enterCoordinates(matrix);
        }


    }

    public static void checkGameStatus(Character[][] matrix) {
        int emptyCount = 0;
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '_') {
                    emptyCount++;
                }
                if (matrix[i][j] == 'X') {
                    xCount++;
                }
                if (matrix[i][j] == 'O') {
                    oCount++;
                }
            }

        }


        if (matrix[0][0] + matrix[0][1] + matrix[0][2] == 264 ||
                matrix[1][0] + matrix[1][1] + matrix[1][2] == 264 ||
                matrix[2][0] + matrix[2][1] + matrix[2][2] == 264 ||
                matrix[0][0] + matrix[1][0] + matrix[2][0] == 264 ||
                matrix[0][1] + matrix[1][1] + matrix[2][1] == 264 ||
                matrix[0][2] + matrix[1][2] + matrix[2][2] == 264 ||
                matrix[0][0] + matrix[1][1] + matrix[2][2] == 264 ||
                matrix[0][2] + matrix[1][1] + matrix[2][0] == 264) {

            xWinners = true;

        }

        if (matrix[0][0] + matrix[0][1] + matrix[0][2] == 237 ||
                matrix[1][0] + matrix[1][1] + matrix[1][2] == 237 ||
                matrix[2][0] + matrix[2][1] + matrix[2][2] == 237 ||
                matrix[0][0] + matrix[1][0] + matrix[2][0] == 237 ||
                matrix[0][1] + matrix[1][1] + matrix[2][1] == 237 ||
                matrix[0][2] + matrix[1][2] + matrix[2][2] == 237 ||
                matrix[0][0] + matrix[1][1] + matrix[2][2] == 237 ||
                matrix[0][2] + matrix[1][1] + matrix[2][0] == 237) {

            oWinners = true;
        }

        if (Math.abs(xCount - oCount) > 1 || xWinners && oWinners) {
            System.out.println("Impossible");
            return;
        }
        else if (xWinners) {
            System.out.println("X wins");
            return;
        }
        else if (oWinners) {
            System.out.println("O wins");
            return;
        }

        else if (count == 10) {
            draw = true;
            System.out.println("Draw");
        }
        else if (emptyCount > 0) {
            //System.out.println("Game not finished");
        }


    }
}
