package mygame;
public class Knight extends Unit {
    public Knight() {
        super(8, 1, 3, 2, 15, 3); // damageToEnemy, damageToSelf, productionCost, costPerTurn, health, mobility
    }

    @Override
    public void attack(Unit enemy) {
        // Standard attack logic
        // Knights do not have a special damage interaction
    }
    public Knight(int x, int y) {
        super(8, 1, 3, 2, 15, 3); // existing stats
        setPosition(x, y); // Set the initial position
        this.image = Unit.loadImage("../sprites/knight.png");
    }
    @Override
    public String getType(){
        return "Knight";
    }
}
