package hotciv.standard;

import VersionControl.GammaVersion;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.ARCHER;
import static hotciv.framework.GameConstants.SETTLER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestThetaCiv {

    private GameImpl game;
    public static final String UFO = "ufo";

    @Before
    public void setUp()
    {
        game = new GameImpl(new GammaVersion());
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
}
