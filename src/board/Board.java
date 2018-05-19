package board;

import java.util.List;

public class Board {

    public static final String BASE_VERSION = "BASE";

    private final BoardPieceFactory factory;

    private List<Node> nodes;
    private List<Edge> edges;
    private List<Tile> tiles;

    public Board(String version) {
        factory = new BoardPieceFactory(version);
        generateBoard(version);
    }

    private void generateBoard(String version) {
        switch(version) {
            case BASE_VERSION:
            default:
                generateBaseBoard();
        }
    }

    private void generateBaseBoard() {

    }
}
