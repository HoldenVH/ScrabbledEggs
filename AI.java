import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AI extends Player{
    public AI(){
	score = 0;
	name = "AI";
    }

    public ArrayList placeWord(){
	int counter = 1000000;
	System.out.println(counter);
	int x = (int) (Math.random() * 13);
	int y = (int) (Math.random() * 13);
	String dirstr;
	if ( Math.random() < .5){
	    dirstr = "r";
	}
	else{
	    dirstr = "d";
	}
	System.out.println(dirstr);
	System.out.println(counter);
	//String inputstr = "";
	while (Board.board[y][x] == ' '){
	    //System.out.println("y = " + y);
	    //System.out.println("x = " + x);
	if (dirstr == "r"){
	    System.out.println("gets past dirstr");
	    if (x == 0){
		while (Board.board[y][x] == ' ' && Board.board[y][x+1] != ' '){
		    x = (int) (Math.random() * 13);
		    System.out.println(x);
		    y = (int) (Math.random() * 13);
		    System.out.println(y);
		}
	    }
	    else if (x == 14){
		while (Board.board[y][x] == ' ' && Board.board[y][x-1] != ' '){
		    x = (int) (Math.random() * 13);
		    System.out.println(x);
		    y = (int) (Math.random() * 13);
		    System.out.println(y);
		}
	    }

	    else if (x != 0 && x != 14){
		System.out.println("runms bois");
		while (Board.board[y][x] == ' ' && (Board.board[y][x-1] != ' ' || Board.board[y][x+1] != ' ')){
		    x = (int) (Math.random() * 13);
		    System.out.println(x);
		    y = (int) (Math.random() * 13);
		    System.out.println(y);
		}
	    }
	}
	else{
	    if (y == 0){
		while (Board.board[y][x] == ' ' && Board.board[y+1][x] != ' '){
		    x = (int) (Math.random() * 13);
		    System.out.println(x);
		    y = (int) (Math.random() * 13);
		    System.out.println(y);
		}
	    }
	    else if (y == 14){
		while (Board.board[y][x] == ' ' && Board.board[y-1][x] != ' '){
		    x = (int) (Math.random() * 13);
		    System.out.println(x);
		    y = (int) (Math.random() * 13);
		    System.out.println(y);
		}
	    }

	    else if (y != 0 && y != 14){
		while (Board.board[y][x] == ' ' && (Board.board[y-1][x] != ' ' || Board.board[y+1][x] != ' ')){
		    x = (int) (Math.random() * 13);
		    System.out.println(x);
		    y = (int) (Math.random() * 13);
		    System.out.println(y);
		}
	    }
	}
	}

	System.out.println(counter + 1);
	System.out.println("y = " + y);
	System.out.println("x = " + x);
	System.out.println("letter on board = " + Board.board[y][x]);
	char letter = Board.board[y][x];
	String word;
	int startingIndex = -1;
	int endingIndex = -1;
	for (int i = 0; i < Board.dictionary.size(); i++){
	    //System.out.println("dictionary size = " + Board.dictionary.size());
	    String lowercase = Board.dictionary.get(i).toLowerCase();
	    char firstChar = lowercase.charAt(0);
	    //System.out.println("firstChar = " + firstChar);
	    //System.out.println("letter = " + letter);
	    //System.out.println(firstChar == letter);
	    if (firstChar == letter){
		startingIndex = i;
	    }
	    if (firstChar - 1 == letter){
		endingIndex = i;
		break;
	    }
	}
	System.out.println(counter + 3);
	/*
	File file = new File("dictionary.txt");
        try {
	    Scanner sc = new Scanner(file);
	    while(sc.hasNextLine()) {
		index += 1;
		//System.out.println(sc.nextLine());
		//System.out.println(sc.nextLine().equals(word));
	        String lowercase = sc.nextLine().toLowerCase();
		char firstChar = lowercase.substring(0,1).charAt(0);
		if(firstChar == letter) {
		    startingIndex = index;
		}
		if (firstChar -1 == letter){
		    endingIndex = index;
		    break;
		}
	    }
	}
	catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	*/
	ArrayList arr = new ArrayList(5);
	boolean legal = false;
	System.out.println(counter + 4);
	while (!legal){
	    System.out.println("a");
	    counter += 1;
	    if (counter < 0){
		System.out.println(counter);
	    }
	    legal = true;
	    System.out.println("startingIndex = " + startingIndex);
	    System.out.println("endingIndex = " + endingIndex);
	    int randomAdd = (int) (Math.random() * (endingIndex - startingIndex));
	    System.out.println("randomadd = " + randomAdd);
	    int randomIndex = randomAdd + startingIndex;
	    System.out.println("randomindex = " + randomIndex);
	    word = Board.dictionary.get(randomIndex);
	    System.out.println("b");
	    if (word.length() >= 8){
		legal = false;
	    }
	    arr = new ArrayList(5);
	    arr.add(x+1);
	    arr.add(y+1);
	    arr.add(dirstr);
	    arr.add(word);
	    arr.add(1);
	    System.out.println("c");
	    char[] chars = word.toCharArray();
	    if (legal && !hasLetters(chars)){
		legal = false;
	    }
	    System.out.println("d");
	    if(legal && ((dirstr.equals("r")&&(x+word.length() >= 16) )
		||
			 (dirstr.equals("d")&&(y+word.length() >= 16) )) ){
		legal=false; //signifies out of bounds
	    }
	    System.out.println("e");
	    if (legal && !Board.touching(arr)){
		legal = false;
	    }
	    System.out.println("f");
	    if (legal && Board.feedWord(x,y,dirstr.charAt(0),word) == -1){
		legal = false;
	    }
	    System.out.println("g");
	    if (legal && !Board.placeTemp(arr)){
		legal = false;
	    }
	    System.out.println("h");
	    //resets temp board
	    for(int i=0;i<Board.board.length;i++){
		for(int n=0;n<Board.board[i].length;n++){
		    Board.boardTemp[i][n]=Board.board[i][n];
		}
	    }
	    System.out.println("i");
	}
	return arr;
    }
}
