package mygame;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;


public class MapVisualizer extends JPanel {
    private TileType[][] map;
    private final int tileSize = 20; // Or whatever your tile size should be

    public MapVisualizer(TileType[][] map) {
        this.map = map;
        int preferredWidth = map[0].length * tileSize;
        int preferredHeight = map.length * tileSize;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                drawTile(g, x, y);
            }
        }
    }

    private void drawTile(Graphics g, int x, int y) {
        switch (map[y][x]) {
            case GRASS:
                g.setColor(Color.GREEN);
                break;
            case TREE1:
                g.setColor(new Color(0, 100, 0)); // Darker green
                break;
            case WATER:
                g.setColor(Color.BLUE);
                break;
            case MOUNTAIN:
                g.setColor(Color.GRAY);
                break;
            case ROAD:
                g.setColor(Color.ORANGE);
                break;
            case BUILDING:
                g.setColor(Color.RED);
                break;
            // Add cases for other TileTypes
        }
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }
}