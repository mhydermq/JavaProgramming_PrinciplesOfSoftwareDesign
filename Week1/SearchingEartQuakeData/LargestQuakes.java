
/**
 * Write a description of LargestQuakes here.
 * Assignment 5: Finding the Largest Magnitude Earthquakes
 * A class and methods to determine the N biggest earthquakes, 
 * those with largest magnitude.
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
   public void findLargestQuakes(){
       EarthQuakeParser parser = new EarthQuakeParser();
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list = parser.read(source);
       System.out.println("Read " + list.size() + " quakes");
       /*
        // Print all the earthquakes
        for (QuakeEntry qe : list) {
            System.out.println(qe); 
        }
       */
       /*
       //the index location of the largest magnitude earthquakes
       int index = indexOfLargest(list);
       System.out.println("index: " + index + ", mag: " 
       + list.get(index).getMagnitude());
       */
        
       
       //twenty earthquakes of largest magnitude
       ArrayList<QuakeEntry> largest = getLargest(list,20);
       for (QuakeEntry quake : largest) {
            System.out.println(quake);
       }
   } 
   
   public int indexOfLargest(ArrayList<QuakeEntry> data){
       double magMax = 0;
       int index = -1;
       // For each quake in data
        for (QuakeEntry qe : data) {
            //quake's magnitude
            double mag = qe.getMagnitude();
            // If mag is larger than magMax, update
            if (mag > magMax) {
                magMax = mag;
                index = data.indexOf(qe);
            }
       }
       return index;
    }
    
   public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
      ArrayList<QuakeEntry> copy = quakeData; 
      ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
      // Until either largest contains howMany elements or data runs out
      while (largest.size() < howMany || copy.size() == 0) {
            int index = indexOfLargest(copy);
            largest.add(copy.get(index));
            copy.remove(index);
      }
       return largest;
       }
    }
    
       

