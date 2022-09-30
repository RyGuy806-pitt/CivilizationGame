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
}
