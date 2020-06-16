import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    
        //Filling the 2D char array with 9 spaces.
        String input = "         ";
        
        char[] first = input.substring(0, 3).toCharArray();
        char[] second = input.substring(3, 6).toCharArray();
        char[] third = input.substring(6, 9).toCharArray();
        char[][] board = {first, second, third};
        char winner = 0;
        int player = 0;
        
        //Printing the board before the while loop.
        printBoard(board);
        
        while (true) {
            //checking done first so that if either X or O wins, we break the loop.
            winner = checkWinner(board);
            
            if (winner == 'X') {
                System.out.println("X wins");
                break;
            } else if (winner == 'O') {
                System.out.println("O wins");
                break;
            } else if (winner == 'D') {
                System.out.println("Draw");
                break;
            } else if (winner == 'I') {
                System.out.println("Impossible");
                break;
            }
            moveCross(board, player);
            //Increasing the count of player. By increasing count by 1, we are changing from odd to even and so forth.
            //So, if the count is odd, its the turn of one user and if the count is even, its the turn of other user.
            player++;
            printBoard(board);
        }
    }


    public static void printBoard(char[][] board) {
        System.out.println("---------");
        System.out.println("| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |");
        System.out.println("---------");
    }

    public static void moveCross(char[][] board, int player) {
        boolean notOccupied = true;
        int pos1 = 0;
        int pos2 = 0;
        int index1, index2;
        while (notOccupied) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter coordinates: ");
//        int pos1 = scanner.nextInt();
//        int pos2 = scanner.nextInt();

            if (scanner.hasNextInt()) {
                pos1 = scanner.nextInt();
            } else {
                //Skipping the whole line
                System.out.println("You should enter numbers!");
                continue;
            }
            if (scanner.hasNextInt()) {
                pos2 = scanner.nextInt();
            } else {
                //Skipping the whole line
                System.out.println("You should enter numbers!");
                continue;
            }
            index1 = 3 - pos2;
            index2 = pos1 - 1;
            if (index1 < 0 || index1 > 2 || index2 < 0 || index2 > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (board[index1][index2] != ' ') {
                System.out.println("The cell is occupied! Choose another one!");
                continue;
            }
            notOccupied = false;
        }

        //setting default value for output
        char output = ' ';
        //count of player in main method depends if it is 'X' or 'O'
        
        if (player % 2 == 0) {
            output = 'X';
        } else if (player % 2 != 0) {
            output = 'O';
        }
        if (pos1 == 1 && pos2 == 3) {
            board[0][0] = output;
        } else if (pos1 == 2 && pos2 == 3) {
            board[0][1] = output;
        } else if (pos1 == 3 && pos2 == 3) {
            board[0][2] = output;
        } else if (pos1 == 1 && pos2 == 2) {
            board[1][0] = output;
        } else if (pos1 == 2 && pos2 == 2) {
            board[1][1] = output;
        } else if (pos1 == 3 && pos2 == 2) {
            board[1][2] = output;
        } else if (pos1 == 1 && pos2 == 1) {
            board[2][0] = output;
        } else if (pos1 == 2 && pos2 == 1) {
            board[2][1] = output;
        } else if (pos1 == 3 && pos2 == 1) {
            board[2][2] = output;
        }
    }

    public static char checkWinner(char[][] board) {
        int xWins = 0;
        int oWins = 0;
        //Checking wins in column
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                //return board[0][i];
                //My previous implementation to send winner didn't work for implementing 'impossible'. So had to make some changes.
                if (board[0][i] == 'X') {
                    xWins++;
                } else if (board[0][i] == 'O') {
                    oWins++;
                }
            }
        }
        //Checking wins in row
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                //return board[i][0];
                if (board[i][0] == 'X') {
                    xWins++;
                } else if (board[i][0] == 'O') {
                    oWins++;
                }
            }
        }
        //Checking wins diagonally
        for (int i = 0; i < 3; i++) {
            if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
                //board[1][1] is the common point for the diagonal
                // return board[1][1];
                if (board[1][1] == 'X') {
                    xWins++;
                } else if (board[1][1] == 'O') {
                    oWins++;
                }
            }
        }

        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                } else if (board[i][j] == 'O') {
                    countO++;
                }
            }
        }
        //Returning the winning characters. We can use it to compare in main method to find out the winner.
        if ((xWins != 0) && (oWins != 0)) {
            return 'I';
        } else if ((xWins != 0) && (oWins == 0)) {
            return 'X';
        } else if ((xWins == 0) && (oWins != 0)) {
            return 'O';
        } else if (((countX == 4 && countO == 5) || (countX == 5 && countO == 4)) && (xWins == 0 && oWins == 0)) {
            return 'D';
        } else if ((countO - countX) < -2 || (countX - countO) < -2) {
            return 'I';
        } else {
            return 0;
        }
    }
}
