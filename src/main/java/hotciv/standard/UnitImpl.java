package hotciv.standard;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import static Strategies.ThetaUnitAction.UFO;
import static hotciv.framework.GameConstants.*;

public class UnitImpl implements Unit {
    private Position position;
    private String type;
    private Player owner;
    int ArcherStrength = 3;
    int defensiveStrength = 0;
    int offensiveStrength = 0;


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
        if(type != UFO) {
            return 1;
        }
        else{
            return 2;
        }
    }

    @Override
    public int getDefensiveStrength()
    {
        if(type == ARCHER){
            defensiveStrength = ArcherStrength;
        } else if (type == SETTLER) {
            defensiveStrength = 3;
        } else if (type == LEGION) {
            defensiveStrength = 2;
        } else if (type == UFO) {
            defensiveStrength = 8;
        } else {
            //do nothing
        }
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        if(type == ARCHER){
            offensiveStrength = 2;
        } else if (type == SETTLER) {
            offensiveStrength = 0;
        } else if (type == LEGION) {
            offensiveStrength = 4;
        } else if (type== UFO) {
            offensiveStrength = 1;
        } else {
            //do nothing
        }
        return offensiveStrength;
    }

    public void fortified(){
        if(type == ARCHER) {
            ArcherStrength = 6;
        }
    }

    public void notFortified(){
        if(type == ARCHER) {
            ArcherStrength = 3;
        }
    }

    public boolean checkFortified(){
        if(ArcherStrength == 6){
            return true;
        }
        else{
            return false;
        }
    }
}
