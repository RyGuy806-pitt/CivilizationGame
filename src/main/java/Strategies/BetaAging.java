package Strategies;

public class BetaAging implements Aging{
    int y = -4000;
    @Override
    public int calculateTime() {
        //-4000--100 (+=100)
        if(y < -100){
            y += 100;
        }
        //-100, -1, +1, +50
        else if(y == -100){
            y += 99;
        }
        else if(y == -1){
            y += 2;
        }
        else if(y==1){
            y += 49;
        }
        //+50-1750 (+=50)
        else if(y >= 50 && y < 1750){
            y += 50;
        }
        //1750-1900 (+=25)
        else if(y >= 1750 && y< 1900){
            y += 25;
        }
        //1900-1970 (+=5)
        else if(y >= 1900 && y < 1970){
            y += 25;
        }
        //1970-onward (+=1)
        else{
            y += 1;
        }

        return y;
    }
}
