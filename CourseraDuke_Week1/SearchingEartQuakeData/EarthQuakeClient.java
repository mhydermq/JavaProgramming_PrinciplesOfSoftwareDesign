import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    /*public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    */
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getLocation().distanceTo(from)< distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source ="data/nov20quakedata.atom";  //"data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for(QuakeEntry qe:list){
            if(qe.getMagnitude() > 5.0){
                System.out.println(qe);
            }
        }
        */
       ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
       for(QuakeEntry qe : listBig){
           System.out.println(qe);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list,1000*1000,city);
        for(int k=0;k<close.size();k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000+""+entry.getInfo());
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                               double minDepth,double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double qeDepth = qe.getDepth();
            if(qeDepth > minDepth && qeDepth < maxDepth){
                answer.add(qe);
            }
        }        
        return answer;                                   
    }
        
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> quakes = filterByDepth(list,minDepth,maxDepth);
        System.out.println("read data for "+ list.size() + "quakes");
        for(QuakeEntry quake : quakes){
            System.out.println(quake);
        }
        System.out.println("no. of quakes with depth between -12000.0 and -10000.0 is "+quakes.size());
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where,String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if(where.equals("start") && title.startsWith(phrase) ){
                answer.add(qe);
            }
            else if(where.equals("end") && title.endsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equals("any") && title.contains(phrase)){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //String where = "end";
        //String phrase = "Alaska";
        String where = "any";
        String phrase = "Can";
        //String where = "start";
        //String phrase = "Quarry Blast";
        ArrayList<QuakeEntry> quakes = filterByPhrase(list,where,phrase);
        System.out.println("read data for "+list.size() + " quakes");
        for(QuakeEntry quake : quakes){
            System.out.println(quake);
        }
        System.out.println("Found " + quakes.size() + " that match \"" 
                           + phrase + "\" at " + where);
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
  
}
