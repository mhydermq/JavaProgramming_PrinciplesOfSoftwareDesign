
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
	  FileResource fr = new FileResource();
	  String st = fr.asString();
	  st = st.replace('\n', ' ');
	  MarkovZero markov = new MarkovZero();
	  markov.setTraining(st);
	  //setRandom method with the seed 42
	  markov.setRandom(88);
	  //set Random method with the seed 101
	  //markov.setRandom(101);
	  for(int k=0; k < 3; k++){
	       String text = markov.getRandomText(500);
	       printOut(text);
	  }
	}
	
    public void runMarkovOne() {
	   FileResource fr = new FileResource();
	   String st = fr.asString();
	   st = st.replace('\n', ' ');
	   MarkovOne markov = new MarkovOne();
	   markov.setTraining(st);
	   //set Random method with the seed 42
	   markov.setRandom(273);
	   //set Random method with the seed 101
	   //markov.setRandom(101);
	   for(int k=0; k < 3; k++){
		String text = markov.getRandomText(500);
		printOut(text);
	    }
	 }
	 
    public void runMarkovFour(){
           FileResource fr = new FileResource();
	   String st = fr.asString();
	   st = st.replace('\n', ' ');
	   MarkovFour markov = new MarkovFour();
	   markov.setTraining(st);
	   //the seed of the random number generator
	   markov.setRandom(371); 
	   // Printing out three random texts
	   for(int k=0; k < 3; k++){
	        String text = markov.getRandomText(500);
		printOut(text);
	     }
        
       }
    
    public void runMarkovModel(){
           FileResource fr = new FileResource();
	   String st = fr.asString();
	   st = st.replace('\n', ' ');
	   //a new MarkovModel object, initialized with a keyLength of 6
	   MarkovModel markov = new MarkovModel(8);
	   markov.setTraining(st);
	   //the seed of the random number generator
	   markov.setRandom(365); 
	   // Printing three random texts
	   for(int k=0; k < 3; k++){
		String text = markov.getRandomText(500);
		printOut(text);
	    }
        
    }
	
    private void printOut(String s){
	   String[] words = s.split("\\s+");
	   int psize = 0;
	   System.out.println("----------------------------------");
	   for(int k=0; k < words.length; k++){
		System.out.print(words[k]+ " ");
		psize += words[k].length() + 1;
		if (psize > 60) {
		    System.out.println();
		    psize = 0;
		   }
	   }
	     System.out.println("\n----------------------------------");
	}
}
