package mygame;
public class GameMechanics {

    private GameBoard board;

    public GameMechanics(GameBoard board) {
        this.board = board;
    }

    public void moveUnit(Unit unit, int oldX, int oldY, int newX, int newY) {
        // Calculate the distance to check if the move is within the unit's mobility range
        int distance = Math.abs(newX - oldX) + Math.abs(newY - oldY);
        
        if (distance <= unit.getMobility() && board.getUnit(newX, newY) == null) {
            // Move the unit
            board.placeUnit(unit, newX, newY);
            board.placeUnit(null, oldX, oldY); // Remove the unit from the old position
        }
    }
    public void attackUnit(Unit attacker, int targetX, int targetY) {
        Unit target = board.getUnit(targetX, targetY);
        if (target != null) {
            attacker.attack(target);
            // If the target unit's health is reduced to 0 or less, remove it from the board
            if (target.getHealth() <= 0) {
                board.placeUnit(null, targetX, targetY); // Target is destroyed
            }
        }
    }
    public void placeUnit(Unit unit, int x, int y) {
        // This should also check if the placement is within the bounds of the board
        if (x >= 0 && y >= 0 && x < board.getWidth() && y < board.getHeight()) {
            if (board.getTile(x, y) == TileType.GRASS && board.getUnit(x, y) == null) {
                board.placeUnit(unit, x, y);
                board.setTile(x, y, TileType.UNIT); // Set the tile type to UNIT
                System.out.println("Placed unit at: " + x + ", " + y);
            } else {
                System.err.println("Failed to place unit on the board at: " + x + ", " + y);
            }
        } else {
            System.err.println("Placement coordinates out of bounds: " + x + ", " + y);
        }
    }
    
    
    // Additional game mechanics like resource management, etc.
}
