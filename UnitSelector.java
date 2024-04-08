package mygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class UnitSelector {
    private Map<String, ImageIcon> unitIcons;
    private GameBoard gameBoard;
    private GameMechanics gameMechanics; // Added GameMechanics reference
    private static final String ARCHER_IMAGE_PATH = "A:/projects/PixelCivilizations/mygame/sprites/archer.png";
    private static final String KNIGHT_IMAGE_PATH = "A:/projects/PixelCivilizations/mygame/sprites/knight.png";
    private static final String CAVALRY_IMAGE_PATH = "A:/projects/PixelCivilizations/mygame/sprites/cavalry.png";
    private static final String SPEARMAN_IMAGE_PATH = "A:/projects/PixelCivilizations/mygame/sprites/spearman.png";
    private static final String CATAPULT_IMAGE_PATH = "A:/projects/PixelCivilizations/mygame/sprites/catapult.png";



    public UnitSelector(GameBoard gameBoard, GameMechanics gameMechanics) {
        this.gameBoard = gameBoard;
        this.gameMechanics = gameMechanics; // Save the reference
        unitIcons = new HashMap<>();
        loadUnitIcons();
    }

    private void loadUnitIcons() {
        unitIcons.put("Archer", new ImageIcon(ARCHER_IMAGE_PATH));
        unitIcons.put("Knight", new ImageIcon(KNIGHT_IMAGE_PATH));
        unitIcons.put("Cavalry", new ImageIcon(CAVALRY_IMAGE_PATH));
        unitIcons.put("Spearman", new ImageIcon(SPEARMAN_IMAGE_PATH));
        unitIcons.put("Catapult", new ImageIcon(CATAPULT_IMAGE_PATH));
    }
    

    public void showUnitSelectionDialog(int houseX, int houseY) {
        // Options for the JOptionPane
        String[] options = {"Archer", "Knight", "Cavalry", "Spearman", "Catapult"};
        ImageIcon[] icons = {
            new ImageIcon(ARCHER_IMAGE_PATH),
            new ImageIcon(KNIGHT_IMAGE_PATH),
            new ImageIcon(CAVALRY_IMAGE_PATH),
            new ImageIcon(SPEARMAN_IMAGE_PATH),
            new ImageIcon(CATAPULT_IMAGE_PATH)
        };
        
        // Show the options dialog
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose a unit to create:",
                "Unit Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        
        if (choice != -1) {
            String selectedUnit = options[choice];
            placeUnitAroundHouse(selectedUnit, houseX, houseY); // Now correctly calling the new method
        }
    }
    
    public void placeUnitAroundHouse(String unitType, int houseX, int houseY) {
        // Check the tiles around the house to find a grass tile to place the unit
        for (int y = Math.max(0, houseY - 1); y <= Math.min(houseY + 1, gameBoard.getHeight() - 1); y++) {
            for (int x = Math.max(0, houseX - 1); x <= Math.min(houseX + 1, gameBoard.getWidth() - 1); x++) {
                if (gameBoard.getTile(x, y) == TileType.GRASS && gameBoard.getUnit(x, y) == null) {
                    Unit newUnit = UnitFactory.createUnit(unitType, x, y);
                    if (newUnit != null) {
                        gameMechanics.placeUnit(newUnit, x, y); // Now correctly calling placeUnit
                        return; // Exit after placing the unit
                    }
                }
            }
        }
        // If no grass tile is found around the house, handle it accordingly
    }
    public void placeUnit(String unitType, int houseX, int houseY) {
        Unit newUnit = UnitFactory.createUnit(unitType, houseX, houseY);
        if (newUnit != null) {
            gameMechanics.placeUnit(newUnit, houseX, houseY); // Use the method from GameMechanics
        }
    }
}
