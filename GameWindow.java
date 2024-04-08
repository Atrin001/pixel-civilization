package mygame;
import javax.swing.JFrame;
import javax.swing.JButton;

public class GameWindow extends JFrame {
    private void initializeUI() {
            JButton moveButton = new JButton("Move Unit");
            // Add action listener to the button
            // Add the button to the window
        }
        public GameWindow(GameState gameState, GameMechanics gameMechanics) {
            setTitle("Pixel Civilizations");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            // Add the game panel
            GamePanel gamePanel = new GamePanel(gameState, gameMechanics);
            add(gamePanel);
    
            // Make the window visible
            setVisible(true);
        }

    // Methods to update the UI based on game state
}
