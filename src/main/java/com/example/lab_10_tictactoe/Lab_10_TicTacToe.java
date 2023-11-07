package com.example.lab_10_tictactoe;
import java.util.Scanner;

public class Lab_10_TicTacToe {
        private static final int ROW = 3;
        private static final int COL = 3;
        private static String[][] board = new String[ROW][COL];
        private static String currentPlayer = "X"; // X always moves first

        public static void main(String[] args) {
            clearBoard();

            boolean gameFinished = false;
            Scanner scanner = new Scanner(System.in);

            while (!gameFinished) {
                display();
                int[] move = getPlayerMove(scanner);

                int row = move[0];
                int col = move[1];

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    gameFinished = isWin(currentPlayer) || isTie();

                    if (gameFinished) {
                        display();
                        if (isWin(currentPlayer)) {
                            System.out.println(currentPlayer + " wins!");
                        } else {
                            System.out.println("It's a tie!");
                        }

                        System.out.println("Do you want to play again? (yes/no)");
                        String playAgain = scanner.next();
                        if (playAgain.equalsIgnoreCase("no")) {
                            gameFinished = true;
                        } else {
                            clearBoard();
                            currentPlayer = "X";
                        }
                    } else {
                        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            scanner.close();
        }

        private static void clearBoard() {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    board[i][j] = " ";
                }
            }
        }

        private static void display() {
            System.out.println("-------------");
            for (int i = 0; i < ROW; i++) {
                System.out.print("| ");
                for (int j = 0; j < COL; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println("\n-------------");
            }
        }

        private static int[] getPlayerMove(Scanner scanner) {
            int[] move = new int[2];
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
                move[0] = scanner.nextInt() - 1;
                move[1] = scanner.nextInt() - 1;

                if (move[0] >= 0 && move[0] < ROW && move[1] >= 0 && move[1] < COL) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Row and column must be between 1 and 3.");
                }
            }

            return move;
        }

        private static boolean isValidMove(int row, int col) {
            return board[row][col].equals(" ");
        }

        private static boolean isWin(String player) {
            return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
        }

        private static boolean isColWin(String player) {
            for (int col = 0; col < COL; col++) {
                if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                    return true;
                }
            }
            return false;
        }

        private static boolean isRowWin(String player) {
            for (int row = 0; row < ROW; row++) {
                if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                    return true;
                }
            }
            return false;
        }

        private static boolean isDiagonalWin(String player) {
            return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                    (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
        }

        private static boolean isTie() {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (board[i][j].equals(" ")) {
                        return false;
                    }
                }
            }
            return true;
        }
}
