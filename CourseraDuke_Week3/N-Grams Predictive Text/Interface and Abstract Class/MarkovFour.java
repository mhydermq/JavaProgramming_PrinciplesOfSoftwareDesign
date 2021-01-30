
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
//public class MarkovFour implements IMarkovModel {
    //private String myText;
	//private Random myRandom;
public class MarkovFour extends AbstractMarkovModel{	
    public MarkovFour() {
	myRandom = new Random();
      }
	
    public void setRandom(int seed){
	myRandom = new Random(seed);
      }
	
    public void setTraining(String s){
	myText = s.trim();
	}
	
    /**
      * Predicts the next character by finding all the characters 
      * that follow a substring of 4 characters in the training text, 
      * and then randomly picking one of them as the next character.
    */
     public String getRandomText(int numChars){
     if (myText == null) {
	    return "";
	}
     StringBuilder sb = new StringBuilder();
     // Generate a random index from valid indexes, which are all those 
     //indexes that have following characters. The fourth to last index 
     //has no following character due to the key being 4  characters
     //in length, therefore myText.length() - 4 is used
     int index = myRandom.nextInt(myText.length() - 4);
     // Assign to key the one-character string at the random index
     String key = myText.substring(index, index + 4);
     sb.append(key);
     // NOTE: Generate numChars minus one cuz that one is 
     //set before the loop
     for(int k=0; k < numChars - 4; k++){
           // Find all characters that follow the current character
           ArrayList<String> follows = getFollows(key);
	   // Break if no characters were found
           if (follows.size() == 0) {
		   break;
	    }
           // Randomly pick one of them as the successor
           index = myRandom.nextInt(follows.size());
	   String successor = follows.get(index);
	   sb.append(successor);
	   //Update key to successor 
	   key = key.substring(key.length()-3)+successor;
	 }
	 return sb.toString();
	}
	
	/**
	 * Finds all the characters from the private variable myText 
	 * in MarkovOne that 
	 * follow key and puts all these characters into an ArrayList.
	 * @returns the ArrayList
	 *//**
    public ArrayList<String> getFollows(String key) {
	  ArrayList<String> follows = new ArrayList<String>();
	  // Loop through myText until no more characters are found
	  int pos = 0;
	  while (true) {
	       // Find indexes of key and succeeding character
	       int index = myText.indexOf(key, pos);
	       int indexOfSuccessor = index + key.length();
	       // Break if key isn't found or if successor index is greater than last index
	       if (index == -1 || indexOfSuccessor >= myText.length()) {
	            break;
	        }
	       // Add to ArrayList the character immediately after key
	       String successor = myText.substring(indexOfSuccessor, indexOfSuccessor + 1);
	       follows.add(successor);
	       // Update search position in myText
	       pos = index + 1;
	    }
	    return follows;
	}
*/

}
