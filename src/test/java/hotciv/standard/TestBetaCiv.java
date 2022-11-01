package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.BetaVersion;
import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestBetaCiv {
    private Game game;

    /**
     * Fixture for BetaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaVersion());
    }

    @Test
    public void RedShouldOwnBlueCity() {
        //assertThat(game, is(notNullValue()));
        //game.getCityAt(new Position(1, 1))
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
        game.moveUnit(new Position(4, 3), new Position(4, 1));
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.RED));
    }

    @Test
    public void RedShouldWin() {
        //assertThat(game, is(notNullValue()));
        //game.getCityAt(new Position(1, 1))
        game.moveUnit(new Position(4, 3), new Position(4, 1));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void ShouldBeYear3900BC(){
        //game.getPlayerInTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));
    }

    @Test
    public void ShouldBeYear1BC(){
        for(int i = 0; i<40; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(-1));
    }

    @Test
    public void ShouldBeYear1AD(){
        for(int i = 0; i<41; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1));
    }

    @Test
    public void ShouldBeYear50AD(){
        for(int i = 0; i<42; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(50));
    }

    @Test
    public void ShouldBeYear100AD(){
        for(int i = 0; i<43; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(100));
    }

    @Test
    public void ShouldBeYear1775AD(){
        for(int i = 0; i<77; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1775));
    }

    @Test
    public void ShouldBeYear1905AD(){
        for(int i = 0; i<83; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1905));
    }

    @Test
    public void ShouldBeYear1971AD(){
        for(int i = 0; i<97; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1971));
    }
}
