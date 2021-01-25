
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom"; //"data/earthQuakeDataDec6sample1.atom";
        //"data/earthQuakeDataDec6sample2.atom";
        //String source="data/earthquakeDataSampleSix1.atom";
        //"data/earthquakeDataSampleSix2.atom";
        //"data/earthquakeDataSampleSix2.atom";
        //"data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //System.out.println("sorted by magnitude: ");
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);
        System.out.println("EarthQuakes in sorted order: ");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
	System.out.println("Latitude,Longitude,Magnitude,Info");
	for(QuakeEntry qe : list){
	System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
	qe.getLocation().getLatitude(),
        qe.getLocation().getLongitude(),
        qe.getMagnitude(),
        qe.getInfo());
	}
     }
    //Assignment 1: Sort by Depth
    //the index position of the QuakeEntry with the largest depth considering 
    //only those QuakeEntry’s from position from to the end of the ArrayList. 
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIndex=from;
        for(int i=from+1;i<quakeData.size();i++){
            double depth = quakeData.get(i).getDepth();
            double maxDepth = quakeData.get(maxIndex).getDepth();
            if(depth > maxDepth){
                maxIndex=i;
            }
        }
	return maxIndex;
    }
    //sorting the QuakeEntry’s in the ArrayList by depth using the selection 
    //sort algorithm, but in reverse order from largest depth to smallest 
    //depth (the QuakeEntry with the largest depth should be in 
    //the 0th position in the ArrayList). 
     public void sortByLargestDepth(ArrayList<QuakeEntry> in){
           //for(int i=0;i<in.size();i++){
           // i<70 means to 70 passes
            for(int i=0;i<70;i++){
            int maxIndex=getLargestDepth(in,i);
            QuakeEntry maxQuake = in.get(maxIndex);
            QuakeEntry currQuake = in.get(i);
            in.set(i,maxQuake);
            in.set(maxIndex,currQuake);
            //modified method
            //if(checkInSortedOrder(in)){
                //System.out.println("in checkInSortedOrder "+i);
               // System.out.println("Sorted with "+ (i+1)+ " passes");
                //break;
            //}
        }
    }
    
    //Assignment 2: Bubble Sort -
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData,int numSorted){
        for(int i=0; i<quakeData.size()-1-numSorted;i++){
            //saving current and next QuakeEntry
            QuakeEntry currQe=quakeData.get(i);
            QuakeEntry nextQe=quakeData.get(i+1);
            //swaping quake entrys if their magnitude is out of order
            if(currQe.getMagnitude() > nextQe.getMagnitude()){
                quakeData.set(i+1, currQe);
                quakeData.set(i, nextQe);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(QuakeEntry qe : in){
            System.out.println(qe);
        }
        for(int i=0; i<in.size() -1; i++){
            onePassBubbleSort(in, i);
            
            System.out.println("quakes after pass: "+i);
            for(QuakeEntry qe : in){
                System.out.println(qe);
            }
        }
    }
    //Assignment 3: Check for Completion
    /**
     * Returns true if ArrayList is sorted in order by magnitude 
     * from smallest to largest. Otherwise returns false.
    */
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        // Loop through ArrayList and check if adjacent quakes are out of order
        for (int i = 0; i < quakes.size() - 1; i++) {
            QuakeEntry currQe = quakes.get(i);
            QuakeEntry nextQe = quakes.get(i + 1);
            if (currQe.getMagnitude() > nextQe.getMagnitude()){ 
            //if (currQe.getDepth() > nextQe.getDepth()) {
                return false;
            }
        }
        return true;
    }
    //Similar to sortByMagnitudeWithBubbleSort, 
    //but stops early if the ArrayList is already sorted.
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for(int i=0; i<in.size()-1; i++){
            onePassBubbleSort(in,i);
            //stops early if the ArrayList is already sorted
            if(checkInSortedOrder(in)){
                System.out.println("sorted with "+ (i + 1) +"passes");
                break;
            }
        }
    }
    //Similar to sortByMagnitude, but stops early 
    //if the ArrayList is already sorted.
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                System.out.println("Sorted with " + (i + 1) + " passes");
                break;
            }
        }
    }
}
