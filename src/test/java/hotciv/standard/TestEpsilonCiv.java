package hotciv.standard;

import Strategies.Attack;
import Strategies.EpsilonAttack;
import VersionControl.AlphaVersion;
import VersionControl.EpsilonVersion;
import VersionControl.Version;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.stub.GameStub;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TestEpsilonCiv {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonVersion());
    }
    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void RedShouldWinGame() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.setRedWins(3);
        assertThat(g.getWinner(), is(Player.RED));
    }

    @Test
    public void BlueShouldWinGame() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.setBlueWins(3);
        assertThat(g.getWinner(), is(Player.BLUE));
    }

    @Test
    public void RedShouldNotWinGame() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.setRedWins(2);
        assertThat(g.getWinner(), is(nullValue()));
    }

    @Test
    public void BlueShouldNotWinGame() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.setBlueWins(1);
        assertThat(g.getWinner(), is(nullValue()));
    }

    @Test
    public void AttackTrue() {

        GameImpl g = new GameImpl(new AlphaVersion());
        g.setRedWins(2);
        assertThat(g.performAttack(new Position(2, 1), new Position(1,1)), is(true));
    }

    @Test
    public void AttackFalse() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.setBlueWins(1);
        assertThat(g.performAttack(new Position(2, 1), new Position(1,1)), is(true));
    }

    @Test
    public void AlphaAttackTrueAndMoveUnit() {

        GameImpl g = new GameImpl(new AlphaVersion());
        assertThat(g.getUnitAt(new Position (4, 3)).getTypeString(), is(SETTLER));
        g.moveUnit(new Position(4,3), new Position(3,2));
        assertThat(g.getUnitAt(new Position (3, 2)).getTypeString(), is(SETTLER));
    }

    @Test
    public void EpsilonAttackTrueAndMoveUnit() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position (4, 3)).getTypeString(), is(SETTLER));
        g.moveUnit(new Position(4,3), new Position(3,2));
        assertThat(g.getUnitAt(new Position (3, 2)).getTypeString(), is(LEGION));
    }

    @Test
    public void CheckSettlerStrengths() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position (4, 3)).getDefensiveStrength(), is(3));
        assertThat(g.getUnitAt(new Position (4, 3)).getTypeString(), is(SETTLER));
    }

    @Test
    public void CheckLegionStrengths() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position (3, 2)).getAttackingStrength(), is(4));
        assertThat(g.getUnitAt(new Position (3, 2)).getTypeString(), is(LEGION));
    }

    @Test
    public void MoveUnitWorking() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.moveUnit(new Position(3, 2), new Position(4, 3));
        assertThat(g.getUnitAt(new Position(4, 3)).getTypeString(), is(LEGION));

    }

    @Test
    public void MoveUnitDenyMovementReturnLegionFromInitialTile() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.moveUnit(new Position(4, 3), new Position(3, 2));
        assertThat(g.getUnitAt(new Position(3, 2)).getTypeString(), is(LEGION));

    }

    @Test
    public void MoveUnitRemoveTroopReturnNothingAtInitialTile() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        g.moveUnit(new Position(4, 3), new Position(3, 2));
        assertThat(g.getUnitAt(new Position(4, 3)).getTypeString(), is("nothing"));

    }

    @Test
    public void TestStubMap(){
        GameStub g = new GameStub(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position(2, 2)).getTypeString(), is(ARCHER));
    }

    @Test
    public void TestGameStubHasUnitStubsWithDefenseFiveAndHundred(){//at 2,2 and 2,5
        GameStub g = new GameStub(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position(2, 2)).getTypeString(), is(ARCHER));
        assertThat(g.getUnitAt(new Position(2,2)).getDefensiveStrength(), is(5));
        assertThat(g.getUnitAt(new Position(2, 2)).getTypeString(), is(ARCHER));
        assertThat(g.getUnitAt(new Position(2,5)).getDefensiveStrength(), is(100));
        //making sure the UnitStubs are in the StubGame, they return the correct values
    }
// These only work without the random number
//    @Test
//    public void EpsilonAttackCalculatesTerrainAndAllyBonusesReturnsTenAndSix(){
//        EpsilonAttack a = new EpsilonAttack();
//        GameStub g = new GameStub(new EpsilonVersion());
//
//        assertThat(a.totalOffensiveStrength(g, new Position(6, 6), Player.RED), is(10));
//        assertThat(a.totalOffensiveStrength(g, new Position(6, 6), Player.BLUE), is(6));
//        //the line below looks like it should work, but should not. getAttackingStrength is the natural value
//        //Either way at this point the troops should not be able to move
//        //assertThat(g.getUnitAt(new Position(6, 6)).getAttackingStrength(), is(6));
//
//    }
//    @Test
//    public void EpsilonAttackDoesNotAllowMovementWhenDefenseIsGreaterThanAttack(){
//        EpsilonAttack a = new EpsilonAttack();
//        GameStub g = new GameStub(new EpsilonVersion());
//
//        assertThat(a.totalOffensiveStrength(g, new Position(6, 6), Player.BLUE), is(6));
//        assertThat(a.totalDefensiveStrength(g, new Position(7, 7), Player.RED), is(5));
//        //assertThat(g.getUnitAt(new Position(6,6)).getOwner(), is(Player.BLUE));
//        assertThat(g.moveUnit(new Position(7,7), new Position(6,6)), is(false));
//        assertThat(g.moveUnit(new Position(6,6), new Position(7,7)), is(true));
//        assertThat(g.getUnitAt(new Position(7,7)).getOwner(), is(Player.BLUE));
//        assertThat(g.getUnitAt(new Position(6,6)).getOwner(), is(Player.GREEN));
//
//    }

}
