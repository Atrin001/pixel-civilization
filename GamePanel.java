// GamePanel.java
package mygame;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.*;


public class GamePanel extends JPanel {
    private UnitSelector unitSelector;
    private GameState gameState;
    private GameMechanics gameMechanics;
    private final int tileSize = 32;
    private BufferedImage tree1Image;
    private BufferedImage tree2Image;
    private BufferedImage grassImage;
    private BufferedImage waterImage;
    private BufferedImage mountainImage;
    private BufferedImage houseImage;
    private BufferedImage house1Image; // Image for the second house
    private Map<String, BufferedImage> unitImages;
    private boolean dialogVisible = false;
    private static final String HOUSE1_IMAGE_PATH ="../sprites/house.png"; // Path for the second house image
    private static final String ARCHER_IMAGE_PATH = "../sprites/archer.png";
    private static final String KNIGHT_IMAGE_PATH = "../sprites/knight.png";
    private static final String CAVALRY_IMAGE_PATH = "../sprites/cavalry.png"; 
    private static final String SPEARMAN_IMAGE_PATH = "../sprites/spearman.png";
    private static final String CATAPULT_IMAGE_PATH = "../sprites/catapult.png";
    private Map<String, Integer> unitMobility;
    private boolean housePlaced = false; // Track if house is already placed
    private boolean secondHousePlaced = false; // Track if the second house is already placed
    public static final int TILE_SIZE = 32;
    private Unit currentlySelectedUnit;
    private boolean unitSelected = false;
    private Point selectedUnitPosition = null;
    private int selectedUnitMobility = 0;
    



    public GamePanel(GameState gameState, GameMechanics gameMechanics) {
        this.gameState = gameState;
        this.gameMechanics = gameMechanics;
        unitSelector = new UnitSelector(gameState.getBoard(), gameMechanics);
        initializeTiles();
        initializeUnitImages();
        initializeUnitMobility();
        setupMouseAdapter();
        setFocusable(true);
        setKeyBindings();
    }
    private void setupMouseAdapter() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / tileSize;
                int y = e.getY() / tileSize;
                TileType currentTile = gameState.getBoard().getTile(x, y);
                Unit unitAtPosition = gameState.getBoard().getUnit(x, y);
    
                // Check if we are placing a house
                if (!housePlaced && currentTile == TileType.GRASS) {
                    gameState.getBoard().setTile(x, y, TileType.HOUSE);
                    gameState.setFirstHousePosition(x, y); // Set the position of the first house
                    housePlaced = true;
                    repaint();
                    return; // Exit the method after placing the house
                }
    
                // If a house is clicked, show the unit selection dialog
                if (housePlaced && currentTile == TileType.HOUSE && !unitSelected) {
                    showUnitSelectionPanel(x, y);
                    return; // Exit the method after showing the dialog
                }
    
                // If a unit is selected and the destination is valid, move the unit
                if (unitSelected && isTileMovable(x, y)) {
                    moveSelectedUnit(x, y);
                    repaint();
                    return; // Exit the method after moving the unit
                }
                if (!secondHousePlaced && currentTile == TileType.GRASS && housePlaced) { // Ensure first house is placed
                    gameState.getBoard().setTile(x, y, TileType.HOUSE1); // Use a new enum value for the second house
                    secondHousePlaced = true;
                    repaint();
                    return; // Exit the method after placing the second house
                }
                // If a unit is clicked, select it
                if (unitAtPosition != null) {
                    selectUnit(unitAtPosition);
                    repaint();
                    return; // Exit the method after selecting the unit
                }
    
