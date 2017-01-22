
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
	if( (dirstr.equals("r")&&(x+inputstr.length() >= 16) )
	    ||
	    (dirstr.equals("d")&&(y+inputstr.length() >= 16) ) ){
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
    public static String getRack() {
	String retStr = "RACK: ";
	for(char a:rack) {
	    retStr+= a + ",";
	}
	return retStr.substring(0,retStr.length()-1);
    }
    
    public static void populateRack() {
	addLetters(drawBag(7));
    }
    public static char[] drawBag(int numdraws) {
	char[] retArr = new char[numdraws];
	for(int i = 0;i < numdraws;i++) {
	    int a =(int)( Math.random()*Board.BAG.size() );
	    retArr[i]=Board.BAG.get(a);
	    Board.BAG.remove(a);
	}
	return retArr;
    }
		
    public static void addLetters(char[] letters){
	for(char letter:letters){
	    rack.add(letter);
	}
    }
    public static boolean useLetters(char[] letters){
	System.out.println(rack.size());
	//checks if letters are there
	for(char letter:letters){
	    boolean present=false;
	    for(int i=0;i<rack.size();i++) {
		//System.out.println("second for called");
		if(Character.toLowerCase(rack.get(i))==letter){
		    present=true;
		}
	    }
	    if(!present){
		return false;
	    }
	}
	//if they are, removes
	for(char letter:letters){
	    System.out.println(letter);
	    for(int i=0;i<rack.size();i++) {
		System.out.println("second for called");
		if(Character.toLowerCase(rack.get(i))==letter){
		    System.out.println("if loop called");
		    rack.remove(i);
		    System.out.println(getRack() );
		    i--;
		    break;
		}
	    }
	}
	return true;
    }
}
