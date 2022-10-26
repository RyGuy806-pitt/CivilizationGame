package hotciv.stub;

import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.GameConstants.ARCHER;

public class UnitTwoStub implements Unit {

    @Override
    public String getTypeString() {
        return ARCHER;
    }

    @Override
    public Player getOwner() {
        return Player.RED;
    }

    @Override
    public int getMoveCount() {
        return 1;
    }

    @Override
    public int getDefensiveStrength() {
        return 100;
    }

    @Override
    public int getAttackingStrength() {
        return 2;
    }

}
