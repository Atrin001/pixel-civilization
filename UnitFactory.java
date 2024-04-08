package mygame;

public class UnitFactory {
    public static Unit createUnit(String unitType, int x, int y) {
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
                return null;
        }
    }
}