                // If clicked outside, deselect any selected unit
                unitSelected = false;
                currentlySelectedUnit = null;
                repaint();
            }
        });
    }
    
    
    
    
    private void selectUnit(Unit unit) {
        currentlySelectedUnit = unit;
        unitSelected = true;
        selectedUnitPosition = new Point(unit.getX(), unit.getY());
        selectedUnitMobility = unit.getMobility();
    }
    
    private boolean isTileMovable(int x, int y) {
        // Calculate the total movement cost from the current position to the target position
        int totalDistance = Math.abs(x - selectedUnitPosition.x) + Math.abs(y - selectedUnitPosition.y);
    
        // Check if the target coordinates are within the mobility range
        boolean withinMobilityRange = totalDistance <= selectedUnitMobility;
    
        // Check if the target tile is grass and has no unit on it
        boolean isGrassAndUnoccupied = gameState.getBoard().getTile(x, y) == TileType.GRASS &&
                                        gameState.getBoard().getUnit(x, y) == null;
    
        // The tile is movable if it's within the mobility range, it's grass, and unoccupied
        return withinMobilityRange && isGrassAndUnoccupied;
    }
    
    
    private void moveSelectedUnit(int x, int y) {
        if (isTileMovable(x, y)) {
            gameState.getBoard().setUnit(null, selectedUnitPosition.x, selectedUnitPosition.y); // Clear the old position
            gameState.getBoard().setTile(selectedUnitPosition.x, selectedUnitPosition.y, TileType.GRASS); // Set the old position to grass
            gameState.getBoard().setUnit(currentlySelectedUnit, x, y); // Place the unit at the new position
            currentlySelectedUnit.setPosition(x, y); // Update unit's position
            unitSelected = false; // Deselect unit after moving
            repaint(); // Repaint to show the updated board state
        }
    }
    
    
    
    private void highlightMovableTiles(Graphics g, int mobility, int unitX, int unitY) {
        Color highlightColor = new Color(0, 255, 0, 128); // Semi-transparent green
        for (int y = unitY - mobility; y <= unitY + mobility; y++) {
            for (int x = unitX - mobility; x <= unitX + mobility; x++) {
                // Check if the position is within the grid
                if (y >= 0 && y < gameState.getBoard().getHeight() && x >= 0 && x < gameState.getBoard().getWidth()) {
                    int totalDistance = Math.abs(x - unitX) + Math.abs(y - unitY);
                    // Check if the tile is within the mobility range and not occupied by another unit
                    if (totalDistance <= mobility && gameState.getBoard().getTile(x, y) == TileType.GRASS &&
                        gameState.getBoard().getUnit(x, y) == null) {
                        g.setColor(highlightColor);
                        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                    }
                }
            }
        }
    }
    
    
    private void initializeUnitMobility() {
        unitMobility = new HashMap<>();
        unitMobility.put("Archer", 3);
        unitMobility.put("Knight", 3);
        unitMobility.put("Cavalry", 5);
        unitMobility.put("Spearman", 3);
        unitMobility.put("Catapult", 1);
    }
    
    private void setKeyBindings() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quit");
        getActionMap().put("quit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
    }
    private void initializeUnitImages() {
        unitImages = new HashMap<>();
        unitImages.put("Archer", loadImage(ARCHER_IMAGE_PATH));
        unitImages.put("Knight", loadImage(KNIGHT_IMAGE_PATH));
        unitImages.put("Cavalry", loadImage(CAVALRY_IMAGE_PATH));
        unitImages.put("Spearman", loadImage(SPEARMAN_IMAGE_PATH));

        unitImages.put("Catapult", loadImage(CATAPULT_IMAGE_PATH));
    
        // Debugging: Print out the map contents
        for (String key : unitImages.keySet()) {
            System.out.println("Unit key in map: " + key);
        }
    }
    
    private void showUnitSelectionPanel(int x, int y) {
        if (dialogVisible) {
            return;
        }
    
        dialogVisible = true;
        
        JDialog unitSelectionDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Select a Unit", Dialog.ModalityType.APPLICATION_MODAL);
        unitSelectionDialog.setLayout(new GridLayout(0, 5)); // For 5 unit types
    
        String[] unitNames = {"Archer", "Knight", "Cavalry", "Spearman", "Catapult"};
        int imageSize = 100; // The desired image size
    
        for (String unitName : unitNames) {
            BufferedImage originalImage = unitImages.get(unitName);
            if (originalImage != null) {
                Image scaledImage = originalImage.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                
                JButton button = new JButton(unitName, icon); // Create a button with text and icon
                button.setHorizontalTextPosition(SwingConstants.CENTER); // Set text position
                button.setVerticalTextPosition(SwingConstants.BOTTOM); // Set text position
                button.addActionListener(e -> {
                    placeUnitAroundHouse(unitName, x, y);
                    unitSelectionDialog.dispose(); // Close the dialog after selection
                    dialogVisible = false;
                });
    
                unitSelectionDialog.add(button);
            }
        }
    
        unitSelectionDialog.pack(); // Adjust dialog to the components
        unitSelectionDialog.setLocationRelativeTo(this); // Center dialog relative to the panel
        unitSelectionDialog.setVisible(true); // Make the dialog visible
    
        // Add a window listener to reset dialogVisible when the dialog is closed
        unitSelectionDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dialogVisible = false;
            }
        });
    }
    
    
    
    
    
    private void showUnitImagesOnly(int houseX, int houseY) {
        if (dialogVisible) {
            return;
        }
        
        dialogVisible = true;
        String[] unitNames = {"Archer", "Knight", "Cavalry", "Spearman", "Catapult"};
    
        JPanel unitImagePanel = new JPanel(new GridLayout(0, unitNames.length));
    
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Unit Images", Dialog.ModalityType.APPLICATION_MODAL);
    
        for (String unitName : unitNames) {
            BufferedImage img = unitImages.get(unitName);
            if (img != null) {
                ImageIcon icon = new ImageIcon(img);
                JLabel label = new JLabel(icon);
                label.setHorizontalAlignment(JLabel.CENTER);
        
                JButton button = new JButton(unitName);
                button.addActionListener(e -> {
                    placeUnitAroundHouse(button.getText(), houseX, houseY);
                    dialog.dispose(); // Dispose only the dialog
                    dialogVisible = false;
                });
        
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(label, BorderLayout.CENTER);
                panel.add(button, BorderLayout.PAGE_END);
                unitImagePanel.add(panel);
            }
        }
    
        dialog.setContentPane(unitImagePanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dialogVisible = false;
            }
        });
    }
    
    
    
    
    


    private void placeUnitAroundHouse(String unitType, int houseX, int houseY) {
        boolean unitPlaced = false;
    
        // Check surrounding tiles around the house to place the unit
        for (int y = Math.max(0, houseY - 1); y <= Math.min(houseY + 1, gameState.getBoard().getHeight() - 1) && !unitPlaced; y++) {
            for (int x = Math.max(0, houseX - 1); x <= Math.min(houseX + 1, gameState.getBoard().getWidth() - 1) && !unitPlaced; x++) {
                if (gameState.getBoard().getTile(x, y) == TileType.GRASS && gameState.getBoard().getUnit(x, y) == null) {
                    // Create and place the unit if the spot is valid
                    Unit newUnit = UnitFactory.createUnit(unitType, x, y);
                    if (newUnit != null) {
                        gameMechanics.placeUnit(newUnit, x, y);
                        System.out.println("Placed " + unitType + " at (" + x + "," + y + ")");
                        unitPlaced = true;
                        currentlySelectedUnit = newUnit;
                    } else {
                        System.err.println("Failed to place " + unitType + " at (" + x + "," + y + ")");
                    }
                }
            }
        }
        
        if (!unitPlaced) {
            System.err.println("No valid spot found to place the unit around the house at (" + houseX + "," + houseY + ")");
        } else {
            repaint(); // Only repaint if a unit was placed
        }
        if (unitPlaced) {
            int mobility = unitMobility.getOrDefault(unitType, 0); // Get mobility, default to 0 if not found
            highlightMovableTiles(getGraphics(), mobility, houseX, houseY);
            repaint(); // Repaint to update the view
        }
    }
    
    
    
    
    private Unit createUnit(String unitType, int x, int y) {
        switch (unitType) {
            case "Archer":
                return new Archer(x, y);
            case "Knight":
                return new Knight(x, y);
            case "Cavalry":
                return new Cavalry(x, y);
            case "Spearman":
                return new Spearman(x, y);
            case "Catapult":
                return new Catapult(x, y);
            default:
                throw new IllegalArgumentException("Unknown unit type: " + unitType);
        }
    }

    private void initializeTiles() {
        // Load the images
        tree1Image = loadImage("../sprites/tree1.png");
        tree2Image = loadImage("../sprites/tree2.png");
        grassImage = loadImage("../sprites/grass.png");
        waterImage = loadImage("../sprites/water.png");
        mountainImage = loadImage("../sprites/mountain.png");
        houseImage = loadImage("../sprites/house.png"); // Load house image
        house1Image = loadImage(HOUSE1_IMAGE_PATH);
        // Check if any images failed to load
        if (tree1Image == null || tree2Image == null || grassImage == null || waterImage == null || mountainImage == null || houseImage == null || house1Image == null) {
            System.err.println("Failed to load one or more images. Check file paths.");
            // Handle error - perhaps set a default image or exit
        }
    }

    protected BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                img = ImageIO.read(file);
            } else {
                System.err.println("File does not exist: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error reading image file: " + e.getMessage());
        }
        return img;
    }
    
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTiles(g);
        drawUnits(g);
        // If a unit is selected, highlight movable tiles
        if (unitSelected) {
            highlightMovableTiles(g, selectedUnitMobility, selectedUnitPosition.x, selectedUnitPosition.y);
        }
    }

    
    private void selectUnit(int x, int y) {
        // Placeholder for selecting a unit; actual implementation will vary
        // You need to write the logic to determine which unit is at the x, y coordinates
        // and set the currentlySelectedUnit field accordingly
        currentlySelectedUnit = gameState.getBoard().getUnit(x, y);
    }

    private void drawUnits(Graphics g) {
        for (int y = 0; y < gameState.getBoard().getHeight(); y++) {
            for (int x = 0; x < gameState.getBoard().getWidth(); x++) {
                Unit unit = gameState.getBoard().getUnit(x, y);
                if (unit != null) {
                    BufferedImage unitImage = getUnitImage(unit);
                    if (unitImage != null) {
                        g.drawImage(unitImage, x * tileSize, y * tileSize, tileSize, tileSize, this);
                    }
                }
            }
        }
    }
    
    private void drawTiles(Graphics g) {
        TileType[][] map = gameState.getBoard().getMap();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                BufferedImage tileImage = getTileImage(map[y][x]);
                if (tileImage != null) {
                    g.drawImage(tileImage, x * tileSize, y * tileSize, tileSize, tileSize, this);
                }
    
                // Check if the tile is a house
                if (map[y][x] == TileType.HOUSE) {
                    Point firstHousePos = gameState.getFirstHousePosition();
                    Point secondHousePos = gameState.getSecondHousePosition();
                    if (firstHousePos != null && firstHousePos.x == x && firstHousePos.y == y) {
                        g.drawImage(houseImage, x * tileSize, y * tileSize, tileSize, tileSize, this);
                    } else if (secondHousePos != null && secondHousePos.x == x && secondHousePos.y == y) {
                        g.drawImage(house1Image, x * tileSize, y * tileSize, tileSize, tileSize, this);
                    }
                }
            }
        }
    }
    
    
    
    
    
    
    private BufferedImage getTileImage(TileType type) {
        // This switch statement was already present in your provided code
        switch (type) {
            case GRASS:
                return grassImage;
            case WATER:
                return waterImage;
            case MOUNTAIN:
                return mountainImage;
            case TREE1:
                return tree1Image;
            case TREE2:
                return tree2Image;
            case HOUSE:
                return houseImage;
            case HOUSE1: // Assuming HOUSE1 is a new enum value for the second house
                return house1Image;
            default:
                return null; // Or handle the default case as appropriate
        }
    }

    private BufferedImage getUnitImage(Unit unit) {
        if (unit != null) {
            return unitImages.get(unit.getClass().getSimpleName());
        }
        return null;
    }
}
