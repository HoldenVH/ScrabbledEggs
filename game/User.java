import cs1.Keyboard;
import java.util.ArrayList;

public class User extends Player{
    //INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>
    public User(){
	score=0;
	name="n00b";
    }
    public User(String n){
	this();
	name=n;
    }
    
    public static ArrayList placeWord(){
	ArrayList input=new ArrayList();
	input.add(Keyboard.readInt());
	input.add(Keyboard.readInt());
	input.add(Keyboard.readWord());
	input.add(Keyboard.readWord());
	return input;
    }
    public void addLetters(char[] letters){
	for(char letter:letters){
	    rack.add(letter);
	}
    }
    public void useLetters(char[] letters){
	for(int i=0;i<rack.size();i++)
	    for(char letter:letters)
		if(rack.get(i)==letter){
		    rack.remove(i);
		    i--;
		}
    }
}
