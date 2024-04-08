package mygame;
public class Spearman extends Unit {
    public Spearman() {
        super(3, 1, 1, 1, 10, 3); // damageToEnemy, damageToSelf, productionCost, costPerTurn, health, mobility
    }

    @Override
    public void attack(Unit enemy) {
        // Spearman has special damage against Cavalry
        if (enemy instanceof Cavalry) {
            enemy.health -= 10; // Spearman deals 10 damage to Cavalry
        } else {
            enemy.health -= this.damageToEnemy;
        }
        this.health -= this.damageToSelf; // Damage to self after attacking
    }
    public Spearman(int x, int y) {
        super(3, 1, 1, 1, 10, 3); // existing stats
        setPosition(x, y); // Set the initial position
        this.image = Unit.loadImage("sprites/spearman.png");
    }
    @Override
    public String getType(){
        return "Spearman";
    }
}
