package board;

import java.util.Set;

public class Tile {

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

    public void setRobber(boolean hasRobber) {
        this.hasRobber = hasRobber;
    }
}
