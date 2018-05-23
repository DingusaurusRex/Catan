package board;

import java.util.ArrayList;
import java.util.Iterator;
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
        List<Node> firstLayerNodes = generateLayer(1, centerLayerNodes);
        List<Node> secondLayerNodes = generateLayer(2, firstLayerNodes);
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

    private List<Node> generateLayer(int layerNumber, List<Node> innerLayerNodes) {
        List<Node> currentLayerNodes = new ArrayList<>();

        Iterator<Node> nodeIterator = innerLayerNodes.iterator();

        Node firstOuterLayerNode = null;
        Node previousOuterLayerNode = null;
        Node firstInnerLayerNode = null;
        Node latestInnerLayerNode = null;

        for (int i = 0; i < (Tile.TILE_NUM_SIDES * layerNumber); i++) {
            // Create the tile
            Tile tile = factory.getTile();
            tiles.add(tile);

            int numInnerLayerNodes = 2;

            // Get the nodes from the previous layer
            Node lowerInnerLayerNode;
            if (firstInnerLayerNode == null) {
                if (layerNumber % 2 == 1) {
                    lowerInnerLayerNode = nodeIterator.next().withTile(tile);
                } else {
                    lowerInnerLayerNode = innerLayerNodes.get(innerLayerNodes.size() - 1).withTile(tile);
                }
                firstInnerLayerNode = lowerInnerLayerNode;
            } else {
                lowerInnerLayerNode = latestInnerLayerNode.withTile(tile);
            }
            tile.addNode(lowerInnerLayerNode);

            // Get the middle inner layer node, if needed
            if (layerNumber != 1 && i % 2 == 0) {
                Node middleNode = nodeIterator.next().withTile(tile);
                tile.addNode(middleNode);
                numInnerLayerNodes++;
            }

            Node upperInnerLayerNode;
            if (i == (Tile.TILE_NUM_SIDES * layerNumber) - 1) {
                upperInnerLayerNode = firstInnerLayerNode.withTile(tile);
            } else {
                upperInnerLayerNode = nodeIterator.next().withTile(tile);
            }

            latestInnerLayerNode = upperInnerLayerNode;

            // Add inner layer nodes to this tile
            tile.addNode(upperInnerLayerNode);

            // Get the first tile node
            Node firstTileNode;

            // If this is the first node of this layer
            if (firstOuterLayerNode == null) {
                firstTileNode = factory.getNode().withTile(tile);
                tile.addNode(firstTileNode);
                currentLayerNodes.add(firstTileNode);
                nodes.add(firstTileNode);

                Edge edge = factory.getEdge().withNode(lowerInnerLayerNode).withNode(firstTileNode);
                lowerInnerLayerNode.addEdge(edge);
                firstTileNode.addEdge(edge);
                edges.add(edge);

                firstOuterLayerNode = firstTileNode;
            } else {
                firstTileNode = previousOuterLayerNode.withTile(tile);
                tile.addNode(firstTileNode);
            }

            previousOuterLayerNode = firstTileNode;

            // Populate outer layer nodes
            for (int j = 1; j < Tile.TILE_NUM_SIDES - numInnerLayerNodes; j++) {
                Node node;
                // If this is the last tile and the last node to generate on that tile, use the node
                if (j == Tile.TILE_NUM_SIDES - 3 && i == (Tile.TILE_NUM_SIDES * layerNumber) - 1) {
                    node = firstOuterLayerNode.withTile(tile);
                    tile.addNode(node);
                } else {
                    node = factory.getNode().withTile(tile);
                    tile.addNode(node);
                    currentLayerNodes.add(node);
                    nodes.add(node);
                }

                // Add edge
                Edge edge = factory.getEdge().withNode(previousOuterLayerNode).withNode(node);
                previousOuterLayerNode.addEdge(edge);
                node.addEdge(edge);
                edges.add(edge);

                previousOuterLayerNode = node;
            }

            // generate edge to inner layer for last outer layer node
            if (i != (Tile.TILE_NUM_SIDES * layerNumber) - 1) {
                Edge edge = factory.getEdge().withNode(previousOuterLayerNode).withNode(upperInnerLayerNode);
                previousOuterLayerNode.addEdge(edge);
                upperInnerLayerNode.addEdge(edge);
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
