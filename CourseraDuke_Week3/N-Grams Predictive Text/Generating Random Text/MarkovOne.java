
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
	
    public MarkovOne() {
	myRandom = new Random();
     }
	
    public void setRandom(int seed){
	myRandom = new Random(seed);
     }
	
    public void setTraining(String s){
	myText = s.trim();
 	}
	
    public String getRandomText(int numChars){
	if (myText == null){
		return "";
		}
	StringBuilder sb = new StringBuilder();
	// Generate a random index from valid indexes, which are all those indexes that have 
	// following characters. The last index has no following character, therefore
	// myText.length() - 1 is used
	int index = myRandom.nextInt(myText.length() - 1);
	// Assign to key the one-character string at the random index
	String key = myText.substring(index, index + 1);
	sb.append(key);
	// NOTE: Generate numChars minus one cuz that one is set before the loop
	for(int k=0; k < numChars; k++){
	      //int index = myRandom.nextInt(myText.length());
	      //sb.append(myText.charAt(index));
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
	      // Update key to successor 
	      key = successor;
	 } 	
	 return sb.toString();
	}
	
    public ArrayList<String> getFollows(String key){
         ArrayList<String> follows = new ArrayList<String>();
         //looping throug myText until no more characters are found
          int pos = 0;
         while(true){
             //indices of key and succeeding character
             int index = myText.indexOf(key,pos);
             int indexOfSuccessor = index+key.length();
             //if key is not found or if successor index is greater than last index
             if(index == -1 || indexOfSuccessor >= myText.length()){
               break;
              }
             //add the character to ArrayList immediately after key
             String successor = myText.substring(indexOfSuccessor,indexOfSuccessor+1);
             follows.add(successor);
             //updating search positions in myText
             pos=index+1;   
          }
          return follows;
         }

     }
