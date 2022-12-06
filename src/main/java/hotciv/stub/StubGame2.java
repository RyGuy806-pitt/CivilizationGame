package hotciv.stub;

import hotciv.framework.*;

import java.util.*;

import static hotciv.framework.GameConstants.ARCHER;

/** Test stub for game for visual testing of
 * minidraw based graphics.
 *
 * SWEA support code.
 *
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

public class StubGame2 implements Game {

  // === Unit handling ===
  private Position pos_archer_red;
  private Position pos_legion_blue;
  private Position pos_settler_red;
  private Position pos_ufo_red;

  private Unit red_archer;
  private Position pos_city_red;
  private Position pos_city_blue;
  int age = -4000;

  public Unit getUnitAt(Position p) {
    if ( p.equals(pos_archer_red) ) {
      return red_archer;
    }
    if ( p.equals(pos_settler_red) ) {
      return new StubUnit( GameConstants.SETTLER, Player.RED );
    }
    if ( p.equals(pos_legion_blue) ) {
      return new StubUnit( GameConstants.LEGION, Player.BLUE );
    }
    if ( p.equals(pos_ufo_red) ) {
      return new StubUnit( ThetaConstants.UFO, Player.RED );
    }
    return null;
  }

  public City getCityAt(Position p) {
    if( p.equals(pos_city_red) ) {
      return new StubCity(pos_city_red, ARCHER, Player.RED);
    }
    if( p.equals(pos_city_blue) ) {
      return new StubCity(pos_city_blue, ARCHER, Player.BLUE);
    }
    return null;
  }


  // Stub only allows moving red archer
  public boolean moveUnit( Position from, Position to ) { 
    System.out.println( "-- StubGame2 / moveUnit called: "+from+"->"+to );
    if ( from.equals(pos_archer_red) ) {
      pos_archer_red = to;
    }
    // notify our observer(s) about the changes on the tiles
    gameObserver.worldChangedAt(from);
    gameObserver.worldChangedAt(to);
    return true; 
  }

  // === Turn handling ===
  private Player inTurn;
  public void endOfTurn() {
    System.out.println( "-- StubGame2 / endOfTurn called." );
    inTurn = (getPlayerInTurn() == Player.RED ?
              Player.BLUE : 
              Player.RED );
    age += (getPlayerInTurn() == Player.RED) ? 100 : 0;

    if(inTurn == Player.RED) ((StubUnit)red_archer).endTurn();
    gameObserver.turnEnds(inTurn, age);

    // no age increments
  }
  public Player getPlayerInTurn() { return inTurn; }
  

  // === Observer handling ===
  protected GameObserver gameObserver;
  // observer list is only a single one...
  public void addObserver(GameObserver observer) {
    gameObserver = observer;
  } 

  public StubGame2() { 
    defineWorld(1); 
    // AlphaCiv configuration
    pos_archer_red = new Position( 2, 0);
    pos_legion_blue = new Position( 3, 2);
    pos_settler_red = new Position( 4, 3);
    pos_ufo_red = new Position( 6, 4);
    pos_city_red = new Position(5,5);
    pos_city_blue = new Position(5,0);

    // the only one I need to store for this stub
    red_archer = new StubUnit( ARCHER, Player.RED );

    inTurn = Player.RED;
  }

  // A simple implementation to draw the map of DeltaCiv
  protected Map<Position,Tile> world; 
  public Tile getTileAt( Position p ) { return world.get(p); }


  /** define the world.
   * @param worldType 1 gives one layout while all other
   * values provide a second world layout.
   */
  protected void defineWorld(int worldType) {
    world = new HashMap<Position,Tile>();
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        Position p = new Position(r,c);
        world.put( p, new StubTile(GameConstants.PLAINS));
      }
    }
  }

  // TODO: Add more stub behaviour to test MiniDraw updating
  //public City getCityAt( Position p ) { return null; }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }  
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}

  public void setTileFocus(Position position) {
    // TODO: setTileFocus implementation pending.
    System.out.println("-- StubGame2 / setTileFocus called.");
    gameObserver.tileFocusChangedAt(position);
    //System.out.println(" *** IMPLEMENTATION PENDING ***");
  }

}

class StubUnit implements  Unit {
  private String type;
  private Player owner;
  int moveCount;
  public StubUnit(String type, Player owner) {
    this.type = type;
    this.owner = owner;
    moveCount = 1;
  }
  public void endTurn(){
    moveCount = 1;
  }
  public String getTypeString() { return type; }
  public Player getOwner() { return owner; }
  public int getMoveCount() { return 1; }
  public int getDefensiveStrength() { return 0; }
  public int getAttackingStrength() { return 0; }
}

class StubCity implements City{
  private String t;
  private Position p;
  private Player o;
  public StubCity(Position p, String t, Player o) {
    this.t = t;
    this.o = o;
    this.p = p;
  }
  @Override
  public Player getOwner() {
    return o;
  }

  @Override
  public int getSize() {
    return 1;
  }

  @Override
  public int getTreasury() {
    return 0;
  }

  @Override
  public String getProduction() {
    return t;
  }

  @Override
  public String getWorkforceFocus() {
    return "apple";
  }
}
