package board;

import java.util.Set;

public class Node {

    // Edges on this Node
    private Set<Edge> edges;

    // Tiles on this Node
    private Set<Tile> tiles;

    // Type of settlement on this node
    private int settlementType;

    // Player who has a settlement on this node, if there is one
    private int playerNum;

    // Node ID
    private final int id;

    public Node(int id) {
        this.id = id;
    }

    public Node withEdge(Edge edge) {
        edges.add(edge);
        return this;
    }

    public Node withTile(Tile tile) {
        tiles.add(tile);
        return this;
    }

    public void placeSettlement(int settlementType, int playerNum) {
        this.settlementType = settlementType;
        this.playerNum = playerNum;
    }
}
