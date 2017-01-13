import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class DictReader{
    static int lines() throws IOException{
	FileReader r=new FileReader("dictionary.txt");
	BufferedReader lineRead=new BufferedReader(r);

	String l;
	int lines=0;
	while((l=lineRead.readLine())!=null){
	    lines++;
	}
	return lines;
    }
	    
    public static String[] OpenFile(){
	String[] err=new String[0];
	    try{
	    FileReader r=new FileReader("dictionary.txt");
	    BufferedReader lineRead=new BufferedReader(r);
	    
	    String l;
	    int n=lines();
	    String[] dict=new String[n/2];
	    
	    for(int i=0;i<n/2;i++){
		
		dict[i]=lineRead.readLine();
		lineRead.readLine();
	    }
	    
	    lineRead.close();
	    return dict;
	}
	catch(IOException e){
	}
	return err;
    }
}
