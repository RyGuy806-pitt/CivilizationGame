package hotciv.standard;
import VersionControl.AlphaVersion;
import VersionControl.BetaVersion;
import VersionControl.DeltaVersion;
import VersionControl.GammaVersion;
import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestIntegration {
    private Game game;

    @Test   //AlphaCiv
    public void shouldBeRedAsStartingPlayer() {
        game = new GameImpl(new AlphaVersion());
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }
    @Test   //BetaCiv
    public void ShouldBeYear1775AD(){
        game = new GameImpl(new BetaVersion());
        for(int i = 0; i<77; i++){
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1775));
    }
    @Test   //BetaCiv
    public void RedShouldWin(){
        //assertThat(game, is(notNullValue()));
        //game.getCityAt(new Position(1, 1))
        game = new GameImpl(new BetaVersion());
        game.moveUnit(new Position(4,3), new Position(4,2));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(4,2), new Position(4,1));
        assertThat(game.getWinner(), is(Player.RED));
    }
    @Test   //DeltaCiv
    public void ReturnBlueCityForPositionFourFive() {
        game = new GameImpl(new DeltaVersion());
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }
    @Test   //GammaCiv
    public void ArcherDefensiveStrengthIncreaseTo2() {
        game = new GameImpl(new GammaVersion());
        //game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
        game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(6));
    }
}
