package me.najd;

public class Game {
    //0 = empty
    //1 = X
    //2 = O
    private int turn = 1;
    static String fixPlayer(int p) {
        if (p == 1) {
            return " X ";
        } else if (p == 2) {
            return " O ";
        }
        return " . ";
    }
    private final int[][] board = new int[3][3];
    public boolean isEmpty(int r,int c) {
        return board[r][c] == 0;
    }
    public boolean playMove(int r, int c) {
        if (this.isEmpty(r,c) && r < 3 && c < 3) {
            board[r][c] = this.getTurn();
            this.nextTurn();
            return true;
        }
        return false;
    }

    public int getTurn() {
        return turn;
    }

    public void nextTurn() {
        if (this.turn == 1) {
            this.turn = 2;
        } else {
            this.turn = 1;
        }
    }
    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(Game.fixPlayer(this.board[row][column]));
            }
            //new line plz;
            System.out.println();
        }
    }
    private int rowWin() {
        for (int column = 0; column < 3; column++) {
            if (this.board[0][column] == this.board[1][column] && this.board[1][column] == this.board[2][column]) {
                return this.board[0][column];
            }
        }
        return 0;
    }
    private int colWin() {
        for (int row = 0; row < 3; row++) {
            if (this.board[row][0] == this.board[row][1] && this.board[row][1] == this.board[row][2]) {
                return this.board[row][0];
            }
        }
        return 0;
    }
    private int digWin() { //WE'RE GONNA BRUTE IT
        if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]) {
            return this.board[1][1];
        }
        if (this.board[2][0] == this.board[1][1] && this.board[1][1] == this.board[0][2]) {
            return this.board[1][1];
        }
        return 0;
    }
    public int getFreeMoves() {
        int free = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (this.board[r][c] == 0) free++;
            }
        }
        return free;
    }
    public int hasWin() {
        //No moves! Go back!
        if (getFreeMoves() == 0) return 0;
        //this scuffed
        if (rowWin() != 0) {
            return rowWin();
        }
        if (colWin() != 0) {
            return colWin();
        }
        if (digWin() != 0) {
            return digWin();
        }
        return 0;
    }
    public void printHighlightedRow(int r) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (row != r) {
                    System.out.print(Game.fixPlayer(this.board[row][col]));
                }
                if (row == r) {
                    System.out.print(" # ");
                }

            }
            System.out.println();
        }
    }
    public void printHighlightedColumn(int c) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (col != c) {
                    System.out.print(Game.fixPlayer(this.board[row][col]));
                }
                if (col == c) {
                    System.out.print(" # ");
                }
            }
            System.out.println();
        }
    }
}
