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
  //FAILED: miscalculated mod2 and returned blue first
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }
    @Test
    //FAILED: did not fail because mod was fixed for previous test
    public void shouldBeBlueAsStartingPlayer() {
      assertThat(game, is(notNullValue()));
      game.getPlayerInTurn();
      assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }
  @Test
  //FAILED: year was not set and there was never a winner
  public void RedShouldWin(){
    assertThat(game, is(notNullValue()));
    for(int i = 0; i <= 70; i++)
    {
      game.getPlayerInTurn();
    }
    assertThat(game.getWinner(), is(Player.RED));
  }
  @Test
  //FAILED: had to restructure hashmap process and create tileImpl because would return string
  public void shouldReturnOcean(){
    //For tile (1,0), return Oceans
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(), is(OCEANS));
  }
  @Test
  //FAILED: didn't fail because after restructuring, all tile types passed
  public void shouldReturnHills(){
    //For tile (0,1), return Hills
    assertThat(game.getTileAt(new Position(0,1)).getTypeString(), is(HILLS));
  }
  @Test
  //FAILED: didn't fail because after restructuring, all tile types passed
  public void shouldReturnMountain(){
    //For tile (2,2), return Mountains
    assertThat(game.getTileAt(new Position(2,2)).getTypeString(), is(MOUNTAINS));
  }
  @Test
  //FAILED: didn't fail because after restructuring, all tile types passed
  public void shouldReturnPlain(){
    //For tile (4,5), return Plains
    assertThat(game.getTileAt(new Position(4,5)).getTypeString(), is(PLAINS));
  }
  @Test
    //FAILED: didn't have two separate asserts to confirm that player Red chose
  public void shouldReturnArcher(){
    //For tile (2,0), return archer for Red
    assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(ARCHER));
    assertThat(game.getUnitAt(new Position(2,0)).getOwner(), is(Player.RED));
  }
  @Test
    //FAILED: passed after adding another assert for showing which player chose
  public void shouldReturnLegion(){
    //For tile (3,2), return legion for blue
    assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is(LEGION));
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
  }
  @Test
    //FAILED: passed after adding another assert for showing which player chose
  public void shouldReturnSettler(){
    //For tile (4,3), return settler for Red
    assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(SETTLER));
    assertThat(game.getUnitAt(new Position(4,3)).getOwner(), is(Player.RED));
  }
  @Test
    //FAILED: no fails
  public void shouldReturnArcherAfterRedSelection(){
    //Player should be able to select archer
    //City at (1,1) is RED
    assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(ARCHER));
  }
  @Test
   // FAILED: failed because we set legion instead of settler
  public void shouldReturnSettlerAfterBlueSelection(){
    //Player should be able to select Settler
    //City at (4,1) is BLUE
    assertThat(game.getCityAt(new Position(4, 1)).getProduction(), is(SETTLER));
  }
  @Test
    //FAILED: no fails
  public void shouldReturnPopulation1ForBlue(){
    assertThat(game.getCityAt(new Position(4, 1)).getSize(), is(1));
  }
  @Test
   // FAILED: called production instead of treasury, returned wrong value type of string
  public void shouldReturnTreasury6ForRed(){
    assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(6));
  }
  @Test
  public void AttackSettlerWithLegion(){
    game.moveUnit(new Position(3,2), new Position(4,3));
    assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(LEGION));
  }
}
