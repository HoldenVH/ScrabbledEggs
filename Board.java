
import cs1.Keyboard;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.InterruptedException;

public class Board{
    public static String[][] displayBoard = new String[32][32];
    public static char[][] board=new char[15][15];//actual backend board
    public static char[][] boardTemp = new char[15][15]; //temp backend board
    public static final char[] STARTING_BAG={'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D','D','D','E','E','E','E','E','E','E','E','E','E','E','E','F','F','G','G','G','H','H','I','I','I','I','I','I','I','I','I','J','K','L','L','L','L','M','M','N','N','N','N','N','N','O','O','O','O','O','O','O','O','Q','R','R','R','R','R','R','S','S','S','S','T','T','T','T','T','T','U','U','U','U','V','V','W','W','X','Y','Y','Z'};
    public static ArrayList<Character> BAG = new ArrayList<Character>(STARTING_BAG.length);
    private static int[] score = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};//score of each letter in scrabble
    private static int wordScore;
    public static ArrayList<String> dictionary = new ArrayList<String>(267751);
    private static boolean first=true;
        
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
populates ArrayList that holds game bag
     ==================================================*/
    public static void populateBag() {
	for(char a:STARTING_BAG) {
	    BAG.add(a);
	}
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
		args[x][y] = Character.toString(Character.toUpperCase(base[x/2-1][y/2-1]));
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

    /*==================================================
checks to see if words after the first word are touching existing words
      ==================================================*/
    public static boolean touching(ArrayList input){
	int x=(int)input.get(0)-1;
	int y=(int)input.get(1)-1;
	String dir=(String)input.get(2);
	String word=(String)input.get(3);
	if(dir.equals("r")) {
		if (y>0 && y<14){
		    if(x+word.length() == 15) {
			for(int row=y-1;row<y+2;row++){
			    for(int column=x;column<x+word.length();column++){
				if(board[row][column]!=' '){
				    return true;
				}
			    }
			}
		    }
	    
		    else {
			for(int row=y-1;row<y+2;row++){
			    for(int column=x;column<=x+word.length();column++){
				if(board[row][column]!=' '){
				    return true;
				}
			    }
			}
		    }
		}
		else if(y==0){
		    if(x+word.length() == 15) {
			for(int row=y;row<y+2;row++){
			    for(int column=x;column<x+word.length();column++){
				if(board[row][column]!=' '){
				    return true;
				}
			    }
			}
		    }
		    else {
			for(int row=y;row<y+2;row++){
			    for(int column=x;column<=x+word.length();column++){
				if(board[row][column]!=' '){
				    return true;
				}
			    }
			}
		    }
		}
		else if(y==14){
		    if(x+word.length() == 15) {
			for(int row=y-1;row<y;row++){
			    for(int column=x;column<x+word.length();column++){
				if(board[row][column]!=' '){
				    return true;
				}
			    }
			}
		    }
		    else {
			for(int row=y-1;row<y;row++){
			    for(int column=x;column<=x+word.length();column++){
				if(board[row][column]!=' '){
				    return true;
				}
			    }
			}
		    }
		}
	    }
       
	if(dir.equals("d") ){
	    if (x > 0 && x < 14) {
		if(y+word.length() == 15) {
		    for(int column=x-1;column<x+2;column++){
			for(int row=y;row<y+word.length();row++){
			    if(board[row][column]!=' '){
				return true;
			    }
			}
		    }
		}
		else {
		    for(int column=x-1;column<x+2;column++){
			for(int row=y;row<=y+word.length();row++){
			    if(board[row][column]!=' '){
				return true;
			    }
			}
		    }
		}
	    }
	    else if(x==0){
        	if(y+word.length() == 15) {
		    for(int column=x;column<x+2;column++){
			for(int row=y;row<y+word.length();row++){
			    if(board[row][column]!=' '){
				return true;
			    }
			}
		    }
		}
		else {    
		    for(int column=x;column<x+2;column++){
			for(int row=y;row<=y+word.length();row++){
			    if(board[row][column]!=' '){
				return true;
			    }
			}
		    }
		}
	    }
	    else if(x==14){
		if(y+word.length() == 15) {
		    for(int column=x-1;column<x+1;column++){
			for(int row=y;row<y+word.length();row++){
			    if(board[row][column]!=' '){
				return true;
			    }
			}
		    }
		}

		else {
		    for(int column=x-1;column<x+1;column++){
			for(int row=y;row<=y+word.length();row++){
			    if(board[row][column]!=' '){
				return true;
			    }
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
	int first = 0;
	middle = highest/2;
	while(first < highest){ //searches until min matches max
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
	    retScore += score[word[i] - 97];
	}
	return retScore;
    }

    //=======================================================
    //all branches legit -> score, any wrong->-1
    //=======================================================
    public static int feedWord(int x, int y, char dir, String word) {
	char[] holder;
	String holderString = "";
	int retScore = 0;
	int xLeft;
	int xRight;
	int startingX;
	int endingX;
	int yUp;
	int yDown;
	int startingY;
	int endingY;
	if(dir == 'r') { //if entered horizontally
	      if((x != 0 && boardTemp[y][x-1] != ' ') || (x+word.length() <= 14 && boardTemp[y][x+word.length()] != ' ')) { 
		//resets holder string
		holderString="";
		xLeft = 0;
		xRight=0;
		    
		while(x-xLeft > 0 && boardTemp[y][x-xLeft-1] != ' ') {
		    xLeft+=1;
		}
		while(x+word.length()+xRight <= 14 && boardTemp[y][x+word.length()+xRight] != ' '){
		    xRight+=1;
		}
		startingX = x-xLeft;
		endingX = x+word.length()-1+xRight;
		//sets size of holder char[]
		      holder = new char[endingX-startingX+1];
		//
		for(int counter = 0; counter <= endingX-startingX; counter++) {
		    holder[counter] = boardTemp[y][startingX+counter];
		    holderString += Character.toString(boardTemp[y][startingX+counter]);
		    if (isWord(holderString) ){
			retScore += scoreWord(holder);
			//	retScore -= scoreWord(word);
		    }
		    else{if(!holderString.equals("")){
			    System.out.println("---------\nINVALID WORD\n-------");
			    return -1;
			}
		    }
		}
	    }

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
		    
		    //sets size of holder char[]
		    holder = new char[endingY-startingY+1];
		    for(int counter = 0; counter <= endingY-startingY; counter++) {
			holder[counter] = boardTemp[startingY+counter][x + i];
			holderString += Character.toString(boardTemp[startingY+counter][x+i]);
		
		    }
		    if (isWord(holderString)&& board[y][x+i]==' ' ){
			retScore += scoreWord(holder);
			
		    }
		    else if(isWord(holderString) ) {
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
	    if((y != 0 && boardTemp[y-1][x] != ' ') || (y+word.length() <= 14 && boardTemp[y+word.length()][x] != ' ')) { 
		//resets holder string
		holderString="";
		yUp = 0;
		yDown=0;
		    
		while(y-yUp > 0 && boardTemp[y-yUp-1][x] != ' ') {
		    yUp+=1;
		}
		while(y+word.length()+yDown <= 14 && boardTemp[y+word.length()+yDown][x] != ' '){
		    yDown+=1;
		}
		startingY = y-yUp;
		endingY = y+word.length()-1+yDown;
		//sets size of holder char[]
		      holder = new char[endingY-startingY+1];
		//
		for(int counter = 0; counter <= endingY-startingY; counter++) {
		    holder[counter] = boardTemp[startingY+counter][x];
		    holderString += Character.toString(boardTemp[startingY+counter][x]);
		    if (isWord(holderString)){
			retScore += scoreWord(holder);
			//retScore -= scoreWord(word);
		    }
		    else{if(!holderString.equals("")){
			    System.out.println("---------\nINVALID WORD\n-------");
			    return -1;
			}
		    }
		}
	    }
	    
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
		    if (isWord(holderString)&& board[y+i][x]==' ' ){
			retScore += scoreWord(holder);
			
		    }
		    else if(isWord(holderString) ) {
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
	if(!dir && !((String)input.get(2)).equals("d")){//if input is not d or r
	    System.out.println("ERROR: Please input a valid DIRECTION next time");
	    return false;
	}
	char[] word=((String)input.get(3)).toCharArray();//splits word input into chars

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
    public static boolean place(ArrayList input, Player p){
	 if (((int) input.get(4)) == 1 && touching(input)&&placeTemp(input)){
	    //gets start pos, adjusts for use with board
	    int x=(int)input.get(0)-1;
	    int y=(int)input.get(1)-1;
	    String wordS = (String)input.get(3);
	    if(x>14||y>14||x<0||y<0){//if user input out of bounds
		System.out.println("ERROR: Please input valid COORDINATES next time");
		return false;
	    }
	    //right is true down is false
	    boolean dir=((String)input.get(2)).equals("r");
	    if(!dir && !((String)input.get(2)).equals("d")){//if input is not d or r
		System.out.println("ERROR: Please input a valid DIRECTION next time");
		return false;
	    }
	    if(first) { //if first word is not centered, center it
		boolean centered = false;
		if(dir) {
		    if(y==7 && x <= 7 && x+wordS.length() >= 7) {
			centered=true;
		    }
		    else {
			x = 7;
			y = 7;
			System.out.println("You didn't center it so we centered it for you");
		    }
		}
		else {
		    if(x==7 && y <= 7 && y+wordS.length() >= 7) {
			centered=true;
		    }
		    else {
			x = 7;
			y = 7;
			System.out.println("You didn't center it so we centered it for you");
		    }
		}     
	    }
	    char[] word=wordS.toCharArray();//splits word input into chars
	    //enter word horizontally

	    ArrayList<Character> lettersNeeded=new ArrayList<Character>();
	    if(dir){
		for(int i=0;i<word.length;i++){
		    if(board[y][x+i]==' '){
			lettersNeeded.add(word[i]);
		    }
		}   
	    }
	    //enter word vertically
	    else{
		
		for(int i=0;i<word.length;i++){
		    if(board[y+i][x]==' '){
			lettersNeeded.add(word[i]);
		    }
		}
	    }
	    char[] lettersUsed=new char[lettersNeeded.size()];
	    for(int i=0;i<lettersNeeded.size();i++){
		lettersUsed[i]=lettersNeeded.get(i);
	    }
	    if(!p.hasLetters(lettersUsed)){
		return false;
	    }

	    if(feedWord(x,y,input.get(2).toString().charAt(0),(String)input.get(3)) != -1){
		if(dir){
     		    for(int i=0;i<word.length;i++){
			board[y][x+i]=word[i];
		    }   
		}
		//enter word vertically
		else{
		
		    for(int i=0;i<word.length;i++){
			board[y+i][x]=word[i];
		    }
		}
    		wordScore = scoreWord(word) + feedWord(x,y, input.get(2).toString().charAt(0), (String)input.get(3));//sets score of word
		p.addScore(wordScore);
		p.useLetters(lettersUsed);
	    }
	    else {
		return false;
	    }
	    first=false;
	    return true;
	}
	else{
	    return false;
	}
	
     }
    
    //-----------------------------------------------------------------------------------
    
    /*====================
ONE METHOD TO RULE THEM ALL
      ====================*/
    public static void play(){
       	ArrayList input;
	String quitcheck = "";
	int boundcheck;
	char[] lettersUsed;//splits word input into chars
	boolean running = true;
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
	//setup
	System.out.println("Welcome to Dabbling with Scrabbling! Please enter 1 for one player or 2 for two players");
	int playerNum = Keyboard.readInt();
	populateBag();
	System.out.println("Player 1, state your name:");
	Player p1=new User(Keyboard.readWord());
	Player p2;
	if (playerNum == 2){
	    System.out.println("Player 2, state your name:");
	    p2=new User(Keyboard.readWord());
	}
	else{
	    p2 = new AI();
	}
	p1.populateRack();
	p2.populateRack();
	Player[] players= new Player[]{p1, p2};

	System.out.println("You intrepid warriors are now to set out on a vicious battle to the death, or, preferably, the higher score when the bag runs out of letters. Keep in mind that this game of Scrabble is mad hardcore, yo, and that entering an incorrect word will forfeit your turn!\n Players, take your marks,\nget set,\nGO!");

	//gamePlay
       	while(running && BAG.size()>= 7-p1.rack.size()) {
	    for(Player p: players){
		System.out.println("Score of "+p1.name+ " now: " + p1.score);
		System.out.println("Score of "+p2.name+ " now: " + p2.score);
	    
		System.out.println(p.name + ", it's your turn. Type any character and press ENTER to proceed");
		Keyboard.readString();
		populate(displayBoard,board);
		print2d(displayBoard);
		System.out.println("Rack before word entered");
		System.out.println(p.getRack() );
		System.out.println("INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>, enter any x,y,dir,and: rerollrack - to get a new rack or iquit - to end the game.");
		if(first) System.out.println("The first word of the game must pass through 8,8. PLEASE ENTER COORDINATES LESS THAN OR EQUAL TO 8 (KNOWN BUG)");
		    
		input = p.placeWord();
		quitcheck=(String)input.get(3);
		boundcheck=(int)input.get(4);
		lettersUsed=quitcheck.toCharArray();
		if(place(input, p)){
		    populate(displayBoard,board);
		    print2d(displayBoard);
		    p.addLetters(p.drawBag(7-p.rack.size() ));
		    //replenishes rack
		    System.out.println("Rack after word entered");
		    System.out.println(p.getRack() );
		    System.out.println("Turn score: " + wordScore);
		}
		else if(quitcheck.equals("iquit")) {
		    running=false;
		    break;
		}
		else if(quitcheck.equals("rerollrack")) {
		    for(char a: p.rack) {
			BAG.add(a);
		    }
		    p.rack=new ArrayList<Character>(7);
		    p.populateRack();
		    System.out.println("YOUR NEW " + p.getRack());
		}
		else if(boundcheck == 2) {
		    System.out.println("Entry was out of bounds");
		}

		else if(!isWord(quitcheck) ) {
		    System.out.println("That is not a word");
		}
		else if(!p.hasLetters(lettersUsed)){
		    System.out.println("wrong letters");
		}
		//resets temp board
		for(int i=0;i<board.length;i++){
		    for(int n=0;n<board[i].length;n++){
			boardTemp[i][n]=board[i][n];
		    }
		}
		try{
		    Thread.sleep(5000);
		}
		catch(InterruptedException e){
		}
		System.out.println("printf \"\033c\"");
	    }
	}
	print2d(displayBoard);
	System.out.println("Thanks for playing");
	System.out.println(p1.name +", you scored a total of " + p1.score + " points");
	System.out.println(p2.name +", you scored a total of " + p2.score + " points");
    
    }
}
