package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import static Strategies.ThetaUnitAction.UFO;
import static hotciv.framework.GameConstants.*;

public class CityImpl implements City {
    private Position position;
    private String typeProd = ARCHER;
    private Player owner;

    private int population = 1;
    private int treasury = 6;

    private int productCost = 4;

    private int treasuryAdd = 6;

    public CityImpl(Position p, String t, Player o){
        this.position = p;
        this.typeProd = t;
        this.owner = o;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player p){ this.owner = p; }

    @Override
    public int getSize() {
        return population;
    }

    public void setSize(int x){
        population = x;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public String getProduction() {
        return typeProd;
    }

    public void changeProduction(String x){
        typeProd = x;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }


    public void IncrementTreasury(){
        treasury += treasuryAdd;//this is 6, will change later
    }

    public void DecrementTreasury(){
        treasury -= productCost;
    }

    public int getProductCost(){ return productCost; }

    public Position getPosition(){
        return position;
    }

    public void setProductCost(String type){
        if(type == ARCHER || type == SETTLER || type == LEGION){
            productCost = 4;
        }
        else if(type == UFO){
            productCost = 60;
        }
        else{
            productCost = 4;
        }
    }


}
