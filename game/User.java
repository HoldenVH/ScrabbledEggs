import cs1.Keyboard;
import java.util.ArrayList;

public class User extends Player{
    //INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>
    public User(){
	score=0;
	rack=new ArrayList<Character>{'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D','D','D','E','E','E','E','E','E','E','E','E','E','E','E','F','F','G','G','G','H','H',
		'I','I','I','I','I','I','I','I','I','J','K','L','L','L','L','M','M','N','N','N','N','N','N','O','O','O','O','O','O','O','O','Q','R','R','R','R','R','R','S','S','S','S',
		'T','T','T','T','T','T','U','U','U','U','V','V','W','W','X','Y','Y','Z' 
		};
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
