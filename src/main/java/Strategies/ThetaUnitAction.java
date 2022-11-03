package Strategies;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class ThetaUnitAction implements UnitAction{
    public static final String UFO = "ufo";
    @Override
    public void setUnitAction(GameImpl g, Position p, HashMap<Position, UnitImpl> UM, HashMap<Position, CityImpl> CM) {
        UnitImpl unit = UM.get(p);
        String unitName = unit.getTypeString();
        Player owner = unit.getOwner();

        if(unitName == SETTLER)
        {
            // clear from unit map
            UM.remove(p);
            UM.put(p, new UnitImpl(p, "nothing", owner));

            // add to city map with ARCHER as production
            CM.put(p, new CityImpl(p, ARCHER, owner));
        }
        else if(unitName == ARCHER)
        {
//            if(UM.get(p).getDefensiveStrength() == 1) {
//                UM.get(p).fortified();
//            }
//
//            if(UM.get(p).getDefensiveStrength() == 2) {
//                UM.get(p).notFortified();
//            }
            if(UM.get(p).checkFortified() == false) {
                UM.get(p).fortified();
            }
            else{
                UM.get(p).notFortified();
            }
        }
        else if(unitName == LEGION)
        {
            // do nothing
        }
        else if(unitName == UFO){
            int population = 0;
            if(CM.get(p) != null){
                population = CM.get(p).getSize();
                CM.get(p).setSize(population-1);
                if(CM.get(p).getSize() == 0){
                    CM.remove(p);
                }
            }
        }
        else
        {
            // do nothing
        }
    }
}

