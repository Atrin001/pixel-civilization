package mygame;
public class GameBoard {
    private final Unit[][] board;
    private TileType[][] tileTypes; // Add this field to keep track of the tile types


    public GameBoard(int width, int height) {
        board = new Unit[height][width];
        tileTypes = new TileType[height][width]; // Initialize the tile types array
        // Fill the tileTypes array with default values, for example, GRASS
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tileTypes[y][x] = TileType.GRASS;
            }
        }

    }
    public TileType[][] getMap() {
        return tileTypes;
    }
    public void setTile(int x, int y, TileType type) {
        tileTypes[y][x] = type;
    }
    public void placeUnit(Unit unit, int x, int y) {
        if(unit != null) {
            unit.setPosition(x, y); // Update the unit's position
        }
        board[y][x] = unit;
    }
    public TileType getTile(int x, int y) {
        if (y >= 0 && y < tileTypes.length && x >= 0 && x < tileTypes[y].length) {
            return tileTypes[y][x];
        } else {
            return null;
        }
    }
    public void setUnit(Unit unit, int x, int y) {
        if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
            board[y][x] = unit;
            if (unit != null) {
                unit.setPosition(x, y); // Update the unit's position if it is not null
            }
        }
    }
    public Unit[][] getUnits() {
        return board;
    }

    public void adjustBoardSize(int newWidth, int newHeight) {
        if (tileTypes.length != newHeight || tileTypes[0].length != newWidth) {
            TileType[][] newMap = new TileType[newHeight][newWidth];
    
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    if (y < tileTypes.length && x < tileTypes[y].length) {
                        newMap[y][x] = tileTypes[y][x];
                    } else {
                        newMap[y][x] = TileType.GRASS; // Only new tiles outside the original bounds get set to grass
                    }
                }
            }
            tileTypes = newMap;
        }
    }

    public Unit getUnit(int x, int y) {
        return board[y][x];
    }

    public void removeUnit(int x, int y) {
        board[y][x] = null;
    }
    
    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        return board.length;
    }
    // Methods for moving units on the board
    // ...
}
