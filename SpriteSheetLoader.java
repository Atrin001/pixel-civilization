package mygame;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Point;


public class SpriteSheetLoader {
    private Map<TileType, BufferedImage> tileImages;
    private Map<TileType, Point> tilePositions;

    public SpriteSheetLoader() {
        this.tileImages = new HashMap<>();
        this.tilePositions = new HashMap<>();
    }

    public void setTileImage(TileType type, BufferedImage image) {
        tileImages.put(type, image);
    }

    public BufferedImage getTile(TileType type) {
        return tileImages.get(type);
    }
    
    // Helper method to load images
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
