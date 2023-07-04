import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X;

        // Initialize the board with empty cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (true) {
            System.out.println("\nCurrent Board:");
            displayBoard();

            System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]):");

            int row = -1;
            int col = -1;

            // Prompt the player to enter a valid move
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.matches("[1-3] [1-3]")) {
                    row = Character.getNumericValue(input.charAt(0)) - 1;
                    col = Character.getNumericValue(input.charAt(2)) - 1;

                    if (isValidMove(row, col)) {
                        break;
                    } else {
                        System.out.println("Invalid move. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter row and column numbers separated by a space.");
                }
            }

            makeMove(row, col);

            if (isGameOver()) {
                System.out.println("\nFinal Board:");
                displayBoard();
                System.out.println("Game over. The winner is " + currentPlayer + "!");
                break;
            }

            // Switch the current player
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }

        scanner.close();
    }

    private void displayBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(" " + board[row][col] + " ");

                if (col < BOARD_SIZE - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (row < BOARD_SIZE - 1) {
                System.out.println("-----------");
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY);
    }

    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private boolean isGameOver() {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] != EMPTY && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] != EMPTY && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        // Check for a tie
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
