package mygame;

public class PixelCivilizations {
    public static void main(String[] args) {
        GameState gameState = new GameState(10, 10);
        GameMechanics gameMechanics = new GameMechanics(gameState.getBoard());
        GamePanel gamePanel = new GamePanel(gameState, gameMechanics); // Instantiate GamePanel

        // Create and show the game window
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameWindow gameWindow = new GameWindow(gameState, gameMechanics);
                gameWindow.add(gamePanel); // Add GamePanel to GameWindow
                gameWindow.setVisible(true);
            }
        });

        // Instantiate and start the game loop with all necessary components
        GameLoop gameLoop = new GameLoop(gameState, gameMechanics, gamePanel);
        gameLoop.start();
    }
}
