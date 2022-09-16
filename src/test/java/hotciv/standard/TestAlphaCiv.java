package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }
  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    // TODO: reenable the assert below to get started...
     assertThat(game.getPlayerInTurn(), is(Player.RED));
  }
  @Test
  public void shouldBeBlueAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    game.getPlayerInTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }
  @Test
  public void RedShouldWin(){
    assertThat(game, is(notNullValue()));
    game.getPlayerInTurn();
    assertThat(game.getWinner(), is(Player.RED));
  }
  @Test
  public void shouldReturnOcean(){
    //For tile (1,0), return Oceans
    game = new GameImpl();
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(), is(OCEANS));
  }
  @Test
  public void shouldReturnHills(){
    //For tile (0,1), return Hills
    game = new GameImpl();
    assertThat(game.getTileAt(new Position(0,1)).getTypeString(), is(HILLS));
  }
  @Test
  public void shouldReturnMountain(){
    //For tile (2,2), return Mountains
    game = new GameImpl();
    assertThat(game.getTileAt(new Position(2,2)).getTypeString(), is(MOUNTAINS));
  }
  @Test
  public void shouldReturnPlain(){
    //For tile (4,5), return Plains
    game = new GameImpl();
    assertThat(game.getTileAt(new Position(4,5)).getTypeString(), is(PLAINS));
  }
  @Test
  public void shouldReturnArcher(){
    //For tile (2,0), return archer for Red
    game = new GameImpl();
    assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(ARCHER));
    assertThat(game.getUnitAt(new Position(2,0)).getOwner(), is(Player.RED));
  }
  @Test
  public void shouldReturnLegion(){
    //For tile (3,2), return legion for blue
    game = new GameImpl();
    assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is(LEGION));
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
  }
  @Test
  public void shouldReturnSettler(){
    //For tile (4,3), return settler for Red
    game = new GameImpl();
    assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(SETTLER));
    assertThat(game.getUnitAt(new Position(4,3)).getOwner(), is(Player.RED));
  }
}
