import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";//"data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        */
       
        Filter f = new MagnitudeFilter(3.5,4.5);
        ArrayList<QuakeEntry> answer = filter(list,f);
        f = new DepthFilter(-55000.0,-20000.0);
        answer = filter(answer,f);
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
       System.out.println("quakes meet the magnitude and depth filter are: "
       + answer.size());
       
       /*
       Location Denver = new Location(39.7392, -104.9903); //(35.42, 139.43);
       float max = 1000000;
       Filter f = new DistanceFilter(Denver,max);
       ArrayList<QuakeEntry> answer = filter(list,f);
       System.out.println("found "+answer.size());
       f = new PhraseFilter("end","a");
       answer = filter(answer,f);
       for(QuakeEntry qe : answer){
           System.out.println(qe);
        }
        System.out.println("earthquakes meet the distance and phrase filter are: "
                                  +answer.size()+" earthquakes"); */
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    public void testMatchAllFilter(){
        String source = "data/nov20quakedata.atom";  //"data/nov20quakedatasmall.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        /*
        for(QuakeEntry qe : list){
            System.out.println(qe); 
        }
        */
        System.out.println("total number of earthquakes read: " 
                            +list.size()+"quakes");                  
        MatchAllFilter maf = new MatchAllFilter();
        //filter for magnitude
        Filter f = new MagnitudeFilter(1.0,4.0);
        maf.addFilter(f);
        //filter for depth
        f = new DepthFilter(-180000.0,-30000.0);
        maf.addFilter(f);
        //filter for phrase
        f = new PhraseFilter("any","o");
        maf.addFilter(f);
        ArrayList<QuakeEntry> result = filter(list,maf);
        for(QuakeEntry qe : result){
            System.out.println(qe);
        }
        System.out.println("Found using 3 filter: " + result.size() + " earthquakes");
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void testMatchAllFilter2(){
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("number of quakes: "+list.size()+" earthquakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        //filter for magnitude
        Filter f = new MagnitudeFilter(0.0,5.0);
        maf.addFilter(f);
        //filter for distance
        //the distance from Tulsa, Oklahoma at location (36.1314, -95.9372) 
        //is less than 10000000 meters
        Location Billund = new Location(55.7308, 9.1153);
        f = new DistanceFilter(Billund,3000000);
        maf.addFilter(f);
        //filter fo phrase
        f = new PhraseFilter("any","e");
        maf.addFilter(f);
        
        ArrayList<QuakeEntry> answer = filter(list,maf);
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size() + " earthquakes ");
        System.out.println("Filters used are: " + maf.getName());
    }
}
