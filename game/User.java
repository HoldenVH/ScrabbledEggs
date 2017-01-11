import cs1.Keyboard;

public class User extends Player{
    //INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>
    public User(){
	score=0;
	rack=new ArrayList<char>;
    }
    public User(String n){
	this();
	name=n;
    }
    
    public ArrayList placeWord(char[][] board){
	ArrayList input=new ArrayList();
	input.add(Keyboard.readInt());
	input.add(Keyboard.readInt());
	input.add(Keyboard.readChar());
	input.add(Keyboard.readWord());
	return input;
    }
    public int wordScore(int s){
	score+=s;
    }
    public void addLetters(char[] letters){
	for(letter:letters){
	    rack.add(letter);
	}
    }
    public void useLetters(char[] letters){
    }
}
