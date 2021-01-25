
/**
 * Write a description of PhraseFilter here.
 * Programming Exercise: Filtering Data
 * Assignment 1: Implementing Filters
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String name;
    
    public PhraseFilter(String s1,String s2){
        where = s1;
        phrase = s2;
        name = "Phrase";
    }

    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if(where.equals("start") && title.startsWith(phrase)){
            return true;
        }
        else if(where.equals("end") && title.endsWith(phrase)){
            return true;
        }
        else if(where.equals("any") && title.contains(phrase)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getName(){
        return name;
    }
}
