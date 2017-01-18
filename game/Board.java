import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Board{
    public static String[][] displayBoard = new String[32][32];
    private static char[][] board=new char[15][15];//actual backend board
    private static char[][] boardTemp = new char[15][15]; //temp backend board
    private final char[] STARTING_BAG={'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D','D','D','E','E','E','E','E','E','E','E','E','E','E','E','F','F','G','G','G','H','H','I','I','I','I','I','I','I','I','I','J','K','L','L','L','L','M','M','N','N','N','N','N','N','O','O','O','O','O','O','O','O','Q','R','R','R','R','R','R','S','S','S','S','T','T','T','T','T','T','U','U','U','U','V','V','W','W','X','Y','Y','Z'};
    private static int[] score = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};//score of each letter in scrabble
    private static int wordScore;
    public static ArrayList<String> dictionary = new ArrayList<String>(267751);
    private static boolean first=true;
    private static boolean running=true;

    /*==================================================
      displays base board
      ==================================================*/

    public String toString(){
	String retStr = "";
	for( int i = 0; i < board.length; i++ ) {
	    for( int j = 0; j < board[i].length; j++ ){ 
		retStr += board[i][j] + "";
	    }
	    retStr += "";
	}
	return retStr;
    }

    /*==================================================
      checks if anything has been placed on the board
      ==================================================*/
    public static boolean emptyCheck() {
    	for(int i=0;i< board.length;i++){
	    for(int n=0;n< board.length;n++){
		if(board[i][n] !=(char)' '){
		    return false;
		}
	    }
	}
	return true;
    }

    /*==================================================
      generalized print for 2d arrays
      ==================================================*/
    public static void print2d( String[][] a ) { 
	for( int i = 0; i < a.length; i++ ) {
	    for( int j = 0; j < a[i].length; j++ ) 
		System.out.print( a[i][j] + " " );
	    System.out.println();
	}
    }

    /*==================================================
      makes the board that will be displayed from the char array base
      ==================================================*/
    public static void populate(String args[][], char[][] base){
	args[0][0] = "  ";
	
	//==============================
	//creates top row column numbers
	//==============================
	
	//sets column numbers at top
	for (int y = 2; y < args[0].length; y+=2){
	    args[0][y] = (y/2) + "";
	}
	//sets spaces between single digit column numbers
	for (int y = 1; y < 20; y+=2){
	    args[0][y] = " ";
	}
	//defines placeholder values for double digit column numbers
	for (int y = 21; y < args[0].length; y+=2){
	    args[0][y] = "";
	}

	//==============================
	//creates side row numbers
	//==============================

	//*
	//sets row numbers at left for single digit row numbers
       	for (int x = 2; x < 20; x+=2){
	    args[x][0] = " " + (x/2);
	}

	//*
	//sets row numbers at left for double digit row numbers
	for (int x = 20; x < args[0].length; x+=2){
	    args[x][0] = x/2 + "";
	}
	//*/

	//sets spaces between horizontal bars for double digit row numbers
	for (int x = 21; x < args[0].length; x+=2){
	    args[x][0] = "  ";
	}

	//sets spaces between horizontal bars for single digit row numbers
	for (int x = 1; x < 20; x+=2){
	    args[x][0] = "  ";
	}

	//==============================
       	//creates board
	//==============================
	
	//sets letter values
	for (int x = 2; x < args.length; x+=2){
	    for (int y = 2; y < args[x].length; y+=2){
		args[x][y] = Character.toString(base[x/2-1][y/2-1]);
	    }
	}
	//creates vertical column borders
	for (int x = 2; x < args.length; x+=2){
	    for (int y = 1; y < args[x].length; y+=2){
		args[x][y] = "|";
	    }
	}
	//creates horizontal row borders
	for (int x = 1; x < args.length; x+=2){
	    for (int y = 2; y < args[x].length; y+=2){
		args[x][y] = "-";
	    }
	}
	//sets spaces between horizontal row borders
	for (int x = 1; x < args.length; x+=2){
	    for (int y = 1; y < args[x].length; y+=2){
		args[x][y] = "+";
	    }
	}
    }

    /*
    //checks if input is a word
    public static boolean isWord(String word) {
    File file = new File("dictionary.txt");
    try {
    Scanner sc = new Scanner(file);
    while(sc.hasNextLine()) {
    //System.out.println(sc.nextLine());
    //System.out.println(sc.nextLine().equals(word));
    String lowercase = sc.nextLine().toLowerCase();
    if(lowercase.equals(word)) {
    return true;
    }
    }
    }
    catch (FileNotFoundException e) {
    e.printStackTrace();
    }
    return false;
    }
    */

    /*==================================================
      checks to see if words after the first word are touching existing words
      ==================================================*/
    public static boolean touching(ArrayList input){
	int x=(int)input.get(0)-1;
	int y=(int)input.get(1)-1;
	String dir=(String)input.get(2);
	String word=(String)input.get(3);
	if(dir.equals("r")&&y>0&&y<14){
	    for(int row=y-1;row<y+2;row++){
		for(int column=x;column<=x+word.length();column++){
		    if(board[row][column]!=' '){
			return true;
		    }
		}
	    }
	}
	else{if(y==0){
		for(int row=y;row<y+2;row++){
		    for(int column=x;column<=x+word.length();column++){
			if(board[row][column]!=' '){
			    return true;
			}
		    }
		}
	    }
	    if(y==14){
		for(int row=y-1;row<y;row++){
		    for(int column=x;column<x+word.length();column++){
			if(board[row][column]!=' '){
			    return true;
			}
		    }
		}
	    }
	}
	if(dir.equals("d")&&x>0&&x<14){
	    for(int column=x-1;column<x+2;column++){
		for(int row=y;row<=y+word.length();row++){
		    if(board[row][column]!=' '){
			return true;
		    }
		}
	    }
	}
	else{if(x==0){
		for(int column=x;column<x+2;column++){
		    for(int row=y;row<=y+word.length();row++){
			if(board[row][column]!=' '){
			    return true;
			}
		    }
		}
	    }
	    if(x==14){
		for(int column=x-1;column<x+1;column++){
		    for(int row=y;row<y+word.length();row++){
			if(board[row][column]!=' '){
			    return true;
			}
		    }
		}
	    }
	}
	if(!first){
	    System.out.println("--------------\nINVALID LOCATION\n--------------");
	}
	return first;
    }

    
    /*==================================================
      checks if word is in scrabble dictionary
      ==================================================*/
    public static boolean isWord(String word){
	word = word.toUpperCase();
	int middle;
	int highest = dictionary.size();
	//System.out.println(dictionary.size());
	int first = 0;
	middle = highest/2;
	//System.out.println(word);
	
	while(first < highest){ //searches until min matches max
	    //System.out.println(middle);//diag
	    //if word  in second half of dictionary
	    if(dictionary.get(middle).compareTo(word)>0){
		highest = middle;
		middle = (first+highest)/2;
	    }
	    //if word in first half of dictionary
	    else if (dictionary.get(middle).compareTo(word)<0){
		first = middle + 1;
		middle = (first+highest)/2;
	    }
	    //reaches only if dictionary.get(middle).compareTo(word) = 0, so the word must be in the dictionary 
	    else{
		return true;
	    }
	}
	//if dictionary has been completely scanned through
	return false;
    }
    
    //==================================================
    //scores word
    //==================================================
    public static int scoreWord(char[] word) {
	int retScore = 0;
	for(int i = 0; i < word.length; i++){
	    //System.out.println(word[i] - 97 + "letter num");
	    //System.out.println(score[word[i] - 97]);
	    retScore += score[word[i] - 97];
	    //System.out.println(retScore);
	}
	return retScore;
    }

    //=======================================================
    //all branches legit -> score, any wrong->-1
    //=======================================================
    public static int feedWord(int x, int y, char dir, String word) {
	//System.out.println("real y:    " + y);//diag
	//System.out.println("real x:    " + x);//diag
	char[] holder;
	String holderString = "";
	int retScore = 0;
	if(dir == 'r') { //if entered horizontally
	    int yUp;
	    int yDown;
	    int startingY;
	    int endingY;
	    //checks each letter of placed word for intersections
	    for (int i = 0; i<word.length(); i++) {
		if((y != 0 && boardTemp[y-1][x+i] != ' ') || (y != 14 && boardTemp[y+1][x+i] != ' ')) { //checks above and below
		    //resets holder string
		    holderString="";
		    yUp = 0;
		    yDown=0;
		    
		    while(y-yUp > 0 && boardTemp[y-yUp-1][x+i] != ' ') {
			yUp+=1;
		    }
		    while(y+yDown < 14 && boardTemp[y+yDown+1][x+i] != ' '){
			yDown+=1;
		    }
		    startingY = y-yUp;
		    endingY = y+yDown;
		    /*
		      System.out.println("---------------------------------");
		      System.out.println("yUp:    "+ yUp);
		      System.out.println("yDown:    " + yDown);
		      System.out.println("-----------------------------------------------");
		      System.out.println("startingY:     " + startingY);
		      System.out.println("endingY:     " + endingY);
		    *///lots of diagnostics
		    //boardTemp[y-yUp][x+i]
		    //boardTemp[y+yDown][x+i]

		    //sets size of holder char[]
		    holder = new char[endingY-startingY+1];
		    //
		    for(int counter = 0; counter <= endingY-startingY; counter++) {
			holder[counter] = boardTemp[startingY+counter][x + i];
			holderString += Character.toString(boardTemp[startingY+counter][x+i]);
			/*
			  System.out.println("Counter:   " + counter);
			  System.out.println("BoardTemp:" + Character.toString(boardTemp[startingY+counter][x+i]) + "|");
			  System.out.println("holderString:     " + holderString);
			*/
		    }/*
		       System.out.println(holderString);
		     *///no such thing as too many diagnostic print statements
		    if (isWord(holderString)){
			retScore += scoreWord(holder);
		    }
		    else{if(!holderString.equals("")){
			    System.out.println("---------\nINVALID WORD\n-------");
			    return -1;
			}
		    }
		}
	    }
	}

	else { //if entered vertically
	    int xLeft;
	    int xRight;
	    int startingX;
	    int endingX;
	    for (int i = 0 ; i < word.length() ; i++) {
		if((x != 0 && boardTemp[y+i][x-1] != ' ') || (x != 14 && boardTemp[y+i][x+1] != ' ')) { //checks right and left
		    //resets holder string
		    holderString="";
		    xLeft = 0;
		    xRight=0;
		    while(x-xLeft > 0 && boardTemp[y+i][x-xLeft-1] != ' ') {
			xLeft+=1;
		    }
		    while(x+xRight < 14 && boardTemp[y+i][x+xRight+1] != ' '){
			xRight+=1;
		    }
		    startingX = x-xLeft;
		    endingX = x+xRight;
		    //boardTemp[y+i][x-startingX]
		    //boardTemp[y+i][x+startingY]
		    holder = new char[endingX-startingX+1];
		    for(int counter = 0; counter <= endingX-startingX; counter++) {
			holder[counter] = boardTemp[y+i][startingX+counter];
			holderString += Character.toString(boardTemp[y+i][startingX+counter]);
		    }
		    if (isWord(holderString)){
			retScore += scoreWord(holder);
			
		    }
		    else{if(!holderString.equals("")){
			    System.out.println("---------\nINVALID WORD\n-------");
			    return -1;
			}
			System.out.println(holderString);
		    }
		}
	    }
	}
	return retScore;
    }

    
    /*=========================================================================
      creates temporary board with word placed to check legality
      ===========================================================================*/
    public static boolean placeTemp(ArrayList input){
	//gets start pos, adjusts for use with board
   	int x=(int)input.get(0)-1;
	int y=(int)input.get(1)-1;
	if(x>14||y>14||x<0||y<0){//if user input out of bounds
	    System.out.println("ERROR: Please input valid COORDINATES next time");
	    return false;
	}
	//right is true down is false
	boolean dir=((String)input.get(2)).equals("r");
	//System.out.println("foo"+(String)input.get(2)+ "boo");
	if(!dir && !((String)input.get(2)).equals("d")){//if input is not d or r
	    System.out.println("ERROR: Please input a valid DIRECTION next time");
	    return false;
	}
	//System.out.println((String)input.get(3));//diag
	char[] word=((String)input.get(3)).toCharArray();//splits word input into chars

	//System.out.println(wordScore);
	//enter word horizontally
	if(dir){
	    for(int i=0;i<word.length;i++){
		boardTemp[y][x+i]=word[i];
	    }
	}
	//enter word vertically
	else{
	    for(int i=0;i<word.length;i++){
		boardTemp[y+i][x]=word[i];
	    }
	}
	return true;
    }
    //--------------------------------------------------------------------------------------
    public static boolean place(ArrayList input){
	if (((int) input.get(4)) == 1 && touching(input)&&placeTemp(input)){
	    //gets start pos, adjusts for use with board
	    int x=(int)input.get(0)-1;
	    int y=(int)input.get(1)-1;
	    if(x>14||y>14||x<0||y<0){//if user input out of bounds
		System.out.println("ERROR: Please input valid COORDINATES next time");
		return false;
	    }
	    //right is true down is false
	    boolean dir=((String)input.get(2)).equals("r");
	    //System.out.println("foo"+(String)input.get(2)+ "boo");
	    if(!dir && !((String)input.get(2)).equals("d")){//if input is not d or r
		System.out.println("ERROR: Please input a valid DIRECTION next time");
		return false;
	    }
	    //System.out.println((String)input.get(3));//diag
	    char[] word=((String)input.get(3)).toCharArray();//splits word input into chars
	    //System.out.println(wordScore);
	    //enter word horizontally
	    if(dir){
		if(feedWord(x,y,input.get(2).toString().charAt(0),(String)input.get(3)) != -1 || emptyCheck() ) {
		    for(int i=0;i<word.length;i++){
			board[y][x+i]=word[i];
		    }
		    
		}
		else {
		    return false;
		}
	    }
	    //enter word vertically
	    else{
		if(feedWord(x,y,input.get(2).toString().charAt(0),(String)input.get(3)) != -1 || emptyCheck() ) {
		    for(int i=0;i<word.length;i++){
			board[y+i][x]=word[i];
		    }
		}
		else {
		    return false;
		}	
	    }
	    if(feedWord(x,y,input.get(2).toString().charAt(0),(String)input.get(3)) != -1) {
		wordScore = scoreWord(word) + feedWord(x,y, input.get(2).toString().charAt(0), (String)input.get(3));//sets score of word
		User.addScore(wordScore);
	    }
	    first=false;
	    return true;
	}
	else{
	    return false;
	}
	
    }

    public static void setRunningFalse() {
	running = false;
    }
	
    //-----------------------------------------------------------------------------------
    
    /*====================
      ONE METHOD TO RULE THEM ALL
      ====================*/
    public static void play(){
	String quitcheck = "";
	ArrayList input;
	try{
	    FileReader reader = new FileReader("dictionary.txt");
	    Scanner scanner = new Scanner(reader);	
	    for(int x = 0; scanner.hasNextLine(); x++){
		dictionary.add(scanner.nextLine());
	    }			
	    scanner.close();
	}
	catch(FileNotFoundException e){
	    System.out.println("error");
	}
	//*/
	for(int i=0;i<board.length;i++){
	    for(int n=0;n< board.length;n++){
		board[i][n]=(char)' ' ;
		boardTemp[i][n]=(char)' ';
	    }
	}

	//System.out.println(isWord("bird"));

	/*
	  for (int x = 0; x < 10; x++){
	  System.out.println(dictionary[x]);
	  }
	*/
       	while(running) {
	    populate(displayBoard,board);
	    print2d(displayBoard);
	    System.out.println("INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>, enter any x,y,dir,and iquit as your word to end the game");
	    input = User.placeWord();
	    quitcheck=(String)input.get(3);
	    System.out.println("You entered:" + quitcheck);
	    if(place(input)){
		// populate(displayBoard,board);
		// print2d(displayBoard);
		System.out.println("Turn score: " + wordScore);
		System.out.println("Score of User now: " + User.score);
		System.out.println("INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>");
	    }
	    else if(quitcheck.equals("iquit")) {
		System.out.println("Thanks for playing");
		System.out.println("You scored a total of " + User.score + " points");
		setRunningFalse();
	    }
	    else {
		System.out.println("That is not a word");
	    }
	}
    
    } 	    
    


    
    public static void main(String args[]){
	//	play();
    }
}
