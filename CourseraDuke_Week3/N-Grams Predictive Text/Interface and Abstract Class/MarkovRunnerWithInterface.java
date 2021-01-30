
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size,int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);          
        System.out.println("running with " + markov);
        //record time before running model
        long startTime = System.nanoTime();
        //run model three times
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
	//record time just after running model,calculate time
	long endTime = System.nanoTime();
	long timeElapsed = (endTime - startTime);
	System.out.println("finished in: " + timeElapsed);
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
		int seed = 25;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,seed);

    }
    
    public void testHashMap() {
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        int size = 50;
        int seed = 615;
        EfficientMarkovModel emFive = new EfficientMarkovModel(5);
        runModel(emFive, st, size, seed);
        
        
        //int size = 50;
        //int seed = 42;
        //String text ="yes-this-is-a-thin-pretty-pink-thistle";
        
        //EfficientMarkovModel emTwo = new EfficientMarkovModel(2);
        //emTwo.setTraining(text);
        //emTwo.setRandom(seed);
        //String st = emTwo.getRandomText(size);
        //printOut(st);
    }
    
    public void compareMethods() {
        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        EfficientMarkovModel emTwo = new EfficientMarkovModel(2);
        runModel(emTwo, st, size, seed);
        
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        
        /**FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        MarkovModel mm = new MarkovModel(2);
        runModel(mm, st, size, seed);
        
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        runModel(emm, st, size, seed);
        */
        
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
