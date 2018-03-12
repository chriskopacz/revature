package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 *
	 * @param string
	 * @return
	 */
	public String reverse(String aString) {
		//catch the empty string
		if(aString.length() == 0)
		{
			return "";
		}
		int stringLength = aString.length();
		if(stringLength == 0)
		{
			return null;
		}
		char[] result = new char[stringLength];
		int i;
		for(i=0;i<stringLength;i++)
		{
			result[i]=aString.charAt(stringLength-i-1);
		}

		return new String(result);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {

		//replace hyphen with space, split into array of strings using space as separator
		String[] array = phrase.replaceAll("-"," ").split(" ");
		//create a character array to store the first letter of each word in the separated string
		char[] acronym = new char[array.length];
		int i; //iterative variable

		//step through each string in the array, store the first character in acronym
		for(i=0;i<array.length;i++)
		{
			acronym[i] = array[i].charAt(0);
		}

		//convert the character array to a string, capitalize it, return it
		return new String(acronym).toUpperCase();

	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double One, double Two, double Three) {
			this();
			this.sideOne = One;
			this.sideTwo = Two;
			this.sideThree = Three;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double one) {
			this.sideOne = one;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double two) {
			this.sideTwo = two;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double three) {
			this.sideThree = three;
		}

		public boolean isEquilateral() {
			if(this.sideOne == this.sideTwo && this.sideOne == this.sideThree && this.sideTwo == this.sideThree)
			{
				return true;
			}

			//default return case
			return false;
		}

		public boolean isIsosceles() {
			//if the triangle is not equilateral and is not scalene, then it is isosceles
			if(!this.isEquilateral() && !this.isScalene())
			{
				return true;
			}//if

			//default return case
			return false;
		}

		public boolean isScalene() {
			if(this.sideOne != this.sideTwo && this.sideOne != this.sideThree && this.sideTwo != this.sideThree)
			{
				return true;
			}

			//default return case
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int i; //iterative variable
		int score = 0;

		//loop through each character in the string to calculate the score
		for(i=0;i<string.length();i++)
		{
			char temp = string.toLowerCase().charAt(i); //convert to lowercase for simplicity
			switch(temp)
			{
			//switch cases containing point values for each letter
			case 'a': score += 1; break;
			case 'b': score += 3; break;
			case 'c': score += 3; break;
			case 'd': score += 2; break;
			case 'e': score += 1; break;
			case 'f': score += 4; break;
			case 'g': score += 2; break;
			case 'h': score += 4; break;
			case 'i': score += 1; break;
			case 'j': score += 8; break;
			case 'k': score += 5; break;
			case 'l': score += 1; break;
			case 'm': score += 3; break;
			case 'n': score += 1; break;
			case 'o': score += 1; break;
			case 'p': score += 3; break;
			case 'q': score += 10; break;
			case 'r': score += 1; break;
			case 's': score += 1; break;
			case 't': score += 1; break;
			case 'u': score += 1; break;
			case 'v': score += 4; break;
			case 'w': score += 4; break;
			case 'x': score += 8; break;
			case 'y': score += 4; break;
			case 'z': score += 10; break;
			default: System.out.println(string);break;
			}//switch

		}

		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		//remove all characters that arent digits, store the result
		String result;
		result = string.replaceAll("[^0-9]", "");

		//if the length is over or under 10, throw error, otherwise return result
		if(result.length() < 10)
		{
			throw new IllegalArgumentException();
		}else if(result.length() > 10)
		{
			throw new IllegalArgumentException();
		}else
		{
			return result;
		}
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		//get rid of newlines and commas
		string = string.replaceAll("\n", "");
		string = string.replaceAll(",", " ");
		//split the string into an array of strings using " " as a serparator
		String[] array = string.split(" ");
		int i; //iterative variable
		Map<String, Integer> rs = new HashMap<String, Integer>(); //initialize the map

		for(i=0;i<array.length;i++)
		{
			if(rs.containsKey(array[i]))
			{
				//if the map already contains the word, then increment the counter by 1
				rs.put(array[i], rs.get(array[i])+1);
			}else
			{
				//if the map doesnt contain the word, then insert it as a new key and give ait a value of 1
				rs.put(array[i], 1);
			}//if
		}//for

		//System.out.println(rs);

		return rs;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			//set initial left, mid, right
			int left = 0;
			int right = sortedList.size();
			return search(left,right,t);
			
			
			/*
			 * uncomment this section for the while-loop version (and comment out the above return statement as well)
			 * 
			int mid = right/2 + left;
			int count = 0;
			int max = sortedList.size();
			while(left <= right && count <= max)
			{
				if(sortedList.get(mid).equals(t))
				{
					//if value is found, return its index
					return mid;
				}else if((Integer)sortedList.get(mid) < (Integer)t)
				{
					//if value is greater than mid, then move left to mid+1 and reassign mid
					left = mid+1;
					mid = (right-left)/2+left;
				}else if((Integer)sortedList.get(mid) > (Integer)t)
				{
					//if value is less than mid, then move right to mid-1 and reassign mid
					right = mid-1;
					mid = (right-left)/2+left;
				}
				//iterate count with each loop/search
				count += 1;
			}
			
			//return -1 if number of searches exceeds number of elements in list (indicates possible infinite loop)
			return -1;
			*
			*end of the while-loop section
			*/
		}
		
		//recursive version of the binary search
		public int search(int left, int right, T t)
		{
			int mid = (int)(right+left)/2;
			
			if(sortedList.get(mid).equals(t))
			{
				//if the value is found, return the corresponding index
				return mid;
			}else if((Integer)sortedList.get(mid) < (Integer)t)
			{
				//if the value is greater than the one located at index=mid, then move 'left' to mid+1, and recursively search again
				//left = mid;
				return search(mid,right,t);
			}else
			{
				//if the value is less than the one located at index=mid, then move 'right' to mid-1, and recursively search again
				//right = mid;
				return search(left,mid,t);
			}
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		string = string.toLowerCase();
		String[] allStrings = string.split(" ");
		String[] resultStrings = new String[allStrings.length];
		int firstVowel=0;
		int i,iter;
		String temp = null;
		String result = "";

		//loop through array allStrings[]
		for(iter=0;iter<allStrings.length;iter++) //for1
		{
			temp = new String(allStrings[iter]);
			if(Character.toString(temp.charAt(0)).matches("[aeiou]"))
			{	//if a word begins with a vowel sound, add an "ay" to the end of the word
				resultStrings[iter] = new String(temp + "ay");	
			}else
			{
				//loop through temp looking for the first value
				for(i=0;i<temp.length();i++) //for2
				{
					if(Character.toString(temp.charAt(i)).matches("[aeiou]"))
					{
						if(temp.charAt(i-1) == 'q')
						{	//this allows for the 'qu' sound to be counted as a consonant rather than a vowel
							continue;
						}else
						{
							firstVowel = i;
							break;
						}
					}
				}//for2

			}//if-else
			//form the resulting string
			resultStrings[iter] = new String(temp.substring(firstVowel,temp.length()) + temp.substring(0,firstVowel) + "ay");
		}//for1
		
		



		//form the result
		for(iter=0;iter<resultStrings.length;iter++)
		{
			result = result.concat(resultStrings[iter] + " ");
		}
		result = result.trim();
		
		return result;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		Integer in = new Integer(input);
		String intString = in.toString();
		int length = intString.length();
		int i;
		int sum=0;
		//System.out.println(input);
		for(i=0;i<length;i++)
		{
			int temp = Character.getNumericValue(intString.charAt(i));
			sum += Math.pow(temp,length);
		}
		//System.out.println(sum);
		//System.out.println(sum==input);
		return (sum == input);
		//return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	//	   List<Integer>    <---orriginally this (had to change list type to get tests to pass)
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<Long>();
		long i;

		for(i=2;i<=l;i++)
		{
			while(l%i==0)
			{
				primeFactors.add(i);
				l /= i;
			}
		}
		//System.out.println(primeFactors);
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			int rot = this.key;
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			int i;
			char[] array = new char[100];
			String cipherText;

			for(i=0;i<string.length();i++)
			{
				//System.out.println("string: " + string.charAt(i));
				//check if character is alphabetic, whitespace, or punctuation
				if(Character.isLetter(string.charAt(i)))
				{
					//check if character is lowercase or uppercase, and add corrensponding rotated character to array
					if(Character.isUpperCase(string.charAt(i)))
					{
						int index = alphabet.indexOf(Character.toLowerCase(string.charAt(i)));
						array[i] = Character.toUpperCase(alphabet.charAt((index+rot)%26));
					}else
					{
						int index = alphabet.indexOf(string.charAt(i));
						array[i] = alphabet.charAt((index+rot)%26);
					}
				}else
				{
					//if character is not a letter, then it is whitespace, punctuation, or a number
					//in that case, just add that character to array
					array[i] = string.charAt(i);
				}
				//System.out.println("ciph: " + array[i]);
			}//for

			//convert array to String and trim remaining whitespace
			cipherText = new String(array);
			cipherText = cipherText.trim();
			//System.out.println(cipherText);
			return cipherText;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		if(i==0)
		{
			throw new IllegalArgumentException();
		}else if(i == 1)
		{
			return 2;
		}else
		{
			List<Integer> primes = new ArrayList<Integer>();
			int stop;
			int testNum=3;
			int iter;
			boolean check = true;
			primes.add(2);
			while(primes.size() != i)
			{
				check = true;
				stop = (int)Math.sqrt(testNum)+1;
				for(iter=2;iter<stop;iter++)
				{
					if(testNum%iter==0)
					{
						check = false;
						break;
					}//if
				}//for
				if(check == true)
				{
					primes.add(testNum);
				}
				testNum += 2;
			}//while
			//System.out.println(primes);
			return primes.get(i-1);
		}
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String originalAlphabet = "abcdefghijklmnopqrstuvwxyz";
			String cipherAlphabet = "zyxwvutsrqponmlkjihgfedcba";
			string = string.replaceAll(",", " ");
			string = string.toLowerCase();
			string = string.replace("."," ");
			string = string.replace(" ","");
			//System.out.println(string);
			char[] array = new char[100];
			String result = "";
			int arrayCounter = 0;
			int i;

			//iterate through the string, replace each character via the key to form the ciphertext
			for(i=0;i<string.length();i++)
			{
				if(Character.isLetter(string.charAt(i)))
				{
					array[arrayCounter] = cipherAlphabet.charAt(originalAlphabet.indexOf(string.charAt(i)));
				}else
				{
					array[arrayCounter] = string.charAt(i);
				}
				arrayCounter += 1;
			}//for

			//format the ciphertext to be groups of 5 characters separated by a space
			i=0;
			while(i<array.length-1)
			{
				if(i%5==0 && i != 0)
				{
					result = result.concat(" "+array[i]);
					i += 1;
				}else
				{
					result = result.concat(""+array[i]);
					i += 1;
				}
			}

			result = result.trim();
			//System.out.println(result);
			return result;
			//return null;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String originalAlphabet = "abcdefghijklmnopqrstuvwxyz";
			String cipherAlphabet = "zyxwvutsrqponmlkjihgfedcba";
			string = string.replace(" ", "");
			char[] array = new char[100];
			int i;

			for(i=0;i<string.length();i++)
			{
				if(Character.isLetter(string.charAt(i)))
				{
					array[i] = originalAlphabet.charAt(cipherAlphabet.indexOf(string.charAt(i)));			
				}else
				{
					array[i] = string.charAt(i);
				}
			}
			String temp = new String(array);
			temp = temp.trim();
			return temp;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		//remove hyphens
		string = string.replace("-","");
		int sum = 0;
		int temp;
		int i;

		for(i=0;i<string.length();i++)
		{
			if(Character.isDigit(string.charAt(i)))
			{
				temp = (Character.getNumericValue(string.charAt(i)));
				sum += (10-i)*temp;
			}else
			{
				if(string.charAt(i)=='X')
				{
					temp = 10;
					sum += (10-i)*temp;
				}else
				{
					return false; //return false if any character other than 'X' is in the isbn
				}
			}//if
		}//for


		return (sum%11==0);
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		//catch the empty string
		if(string.isEmpty())
		{
			return false;
		}
		//get rid of newlines, commas, and spaces
		string = string.toLowerCase();
		string = string.replaceAll("\n", "");
		string = string.replaceAll(",", "");
		string = string.replaceAll(" ","");
		int i; //iterative variable
		Map<Character, Integer> rs = new HashMap<Character, Integer>();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		//enter every letter into the map with an initial count of 0
		for(i=0;i<alphabet.length();i++)
		{
			rs.put(alphabet.charAt(i),0);
		}

		//for each letter in 'string' iterate its corresponding value in 'rs'
		for(i=0;i<string.length();i++)
		{
			if(rs.containsKey(string.charAt(i)))
			{
				//if the map already contains the character, then increment the counter by 1
				//				rs.put(string.charAt(i), rs.get(string.charAt(i)+1));
				rs.merge(string.charAt(i), 1, Integer::sum);
			}//if
		}//for

		//check to see if every letter was used at least once
		for(i=0;i<alphabet.length();i++)
		{
			if(rs.get(alphabet.charAt(i))==0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		long gigasec = (int)Math.pow(10, 9);
		LocalDateTime result = null;

		if(given instanceof LocalDateTime)
		{
			result = (LocalDateTime)given;
		}else if(given instanceof LocalDate)
		{
			result = LocalDateTime.of((LocalDate)given, LocalTime.of(0,0));
		}
		result = result.plusSeconds(gigasec);



		return result;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		List<Integer> multiples = new ArrayList<Integer>();
		Integer sum;
		int iter;
		int num;

		for(iter=0;iter<set.length;iter++)
		{
			num = set[iter];
			while(num<i)
			{
				multiples.add(num);
				num += set[iter];
			}
		}
		//converting to a HashSet reduces the original list to contain only unique elements
		Set<Integer> mults = new HashSet<Integer>(multiples);

		//convert back to an ArrayList
		List<Integer> multsFinal = new ArrayList<Integer>(mults);

		sum = multsFinal.stream().mapToInt(Integer::intValue).sum();


		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		string = string.replace(" ","");
		int[] array = new int[string.length()];
		int i;
		int temp;
		int sum=0;


		for(i=0;i<string.length();i++)
		{
			if(Character.isDigit(string.charAt(i)))
			{
				if(i%2 == 1)
				{
					temp = 2*Character.getNumericValue(string.charAt(string.length()-i-1));
					if(temp>9)
					{
						temp = temp-9;
					}
					array[array.length-i-1] = temp;
				}else
				{
					temp = Character.getNumericValue(string.charAt(string.length()-i-1));
					array[array.length-i-1] = temp;
				}
			}else
			{
				return false;
			}
		}

		for(i=0;i<array.length;i++)
		{
			sum += array[i];
		}

		return (sum%10==0 && sum != 0);
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		//if contains plus, minus, divided by, multiplied by
		if(!(string.contains("plus") || string.contains("minus") || string.contains("multiplied") || string.contains("divided")))
		{
			return -10000; //fail condition if word problem doesnt contain accepted mathematical operators
		}
		//split string with " " as separator
		string = string.replace("?", ""); //remove question mark
		String[] brokenUp = string.split(" ");
		int[] nums = new int[2]; //will contain the numbers used in the operation
		int numCheck = 0;
		String operator = null; //will contain the operator being used in the word problem
		int i; //iterative variable
		int result; //return variable

		//search to find the two numbers in the word problem
		for(i=0;i<brokenUp.length;i++)
		{
			try
			{
				nums[numCheck] = Integer.valueOf(brokenUp[i]);
				if(numCheck == 0)
				{
					operator = brokenUp[i+1];
					numCheck = 1;
				}
			}catch(NumberFormatException nfe)
			{
				//do nothing
			}
		}
		//System.out.println(nums[0]+ " " + operator + " " + nums[1]);

		//switch statement to handle case
		switch(operator)
		{
		case "plus":
			result = nums[0] + nums[1];
			break;
		case "minus":
			result = nums[0] - nums[1];
			break;
		case "multiplied":
			result = nums[0]*nums[1];
			break;
		case"divided":
			result = nums[0]/nums[1];
			break;
		default:
			result = -10000;
			break;
		}

		return result;
	}

}
