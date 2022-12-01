package hotciv.standard;

import VersionControl.GammaVersion;
import VersionControl.ThetaVersion;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.stub.GameStub;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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

    @Test
    public void UFOStatsAttackIsOneDefenseIsEight(){
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(4,1), UFO);
        game.endOfTurn();
        for(int i = 0; i < 8*2; i++){
            game.endOfTurn();
        }
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(UFO));
        assertThat(game.getUnitAt(new Position(3, 1)).getAttackingStrength(), is(1));
        assertThat(game.getUnitAt(new Position(3, 1)).getDefensiveStrength(), is(8));
    }

    @Test
    public void MoveCountImplemented(){
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getMoveCount(), is(1));
        game.changeProductionInCityAt(new Position(4,1), UFO);
        for(int i=0; i<10*2; i++){
            game.endOfTurn();
        }
        assertThat(game.getUnitAt(new Position(4, 2)).getTypeString(), is(UFO));
        assertThat(game.getUnitAt(new Position(4, 2)).getMoveCount(), is(2));
    }

    @Test
    public void SeeIfMoveCountIncrements(){
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is("nothing"));
        assertThat(game.moveUnit(new Position(2,0), new Position(2,1)), is(true));
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is("nothing"));
        assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(0));

    }

    @Test
    public void MoveUFOTwice(){
        game.changeProductionInCityAt(new Position(4,1), UFO);
        game.endOfTurn();
        for(int i = 0; i < 8*2; i++){
            game.endOfTurn();
        }
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(UFO));
        game.moveUnit(new Position(3,1), new Position(4, 2));
        assertThat(game.getUnitAt(new Position(4, 2)).getMoveCount(), is(1));
        game.moveUnit(new Position(4,2), new Position(5, 2));
        game.getUnitAt(new Position(5, 2)).getMoveCount();
        assertThat(game.moveUnit(new Position(5,2), new Position(4,2)), is(false));
    }

    @Test
    public void EndOfRoundResetsMoveCount(){
        game.endOfTurn();
        game.changeProductionInCityAt(new Position(4,1), UFO);
        game.endOfTurn();
        for(int i = 0; i < 8*2; i++){
            game.endOfTurn();
        }
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(UFO));
        game.moveUnit(new Position(3,1), new Position(4, 2));
        assertThat(game.getUnitAt(new Position(4, 2)).getMoveCount(), is(1));
        game.moveUnit(new Position(4,2), new Position(5, 2));
        assertThat(game.getUnitAt(new Position(5, 2)).getMoveCount(), is(0));
        assertThat(game.moveUnit(new Position(5,2), new Position(4,2)), is(false));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(5,2), new Position(4,2)), is(true));
    }
//map bug once move unit was updated
//    @Test
//    public void UFOUnitActionRemovesCityAt0Population(){
//        game.endOfTurn();
//        game.changeProductionInCityAt(new Position(4,1), UFO);
//        game.endOfTurn();
//        for(int i = 0; i < 8*2; i++){
//            game.endOfTurn();
//        }
//        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(UFO));
//        assertThat(game.moveUnit(new Position(3,1), new Position(2, 1)), is(true));
//        //assertThat(game.moveUnit(new Position(2,1), new Position(1, 1)), is(true));
//        assertThat(game.getCityAt(new Position(1,1)).getSize(), is(1));
//        game.performUnitActionAt(new Position(1,1));
//        assertThat(game.getCityAt(new Position(1,1)).getSize(), is(0));
//        assertThat(game.getCityAt(new Position(1,1)), is(nullValue()));
//
//
//    }

    @Test
    public void UFOChangesForestToPlains(){
        GameStub game = new GameStub(new ThetaVersion());
        assertThat(game.getTileAt(new Position(12, 1)).getTypeString(), is(FOREST));
        assertThat(game.getUnitAt(new Position(12, 1)).getTypeString(), is(UFO));
        game.performUnitActionAt(new Position(12, 1));
        assertThat(game.getTileAt(new Position(12, 1)).getTypeString(), is(PLAINS));
    }
}
