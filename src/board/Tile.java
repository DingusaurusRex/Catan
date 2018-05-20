package board;

import java.util.HashSet;
import java.util.Set;

public class Tile {

    public static final String DESERT_TILE = "DESERT";
    public static final String SHEEP_TILE = "SHEEP";
    public static final String WHEAT_TILE = "WHEAT";
    public static final String WOOD_TILE = "WOOD";
    public static final String STONE_TILE = "STONE";
    public static final String BRICK_TILE = "BRICK";

    public static final int TILE_NUM_SIDES = 6;

    // List of nodes on this tile
    private Set<Node> nodes;

    // Whether the robber is on this tile
    private boolean hasRobber;

    // The resource on this tile
    private final String resource;

    // The number to roll on this tile
    private final int number;

    // tile ID
    private final int id;

    public Tile(int id, String resource, int number) {
        this.nodes = new HashSet<>();

        this.id = id;
        this. resource = resource;
        this.number = number;
        this.hasRobber = resource.equals(DESERT_TILE);
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public String getResource() {
        return this.resource;
    }

    public int getNumber() {
        return this.number;
    }

    public int getId() {
        return this.id;
    }

    public void setRobber(boolean hasRobber) {
        this.hasRobber = hasRobber;
    }

    public String toString() {
        String result = "[Tile: (";
        result += "id: " + id + ", ";
        result += "resource: " + resource + ", ";
        result += "number: " + number + ", ";
        result += "nodeIds: [";
        for (Node node : nodes) {
            result += node.getId() + " ";
        }
        result += "])]";
        return result;
    }
}
