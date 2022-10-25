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

    /** Fixture for alphaciv testing. */
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
    public void ArcherDefensiveStrengthDecreaseTo1() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getCityAt(new Position(4, 3)).getOwner(), is(Player.RED));
    }
}

