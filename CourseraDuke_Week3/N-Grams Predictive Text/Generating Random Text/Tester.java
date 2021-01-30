
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        //ArrayList<String> follows = markov.getFollows("t");
        //ArrayList<String> follows = markov.getFollows("e");
        ArrayList<String> follows = markov.getFollows("es");
        //ArrayList<String> follows = markov.getFollows(".");
        //ArrayList<String> follows = markov.getFollows("t.");
        System.out.println("an ArrayList " + follows+ " of size "+ follows.size());
    }
    
    public void testGetFollowsWithFile(){
        MarkovOne markov = new MarkovOne();
        //set the training text to a user selected file
        FileResource fr = new FileResource();
        String frStr = fr.asString().replace('\n',' ');
        markov.setTraining(frStr);
        //calling get follows
        ArrayList<String> follows = markov.getFollows("th");
        System.out.println(follows.size());
    }
}
