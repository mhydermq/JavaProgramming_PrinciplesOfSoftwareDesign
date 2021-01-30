
/**
 * Write a description of class MarkovRunner here.
 * Class MarkovRunner is used to run models based on markovWord and 
 * EfficientMarkovWord classes. Using this class we can select an 
 * original text for our random text generation,size of the text generated 
 * as well as we can set the seed value for the text generation for 
 * testing purposes. We can also set up how many words we want to use for 
 * the following wordrandom generation. Using more words will allow us to 
 * create a random text that will be more cohesive, less random and more 
 * accurate representation of the original text.
 * 
 * @author (your name) 
 * @version (01/28/2021)
 */

import edu.duke.*;

public class MarkovRunner {/**
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } */

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord markovWord = new MarkovWord(3);
        runModel(markovWord, st, 120,643); //844);
        
        //EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        //runModel(efficientMarkovWord, st, 120, 65);
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
    public void testHashMap() {
        //String st = "this is a test yes this is really a test";
        String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord em2 = new EfficientMarkovWord(2);
        runModel(em2, st, 50, 42);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        MarkovWord mw2 = new MarkovWord(2);
        //time just before running model
        long startTime = System.nanoTime();
        // run model
        runModel(mw2, st, 100, 42);
        //time just after running model, calculate run time
        long endTime = System.nanoTime();
        long timeElapsed = (endTime - startTime);
        System.out.println("time the run took: " + timeElapsed/1000000000.0);
        
        
        EfficientMarkovWord emw2 = new EfficientMarkovWord(2);
        //time just before running model
        startTime = System.nanoTime();
        // Run model
        runModel(emw2, st, 100, 42);
        //time just after running model, calculate run time
        endTime = System.nanoTime();
        timeElapsed = (endTime - startTime);
        System.out.println("time the run took: " + timeElapsed/1000000000.0);
        
        
    }

}
