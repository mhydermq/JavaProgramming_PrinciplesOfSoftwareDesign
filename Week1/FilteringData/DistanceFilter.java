
/**
 * Write a description of DistanceFilter here.
 * Programming Exercise: Filtering Data
 * Assignment 1: Implementing Filters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location givenLoc;
    private float maxDist;
    private String name;
    
    public DistanceFilter(Location loc,float max){
        givenLoc = loc;
        maxDist = max;
        name ="Distance";
    }
    
    public boolean satisfies(QuakeEntry qe){
        Location quakeLoc = qe.getLocation();
        float dist = givenLoc.distanceTo(quakeLoc);
        if(dist < maxDist){
            return true;
        }
        return false;
    }
    
    public String getName(){
        return name;
    }
}
