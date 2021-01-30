
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author (your name) 
 * @version (01/28/2021)
 */

public interface IMarkovModel {
    public void setTraining(String text);
    
    public void setRandom(int seed);
    
    public String getRandomText(int numChars);

}
