package board;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the road space between
 */
public class Edge {

    // The list of nodes that this edge is connected to
    private Set<Node> nodes;

    // Whether this edge has a road
    private boolean hasRoad;

    // The player who has a road on this edge, if there is a road
    private int playerNum;

    // ID for this edge
    private final int id;

    public Edge(int id) {
        nodes = new HashSet<>();

        this.id = id;
        this.hasRoad = false;
        this.playerNum = 0;
    }

    public Edge withNode(Node node) {
        nodes.add(node);
        return this;
    }

    public int getId() {
        return this.id;
    }

    public boolean hasRoad() {
        return this.hasRoad;
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public void placeRoad(int playerNum) {
        hasRoad = true;
        this.playerNum = playerNum;
    }

    public String toString() {
        String result = "[Edge: (";
        result += "id: " + id + ", ";
        result += "hasRoad: " + hasRoad + ", ";
        result += "playerNum: " + playerNum + ", ";
        result += "nodeIds: [";
        for (Node node : nodes) {
            result += node.getId() + " ";
        }
        result += "])]";

        return result;
    }
}
