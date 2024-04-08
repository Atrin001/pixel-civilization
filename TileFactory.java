package mygame;

import java.awt.image.BufferedImage;

public class TileFactory {
    // Assuming spriteSheetLoader is properly initialized elsewhere
    private static SpriteSheetLoader spriteSheetLoader = new SpriteSheetLoader();

    public static Tile createTile(TileType type) {
        BufferedImage tileImage = spriteSheetLoader.getTile(type);
        if (tileImage != null) {
            // Assuming Tile class is defined and has a constructor that takes a BufferedImage and TileType
            return new Tile(tileImage, type);
        } else {
            throw new IllegalArgumentException("Unsupported tile type: " + type);
        }
    }
}
