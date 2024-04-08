package mygame;
public class MapTest {
    public static void main(String[] args) {
        TileType[][] map = generateMap(30, 30); // Assuming you're generating a 30x30 map

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println(); // New line at the end of each row
        }
    }

    public static TileType[][] generateMap(int width, int height) {
        TileType[][] map = new TileType[height][width];
        
        // Fill the map with grass as the base tile
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = TileType.GRASS;
            }
        }

        // Add trees to create a forest
        for (int y = 7; y < 10; y++) {
            for (int x = 10; x < 15; x++) {
                map[y][x] = TileType.TREE1;
            }
        }
        for (int y = 10; y < 13; y++) {
            for (int x = 10; x < 15; x++) {
                map[y][x] = TileType.TREE1;
            }
        }

        // Create a river
        for (int x = 0; x < width; x++) {
            map[20][x] = TileType.WATER;
        }

        // Add mountains
        for (int y = 2; y < 7; y++) {
            map[y][5] = TileType.MOUNTAIN;
        }

        // Place a road
        for (int x = 5; x < 20; x++) {
            map[25][x] = TileType.ROAD;
        }

        // Position buildings
        map[5][5] = TileType.BUILDING;
        map[15][15] = TileType.BUILDING;

        // ... additional logic to place tiles according to your game design

        return map;
    }

    // ... Your TileType enum and other classes ...
}
