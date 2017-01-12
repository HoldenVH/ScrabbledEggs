public class Board{
    public static String[][] board = new String[31][31];
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
	for (int x = 1; x < args.length; x+=2){
	    for (int y = 1; y < args[x].length; y+=2){
		args[x][y] = "a";
	    }
	}
	for (int x = 1; x < args.length; x+=2){
	    for (int y = 0; y < args[x].length; y+=2){
		args[x][y] = "|";
	    }
	}
	for (int x = 0; x < args.length; x+=2){
	    for (int y = 1; y < args[x].length; y+=2){
		args[x][y] = "-";
	    }
	}
	for (int x = 0; x < args.length; x+=2){
	    for (int y = 0; y < args[x].length; y+=2){
		args[x][y] = " ";
	    }
	}
    }
    public static void main(String args[]){
	populate(board);
	print1(board);
    }
}
