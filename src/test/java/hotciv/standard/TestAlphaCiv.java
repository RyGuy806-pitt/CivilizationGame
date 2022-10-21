package hotciv.standard;

import VersionControl.AlphaVersion;
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
    game = new GameImpl(new AlphaVersion());
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
    //assertThat(game, is(notNullValue()));
    for(int i = 0; i <= 72; i++)
    {
      game.endOfTurn();
      game.getPlayerInTurn();
    }
    assertThat(game.getWinner(), is(Player.RED));
    assertThat(game.getAge(), is(3300));
  }
  @Test
  public void shouldReturnOcean(){
    //For tile (1,0), return Oceans
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(), is(OCEANS));
  }
  @Test
  public void shouldReturnHills(){
    //For tile (0,1), return Hills
    assertThat(game.getTileAt(new Position(0,1)).getTypeString(), is(HILLS));
  }
  @Test
  public void shouldReturnMountain(){
    //For tile (2,2), return Mountains
    assertThat(game.getTileAt(new Position(2,2)).getTypeString(), is(MOUNTAINS));
  }
  @Test
  public void shouldReturnPlain(){
    //For tile (4,5), return Plains
    assertThat(game.getTileAt(new Position(4,5)).getTypeString(), is(PLAINS));
  }
  @Test
  public void shouldReturnArcher(){
    //For tile (2,0), return archer for Red
    assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(ARCHER));
    assertThat(game.getUnitAt(new Position(2,0)).getOwner(), is(Player.RED));
  }
  @Test
  public void shouldReturnLegion(){
    //For tile (3,2), return legion for blue
    assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is(LEGION));
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(), is(Player.BLUE));
  }
  @Test
  public void shouldReturnSettler(){
    //For tile (4,3), return settler for Red
    assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(SETTLER));
    assertThat(game.getUnitAt(new Position(4,3)).getOwner(), is(Player.RED));
  }
  @Test
  public void shouldReturnArcherAfterRedSelection(){
    //Player should be able to select archer
    //City at (1,1) is RED
    assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(ARCHER));
  }
  @Test
  public void shouldReturnSettlerAfterBlueSelection(){
    //Player should be able to select Settler
    //City at (4,1) is BLUE
    assertThat(game.getCityAt(new Position(4, 1)).getProduction(), is(SETTLER));
  }
  @Test
  public void shouldReturnPopulation1ForBlue(){
    assertThat(game.getCityAt(new Position(4, 1)).getSize(), is(1));
  }
  @Test
  public void shouldReturnTreasury6ForRed(){
    assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(6));
  }
  @Test
  public void AttackSettlerWithLegion(){
    game.moveUnit(new Position(3,2), new Position(4,3));
    assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(LEGION));
  }

  @Test
  public void ProduceTroops(){
    game.endOfTurn();
    game.getPlayerInTurn();
    game.endOfTurn();
    game.getPlayerInTurn();
    assertThat(game.getUnitAt(new Position(3, 1)).getTypeString(), is(ARCHER));
  }

  @Test
  public void DecrementTreasury(){
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(8));
  }

  @Test
  public void DecrementTreasurySecondTroop(){
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getUnitAt(new Position(4, 2)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(10));
  }

  @Test
  public void DecrementTreasuryThirdTroop(){
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 18 - 4*2
    game.endOfTurn();
    game.endOfTurn();//treasury should be 24 - 4*3 = 12
    assertThat(game.getUnitAt(new Position(5, 2)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(12));
  }

  @Test
  public void DecrementTreasuryFourthTroop(){
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 18 - 4*2
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 30 - 4*4 = 14
    assertThat(game.getUnitAt(new Position(5, 1)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(14));
  }
  @Test
  public void DecrementTreasuryFifthTroop(){
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 18 - 4*2
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 30 - 4*4 = 14
    game.endOfTurn();
    game.endOfTurn();//treasury should be 36 - 4*5 = 16
    assertThat(game.getUnitAt(new Position(5, 0)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(16));
  }

  @Test
  public void DecrementTreasurySixthTroop(){
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 18 - 4*2
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 30 - 4*4 = 14
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 42 - 4*6 = 18
    assertThat(game.getUnitAt(new Position(4, 0)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(18));
  }

  @Test
  public void DecrementTreasurySeventhTroop(){
    game.endOfTurn();
    game.endOfTurn();//treasury should be 12 - 4
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 24 - 4*3 = 12
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 36 - 4*5 = 16
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 48 - 4*7 = 20
    assertThat(game.getUnitAt(new Position(3, 0)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(20));
  }

  @Test
  public void DecrementTreasuryEighthTroop(){
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 18 - 4*2
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 30 - 4*4 = 14
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 42 - 4*6 = 18
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 48 - 4*7 = 22
    assertThat(game.getUnitAt(new Position(4, 1)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(4, 1)).getTreasury(), is(22));
  }

  @Test
  public void DecrementTreasuryNinthTroop(){
    game.endOfTurn();
    game.endOfTurn();//treasury should be 12 - 4
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 24 - 4*3 = 12
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 36 - 4*5 = 16
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 48 - 4*7 = 20
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();
    game.endOfTurn();//treasury should be 60 - 4*9 = 24
    assertThat(game.getUnitAt(new Position(1, 1)).getTypeString(), is(ARCHER));
    assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(24));
  }

  @Test
  public void RedDecrementTreasury(){
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getCityAt(new Position(1, 1)).getTreasury(), is(8));
  }

  @Test
  public void RedProduceTroops(){
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getUnitAt(new Position(0, 1)).getTypeString(), is(ARCHER));
  }
}
//change