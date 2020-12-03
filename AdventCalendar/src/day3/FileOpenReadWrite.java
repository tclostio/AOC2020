package day3;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileOpenReadWrite {

    Scanner fin;
    int i = 0;
    
    FileOpenReadWrite(String fileName, ArrayList<Character> input){
        try {
            fin = new Scanner(new File(fileName));
        }
        catch(IOException e) {
            System.err.println(e);
        }
        
        while(fin.hasNext()) {
        	String line = fin.next();
        	for (int i = 0; i < 31; i++) {
	        	char part = line.charAt(i);
	        	input.add(part);
        	}
        }
       
    }
}

