package me.najd;

public class Game {
    //0 = empty
    //1 = X
    //2 = O

    /** Either one or two,
     *  Where 1 is X and 2 is O
     */
    private int turn = 1;

    /**
     * Makes the turn/player/space human-readable
     * @param p The Player, this can be also 0 (Empty)
     * @return a {@link String} that is human-readable
     */
    static String fixPlayer(int p) {
        if (p == 1) {
            return " X ";
        } else if (p == 2) {
            return " O ";
        }
        return " . ";
    }

    /**
     * Board, where the game takes place.
     * A 3x3 Matrix
     */
    private final int[][] board = new int[3][3];

    /**
     * Check if a place in the board is empty
     * @param r The row to check
     * @param c The column to check
     * @return true if it is empty or false if it isn't (duh)
     */
    public boolean isEmpty(int r,int c) {
        if (r > -1 && c > -1 && r < 3 && c < 3) return board[r][c] == 0;
        return false;
    }

    /**
     * This is what drives the game
     *
     * @param r The row where you want the move to be
     * @param c The column where you want the move to be
     * @return A {@link Boolean} true if it's legal and false if it isn't.
     *         The same as {@link #isEmpty(int, int)}
     */
    public boolean playMove(int r, int c) {
        if (this.isEmpty(r,c) && r < 3 && c < 3) {
            board[r][c] = this.getTurn();
            this.nextTurn();
            return true;
        }
        return false;
    }

    /**
     * Returns what player's goes now
     * @return The player that goes now
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Make the next turn.
     * If 1 then 2.
     * If 2 then 1.
     */
    public void nextTurn() {
        if (this.turn == 1) {
            this.turn = 2;
        } else {
            this.turn = 1;
        }
    }

    /**
     * Prints the board to the screen
     * So you know.
     * The game is playable?
     */
    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(Game.fixPlayer(this.board[row][column]));
            }
            //new line plz;
            System.out.println();
        }
    }

    /**
     * Returns who won, in the rows
     * @return Who won (0 if none)
     * @see #turn
     */
    private int rowWin() {
        for (int column = 0; column < 3; column++) {
            if (this.board[0][column] == this.board[1][column] && this.board[1][column] == this.board[2][column]) {
                return this.board[0][column];
            }
        }
        return 0;
    }
    /**
     * Returns who won, in the columns
     * @return Who won (0 if none)
     * @see #turn
     */
    private int colWin() {
        for (int row = 0; row < 3; row++) {
            if (this.board[row][0] == this.board[row][1] && this.board[row][1] == this.board[row][2]) {
                return this.board[row][0];
            }
        }
        return 0;
    }
    /**
     * Returns who won, diagonally
     * @return Who won (0 if none)
     * @see #turn
     */
    private int digWin() { //WE'RE GONNA BRUTE IT
        if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]) {
            return this.board[1][1];
        }
        if (this.board[2][0] == this.board[1][1] && this.board[1][1] == this.board[0][2]) {
            return this.board[1][1];
        }
        return 0;
    }

    /**
     * Get the numbers of empty spaces.
     * This is used to see if there is a draw or not
     * @return an {@link Integer} of moves left
     */
    public int getFreeMoves() {
        int free = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (this.board[r][c] == 0) free++;
            }
        }
        return free;
    }

    /**
     * Return who won. If they did...
     * @return Who won (0 if no one)
     */
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

    /**
     * Print the row, but highlighted,
     * to make the move decision
     * much easier.
     * @param r Row
     */
    public void printHighlightedRow(int r) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (row != r) {
                    System.out.print(Game.fixPlayer(this.board[row][col]));
                }
                if (row == r && this.board[row][col] == 0) {
                    System.out.print(" # ");
                } else if (row == r){
                    System.out.print(Game.fixPlayer(this.board[row][col]));
                }

            }
            System.out.println();
        }
    }

    /**
     * Print the column, but highlighted,
     * to make the move decision
     * much easier.
     * @param c Column
     */
    public void printHighlightedColumn(int c) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (col != c ) {
                    System.out.print(Game.fixPlayer(this.board[row][col]));
                }
                if (col == c && this.board[row][col] == 0) {
                    System.out.print(" # ");
                } else if (col == c) {
                    System.out.print(Game.fixPlayer(this.board[row][col]));
                }
            }
            System.out.println();
        }
        System.out.println("*********");
    }
}
