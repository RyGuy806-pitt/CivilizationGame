package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.EpsilonVersion;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

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
        assertThat(g.performAttack(new Position(2, 1), new Position(1,1)), is(false));
    }

//    @Test
//    public void AttackTrueAndMoveUnit() {
//
//        GameImpl g = new GameImpl(new AlphaVersion());
//        g.moveUnit(new Position(4,3), new Position(3,2));
//        assertThat(g.getUnitAt(new Position (3, 2), is()));
//    }
}
