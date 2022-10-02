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
        assertThat(game, is(notNullValue()));
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }
    @Test   //BetaCiv
    public void ShouldBeYear1775AD(){
        game = new GameImpl(new BetaVersion());
        for(int i = 0; i<76; i++){
            game.getAge();
        }

        assertThat(game.getAge(), is(1775));
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
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(1));
        game.performUnitActionAt(new Position(2, 0));
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(2));
    }
}
