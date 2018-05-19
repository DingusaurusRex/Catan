package board;

import java.util.Set;

public class Tile {

    public static final String DESERT_TILE = "DESERT";
    public static final String SHEEP_TILE = "SHEEP";
    public static final String WHEAT_TILE = "WHEAT";
    public static final String WOOD_TILE = "WOOD";
    public static final String STONE_TILE = "STONE";
    public static final String BRICK_TILE = "BRICK";

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
        this.id = id;
        this. resource = resource;
        this.number = number;
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
}
