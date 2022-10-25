package hotciv.standard;

import VersionControl.EpsilonVersion;
import hotciv.framework.Game;
import hotciv.framework.Player;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
}
