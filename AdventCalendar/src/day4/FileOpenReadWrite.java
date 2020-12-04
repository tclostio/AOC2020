package day4;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileOpenReadWrite {

    Scanner fin;
    int i = 0;
    
    FileOpenReadWrite(String fileName, ArrayList<String> input){
        try {
            fin = new Scanner(new File(fileName));
        }
        catch(IOException e) {
            System.err.println(e);
        }
        
        String whole = "";
        
        while(fin.hasNext()) {
        	String line = fin.nextLine();
        	if (!line.equals("")) {
        		whole += line + " ";	//combines each line together
        	}
        	else {
        		input.add(whole);		//passport is add as one line versus multiple
        		whole = "";
        	}
        }
        input.add(whole);	//needed to include last line
       
    }
}

