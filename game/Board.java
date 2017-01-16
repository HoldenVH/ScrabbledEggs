import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Board{
    public static String[][] displayBoard = new String[32][32];
    private static char[][] board=new char[15][15];//actual backend board
    private final char[] STARTING_BAG={'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D','D','D','E','E','E','E','E','E','E','E','E','E','E','E','F','F','G','G','G','H','H','I','I','I','I','I','I','I','I','I','J','K','L','L','L','L','M','M','N','N','N','N','N','N','O','O','O','O','O','O','O','O','Q','R','R','R','R','R','R','S','S','S','S','T','T','T','T','T','T','U','U','U','U','V','V','W','W','X','Y','Y','Z'};
    private static int[] score = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};//score of each letter in scrabble
    private static int wordScore;
    public static ArrayList<String> dictionary = new ArrayList<String>(267751);
    
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
    public static void print2d( String[][] a ) { 
	for( int i = 0; i < a.length; i++ ) {
	    for( int j = 0; j < a[i].length; j++ ) 
		System.out.print( a[i][j] + " " );
	    System.out.println();
	}
    }
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
		args[x][y] = " ";
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

    public static boolean isWord(String word){
	word = word.toUpperCase();
	int middle;
	int highest = dictionary.size();
	//System.out.println(dictionary.size());
	int first = 0;
	middle = highest/2;
	//System.out.println(word);
	
	while(first < highest){
	    //System.out.println(dictionary.get(middle));
	    if(dictionary.get(middle).compareTo(word)>0){
		highest = middle;
		middle /= 2;
	    }
	    else if (dictionary.get(middle).compareTo(word)<0){
		first = middle + 1;
		middle = (middle+highest)/2;
	    }
	    else{
		return true;
	    }
	}
	return false;
    }
    

    //scores word
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

    public static int feedWord(int x, int y, char dir, String word) {
	char[] holder;
	String holderString = "";
	int retScore = 0;
	if(dir == 'r') { //if entered horizontally
	    int yUp;
	    int yDown;
	    int startingY;
	    int endingY;
	    for (int i = 0; i<word.length(); i++) {
		if((y != 0 && board[y-1][x+i] != ' ') || (y != 14 && board[y+1][x+i] != ' ')) { //checks above and below
		    yUp = 0;
		    yDown=0;
		    while(y-yUp >= 0 && board[y-yUp][x+i] != ' ') {
			yUp+=1;
		    }
		    while(y+yDown <= 14 && board[y+yDown][x+i] != ' '){
			yDown+=1;
		    }
		    startingY = y-yUp;
		    endingY = y+yDown;
		    //board[y-yUp][x+i]
		    //board[y+yDown][x+i]
		    holder = new char[endingY-startingY+1];
		    for(int counter = startingY; counter <= endingY; i++) {
			holder[i] = board[startingY][x+counter];
			holderString += Character.toString(board[startingY][x+counter]);
		    }
		    if (isWord(holderString)){
			retScore += scoreWord(holder);
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
		if((x != 0 && board[y+i][x-1] != ' ') || (x != 14 && board[y+i][x+1] != ' ')) { //checks right and left
		    xLeft = 0;
		    xRight=0;
		    while(x-xLeft >= 0 && board[y+i][x-xLeft] != ' ') {
			xLeft+=1;
		    }
		    while(x+xRight <= 14 && board[y+i][x+xRight] != ' '){
			xRight+=1;
		    }
		    startingX = x-xLeft;
		    endingX = x+xRight;
		    //board[y+i][x-startingX]
		    //board[y+i][x+startingY]
		    holder = new char[endingX-startingX+1];
		    for(int counter = startingX; counter <= endingX; i++) {
			holder[i] = board[startingX][x+counter];
			holderString += Character.toString(board[startingX][x+counter]);
		    }
		    if (isWord(holderString)){
			retScore += scoreWord(holder);
		    }
		}
	    }
	}
	return retScore;
    }

   
    public static boolean place(ArrayList input){
	if (((int) input.get(4)) == 1){
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
	wordScore = scoreWord(word) + feedWord(x,y, input.get(2).toString().charAt(0), (String)input.get(3));//sets score of word
	User.addScore(wordScore);
	//System.out.println(wordScore);
	//enter word horizontally
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
	return true;
    }
	else{
	    return false;
	}
    }


    
    public static void main(String args[]){
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
	System.out.println("huh");
	for(int i=0;i<board.length;i++){
	    for(int n=0;n< board.length;n++){
		board[i][n]=(char)' ' ;
	    }
	}

	//System.out.println(isWord("bird"));

	/*
	for (int x = 0; x < 10; x++){
	    System.out.println(dictionary[x]);
	}
	*/

	//*
	System.out.println("INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>");
	while(place(User.placeWord())){
	    populate(displayBoard,board);
	    print2d(displayBoard);
	    System.out.println("Turn score: " + wordScore);
	    System.out.println("Score of User now: " + User.score);
	    System.out.println("INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>");
	    
	}
	//*/
    }
}
