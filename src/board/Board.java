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
        List<Node> centerLayerNodes = generateCenterTile();
        List<Node> secondLayerNodes = generateFirstLayer(centerLayerNodes);
    }

    private List<Node> generateCenterTile() {
        List<Node> centerLayerNodes = new ArrayList<>();

        Tile centerTile = factory.getTile();
        tiles.add(centerTile);

        Node firstNode = factory.getNode().withTile(centerTile);
        centerTile.addNode(firstNode);
        centerLayerNodes.add(firstNode);
        nodes.add(firstNode);
        Node previousNode = firstNode;

        for (int i = 1; i < Tile.TILE_NUM_SIDES; i++) {
            Node node = factory.getNode().withTile(centerTile);
            centerTile.addNode(node);
            centerLayerNodes.add(node);
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

        return centerLayerNodes;
    }

    private List<Node> generateFirstLayer(List<Node> centerLayerNodes) {
        List<Node> currentLayerNodes = new ArrayList<>();

        Node firstLayerNode = null;
        Node previousTileNode = null;

        for (int i = 0; i < Tile.TILE_NUM_SIDES; i++) {
            // Create the tile
            Tile tile = factory.getTile();
            tiles.add(tile);

            // Get the nodes from the previous layer
            Node lowerPreviousLayerNode = centerLayerNodes.get(i).withTile(tile);
            Node upperPreviousLayerNode;
            if (i == Tile.TILE_NUM_SIDES - 1) {
                upperPreviousLayerNode = centerLayerNodes.get(0).withTile(tile);
            } else {
                upperPreviousLayerNode = centerLayerNodes.get(i + 1).withTile(tile);
            }

            // Add previous layer nodes to this tile
            tile.addNode(lowerPreviousLayerNode);
            tile.addNode(upperPreviousLayerNode);

            // Get the first tile node
            Node firstTileNode;

            // If this is the first node of this layer
            if (firstLayerNode == null) {
                firstTileNode = factory.getNode().withTile(tile);
                tile.addNode(firstTileNode);
                currentLayerNodes.add(firstTileNode);
                nodes.add(firstTileNode);

                Edge edge = factory.getEdge().withNode(lowerPreviousLayerNode).withNode(firstTileNode);
                lowerPreviousLayerNode.addEdge(edge);
                firstTileNode.addEdge(edge);
                edges.add(edge);

                firstLayerNode = firstTileNode;
            } else {
                firstTileNode = previousTileNode.withTile(tile);
                tile.addNode(firstTileNode);
            }

            previousTileNode = firstTileNode;

            // Populate outer layer nodes
            for (int j = 1; j < Tile.TILE_NUM_SIDES - 2; j++) {
                Node node;
                // If this is the last tile and the last node to generate on that tile, use the first tile
                if (j == Tile.TILE_NUM_SIDES - 3 && i == Tile.TILE_NUM_SIDES - 1) {
                    node = firstLayerNode.withTile(tile);
                    tile.addNode(node);
                } else {
                    node = factory.getNode().withTile(tile);
                    tile.addNode(node);
                    currentLayerNodes.add(node);
                    nodes.add(node);
                }

                // Add edge
                Edge edge = factory.getEdge().withNode(previousTileNode).withNode(node);
                previousTileNode.addEdge(edge);
                node.addEdge(edge);
                edges.add(edge);

                previousTileNode = node;
            }

            // generate edge to inner layer for last outer layer node
            if (i != Tile.TILE_NUM_SIDES - 1) {
                Edge edge = factory.getEdge().withNode(previousTileNode).withNode(upperPreviousLayerNode);
                previousTileNode.addEdge(edge);
                upperPreviousLayerNode.addEdge(edge);
                edges.add(edge);
            }
        }

        return currentLayerNodes;
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
