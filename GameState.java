
package mygame;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;


public class GameState {
    private GameBoard board;
    private Point firstHousePosition; // Position of the first house
    private Point secondHousePosition; // Position of the second house
    private int currentPlayer; // 1 for player one, 2 for player two

    public GameState(int width, int height) {
        this.board = new GameBoard(width, height);
        generateRandomMap();
        firstHousePosition = null;
        secondHousePosition = null;
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void switchPlayer() {
        currentPlayer = (currentPlayer % 2) + 1; // Switch between 1 and 2
    }
    
    public GameBoard getBoard() {
        return board;
    }
    public Point getFirstHousePosition() {
        return firstHousePosition;
    }

    public void setFirstHousePosition(int x, int y) {
        firstHousePosition = new Point(x, y);
    }

    public Point getSecondHousePosition() {
        return secondHousePosition;
    }

    public void setSecondHousePosition(int x, int y) {
        secondHousePosition = new Point(x, y);
    }

    

    public void generateRandomMap() {
        Random random = new Random();
        TileType[][] map = board.getMap();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                double chance = random.nextDouble();
                if (chance < 0.02) {
                    map[y][x] = TileType.TREE1;
                } else if (chance < 0.05) {
                    map[y][x] = TileType.TREE2;
                } else if (chance < 0.85) {
                    map[y][x] = TileType.GRASS;
                } else if (chance < 0.95) {
                    map[y][x] = TileType.WATER;
                } else {
                    map[y][x] = TileType.MOUNTAIN;
                }
            }
        }
    }
}
