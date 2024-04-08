package mygame;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class Unit {
    protected int damageToEnemy;
    protected int damageToSelf;
    protected int productionCost;
    protected int costPerTurn;
    protected int health;
    protected int mobility;
    protected int x;
    protected int y;
    protected BufferedImage image;
    private Map<String, Integer> unitMobility;


    public Unit(int damageToEnemy, int damageToSelf, int productionCost, int costPerTurn, int health, int mobility) {
        this.damageToEnemy = damageToEnemy;
        this.damageToSelf = damageToSelf;
        this.productionCost = productionCost;
        this.costPerTurn = costPerTurn;
        this.health = health;
        this.mobility = mobility;
    }
    public int getHealth() {
        return health;
    }
   

    protected void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    protected static BufferedImage loadImage(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                return ImageIO.read(file);
            } else {
                System.err.println("File not found: " + path);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + path);
            e.printStackTrace();
            return null;
        }
    }

    public int getMobility() {
        return mobility;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public abstract String getType();

    // Setters for x and y if needed
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
   
    

    // Special interaction method
    public abstract void attack(Unit enemy);

    // Getters and Setters
    // ...
}
