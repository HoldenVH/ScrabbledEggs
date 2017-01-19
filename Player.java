

import java.util.ArrayList;
public abstract class Player{

    protected static ArrayList<Character> rack = new ArrayList<Character>(7);
    protected static int score;
    protected String name;

    public static int addScore(int s){
	score+=s;
	return score;//returns updated score
    }
    
    //public abstract ArrayList placeWord(char[][] board);
}
