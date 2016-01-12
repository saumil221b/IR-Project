//PART D

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Palindrome {
	
	static String stringVar = null;
	static int i = 0;
	static ArrayList<String> tokens = new ArrayList<>();
	static Hashtable<String, Integer> palindromeHash = new Hashtable<String, Integer>();
	
	public static void main(String [] args) throws IOException{
		
		try{
			System.out.println("Entire filepath : ");
			Scanner input = new Scanner(System.in);
			String file_name = input.nextLine();
			
			BufferedReader br = new BufferedReader(new FileReader(file_name)); 
	
			String stringLine = null;
			while((stringLine = br.readLine()) != null) {
		
				tokenizeFile(stringLine);
			}
			computePalindromeFrequencies();
			printOutput();
			br.close();
			input.close();
		}
		catch(Exception e){
		
			System.out.println("Sorry, Could not locate path !! \nPlease give valid file path");
		}
	}
	
	static void tokenizeFile(String stringLine){
		
		Scanner scan;
		String stringTemp;
		scan = new Scanner(stringLine);
		scan.useDelimiter("[^\\w]+");

		while(scan.hasNext()){
			
			stringTemp = scan.next().toLowerCase();
			tokens.add(i, stringTemp);
			i++;
		}
		scan.close();
	}
	
	static boolean isPalindrome(String str){
		 int n = str.length();
		 stringVar = str;
		 for (int i=0;i<(n / 2) + 1;++i) {
			 if (str.charAt(i) != str.charAt(n - i - 1)) {
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 static void computePalindromeFrequencies(){
		 
		 int tkSize = tokens.size();
		 for(int i = 0;i<tkSize-1; i++){
		 
			 String str1 = (String) tokens.get(i);
			 if (isPalindrome(str1)==true && stringVar.length()!=1){
				 
				 if(palindromeHash.containsKey(stringVar)){
						
						int j = palindromeHash.get(stringVar);
						j = j+1;
						palindromeHash.put(stringVar, j);
					}
				
				else{
						palindromeHash.put(stringVar, 1);
					}
			 }
			 String str3 = str1;
			 String str4 = str1;
			 
			 for(int j = i+1;(j<i+10 && j<tkSize);j++){
			 
				 String str2 = (String) tokens.get(j);
				 str4 = str4 + " ";
				 str4 = str4 + str2;
				 str3 = str3+str2;
				
				 if (isPalindrome(str3)==true){
					 if(palindromeHash.containsKey(str4)){
							
						 int j2 = palindromeHash.get(str4);
						 j2 = j2+1;
						 palindromeHash.put(str4, j2);
					 }
					
					 else{
							palindromeHash.put(str4, 1);
					 }
				 }	 
			 }
		 }		 	 
	 }
	 static void printOutput() {
			int ans = 0; 
			for (String key : palindromeHash.keySet()) {
				System.out.printf("%-30.30s  %-30.30s%n", key, palindromeHash.get(key));
			    ans = ans + palindromeHash.get(key);
			}	
			System.out.println("\nNumber of Unique palindromes :  " + palindromeHash.size());	
			System.out.println("Total palindromes : " + ans);
	 }	 
}
