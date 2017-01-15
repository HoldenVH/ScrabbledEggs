import java.io.*;
import java.util.*;

public class DictReader{
    public static String[] OpenFile(){
	String[] retStr = new String[535501];
	File file = new File("dictionary.txt");
	try{
	    Scanner sc = new Scanner(file);
	    boolean check = sc.hasNextLine();
	    for (int x = 0; check; x++){
		retStr[x] = sc.nextLine();
		check = sc.hasNextLine();
	    }
	}
	catch (FileNotFoundException e){
	    e.printStackTrace();
	}
	return retStr;
    }

    /*
    public static boolean isWord(char[] word){
	int min=0;
	int max=535501;
	boolean same;
	boolean bob = false;
	while(min<max){
	    int i=0;
	    same=true;
	    while(same&& i<word.length && i<dictionary[(min+max)/2].length()){
		//System.out.println(dictionary[(min+max)/2]);
		if(word[i]<dictionary[(min+max)/2].toLowerCase().toCharArray()[i]){
		    max=(min+max)/2;
		    same=false;
		    System.out.println("low");
		}
		else{
		    if(word[i]>dictionary[(min+max)/2].toLowerCase().toCharArray()[i]){
			min=(min+max)/2;
			same=false;
			for(int n=0;n<1000000;n+=2){
			    n--;
			}
			
			//	System.out.println((dictionary[(min+max)/2].toLowerCase().toCharArray())[0]);
		    }
		    else{
			if(i==word.length-1 && i==dictionary[(min+max)/2].length()){
			    bob=true;
				}
			min= (min+max)/2 ;
			same=false;
		    }
		}
	    }
	}
	return bob;
    }
    */
    /*
    public static boolean isWord(ArrayList<Character> word) {
    int counter = 0;
    int min = 0;
    int max = dictionary.length;
    boolean same = false;

    System.out.println((min+max)/2);
    System.out.println(dictionary[(min+max)/2]);
    while(counter < word.size()&& min < max) {
	if(word.get(counter) == dictionary[(min+max)/2].charAt(counter) ) {
		counter++;
    }
	else if(word.get(counter) > dictionary[(min+max)/2].charAt(counter) ) {
	    min = ( (min+max) / 2);
    }
	else if(word.get(counter) < dictionary[(min+max)/2].charAt(counter) ) {
	    max =( (min+max) / 2);
}
    }
    for(int i = 0;i<word.size();i++) {
	if(word.get(i) == dictionary[min].charAt(i) ) {
	    same = true;
	}
	else {
	    same = false;
	}
    }
    return same;
    }
    */

    public static void main(String args[]){
	ArrayList<Character> bird= new ArrayList<Character>();
	bird.add('b');
	bird.add('i');
	bird.add('r');
	bird.add('d');
	System.out.println(isWord(bird));
	System.out.println("ADSD");
	*/

	//*
	System.out.println(1);
	char[] bird = new char[4];
	bird[0] = 'b';
	bird[1] = 'i';
	bird[2] = 'r';
	bird[3] = 'd';
	System.out.println(2);
	System.out.println(isWord(bird));
	System.out.println(3);

    }
}
