package board;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final String BASE_VERSION = "BASE";

    private final BoardPieceFactory factory;

    private List<Node> nodes;
    private List<Edge> edges;
    private List<Tile> tiles;

    public Board(String version) {
        factory = new BoardPieceFactory(version);

        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        tiles = new ArrayList<>();

        generateBoard(version);
    }

    private void generateBoard(String version) {
        switch(version) {
            case BASE_VERSION:
            default:
                generateBaseBoard();
        }
        printBoard();
    }

    private void generateBaseBoard() {
        generateCenterTile();
    }

    private void generateCenterTile() {
        Tile centerTile = factory.getTile();
        tiles.add(centerTile);

        List<Node> firstLayerNodes = new ArrayList<>();

        Node firstNode = factory.getNode().withTile(centerTile);
        centerTile.addNode(firstNode);
        firstLayerNodes.add(firstNode);
        nodes.add(firstNode);
        Node previousNode = firstNode;

        for (int i = 1; i < Tile.TILE_NUM_SIDES; i++) {
            Node node = factory.getNode().withTile(centerTile);
            centerTile.addNode(node);
            firstLayerNodes.add(node);
            nodes.add(node);

            Edge edge = factory.getEdge().withNode(previousNode).withNode(node);
            node.addEdge(edge);
            previousNode.addEdge(edge);
            edges.add(edge);

            previousNode = node;
        }

        Edge edge = factory.getEdge().withNode(previousNode).withNode(firstNode);
        previousNode.addEdge(edge);
        firstNode.addEdge(edge);
        edges.add(edge);
    }

    public void printBoard() {
        System.out.println("Nodes:");
        for (Node node : nodes) {
            System.out.println(node);
        }
        System.out.println();
        System.out.println("Edges:");
        for (Edge edge : edges) {
            System.out.println(edge);
        }
        System.out.println();
        System.out.println("Tiles:");
        for (Tile tile : tiles) {
            System.out.println(tile);
        }
    }
}
