package day1;

import java.util.ArrayList;

public class Numbers {
	private int partOne(ArrayList<Integer> input) {	//find two integers in list that add to 2020
		int answer = 0;
		for(int i: input) {
			for(int j: input) {
				if (j+i == 2020) {
					answer = j*i;					//final answer is the two integers multiplied
				}
			}
		}
		return answer;
	}
	
	private int partTwo(ArrayList<Integer> input) {	//find three integers in list that add to 2020
		int answer = 0;
		for(int i: input) {
			for(int j: input) {
				for(int k: input) {
					if (k+j+i == 2020) {
						answer = k*j*i;				//final answer is the three integers multiplied
					}
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Numbers dayOne = new Numbers();
		ArrayList<Integer> input = new ArrayList<Integer>();
		try {										//opens file and puts it in array list
	        new FileOpenReadWrite("input_day1.txt", input);
		}
	    catch(Exception e) {						//if file doesn't exist
	        System.err.println(e);
	    }
		
		System.out.println(dayOne.partOne(input));	//prints each answer
		System.out.println(dayOne.partTwo(input));
	}
}
