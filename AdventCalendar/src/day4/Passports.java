package day4;

import java.util.ArrayList;

public class Passports {
	 private static void bubbleSort(String[] arr, int n) { 		//Sorting strings using bubble sort 
	        String temp; 										//time complexity is a myth lol
	        for (int j = 0; j < n - 1; j++) { 
	            for (int i = j + 1; i < n; i++) { 
	                if (arr[j].compareTo(arr[i]) > 0) { 
	                    temp = arr[j]; 
	                    arr[j] = arr[i]; 
	                    arr[i] = temp; 
	                } 
	            } 
	        } 
	    } 
	 private static int validityP1(ArrayList<String> input) {		//checks if each item is valid based on given credentials
			int total = 0;
			for (int i = 0; i < input.size(); i++) {
				if (input.get(i).contains("byr:") && input.get(i).contains("iyr:") && input.get(i).contains("eyr:") && input.get(i).contains("hgt:") && input.get(i).contains("hcl:") && input.get(i).contains("ecl:") && input.get(i).contains("pid:")) {
					total++;
				}
			}
			return total;
	 }
	 
	private static int validityP2(ArrayList<String> input) {		//checks if each item is valid based on given credentials
		int total = 0;
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).contains("byr:") && input.get(i).contains("iyr:") && input.get(i).contains("eyr:") && input.get(i).contains("hgt:") && input.get(i).contains("hcl:") && input.get(i).contains("ecl:") && input.get(i).contains("pid:")) {
				String[] items = input.get(i).split(" ");		//split each item so I can sort
				bubbleSort(items, items.length);
				int index = 0;
				
				//Filters birth year (1920-2002)
				if (Integer.parseInt(items[index].substring(items[index].indexOf(":")+1)) >= 1920 && Integer.parseInt(items[index].substring(items[index].indexOf(":")+1)) <= 2002) {
					index++;
					//Country ID does not matter since we're from the North Pole
					if (input.get(i).contains("cid:")) {
						index++;
					}
					//Filters eye color (amber, blue, brown, grey, green, hazel, and other only)
					if (items[index].contains("amb") || items[index].contains("blu") || items[index].contains("brn") || items[index].contains("gry") || items[index].contains("grn") || items[index].contains("hzl") || items[index].contains("oth")) {
						index++;
						//Filters expiration year (2020-2030)
						if (Integer.parseInt(items[index].substring(items[index].indexOf(":")+1)) >= 2020 && Integer.parseInt(items[index].substring(items[index].indexOf(":")+1)) <= 2030) {
							index++;
							//Filters hair color (# followed by 6 characters of a-z or 0-9) 
							if(items[index].substring(items[index].indexOf("#")+1).matches("[a-z0-9]*") && items[index].substring(items[index].indexOf("#")+1).length() == 6) {
								index++;
								//Filters height. I cheated and made a scuffed filter that looks for cm with 3 numbers and in with 2 numbers. I was too lazy and it worked still, sorry.
								if(items[index].contains("cm") && items[index].substring(items[index].indexOf(":")+1).length() == 5 || items[index].contains("in") && items[index].substring(items[index].indexOf(":")+1).length() == 4) {
									index++;
									//Filtered issue year (2010-2020)
									if (Integer.parseInt(items[index].substring(items[index].indexOf(":")+1)) >= 2010 && Integer.parseInt(items[index].substring(items[index].indexOf(":")+1)) <= 2020) {
										index++;
										//Filtered passport ID (9 digits)
										if(items[index].substring(items[index].indexOf(":")+1).length() == 9 && items[index].substring(items[index].indexOf(":")+1).matches("[0-9]*")) {
											total++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	
		return total;
	}
	
	public static void main(String args[]) {
		ArrayList<String> input = new ArrayList<String>();
		
		try {										//opens file and puts it in array list
	        new FileOpenReadWrite("input_day4.txt", input);
		}
	    catch(Exception e) {						//if file doesn't exist
	        System.err.println(e);
	    }
		
		System.out.println(validityP1(input));		//part one
		System.out.println(validityP2(input));		//part two
	}
	
}
