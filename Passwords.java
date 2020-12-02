package day2;

import java.util.ArrayList;

public class Passwords {
	private int getLowerNumber(String input) {			//gets lower bound number
		String numbers = input.replaceAll("[^\\d]", " ").trim();	//Copies only numbers into new string
		int lower = Integer.parseInt(numbers.substring(0, numbers.indexOf(" ")));
		return lower;
	}
	
	private int getHigherNumber(String input) {			//gets upper bound number
		String numbers = input.replaceAll("[^\\d]", " ").trim();	//Copies only numbers into new string
		int higher = Integer.parseInt(numbers.substring(numbers.indexOf(" ")+1));
		return higher;
	}
	
	private boolean checkPasswordPartOne(String input, int lower, int higher) {	//counts number of letters and checks with lower/upper bounds
		String letter = input.substring(input.indexOf(" ")+1, input.indexOf(":"));	//gets letter we're looking for
		String password = input.substring(input.indexOf(":")+1).trim();		//gets only the password
		int count = 0;
		for (int i = 0; i < password.length(); i++) {
		    if (Character.toString(password.charAt(i)).equals(letter)) {	//compares each letter to the one we're looking for
		        count++;
		    }
		}
		if (count >= lower && count <= higher) {							//compares count to lower & upper bound
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private boolean checkPasswordPartTwo(String input, int lower, int higher) {
		String letter = input.substring(input.indexOf(" ")+1, input.indexOf(":"));	//gets letter we're looking for
		String password = input.substring(input.indexOf(":")+1).trim();		//gets only the password

		if (Character.toString(password.charAt(lower-1)).equals(letter) && !Character.toString(password.charAt(higher-1)).equals(letter)) {	//if lower bound equals and upper doesn't   
			return true;
		}
		else if (!Character.toString(password.charAt(lower-1)).equals(letter) && Character.toString(password.charAt(higher-1)).equals(letter)) {	//if upper bound equals and lower doesn't  
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		Passwords dayTwo = new Passwords();
		ArrayList<String> input = new ArrayList<String>();
		try {										//opens file and puts it in array list
	        new FileOpenReadWrite("input_day2.txt", input);
		}
	    catch(Exception e) {						//if file doesn't exist
	        System.err.println(e);
	    }
		
		ArrayList<Integer> correctP1 = new ArrayList<Integer>();	//holds all indexes of passwords that are correct for part one
		
		for (int i = 0; i < input.size(); i++) {	//Part One
			if (dayTwo.checkPasswordPartOne(input.get(i), dayTwo.getLowerNumber(input.get(i)), dayTwo.getHigherNumber(input.get(i)))) {
				correctP1.add(i);
			}
		}
		System.out.println(correctP1.size());		//prints Part One answer
		
		ArrayList<Integer> correctP2 = new ArrayList<Integer>();
		
		for (int j = 0; j < input.size(); j++) {	//Part Two
			if (dayTwo.checkPasswordPartTwo(input.get(j), dayTwo.getLowerNumber(input.get(j)), dayTwo.getHigherNumber(input.get(j)))) {
				correctP2.add(j);
			}
		}
		
		System.out.println(correctP2.size());		//prints Part Two answer
		
	}
		
}
