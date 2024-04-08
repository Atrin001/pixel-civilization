package mygame;

public class Archer extends Unit {
    public Archer() {
        super(2, 3, 2, 1, 10, 3); // damageToEnemy, damageToSelf, productionCost, costPerTurn, health, mobility
    }

    @Override
    public void attack(Unit enemy) {
        // Standard attack logic
        // Archers do not have a special damage interaction
    }
    public Archer(int x, int y) {
        super(2, 3, 2, 1, 10, 3); // existing stats
        setPosition(x, y); // Set the initial position
        // Call the loadImage method to set the image attribute
        this.image = Unit.loadImage("../sprites/archer.png");
    }
    @Override
    public String getType(){
        return "Archer";
    }
}
