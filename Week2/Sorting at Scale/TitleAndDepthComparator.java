
/**
 * Write a description of TitleAndDepthComparator here.
 * Programming Exercise: Sorting at Scale
 * Week 2
 * Assignment 2: Title Comparator 
 * a Comparator to sort earthquakes by title first and break ties by depth. 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1,QuakeEntry q2){
        if(q1.getInfo().compareTo(q2.getInfo()) < 0){
            return -1;
        } else if(q1.getInfo().compareTo(q2.getInfo()) > 0){
            return 1;
        } else{
            return Double.compare(q1.getDepth(),q2.getDepth());
        }
    }
    
}
