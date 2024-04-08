package mygame;

import java.awt.Point;
import java.util.EnumMap;
import java.util.Map;

public class TileRule {
    private final Map<TileType, Point> tileSprites;
    private final Map<TileType, Integer> tileWeights;
    private final Map<TileType, int[]> tileEdges;

    public TileRule() {
        tileSprites = new EnumMap<>(TileType.class);
        tileWeights = new EnumMap<>(TileType.class);
        tileEdges = new EnumMap<>(TileType.class);

        // Initialize sprite positions for each tile type
        tileSprites.put(TileType.GRASS, new Point(16, 0));
        tileSprites.put(TileType.WATER, new Point(128, 176));
        // ... add other tile types

        // Initialize weights for each tile type
        tileWeights.put(TileType.GRASS, 16);
        tileWeights.put(TileType.WATER, 4);
        // ... add other tile types

        // Initialize edges for each tile type
        tileEdges.put(TileType.GRASS, new int[] {0, 0, 0, 0}); // Example: {NORTH, EAST, SOUTH, WEST}
        tileEdges.put(TileType.WATER, new int[] {1, 1, 1, 1});
        // ... add other tile types
    }

    public Point getSpritePosition(TileType type) {
        return tileSprites.get(type);
    }

    public int getWeight(TileType type) {
        return tileWeights.getOrDefault(type, 1);
    }

    public int[] getEdges(TileType type) {
        return tileEdges.get(type);
    }
}
