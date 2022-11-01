package hotciv.standard;

import VersionControl.BetaVersion;
import VersionControl.EpsilonVersion;
import VersionControl.ZetaVersion;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.stub.GameStub;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestZetaCiv {

    private GameImpl game;

    /** Fixture for ZetaCiv testing **/

    @Before
    public void setUp() {
        game = new GameImpl(new ZetaVersion());
    }

    @Test
    public void RedShouldWin() {
        //assertThat(game, is(notNullValue()));
        //game.getCityAt(new Position(1, 1))
        game.moveUnit(new Position(4, 3), new Position(4, 1));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void PlayerBlueWinsAfter3AttacksAnd20Rounds(){
        GameStub g = new GameStub(new ZetaVersion());
        for(int i=0; i<25; i++){
            g.endOfTurn();
        }
        assertThat(g.moveUnit(new Position(6,3), new Position(5,3)), is(true));
        assertThat(g.moveUnit(new Position(6,4), new Position(5,4)), is(true));
        assertThat(g.moveUnit(new Position(6,5), new Position(5,5)), is(true));
        assertThat(g.getWinner(), is(Player.BLUE));

    }

    @Test
    public void PlayerRedStillWinsWhenBlueDoes3AttacksBefore20Turns(){
        game.moveUnit(new Position(4, 3), new Position(4, 1));
        assertThat(game.getWinner(), is(Player.RED));
        game.setBlueWins(3);
        assertThat(game.getWinner(), is(Player.RED));
    }
}
