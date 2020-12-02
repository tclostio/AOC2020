package day2;

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
        
        while(fin.hasNext()) {
        	String line = fin.nextLine();
        	input.add(line);
        }
       
    }
}

