import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//Created by Kyle Beaubien on 7/31/2022
public class ExamsoftCodingAssessment {
	public final static String ENTER_TEXT_MESSAGE = "Please enter your text:";
	public final static String INPUT_IS_EMPTY = "Invalid input- Cannot have no input text please try again.";
	public final static String NO_DATA_FOUND = "No Data";
	public final static String UNEXPECTED_ERROR = "Some unexpected error has occured- please contact support";
	public final static String BLANK_STRING = "";
	public final static String SPACE_STRING = " ";
	public final static String WHITESPACE_REGEX = "\\s+";

	public static void main(String[] args) {
		System.out.println(ENTER_TEXT_MESSAGE);
		// use the scanner for easy text input
		Scanner inputScanner = new Scanner(System.in);
		String textInputFromUser = BLANK_STRING;
		textInputFromUser = inputScanner.nextLine();
		// make sure to close the input
		inputScanner.close();
		// check for empty input
		if (textInputFromUser != null && !textInputFromUser.equals(BLANK_STRING)) {
			try {
				// Call tokenize method to make a hashmap then sort it then print it.
				printResult(sortHashMapByValue(tokenize(textInputFromUser)));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(UNEXPECTED_ERROR);
			}

		} else {
			System.out.println(INPUT_IS_EMPTY);
		}
	}

	// This method takes a string and splits it via space char then takes all the
	// strings and puts them into a hash map with a frequency value unsorted
	public static HashMap<String, Integer> tokenize(String inputString) {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		String[] arrayOfString = inputString.split(SPACE_STRING);
		for (String splitStringValue : arrayOfString) {
			if (splitStringValue != null) {
				// check if there is just whitespace and ignore it
				if (splitStringValue.replaceAll(WHITESPACE_REGEX, splitStringValue).equals(BLANK_STRING)) {
					// ignore blank values
				} else {
					if (hashMap.containsKey(splitStringValue)) {
						// string has already been found in the list add one to the int value
						Integer newWordFrequency = Integer.valueOf(hashMap.get(splitStringValue).intValue() + 1);
						hashMap.put(splitStringValue, newWordFrequency);
					} else {
						// String hasnt been found yet add it to the list with default value 1
						hashMap.put(splitStringValue, 1);
					}
				}
			} else {
				// null skip this value
			}

		}
		return hashMap;
	}

	// This method takes in a unsorted map and sorts it by the value in the hash map
	public static HashMap<String, Integer> sortHashMapByValue(HashMap<String, Integer> unsortedHashMap) {
		if (unsortedHashMap != null) {
			// Create a Linked List for collection sort
			List<Map.Entry<String, Integer>> entitySetLinkedList = new LinkedList<Map.Entry<String, Integer>>(
					unsortedHashMap.entrySet());
			// Collections.sort for comparing the lists
			Collections.sort(entitySetLinkedList, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> mapEntry1, Map.Entry<String, Integer> mapEntry2) {
					// compare 2 to 1 to get the Highest to lowest values
					return (mapEntry2.getValue()).compareTo(mapEntry1.getValue());
				}
			});
			// create the new sorted hashmap
			HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
			// populate the new temp list from the
			for (Map.Entry<String, Integer> hashMapEntry : entitySetLinkedList) {
				sortedHashMap.put(hashMapEntry.getKey(), hashMapEntry.getValue());
			}
			return sortedHashMap;
		} else {
			// HashMap is null
			return null;
		}

	}

	// This method prints the enitre hashmap by value first
	public static void printResult(HashMap<String, Integer> hashMap) {
		if (hashMap != null) {
			// Prints every Frequency with the word
			hashMap.entrySet().forEach(entry -> {
				System.out.println(entry.getValue() + SPACE_STRING + entry.getKey());
			});
		} else {
			// ideally shouldnt ever be null but good to future proof this method.
			System.out.println(NO_DATA_FOUND);

		}
	}
}
