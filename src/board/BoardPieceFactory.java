package board;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class BoardPieceFactory {

    private static final List<String> BASE_TILE_RESOURCES = new ArrayList<String>(Arrays.asList(
            Tile.DESERT_TILE,
            Tile.SHEEP_TILE,
            Tile.SHEEP_TILE,
            Tile.SHEEP_TILE,
            Tile.SHEEP_TILE,
            Tile.WHEAT_TILE,
            Tile.WHEAT_TILE,
            Tile.WHEAT_TILE,
            Tile.WHEAT_TILE,
            Tile.WOOD_TILE,
            Tile.WOOD_TILE,
            Tile.WOOD_TILE,
            Tile.WOOD_TILE,
            Tile.STONE_TILE,
            Tile.STONE_TILE,
            Tile.STONE_TILE,
            Tile.BRICK_TILE,
            Tile.BRICK_TILE,
            Tile.BRICK_TILE
    ));

    private static final List<Integer> BASE_TILE_NUMBERS = new ArrayList<Integer>(Arrays.asList(
        2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12
    ));

    private static final Random random = new Random();

    private List<String> factoryTileResources;
    private List<Integer> factoryTileNumbers;

    private int nextNodeId;
    private int nextEdgeId;
    private int nextTileId;

    public BoardPieceFactory(String version) {
        this.nextNodeId = 0;
        this.nextEdgeId = 0;
        this.nextTileId = 0;

        if (Board.BASE_VERSION.equals(version)) {
            this.factoryTileResources = new ArrayList<String>(BASE_TILE_RESOURCES);
            this.factoryTileNumbers = new ArrayList<Integer>(BASE_TILE_NUMBERS);
        }
    }

    public Node getNode() {
        Node result = new Node(nextNodeId);
        nextNodeId++;
        return result;
    }

    public Edge getEdge() {
        Edge result = new Edge(nextEdgeId);
        nextEdgeId++;
        return result;
    }

    public Tile getTile() {
        String resource = factoryTileResources.remove(random.nextInt(factoryTileResources.size()));
        int number = 7;

        if (!resource.equals(Tile.DESERT_TILE)) {
            number = factoryTileNumbers.remove(random.nextInt(factoryTileNumbers.size()));
        }
        Tile result = new Tile(nextTileId, resource, number);
        nextTileId++;
        return result;
    }
}
