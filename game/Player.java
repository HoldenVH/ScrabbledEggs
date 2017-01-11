public abstract class Player{

    protected ArrayList<char> rack;
    protected int score;
    protected String name;
    
    public ArrayList placeWord(char[][] board);

    public int wordScore(int score);
}
