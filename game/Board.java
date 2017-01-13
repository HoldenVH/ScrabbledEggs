import java.util.ArrayList;

public class Board{
    public static String[][] displayBoard = new String[32][32];
    private final String[] dictionary =DictReader.OpenFile();
    private static char[][] board=new char[15][15];

    
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

    public static boolean place(ArrayList input){
	//gets start pos, adjusts for use with board
   	int x=(int)input.get(0)-1;
	int y=(int)input.get(1)-1;
	if(x>14||y>14||x<0||y<0){
	    return false;
	}
	//right is true down is false
	boolean dir=((String)input.get(2)).equals("r");
	System.out.println("foo"+(String)input.get(2)+ "boo");
	if(!dir && (String)input.get(2)!="d"){
	    System.out.println("please input valid directions next time");
	    // return false;
	}
	System.out.println((String)input.get(3));
	char[] word=((String)input.get(3)).toCharArray();
	if(dir){
	    for(int i=0;i<word.length;i++){
		board[y][x+i]=word[i];
	    }
	}
	else{
	    for(int i=0;i<word.length;i++){
		board[y+i][x]=word[i];
	    }
	}
	return true;
    }
	
    public static void main(String args[]){
	for(int i=0;i<board.length;i++){
	    for(int n=0;n< board.length;n++){
		board[i][n]=(char)' ' ;
	    }
	}
	
	System.out.println("INPUT FORMAT: <x1> <y1> <dir(d/r)> <word>");
	place(User.placeWord());

	populate(displayBoard,board);
	print2d(displayBoard);
    }
}
