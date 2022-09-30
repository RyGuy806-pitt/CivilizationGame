package hotciv.standard;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {
    private Position position;
    private String type;
    private Player owner;
    private int defensiveStrength = 1;

    public UnitImpl(Position p, String t, Player o){
        this.position = p;
        this.type = t;
        this.owner = o;
    }

    @Override
    public String getTypeString() {
        return this.type;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength()
    {
        if(defensiveStrength == 1)
        {
            defensiveStrength = 2;
        }
        else if (defensiveStrength == 2)
        {
            defensiveStrength = 1;
        }
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
