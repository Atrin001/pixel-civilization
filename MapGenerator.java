package mygame;
import java.util.Random;

public class MapGenerator {
    private static final Random random = new Random();

    public static TileType[][] generateMap(int width, int height) {
        TileType[][] map = new TileType[height][width];

        // Step 1: Generate natural terrain
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = generateNaturalTerrain();
            }
        }

        // Step 2: Generate a main road running from west to east
        int roadY = random.nextInt(height); // Random row for the road
        for (int x = 0; x < width; x++) {
            map[roadY][x] = TileType.ROAD;
        }

        // Step 3: Place buildings near the road
        for (int x = 0; x < width; x++) {
            if (map[roadY][x] == TileType.ROAD) {
                // Place buildings above the road if there's space
                // if (roadY > 0) {
                //     map[roadY - 1][x] = TileType.BUILDING;
                // }
                // Place buildings below the road if there's space
                // if (roadY < height - 1) {
                //     map[roadY + 1][x] = TileType.BUILDING;
                // }
            }
        }

        return map;
    }

    private static TileType generateNaturalTerrain() {
        // Simplified probabilities for natural terrain generation
        float chance = random.nextFloat();
        if (chance < 0.02) {
            return TileType.TREE1;
        } else if (chance < 0.04) {
            return TileType.WATER;
        } else if (chance < 0.06) {
            return TileType.MOUNTAIN;
        }
        else if (chance < 0.08) {
            return TileType.TREE2;
        } else {
            return TileType.GRASS;
        }
    }
}
