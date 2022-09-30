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
    public void ReturnPlainsAtPositionOneOne() {
        assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(OCEANS));
    }

    @Test
    public void ReturnPlainsAtPositionZeroFive() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(MOUNTAINS));
    }
//    @Test
//    public void ReturnMountainsAtPositionOneSix() {
//        assertThat(game.getTileAt(new Position(1,6)).getTypeString(), is(MOUNTAINS));
//    }
//    @Test
//    public void ReturnForestAtPositionNineFourteen() {
//        assertThat(game.getTileAt(new Position(9,14)).getTypeString(), is(FOREST));
//    }
//    @Test
//    public void ReturnHillsAtPositionFifteenFive() {
//        assertThat(game.getTileAt(new Position(15,5)).getTypeString(), is(HILLS));
//    }

}
