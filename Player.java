

import java.util.ArrayList;
public abstract class Player{

    protected ArrayList<Character> rack;
    protected static int score;
    protected String name;

    public static int addScore(int s){
	score+=s;
	return score;//returns updated score
    }
    
    //public abstract ArrayList placeWord(char[][] board);
}
