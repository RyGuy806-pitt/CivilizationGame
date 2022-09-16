package hotciv.standard;

public class I_turn {
    static int countTurn = 0;
    public static void setTurn(int input) { countTurn = input; }
    public static int getTurn() {
        return countTurn;
    }
}
