package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.BetaVersion;
import VersionControl.GammaVersion;
import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestGammaCiv {
    private Game game;

    /** Fixture for GammaCiv testing. */
    @Before
    public void setUp()
    {
        game = new GameImpl(new GammaVersion());
    }
    @Test
    public void SettlerShouldBecomeNothingForUnitMap() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is("nothing"));
    }

    @Test
    public void SettlerShouldBecomeRedCityForCityMap() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getCityAt(new Position(4, 3)).getOwner(), is(Player.RED));
    }
    @Test
    public void ArcherDefensiveStrengthDoubleToTwo() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is("nothing"));
    }

    @Test
    public void ArcherDefensiveStrengthIncreaseTo2() {
        //game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
        game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(6));
    }

    @Test
    public void ArcherDefensiveStrengthDecreaseTo1() {
        //game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
        game.performUnitActionAt(new Position(2, 0));
        game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
    }

    @Test
    public void ArcherCannotMoveTwoZero(){
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
        game.performUnitActionAt(new Position(2, 0));
        game.moveUnit(new Position(2, 0), new Position(2,1));
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2,1)).getTypeString(), is("nothing"));

    }
    @Test
    public void ArcherCanMoveTwoOne(){
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
        game.performUnitActionAt(new Position(2, 0));
        game.performUnitActionAt(new Position(2, 0));
        game.moveUnit(new Position(2, 0), new Position(2,1));
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is("nothing"));
        assertThat(game.getUnitAt(new Position(2,1)).getTypeString(), is(ARCHER));

    }
}

