package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.BetaVersion;
import VersionControl.GammaVersion;
import VersionControl.DeltaVersion;
import VersionControl.EpsilonVersion;
import VersionControl.SemiVersion;
import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestSemiCiv {
    private Game game;

    /** Fixture for SemiCiv testing **/

    @Before
    public void setUp()
    {
        game = new GameImpl(new SemiVersion());
    }

    //Testing BetaAging
    @Test
    public void ShouldBeYear3900BC(){
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));
    }
    @Test   //Had to put the if statement in GameImpl in order to properly run endOfTurn()
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
    //Testing EpsilonWinner
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
    //Testing GammaUnitAction
    @Test
    public void SettlerShouldBecomeNothingForUnitMap() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is("nothing"));
    }
    @Test   //Had to put a settler into unit map in DeltaWorldMap
    public void SettlerShouldBecomeRedCityForCityMap() {
        game.performUnitActionAt(new Position(4, 3));
        assertThat(game.getCityAt(new Position(4, 3)).getOwner(), is(Player.RED));
    }
    //Testing DeltaWorldMap
    @Test
    public void ReturnRedCityForPositionEightTwo() {
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }
    @Test
    public void ReturnBlueCityForPositionFourFive() {
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }
    @Test
    public void ReturnOceansAtPositionZeroZero() {
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(OCEANS));
    }
    @Test
    public void ReturnOceansAtPositionOneOne() {
        assertThat(game.getTileAt(new Position(1,1)).getTypeString(), is(OCEANS));
    }

    @Test
    public void ReturnMountainsAtPositionZeroFive() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(MOUNTAINS));
    }

    @Test
    public void ReturnForestAtPositionOneTen() {
        assertThat(game.getTileAt(new Position(1,9)).getTypeString(), is(FOREST));
    }
    @Test
    public void ReturnMountainsAtPositionThreeFour() {
        assertThat(game.getTileAt(new Position(3,4)).getTypeString(), is(MOUNTAINS));
    }
    @Test
    public void ReturnForestAtPositionNineFourteen() {
        assertThat(game.getTileAt(new Position(8,13)).getTypeString(), is(FOREST));
    }
    @Test
    public void ReturnHillsAtPositionFifteenFive() {
        assertThat(game.getTileAt(new Position(14,6)).getTypeString(), is(HILLS));
    }
    //Testing for EpsilonAttack
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
        assertThat(g.performAttack(new Position(2, 1), new Position(1,1)), is(true));
    }
    @Test
    public void AlphaAttackTrueAndMoveUnit() {

        GameImpl g = new GameImpl(new AlphaVersion());
        assertThat(g.getUnitAt(new Position (4, 3)).getTypeString(), is(SETTLER));
        g.moveUnit(new Position(4,3), new Position(3,2));
        assertThat(g.getUnitAt(new Position (3, 2)).getTypeString(), is(SETTLER));
    }
    @Test
    public void EpsilonAttackTrueAndMoveUnit() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position (4, 3)).getTypeString(), is(SETTLER));
        g.moveUnit(new Position(4,3), new Position(3,2));
        assertThat(g.getUnitAt(new Position (3, 2)).getTypeString(), is(LEGION));
    }
    @Test
    public void CheckSettlerStrengths() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position (4, 3)).getDefensiveStrength(), is(3));
        assertThat(g.getUnitAt(new Position (4, 3)).getTypeString(), is(SETTLER));
    }
    @Test
    public void CheckLegionStrengths() {

        GameImpl g = new GameImpl(new EpsilonVersion());
        assertThat(g.getUnitAt(new Position (3, 2)).getAttackingStrength(), is(4));
        assertThat(g.getUnitAt(new Position (3, 2)).getTypeString(), is(LEGION));
    }
}
