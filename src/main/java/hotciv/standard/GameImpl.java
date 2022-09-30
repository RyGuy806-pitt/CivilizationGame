package hotciv.standard;

import Strategies.Aging;
import Strategies.UnitAction;
import Strategies.Winner;
import Strategies.WorldMap;
import VersionControl.Version;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
  private int year = -4000;
  private HashMap<Position, TileImpl> tileMap = new HashMap();
  private HashMap<Position, UnitImpl> unitMap = new HashMap();
  private HashMap<Position, CityImpl> cityMap = new HashMap();

  private Winner WinnerStrat;
  private UnitAction UnitActionStrat;
  private WorldMap WorldMapStrat;
  private Aging AgingStrat;

  public GameImpl(Version Var) {
    this.WinnerStrat = Var.createWinner();
    this.AgingStrat = Var.createAging();
    this.WorldMapStrat = Var.createWorldMap();
    this.UnitActionStrat = Var.createUnitAction();

    //For world layout: tile types
    //This part stays in game Impl
    for(int i=0; i<WORLDSIZE; i++) {
      for(int j=0; j<WORLDSIZE; j++) {
        Position p = new Position(i, j);
        tileMap.put(p, new TileImpl(p, PLAINS));
        unitMap.put(p, new UnitImpl(p, "nothing", Player.GREEN));
      }
    }

    WorldMapStrat.worldBuild(this, unitMap, cityMap, tileMap);

  }
  public Tile getTileAt( Position p ) {
    return tileMap.get(p);
  }
  public Unit getUnitAt( Position p ) {
    return unitMap.get(p);
  }
  public City getCityAt( Position p ) {
    return cityMap.get(p);
  }
  public Player getPlayerInTurn() {
    int turn;
    turn = TurnImpl.getTurn();
    turn++;
    TurnImpl.setTurn(turn);
    endOfTurn();
    getWinner();
    if(turn%2 == 0){
      //this part needs to take a position input of the city in its own function
      produceTroopForCity(cityMap.get(new Position(4,1)));
      produceTroopForCity(cityMap.get(new Position(1,1)));
      return Player.BLUE;
    }
    else {
      return Player.RED;
    }
  }
  public Player getWinner() {
    return WinnerStrat.calculateWinner(this);
  }

  public int getAge() {
    return AgingStrat.calculateTime();
  }
  public boolean moveUnit( Position from, Position to ) {
    //replace with none
    //rewrite unit at map location
    UnitImpl unit = unitMap.get(from);
    String type = unit.getTypeString();
    Player own = unit.getOwner();
    unitMap.remove(from);
    unitMap.put(from, new UnitImpl(from, "nothing", Player.GREEN));
    unitMap.remove(to);
    if(cityMap.get(to).getOwner() != null){
      cityMap.put(to, new CityImpl(to, type, own));
    }
    unitMap.put(to, new UnitImpl(to, type, own));

    return false;
  }
  public void endOfTurn() {
    //each turn should add 100 years
    //each turn should switch the Player in turn at the very end
    //treasury + 6

    year = year + 100;
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {
    UnitActionStrat.setUnitAction(this, p, unitMap, cityMap);
  }
  public void produceTroopForCity(CityImpl c){

    c.IncrementTreasury();//Increment each round
    Position p = c.getPosition();

    int row = p.getRow();//values needed from city
    int column = p.getColumn();
    int Treasury_value = c.getTreasury();

    if(Treasury_value >= c.getProductCost()){
      if((unitMap.get(new Position(row-1, column)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position(row - 1, column), new UnitImpl(new Position(row - 1, column), ARCHER, Player.BLUE));
      }
      else if ((unitMap.get(new Position(row-1, column+1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position(row - 1, column+1), new UnitImpl(new Position(row - 1, column+1), ARCHER, Player.BLUE));
      }
      else if((unitMap.get(new Position( row, column+1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row, column+1), new UnitImpl(new Position(row, column + 1), ARCHER, Player.BLUE));
      }
      else if((unitMap.get(new Position( row+1, column+1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row+1, column+1), new UnitImpl(new Position(row+1, column + 1), ARCHER, Player.BLUE));
      }
      else if((unitMap.get(new Position( row+1, column)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row+1, column), new UnitImpl(new Position(row+1, column), ARCHER, Player.BLUE));
      }else if((unitMap.get(new Position( row+1, column-1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row+1, column-1), new UnitImpl(new Position(row+1, column-1), ARCHER, Player.BLUE));
      }
      else if((unitMap.get(new Position( row, column-1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row, column-1), new UnitImpl(new Position(row, column-1), ARCHER, Player.BLUE));
      }
      else if((unitMap.get(new Position( row-1, column-1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row-1, column-1), new UnitImpl(new Position(row, column-1), ARCHER, Player.BLUE));
      }
      else {
        c.DecrementTreasury();
        unitMap.put(new Position( row, column), new UnitImpl(new Position(row, column), ARCHER, Player.BLUE));
      }

    }
  }
}
