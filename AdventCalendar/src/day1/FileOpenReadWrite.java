package day1;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileOpenReadWrite {

    Scanner fin;
    int i = 0;
    
    FileOpenReadWrite(String fileName, ArrayList<Integer> input){
        try {
            fin = new Scanner(new File(fileName));
        }
        catch(IOException e) {
            System.err.println(e);
        }
        
        while(fin.hasNextInt()) {
        	int num = fin.nextInt();
        	input.add(num);
        }
       
    }
}

