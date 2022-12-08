package hotciv.standard;

import Strategies.*;
import VersionControl.Version;
import hotciv.framework.*;
import hotciv.spy.GameObserverSpy;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

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

  int movesThisTurn = 0;
  private int redPlayerWinCounter = 0;
  private int bluePlayerWinCounter = 0;
  private HashMap<Position, TileImpl> tileMap = new HashMap();
  private HashMap<Position, UnitImpl> unitMap = new HashMap();
  private HashMap<Position, CityImpl> cityMap = new HashMap();

  private Winner WinnerStrat;
  private UnitAction UnitActionStrat;
  private WorldMap WorldMapStrat;
  private Aging AgingStrat;
  private Attack AttackStrat;
  private List<GameObserver> Obs = new ArrayList<>();

  public GameImpl(Version Var) {
    this.WinnerStrat = Var.createWinner();
    this.AgingStrat = Var.createAging();
    this.WorldMapStrat = Var.createWorldMap();
    this.UnitActionStrat = Var.createUnitAction();
    this.AttackStrat = Var.createAttack();

    //For world layout: tile types
    //This part stays in game Impl
    for(int i=0; i<WORLDSIZE; i++) {
      for(int j=0; j<WORLDSIZE; j++) {
        Position p = new Position(i, j);
        tileMap.put(p, new TileImpl(p, PLAINS));
        //unitMap.put(p, new UnitImpl(p, "nothing", Player.GREEN));
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
    int turn_count;
    //endOfTurn();
    turn_count = TurnImpl.getTurn();
    if(turn_count%2 == 0){
      //this part needs to take a position input of the city in its own function
      return Player.RED;
    }
    else {
      return Player.BLUE;
    }
  }
  public Player getWinner() {
    return WinnerStrat.calculateWinner(this);
  }

  public int getAge() {
    return year;
  }

  public void calculateAge() {
    year =  AgingStrat.calculateTime();
  }

  public boolean performAttack(Position to, Position from){ return AttackStrat.attack(this, to, from);
  }
  public boolean moveUnit( Position from, Position to ) {
    //replace with none
    //rewrite unit at map location
    int row, col = 0;
    UnitImpl unit = unitMap.get(from);
    if(unit == null ){
      return false;
    }
    if(unit.getMoveCount()==0){
      for (GameObserver observer : Obs) {
        observer.worldChangedAt(from);
      }
      return false;
    }
    if(tileMap.get(to).getTypeString()==OCEANS){
      for (GameObserver observer : Obs) {
        observer.worldChangedAt(from);
      }
      return false;
    }
    if(unitMap.get(to) != null) {
      if (unit.getOwner() == unitMap.get(to).getOwner()) {
        for (GameObserver observer : Obs) {
          observer.worldChangedAt(from);
        }
        return false;
      }
    }
    row = from.getRow()- to.getRow();
    col = from.getColumn() - to.getColumn();
    if(-1 > row){
      for (GameObserver observer : Obs) {
        observer.worldChangedAt(from);
      }
      return false;
    } else if(row > 1) {
      for (GameObserver observer : Obs) {
        observer.worldChangedAt(from);
      }
      return false;
    } else if(-1 > col){
      for (GameObserver observer : Obs) {
        observer.worldChangedAt(from);
      }
      return false;
    } else if(col > 1) {
      for (GameObserver observer : Obs) {
        observer.worldChangedAt(from);
      }
      //SOS help
      return false;
    }

    if(getPlayerInTurn() != getUnitAt(from).getOwner()){
      for (GameObserver observer : Obs) {
        observer.worldChangedAt(from);
      }
      return false;
    }
    WorldChangeUpdateSpy(from);
    //unitMap.get(from).setMoveCount(unit.getMoveCount() - 1);

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
        //unitMap.put(from, new UnitImpl(from, "nothing", Player.GREEN));
        unitMap.remove(to);
        if (cityMap.get(to) != null) {
          cityMap.put(to, new CityImpl(to, type, own));
        }
        unitMap.put(to, new UnitImpl(to, type, own));
        unitMap.get(to).setMoveCount(unit.getMoveCount() - 1);
        //WorldChangeUpdateSpy(to);
        TileFocusUpdateSpy(to);
        for (GameObserver observer : Obs) {
          observer.worldChangedAt(to);
          observer.tileFocusChangedAt(to);
        }
        return true;
      }
    }
    else{
      unitMap.remove(from);
      unitMap.put(from, new UnitImpl(from, "nothing", Player.GREEN));
    }
    return false;
  }
  public void endOfTurn() {
    //each turn should add 100 years
    //each turn should switch the Player in turn at the very end
    //treasury + 6
    int turn_count;
    turn_count = TurnImpl.getTurn();
    turn_count++;
    TurnImpl.setTurn(turn_count);
    getWinner();
    if(turn_count%2 == 0){
      //this part needs to take a position input of the city in its own function
      for(int i = 0; i<WORLDSIZE; i++){
        for(int j = 0; j<WORLDSIZE; j++){
          if(unitMap.get(new Position(i,j)) != null) {
            if (unitMap.get(new Position(i, j)).getTypeString() != "nothing") {
              unitMap.get(new Position(i, j)).resetMoveCount();
            }
          }
        }
      }

      if(cityMap.get(new Position(4, 1)) != null) {
        produceTroopForCity(cityMap.get(new Position(4, 1)));
        produceTroopForCity(cityMap.get(new Position(1, 1)));
      }
      else {
        //do nothing
      }
    }
    else {
    }
    //year = year + 100;
    calculateAge();
    for(GameObserver observer: Obs){
      observer.turnEnds(getPlayerInTurn(), getAge());
    }
    EndOfTurnUpdateSpy();
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {
    TileFocusUpdateSpy(p);
  }
  public void changeProductionInCityAt( Position p, String unitType ) {
    cityMap.get(p).changeProduction(unitType);
    cityMap.get(p).setProductCost(unitType);
    TileFocusUpdateSpy(p);
  }
  public void performUnitActionAt( Position p ) {
    UnitActionStrat.setUnitAction(this, p, unitMap, cityMap, tileMap);
    for (GameObserver observer : Obs) {
      observer.worldChangedAt(p);
    }
  }

  @Override
  public void addObserver(GameObserver observer) {
    Obs.add(observer);
  }


  private void EndOfTurnUpdateSpy(){
    for(GameObserver observer: Obs){
      observer.turnEnds(getPlayerInTurn(), getAge());
    }
  }

  private void WorldChangeUpdateSpy(Position position) {
    for (GameObserver observer : Obs) {
      observer.worldChangedAt(position);
    }
  }

  private void TileFocusUpdateSpy(Position position){
    for (GameObserver observer : Obs) {
      observer.tileFocusChangedAt(position);
    }
  }


//  public List<GameObserverSpy> ObserverLogs(){
//    return Obs[1];
//  }

  @Override
  public void setTileFocus(Position position) {
    for (GameObserver observer : Obs) {
      observer.tileFocusChangedAt(position);
    }
    //TileFocusUpdateSpy(position);
  }

  public void produceTroopForCity(CityImpl c){

    c.IncrementTreasury();//Increment each round
    Position p = c.getPosition();

    int row = p.getRow();//values needed from city
    int column = p.getColumn();
    int Treasury_value = c.getTreasury();
    String Product = c.getProduction();

    if(Treasury_value >= c.getProductCost()){
      if((unitMap.get(new Position(row-1, column)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position(row - 1, column), new UnitImpl(new Position(row - 1, column), Product, Player.BLUE));
        for (GameObserver observer : Obs) {
          observer.worldChangedAt(new Position(row - 1, column));
        }
      }
      else if ((unitMap.get(new Position(row-1, column+1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position(row - 1, column+1), new UnitImpl(new Position(row - 1, column+1), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position(row - 1, column+1));
      }
      else if((unitMap.get(new Position( row, column+1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row, column+1), new UnitImpl(new Position(row, column + 1), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position( row, column+1));
      }
      else if((unitMap.get(new Position( row+1, column+1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row+1, column+1), new UnitImpl(new Position(row+1, column + 1), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position( row+1, column+1));
      }
      else if((unitMap.get(new Position( row+1, column)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row+1, column), new UnitImpl(new Position(row+1, column), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position( row+1, column));
      }else if((unitMap.get(new Position( row+1, column-1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row+1, column-1), new UnitImpl(new Position(row+1, column-1), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position( row+1, column-1));
      }
      else if((unitMap.get(new Position( row, column-1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row, column-1), new UnitImpl(new Position(row, column-1), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position( row, column-1));
      }
      else if((unitMap.get(new Position( row-1, column-1)).getTypeString() == "nothing")) {
        c.DecrementTreasury();
        unitMap.put(new Position( row-1, column-1), new UnitImpl(new Position(row, column-1), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position( row-1, column-1));
      }
      else {
        c.DecrementTreasury();
        unitMap.put(new Position( row, column), new UnitImpl(new Position(row, column), Product, Player.BLUE));
        WorldChangeUpdateSpy(new Position( row, column));
      }

    }
  }

  public void setRedWins(int x){
    redPlayerWinCounter = x;
  }

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
