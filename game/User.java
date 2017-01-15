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
	input.add(Keyboard.readInt());//<x1>
	input.add(Keyboard.readInt());//<y1>
	input.add(Keyboard.readWord());//<dir(d/r)>
	input.add(Keyboard.readWord());//<word>
	if (Board.isWord((String)input.get(3))){
	    input.add(1);
	}
	else{
	    System.out.println("ERROR: Word not found in dictionary. Please input valid WORD next time");
	    input.add(0);
	}
	return input;
    }
    public void addLetters(char[] letters){
	for(char letter:letters){
	    rack.add(letter);
	}
    }
    public void useLetters(char[] letters){
	for(int i=0;i<rack.size();i++)
	    for(char letter:letters){
		if(rack.get(i)==letter){
		    rack.remove(i);
		    i--;
		    break;
		}
	    }
    }
}
