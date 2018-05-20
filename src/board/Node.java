package board;

import java.util.HashSet;
import java.util.Set;

public class Node {

    public static final int EMPTY_NODE = 0;
    public static final int SETTLEMENT_NODE = 1;
    public static final int CITY_NODE = 2;

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
        edges = new HashSet<>();
        tiles = new HashSet<>();

        this.id = id;
        this.settlementType = EMPTY_NODE;
        this.playerNum = 0;
    }

    public Node withTile(Tile tile) {
        tiles.add(tile);
        return this;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public int getId() {
        return this.id;
    }

    public int getSettlementType() {
        return this.settlementType;
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public boolean hasSettlement() {
        return this.settlementType == EMPTY_NODE;
    }

    public void placeSettlement(int settlementType, int playerNum) {
        this.settlementType = settlementType;
        this.playerNum = playerNum;
    }

    public String toString() {
        String result = "[Node: (";
        result += "id: "+ id + ", ";
        result += "settlementType: " + settlementType + ", ";
        result += "playerNum: " + playerNum + ", ";
        result += "edgeIds: [";
        for (Edge edge : edges) {
            result += edge.getId() + " ";
        }
        result += "], tileIds: [";
        for (Tile tile : tiles) {
            result += tile.getId() + " ";
        }
        result += "])]";
        return result;
    }
}
