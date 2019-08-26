package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Matthew Baker
 * 
 * This is a class to extract the place name and location data from the Listed Building dbf files. 
 * The data for Listed Buildings of England can be downloaded from 
 * https://historicengland.org.uk/listing/the-list/data-downloads/
 * 
 * The data for Listed Buildings of Scotland can be downloaded from
 * https://portal.historicenvironment.scot/spatialdownloads. I manually modified the CSV files to 
 * just include the data I needed as they included a lot more details than the data supplied
 * by Historic England
 * 
 * The dbf file first needs to be converted to a csv to be read by this class
 * 
 * 
 *
 */
public class ListedBuildingsReader {
	
	public String csvFile;
	public final String TYPE = "LB";
	public static int englishCount = 0;
	public static int scottishCount = 0;
	public static int welshCount = 0;
	
	public ListedBuildingsReader() {
		
		
		
	}
	

	/**This method creates the list of ListedBuildings from the English Listed Buildings CSV File data
	 * 
	 *  
	 * @return It returns an array list of type OrdnanceSurveyMonument
	 */
	public ArrayList<OrdnanceSurveyObject> getEnglishBuildingList(String csvFile){
		
		this.csvFile = csvFile;
	
		
		//pattern to check whether string contains a number
		Pattern numberPattern = Pattern.compile("^[0-9]+$");
		
		ArrayList<OrdnanceSurveyObject> buildingList = new ArrayList<>();
		
		Pattern ptn = Pattern.compile(", ");
		
		String splitter = ",";
		
		//holds a line of the CSV file 
		String csvLine;
		//holds the data parsed from each CSV line
		String[] csvCollection;
		
		String[] removeInternalCommaCollection;
		
		String newCsvLine = "";
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
		
			
			while ((csvLine = br.readLine())!=null) {
				
				newCsvLine = "";
				
				removeInternalCommaCollection = ptn.split(csvLine);
				
				for (int i = 0; i < removeInternalCommaCollection.length; i++) {
					
					if (i!=removeInternalCommaCollection.length-1) {
					
						newCsvLine += removeInternalCommaCollection[i]+"; ";
					
					}
					
					else {
					
					newCsvLine += removeInternalCommaCollection[i];
					
					}
					
				}
				
				//System.out.println(newCsvLine.toString());
				
				csvCollection = newCsvLine.split(splitter);
				
				if (csvCollection.length >= 10) {
				
					
					if (csvCollection.length == 11) {
					
					String id = csvCollection[0];
				
					//for building info
					String name = csvCollection[1];
					
					//for building grade
					String grade = csvCollection[3];
				
					//for national grid coordinates
					String ngCo = csvCollection[7];
				
					double easting = Double.parseDouble(csvCollection[9]);
				
					double northing = Double.parseDouble(csvCollection[10]);
					
					if (grade.equals("I")|| grade.equals("II*")) {
					
					OrdnanceSurveyObject building = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
					
					
					buildingList.add(building);
					
					englishCount++;
					
					}
				
					
				}
					
					/*
					
					if (csvCollection.length == 12) {
						
						String id = csvCollection[0];
					
						//for monument info
						String name = csvCollection[1]+";"+csvCollection[2];
						
						//for building grade
						String grade = csvCollection[4];
						
					
						//for national grid coordinates
						String ngCo = csvCollection[8];
					
						double easting = Double.parseDouble(csvCollection[10]);
					
						double northing = Double.parseDouble(csvCollection[11]);
						
					
						if (grade.equals("I") || grade.equals("II*")) {
						
						OrdnanceSurveyObject building = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						buildingList.add(building);
						
						}
						englishCount++;
						
						}
					
					if (csvCollection.length == 13) {
						
						String id = csvCollection[0];
					
						//for monument info
						String name = csvCollection[1]+";"+csvCollection[2]+";"+csvCollection[3];
					
						//for building grade
						String grade = csvCollection[5];
						
						//for national grid coordinates
						String ngCo = csvCollection[9];
					
						double easting = Double.parseDouble(csvCollection[11]);
					
						double northing = Double.parseDouble(csvCollection[12]);
						
						if (grade.equals("I")|| grade.equals("II*")) {
						
						
						OrdnanceSurveyObject building = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						buildingList.add(building);
						
						}
						englishCount++;
						
						}
					
					if (csvCollection.length == 14) {
						
						String id = csvCollection[0];
					
						//for monument info
						String name = csvCollection[1]+";"+csvCollection[2]+";"+csvCollection[3]+";"+csvCollection[4];
					
						//for building grade
						String grade = csvCollection[6];
						
						//for national grid coordinates
						String ngCo = csvCollection[10];
					
						double easting = Double.parseDouble(csvCollection[12]);
					
						double northing = Double.parseDouble(csvCollection[13]);
						
						if (grade.equals("I")|| grade.equals("II*")) {
						
						OrdnanceSurveyObject building = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						buildingList.add(building);
						
						}
						englishCount++;
						
						}
				
				*/
					}
				
				}
				
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Found "+englishCount+" Grade I or II* buildings in England");
		return buildingList;
		
	}
	

	
	/**This method creates the list of Listed Buildings from the modified Scottish Listed Buildings CSV File data
	 * 
	 *  
	 * @return It returns an array list of type OrdnanceSurveyMonument
	 */	
	
public ArrayList<OrdnanceSurveyObject> createScottishBuildingList(String csvFile){
		
		this.csvFile = csvFile;
		
		//pattern to check whether string contains a number
		Pattern numberPattern = Pattern.compile("[0-9]");
		
		ArrayList<OrdnanceSurveyObject> buildingList = new ArrayList<>();
		
		//to split the CSV file
		Pattern ptn = Pattern.compile(", ");
		
		String splitter = ",";
		
		//holds a line of the CSV file 
		String csvLine;
		//holds the data parsed from each CSV line
		String[] csvCollection;
		
		String[] removeInternalCommaCollection;
		
		String newCsvLine = "";
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
			while ((csvLine = br.readLine())!=null) {
				
				newCsvLine = "";
				
				removeInternalCommaCollection = ptn.split(csvLine);
				
				for (int i = 0; i < removeInternalCommaCollection.length; i++) {
					
					if (i!=removeInternalCommaCollection.length-1) {
					
						newCsvLine += removeInternalCommaCollection[i]+"; ";
					
					}
					
					else {
					
					newCsvLine += removeInternalCommaCollection[i];}
					
					
				}
				
				csvCollection = newCsvLine.split(splitter);
				
				//check to remove headers	
				/*String test = ""+csvCollection[0];
				
				Matcher digitMatcher = numberPattern.matcher(test);
				
				if (digitMatcher.matches()) {*/
					
					
					if (csvCollection.length == 5) {
					
					String id = csvCollection[0];
				
					//for monument info
					String name = csvCollection[1];
				
					String ngCo = null;
					
					double easting = Double.parseDouble(csvCollection[2]);
				
					double northing = Double.parseDouble(csvCollection[3]);
					
					String grade = csvCollection[4];
					
					grade = grade.trim();
					
					
					if (grade.equals("A")) {
					
					
					OrdnanceSurveyObject building = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
					
					buildingList.add(building);
					scottishCount++;
					
					}
					
					
					}
					
					
					}
				
				/*}*/
		
				
			
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Found "+scottishCount+" Grade A Listed Buildings in Scotland");
		return buildingList;
		
	}


public ArrayList<OrdnanceSurveyObject> createWelshBuildingList(String csvFile){
	
	this.csvFile = csvFile;
	
	ArrayList<OrdnanceSurveyObject> buildingList = new ArrayList<>();
	
	//to split the CSV file
	String splitter = ",";
	
	//holds a line of the CSV file 
	String csvLine;
	//holds the data parsed from each CSV line
	String[] csvCollection;
	
	try {
		
		BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
		
		while ((csvLine = br.readLine())!=null) {
			
			csvCollection = csvLine.split(splitter);
				
				
				if (csvCollection.length == 6) {
					
					
				String id = csvCollection[0];
					
				if (id.length()>47) {
					
					id = id.substring(48);
					
				}	
				
				String type = TYPE;
			
				//for monument info
				String name = csvCollection[2];
				
				name = FormatTools.sentenceToProperCase(name);
				
				String grade = csvCollection[3];
			
				String ngCo = null;
				
				Object object = "hello";
				
				
				
				double northing = Double.parseDouble(csvCollection[4]);
				
				double easting = Double.parseDouble(csvCollection[5]);
			
				if (grade.equals("I") || grade.equals("II*")) {
				
				
				OrdnanceSurveyObject building = new OrdnanceSurveyObject(id, type, name,ngCo, easting, northing);
				
				buildingList.add(building);
				
				}
				welshCount++;
				
				}
				
				
				}
		
		
		br.close();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	System.out.println("Found "+welshCount+" Grade I or Grade II* Listed Buildings in Wales");
	return buildingList;
	
}



}
