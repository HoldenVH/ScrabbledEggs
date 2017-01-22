import java.util.ArrayList;
public abstract class Player{

    protected ArrayList<Character> rack = new ArrayList<Character>(7);
    protected int score;
    protected String name;

    public int addScore(int s){
	score+=s;
	return score;//returns updated score
    }

    public String getRack() {
	String retStr = "RACK: ";
	for(char a:rack) {
	    retStr+= a + ",";
	}
	return retStr.substring(0,retStr.length()-1);
    }
    
    public void populateRack() {
	addLetters(drawBag(7));
    }
    public char[] drawBag(int numdraws) {
	char[] retArr = new char[numdraws];
	for(int i = 0;i < numdraws;i++) {
	    int a =(int)( Math.random()*Board.BAG.size() );
	    retArr[i]=Board.BAG.get(a);
	    Board.BAG.remove(a);
	}
	return retArr;
    }
		
    public void addLetters(char[] letters){
	for(char letter:letters){
	    rack.add(letter);
	}
    }

    public boolean hasLetters(char[] letters){
	//checks if letters are there
	for(char letter:letters){
	    boolean present=false;
	    for(int i=0;i<rack.size();i++) {
		if(Character.toLowerCase(rack.get(i))==letter){
		    present=true;
		}
	    }
	    if(!present){
		return false;
	    }
	}
	return true;
    }
    public void useLetters(char[] letters){
	//if they are, removes
	for(char letter:letters){
	    for(int i=0;i<rack.size();i++) {
		if(Character.toLowerCase(rack.get(i))==letter){
		    rack.remove(i);
		    i--;
		    break;
		}
	    }
	}
    }
    
    abstract ArrayList placeWord();

}
