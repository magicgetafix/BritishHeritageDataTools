package main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Matthew Baker
 *
 * This class provides formatting tools for the British Heritage Data
 *
 *
 */

public class FormatTools {


    /**This method converts the words in the description to ProperCase unless
     * they are 2 characters in length or less, or the word "and"
     * 
     * It also converts any references to compass directions to the 
     * full word, such as "N" to "North", "SE" to "South" etc.
     *
     * @param string The String which needs changing to ProperCase
     * @return
     */
    public static String sentenceToProperCase(String string){

        String[] wordArray = string.split("\\s");

        String newString = "";

        for (int i = 0; i<wordArray.length; i++){
            wordArray[i] = wordToProperCase(wordArray[i]);

            if (i==0){

                newString = newString+wordArray[i];

            }

            else{
            newString= newString+" "+wordArray[i];

            }
        }

   

        if (newString.length()>1){

            String firstLetter = newString.substring(0,1);
            firstLetter = firstLetter.toUpperCase();

            newString = firstLetter+newString.substring(1);
        }


        return newString;
    }

    /**This method converts any words of more than two characters
     * except the word "and" to proper case. It also replaces compass directions like
     * "N" with the full word.
     *
     * @param string the word you want converting to proper case
     * @return The String converted to proper case
     */

    public static String wordToProperCase(String string){
    	
    	Pattern speechPattern = Pattern.compile("\"");
    	
    	
    	if (string.length()>1) {
    	
    	String letter1 = string.substring(0,1);
    	
    	String lastLetter = string.substring(string.length()-1);
    	
    	Matcher firstLetterMatcher = speechPattern.matcher(letter1);
    	Matcher lastLetterMatcher = speechPattern.matcher(lastLetter);
    	
    	
    	if (firstLetterMatcher.matches()) {
    		
    		string = string.substring(1);
    	}
    	
    	if (lastLetterMatcher.matches()) {
    		
    		if (string.length()>1) {
    		
    		string = string.substring(0, string.length()-1);
    		}
    	}
    	
    	}
    	
    	if (string.length()==1) {
    		
    		
    		if (string.equals("N")) {
    			
    			string = "North";
    			
    		}
    		
    		else if (string.equals("E")) {
    			
    			string = "East";
    		}
    		
    		else if (string.equals("S")) {
    			
    			string = "South";
    		}
    		
    		else if (string.equals("W")) {
    			
    			string = "West";
    		}
    		
    		
    		
    	}


        if (string.length()==2){
        
        	if (string.equalsIgnoreCase("ST")) {
        		
        		return "St";
        	}
        	
            String secondLetter = string.substring(1);
            

            if (secondLetter.equals(".")){

                String newString = string.toUpperCase();
                return newString;
            }
            
            if (string.equals("NE")) {
    			
    			string = "North-East";
    			
    		}
    		
    		else if (string.equals("SE")) {
    			
    			string = "South-East";
    		}
    		
    		else if (string.equals("NW")) {
    			
    			string = "North-West";
    		}
    		
    		else if (string.equals("SW")) {
    			
    			string = "South-West";
    		}
            
            
        }
        
        if (string.length() ==3) {
        	
        	if (string.equals("ENE")|| string.equals("ESE")) {
        		
        		string = "East";
        	}
        	
        	else if (string.equals("NNW")||string.equals("NNE")) {
        		
        		string = "North";
        	}
        	
        	else if (string.equals("SSW")||string.equals("SSE")) {
        		
        		string = "South";
        	}
        	
        	else if (string.equals("WNW")||string.equals("WSW")) {
        		
        		string = "West";
        	}
        	
        	
        	
        }

        if (string.length()>2 && !string.equals("and")) {
        	
        	String bracketCheck = string.substring(0,1);
        	
        	if (!bracketCheck.equals("(")) {
        	
            string = string.toLowerCase();
            String firstLetter = string.substring(0, 1);
            String end = string.substring(1);
            firstLetter = firstLetter.toUpperCase();
            String newString = firstLetter + end;
            return newString;
            
        	}
        	
        	else {
        		
        		string = string.toLowerCase();
                String firstLetter = string.substring(1, 2);
                String end = string.substring(2);
                firstLetter = firstLetter.toUpperCase();
                String newString = "("+firstLetter + end;
                return newString;
                
        	}
        }

        else{return string.toLowerCase();}
    }

    /**This method trims a string to the specified limit of
     * characters. It trims the word to three characters
     * below the limit and adds three dots "..."
     *
     *
     * @param string The string you want to trim
     * @param limit The upper character limit of the word
     * @return
     */
    public static String trimString(String string, int limit){


        limit = limit-3;
        int len = string.length();

        if (len>limit) {

            string = string.substring(0, limit) + "...";
            return string;
        }

        else{return string;}

    }


}
