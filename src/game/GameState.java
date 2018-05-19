package game;

import board.Board;

public class GameState {

    public Board board;

    public GameState(String version) {
        this.board = new Board(version);
    }
}
