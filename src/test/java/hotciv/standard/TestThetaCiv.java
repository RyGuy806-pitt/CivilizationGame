package hotciv.standard;

import VersionControl.GammaVersion;
import VersionControl.ThetaVersion;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestThetaCiv {

    private GameImpl game;
    public static final String UFO = "ufo";

    @Before
    public void setUp()
    {
        game = new GameImpl(new ThetaVersion());
    }
    @Test
    public void SettlerShouldBecomeNothingForUnitMap() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is("nothing"));
    }

    @Test
    public void UFOCanBeProducedWithChangeProductionMethod(){
        assertThat(game.getCityAt(new Position(4,1)).getProduction(), is(SETTLER));
        game.changeProductionInCityAt(new Position(4, 1), UFO);
        assertThat(game.getCityAt(new Position(4,1)).getProduction(), is(UFO));

    }

    @Test
    public void ChangeProductionWorks(){
        assertThat(game.getCityAt(new Position(4,1)).getProduction(), is(SETTLER));
        game.changeProductionInCityAt(new Position(4,1), ARCHER);
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(ARCHER));
        game.changeProductionInCityAt(new Position(4,1), LEGION);
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is(LEGION));

    }
    @Test
    public void UFOIsProducedAtThreeOneWith60InTreasury(){
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is("nothing"));
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(4,1), UFO);
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is("nothing"));
        for(int i = 0; i < 8*2; i++){
            game.endOfTurn();
        }
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(UFO));

    }
}
