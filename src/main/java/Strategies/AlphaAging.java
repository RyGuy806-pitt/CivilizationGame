package Strategies;

public class AlphaAging implements Aging{
    int y = -4000;

    @Override
    public int calculateTime(){
        y += 100;
        return y;
    }

}
