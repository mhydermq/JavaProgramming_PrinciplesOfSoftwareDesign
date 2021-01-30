
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public String toString(){
        return "MarkovModel of order " + order;
        
    }
    
   public void setRandom(int seed){
       myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    //finds all the characters from private variable myText in MarkovOne 
    //that follow key and puts all these characters into an ArrayLIst
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        //loop through myText until no more characters are found
        int pos = 0;
        while(true){
            //find indexes of key and succeeding character
            int index = myText.indexOf(key,pos);
            int indexOfSuccessor = index+key.length();
            //break if key is not found or successor index is greater than last index
            if(index== -1 || indexOfSuccessor >= myText.length()){
                break;
            }
            //add the character to ArrayList immediately after key
            String successor = myText.substring(indexOfSuccessor,indexOfSuccessor+1);
            follows.add(successor);
            //update search position in myText
            pos = index + 1;
        }
        return follows;
    }

}
