package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.ThetaVersion;
import hotciv.framework.Game;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static Strategies.ThetaUnitAction.UFO;
import static hotciv.framework.GameConstants.ARCHER;
import static hotciv.framework.GameConstants.SETTLER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestMoveUnit {
    private Game game;

    /**
     * Fixture for AlphaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ThetaVersion());
    }

    @Test
    public void MoveUFOTwice(){
        game.changeProductionInCityAt(new Position(4,1), UFO);
        game.endOfTurn();
        for(int i = 0; i < 9*2; i++){
            game.endOfTurn();
        }
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(UFO));
        game.moveUnit(new Position(3,1), new Position(4, 2));
        assertThat(game.getUnitAt(new Position(4, 2)).getMoveCount(), is(1));
        game.moveUnit(new Position(4,2), new Position(5, 2));
        assertThat(game.getUnitAt(new Position(5, 2)).getMoveCount(), is(0));
        assertThat(game.moveUnit(new Position(5,2), new Position(4,2)), is(false));
    }

    @Test
    public void ArcherCanMoveOnce(){
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(SETTLER));
        game.moveUnit(new Position(3,1), new Position(4, 2));
        assertThat(game.getUnitAt(new Position(4, 2)).getMoveCount(), is(0));
        assertThat(game.moveUnit(new Position(4,2), new Position(5,2)), is(false));
    }

    @Test
    public void NoTroopOnTheOcean(){
        //Ocean is at 1,0
        game = new GameImpl(new AlphaVersion());
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(2,0), new Position(1,0)), is(false));
    }

    @Test
    public void NoAttackingAllies(){
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(SETTLER));
        assertThat(game.getUnitAt(new Position(4,2)).getTypeString(), is(SETTLER));
        assertThat(game.getUnitAt(new Position(3,1)).getOwner(), is(game.getUnitAt(new Position(4,2)).getOwner()));
        assertThat(game.moveUnit(new Position(3,1), new Position(4,2)), is(false));
    }

    @Test
    public void CanOnlyMoveDistanceOne(){
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(2,0), new Position(2,2)), is(false));
        assertThat(game.moveUnit(new Position(2,0), new Position(0,0)), is(false));
        assertThat(game.moveUnit(new Position(2,0), new Position(0,2)), is(false));
        assertThat(game.moveUnit(new Position(2,0), new Position(1,1)), is(true));
    }

    @Test
    public void CanOnlyMoveOnOwnTurn(){
        assertThat(game.moveUnit(new Position(2,0), new Position(2,2)), is(false));
        assertThat(game.moveUnit(new Position(2,0), new Position(0,0)), is(false));
        assertThat(game.moveUnit(new Position(2,0), new Position(0,2)), is(false));
        assertThat(game.moveUnit(new Position(2,0), new Position(1,1)), is(false));
    }
}
