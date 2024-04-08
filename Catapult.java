package mygame;

public class Catapult extends Unit {
    public Catapult() {
        super(12, 4, 5, 2, 6, 1); // damageToEnemy, damageToSelf, productionCost, costPerTurn, health, mobility
    }

    @Override
    public void attack(Unit enemy) {
        // Standard attack logic
        // Catapults do not have a special damage interaction
    }
    public Catapult(int x, int y) {
        super(12, 4, 5, 2, 6, 1); // existing stats
        setPosition(x, y); // Set the initial position
        this.image = Unit.loadImage("../sprites/catapult.png");
    }
    @Override
    public String getType(){
        return "Catapult";
    }
}
