public class Board{
    public static String[][] board = new String[32][32];
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
    public static void print1( String[][] a ) { 
	for( int i = 0; i < a.length; i++ ) {
	    for( int j = 0; j < a[i].length; j++ ) 
		System.out.print( a[i][j] + " " );
	    System.out.println();
	}
    }
    public static void populate(String args[][]){
	args[0][0] = "  ";
	
	//==============================
	//creates top row column numbers
	//==============================
	
	//sets column numbers at top
	for (int y = 2; y < args[0].length; y+=2){
	    args[0][y] = (y/2)-1 + "";
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
       	for (int x = 2; x < 22; x+=2){
	    args[x][0] = " " + ((x/2)-1);
	}

	//*
	//sets row numbers at left for double digit row numbers
	for (int x = 22; x < args[0].length; x+=2){
	    args[x][0] = x/2-1 + "";
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
		args[x][y] = "a";
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
    public static void main(String args[]){
	populate(board);
	print1(board);
    }
}
