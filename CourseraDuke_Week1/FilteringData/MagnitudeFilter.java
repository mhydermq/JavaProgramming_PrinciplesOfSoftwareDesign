
/**
 * Write a description of MagnitudeFilter here.
 * Programming Exercise: Filtering Data
 * Assignment 1: Implementing Filters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    private String name;
    
    public MagnitudeFilter(double num1,double num2){
        minMag = num1;
        maxMag = num2;
        name = "Magnitude";
        
    }
    
    public boolean satisfies(QuakeEntry qe){
        double mag = qe.getMagnitude();
        if(mag>= minMag && mag<= maxMag){
            return true;
        }
        return false;
    }
    public String getName(){
        return name;
    }
}
