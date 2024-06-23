package main.java.ssss;

import main.java.ssss.board.Board;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(board);
        game.GameLoop();
    }
}
