package mygame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Graphics;

public class Tile {
    private BufferedImage sprite;
    private List<TileType> possibleTypes;
    private TileType type;
    private boolean collapsed;
    
    public Tile(BufferedImage sprite, TileType type) {
        this.sprite = sprite;
        this.type = type;
        this.possibleTypes = new ArrayList<>(List.of(TileType.values()));
        this.collapsed = false;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }   

    public void collapse(Random random) {
        // Randomly select one of the possible types
        type = possibleTypes.get(random.nextInt(possibleTypes.size()));
        collapsed = true;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public BufferedImage getImage() { // Method to get the image of the tile
        return sprite;
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(sprite, x, y, null);
    }
}
