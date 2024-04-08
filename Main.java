package mygame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Get the screen size
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            // Calculate the number of tiles based on the screen size and tile size
            int cols = screenSize.width / GamePanel.TILE_SIZE;
            int rows = screenSize.height / GamePanel.TILE_SIZE;

            // Create instances of GameState and GameMechanics
            GameState gameState = new GameState(cols, rows);
            GameMechanics gameMechanics = new GameMechanics(gameState.getBoard());

            // Create the game panel with the game state and mechanics
            GamePanel gamePanel = new GamePanel(gameState, gameMechanics);

            // Create and configure the main window frame
            JFrame frame = new JFrame("Map Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setSize(screenSize.width, screenSize.height);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            frame.add(gamePanel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
