
/**
 * Write a description of DepthFilter here.
 * Programming Exercise: Filtering Data
 * Assignment 1: Implementing Filters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{ 
    private double minDepth;
    private double maxDepth;
    private String name;
    
    public DepthFilter(double name1,double name2){
        minDepth=name1;
        maxDepth=name2;
        name = "Depth";
    }
    
    public boolean satisfies(QuakeEntry qe){
        double depth = qe.getDepth();
        if(depth>=minDepth && depth<=maxDepth){
            return true;
        }
        return false;
    }
    
    public String getName(){
        return name;
    }

} 
