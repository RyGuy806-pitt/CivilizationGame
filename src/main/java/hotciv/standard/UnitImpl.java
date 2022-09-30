package hotciv.standard;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {
    private Position position;
    private String type;
    private Player owner;
    int defensiveStrength = 1;

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
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    public void fortified(){
        defensiveStrength =2;
    }

    public void notFortified(){
        defensiveStrength =1;
    }

    public boolean checkFortified(){
        if(defensiveStrength == 2){
            return true;
        }
        else{
            return false;
        }
    }
}
