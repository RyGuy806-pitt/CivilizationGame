package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.DeltaVersion;
import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestDeltaCiv {
    private Game game;

    /** Fixture for DeltaCiv testing **/

    @Before
    public void setUp() {
        game = new GameImpl(new DeltaVersion());
    }
    @Test
    public void ReturnRedCityForPositionEightTwo() {
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }
    @Test
    public void ReturnBlueCityForPositionFourFive() {
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }
    @Test
    public void ReturnOceansAtPositionZeroZero() {
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(OCEANS));
    }
    @Test
    public void ReturnOceansAtPositionOneOne() {
        assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(OCEANS));
    }

    @Test
    public void ReturnMountainsAtPositionZeroFive() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(MOUNTAINS));
    }

    @Test
    public void ReturnForestAtPositionOneTen() {
        assertThat(game.getTileAt(new Position(1,9)).getTypeString(), is(FOREST));
    }
    @Test
    public void ReturnMountainsAtPositionThreeFour() {
        assertThat(game.getTileAt(new Position(3,4)).getTypeString(), is(MOUNTAINS));
    }
    @Test
    public void ReturnForestAtPositionNineFourteen() {
        assertThat(game.getTileAt(new Position(8,13)).getTypeString(), is(FOREST));
    }
    @Test
    public void ReturnHillsAtPositionFifteenFive() {
        assertThat(game.getTileAt(new Position(14,6)).getTypeString(), is(HILLS));
    }

}
