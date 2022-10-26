package hotciv.stub;

import Strategies.Attack;
import Strategies.EpsilonAttack;
import VersionControl.EpsilonVersion;
import VersionControl.Version;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class GameStub implements Game {

    private HashMap<Position, TileImpl> tileMap = new HashMap();
    private HashMap<Position, Unit> unitMap = new HashMap();
    private HashMap<Position, CityImpl> cityMap = new HashMap();

    private Attack AttackStrat;

    public GameStub(Version Var){

        this.AttackStrat = Var.createAttack();

        for(int i=0; i<WORLDSIZE; i++) {
            for(int j=0; j<WORLDSIZE; j++) {
                Position p = new Position(i, j);
                tileMap.put(p, new TileImpl(p, FOREST));
                if(i%2==1) {
                    unitMap.put(p, new UnitImpl(p, ARCHER, Player.RED));
                }
                else{
                    unitMap.put(p, new UnitImpl(p, ARCHER, Player.BLUE));
                }
            }
        }
        unitMap.put(new Position(7,8), new UnitImpl(new Position(7,8), ARCHER, Player.BLUE));
        unitMap.put(new Position (2,2), new UnitOneStub());
        unitMap.put(new Position (2,5), new UnitTwoStub());
    }
    @Override
    public Tile getTileAt(Position p) {
        return tileMap.get(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return unitMap.get(p);
    }

    @Override
    public City getCityAt(Position p) {
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }

    public boolean performAttack(Position to, Position from){
        return AttackStrat.attack(this, to, from);
    }
    public boolean moveUnit( Position from, Position to ) {
        //replace with none
        //rewrite unit at map location
        Unit unit = unitMap.get(from);
        String type = unit.getTypeString();
        Player own = unit.getOwner();
        if(performAttack(to, from) == true) {
            if ((type == ARCHER && unit.getDefensiveStrength() == 3) || type != ARCHER) {
                unitMap.remove(from);
                unitMap.put(from, new UnitImpl(from, "nothing", Player.GREEN));
                unitMap.remove(to);
                if (cityMap.get(to) != null) {
                    cityMap.put(to, new CityImpl(to, type, own));
                }
                unitMap.put(to, new UnitImpl(to, type, own));
                return true;
            }
        }
        else{
            unitMap.remove(from);
            unitMap.put(from, new UnitImpl(from, "nothing", Player.GREEN));
        }
        return false;
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {

    }
}
