import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class tictactoe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    static int isOver = 0;

    public static void main(String[] args) {
        // imports
        Random rand = new Random();

        // array to look like a tic tac toe board
        char[][] board = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

        printGameBoard(board);

        while (isOver != 1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the place you wanna play: ");

            int playerPos = sc.nextInt();

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Incorrect Move");
                System.out.println("Enter the place you wanna play: ");
                playerPos = sc.nextInt();
            }
            String checkWinner = checkWinner();
            placePiece(board, playerPos, "player");
            if (checkWinner.length() > 0) {
                System.out.println(checkWinner);
                break;
            }

            int cpuPos = rand.nextInt(9) + 1;

            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(board, cpuPos, "cpu");

            checkWinner = checkWinner();
            if (checkWinner.length() > 0) {
                System.out.println(checkWinner);
                break;
            }

        }
    }

    public static void placePiece(char[][] board, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;

        }
        if (user.equals("cpu")) {
            printGameBoard(board);

        }
        System.out.println();

    }

    // printing the board
    public static void printGameBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static String checkWinner() {

        List<Integer> firstRow = Arrays.asList(1, 2, 3);
        List<Integer> secondRow = Arrays.asList(4, 5, 6);
        List<Integer> thirdRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> middleCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> leftDiag = Arrays.asList(1, 5, 9);
        List<Integer> rightDiag = Arrays.asList(3, 5, 7);

        List<List> condition = new ArrayList<List>();
        condition.add(firstRow);
        condition.add(secondRow);
        condition.add(thirdRow);
        condition.add(leftCol);
        condition.add(middleCol);
        condition.add(rightCol);
        condition.add(leftDiag);
        condition.add(rightDiag);
        for (List list : condition) {
            if (playerPositions.containsAll(list)) {
                isOver = 1;
                return "Nice, Player has won! ";
            } else if (cpuPositions.containsAll(list)) {
                isOver = 1;
                return "sed cat u lost";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                isOver = 1;
                return "board full";
            }
        }
        return "";
    }

}