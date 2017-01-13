import java.util.ArrayList;
public abstract class Player{

    protected ArrayList<Character> rack;
    protected int score;
    protected String name;

    public int wordScore(int s){
	score+=s;
	return score;//returns updated score
    }
    
    //public abstract ArrayList placeWord(char[][] board);
}
