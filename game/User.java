package game;

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
	ArrayList input=new ArrayList(5);
	input.add(Keyboard.readInt());//<x1>
	input.add(Keyboard.readInt());//<y1>
	input.add(Keyboard.readWord());//<dir(d/r)>
	input.add(Keyboard.readWord());//<word>
	int x = (int)input.get(0) - 1;
	int y = (int)input.get(1) - 1;
	String dirstr = (String)input.get(2);
	String inputstr = (String)input.get(3);
	//check if entry is within bounds
	if( (dirstr.equals("r")&&(x+inputstr.length() > 16) )
	    ||
	    (dirstr.equals("d")&&(y+inputstr.length() > 16) ) ){
	    input.add(2);//signifies out of bounds
	}
	//now check if entry is a word
	else if(Board.isWord(inputstr) ) { 
	    input.add(1); //signifies word within bounds
	}
	//in bounds but not a word
	else{
	    input.add(0);//signifies non-word within bounds
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

