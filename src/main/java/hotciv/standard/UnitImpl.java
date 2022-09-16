package hotciv.standard;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {
    private Position position;
    private String type;
    private Player owner;

    public UnitImpl(Position p, String t){
        this.position = p;
        this.type = t;

    }

    @Override
    public String getTypeString() {
        return this.type;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
