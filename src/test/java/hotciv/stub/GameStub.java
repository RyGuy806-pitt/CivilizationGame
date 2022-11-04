package hotciv.stub;

import Strategies.*;
import VersionControl.EpsilonVersion;
import VersionControl.Version;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;
import static hotciv.standard.TestThetaCiv.UFO;

public class GameStub implements Game {

    private int redPlayerWinCounter = 0;
    private int bluePlayerWinCounter = 0;
    private int y = -4000;
    private HashMap<Position, TileImpl> tileMap = new HashMap();
    private HashMap<Position, Unit> unitMap = new HashMap();
    private HashMap<Position, CityImpl> cityMap = new HashMap();

    private Attack AttackStrat;
    private UnitAction ActionStrat;
    private Winner WinnerStrat;
    private Aging AgingStrat;

    public GameStub(Version Var){

        this.AttackStrat = Var.createAttack();
        this.WinnerStrat = Var.createWinner();
        this.ActionStrat = Var.createUnitAction();

        for(int i=0; i<WORLDSIZE; i++) {
            for(int j=0; j<WORLDSIZE; j++) {
                Position p = new Position(i, j);
                tileMap.put(p, new TileImpl(p, FOREST));
                if(i%2==1) {
                    unitMap.put(p, new UnitImpl(p, ARCHER, Player.RED));
                } else if (i==4) {
                    unitMap.put(p, new UnitImpl(p, ARCHER, Player.RED));
                } else if (i ==12) {
                    unitMap.put(p, new UnitImpl(p, UFO, Player.BLUE));
                } else {
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
        if(getBlueWins() == 3){
            return Player.BLUE;
        } else if (getRedWins()== 3) {
            return Player.RED;
        } else {
            return null;
        }
    }

    @Override
    public int getAge() {

            //-4000--100 (+=100)
            if(y < -100){
                y += 100;
            }
            //-100, -1, +1, +50
            else if(y == -100){
                y += 99;
            }
            else if(y == -1){
                y += 2;
            }
            else if(y==1){
                y += 49;
            }
            //+50-1750 (+=50)
            else if(y >= 50 && y < 1750){
                y += 50;
            }
            //1750-1900 (+=25)
            else if(y >= 1750 && y< 1900){
                y += 25;
            }
            //1900-1970 (+=5)
            else if(y >= 1900 && y < 1970){
                y += 5;
            }
            //1970-onward (+=1)
            else{
                y += 1;
            }

            return y;

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
        int RedWins = getRedWins();
        int BlueWins = getBlueWins();
        if(performAttack(to, from) == true) {
            if ((type == ARCHER && unit.getDefensiveStrength() == 3) || type != ARCHER) {
                if(getUnitAt(from).getOwner() == Player.RED){
                    RedWins++;
                    setRedWins(RedWins);
                }

                if(getUnitAt(from).getOwner() == Player.BLUE){
                    BlueWins++;
                    setBlueWins(BlueWins);
                }
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
        Unit unit = unitMap.get(p);
        String unitName = unit.getTypeString();
        Player owner = unit.getOwner();

        if(unitName == SETTLER)
        {
            // clear from unit map
            unitMap.remove(p);
            unitMap.put(p, new UnitImpl(p, "nothing", owner));

            // add to city map with ARCHER as production
            cityMap.put(p, new CityImpl(p, ARCHER, owner));
        }
        else if(unitName == ARCHER)
        {
//            if(UM.get(p).getDefensiveStrength() == 1) {
//                UM.get(p).fortified();
//            }
//
//            if(UM.get(p).getDefensiveStrength() == 2) {
//                UM.get(p).notFortified();
//            }
//            if(unitMap.get(p).checkFortified() == false) {
//                unitMap.get(p).fortified();
//            }
//            else{
//                unitMap.get(p).notFortified();
//            }
        }
        else if(unitName == LEGION)
        {
            // do nothing
        }
        else if(unitName == UFO){
            int population = 0;
            if(cityMap.get(p) != null){
                population = cityMap.get(p).getSize();
                cityMap.get(p).setSize(population-1);
                if(cityMap.get(p).getSize() == 0){
                    cityMap.remove(p);
                }
            }

            if(tileMap.get(p).getTypeString() == FOREST){
                tileMap.put(p, new TileImpl(p, PLAINS));
            }
        }
        else
        {
            // do nothing
        }
    }

    public void setRedWins(int x){ redPlayerWinCounter = x; }

    public int getRedWins(){
        return redPlayerWinCounter;
    }

    public void setBlueWins(int x){
        bluePlayerWinCounter = x;
    }

    public int getBlueWins(){
        return bluePlayerWinCounter;
    }
}
