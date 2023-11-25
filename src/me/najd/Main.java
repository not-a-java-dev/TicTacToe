package me.najd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner readPInput = new Scanner(System.in);
        Game playGame = new Game();
        boolean Win = false;
        while (!Win) {
            System.out.println("You are " + Game.fixPlayer(playGame.getTurn()));
            System.out.print("Row: ");
            int R;
            try {
                R = Integer.parseInt(readPInput.nextLine());
            } catch (Exception e) {
                R = -1; //boo!
            }
            playGame.printHighlightedRow(R);
            System.out.print("Column: ");
            int C;
            try {
                C = Integer.parseInt(readPInput.nextLine());
            } catch (Exception e) {
                C = -1; //boo!
            }
            playGame.printHighlightedColumn(C);
            boolean legal = playGame.playMove(R,C);
            if (legal) {
                int whoWon = playGame.hasWin();
                if (whoWon == 1) {
                    System.out.println(Game.fixPlayer(whoWon) + "Wins!");
                    Win = true;
                } else if (whoWon == 2) {
                    System.out.println(Game.fixPlayer(whoWon) + "Wins!");
                    Win = true;
                } else if (playGame.getFreeMoves() == 0) {
                    System.out.println("Draw!");
                    Win = true; //draw
                }
            } else {
                System.out.println("Nuh uh");
            }
            playGame.printBoard();
        }
        readPInput.close();
    }
}
