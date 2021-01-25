
/**
 * Write a description of MatchAllFilter here.
 * Programming Exercise: Filtering Data
 * Assignment 2: MatchAllFilter and Modification to the Interface
 * class named MatchAllFilter, can store and apply many filters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    public String getName(){
        String name = "";
        for(Filter f : filters){
            name += f.getName() + "";
        }
        return name;
    }
}
