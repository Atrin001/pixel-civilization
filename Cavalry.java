package mygame;

public class Cavalry extends Unit {
    public Cavalry() {
        super(6, 1, 3, 2, 20, 5); // damageToEnemy, damageToSelf, productionCost, costPerTurn, health, mobility
    }

    @Override
    public void attack(Unit enemy) {
        // Standard attack logic
        // Cavalry does not have a special damage interaction
    }
    public Cavalry(int x, int y) {
        super(6, 1, 3, 2, 20, 5); // existing stats
        setPosition(x, y); // Set the initial position
        this.image = Unit.loadImage("../sprites/cavalry.png");
    }
    @Override
    public String getType(){
        return "Cavalry";
    }
}
